/**
 * a hash-set based on closed-hashing with quadratic probing. Extends
 * SimpleHashSet
 * **/
public class ClosedHashSet extends  SimpleHashSet {
    protected String[]table;
    protected int size=0;
    protected boolean[]flags=new boolean[INITIAL_CAPACITY];
    /**
     * A default constructor.
     * **/
     public ClosedHashSet(){
        table=new String[INITIAL_CAPACITY];
    }
    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
     * **/
    public ClosedHashSet(float upperLoadFactor, float lowerLoadFactor){
        super(upperLoadFactor,lowerLoadFactor);
        table=new String[INITIAL_CAPACITY];
    }
    /**
     * Data constructor - builds the hash set by adding the elements one by one.
     * **/
    public ClosedHashSet(java.lang.String[] data){
        table=new String[INITIAL_CAPACITY];
        for (String str:data){
            add(str);}
    }
    /**
     * Add a specified element to the set if it's not already in it.
     * **/
    public boolean add(java.lang.String newValue){
        if(contains(newValue)){
            return false;
        }
        if ((float)(size+1)/capacity()>getUpperLoadFactor()){
            resize(capacity()*2);
        }
        int hashcode=clamp(newValue.hashCode());
        table[hashcode]=newValue;
        size++;
        return true;
    }
    /**
     * helper function for the contain func
     * **/
    public int findIndex(String searchVal ){
        for (int i=0;i<capacity();i++){
            int hash=(searchVal.hashCode()+(i+(i*i))/2)&(capacity()-1);
            if (table[hash]==null&& !flags[hash]){
                return -1;
            }
            if (table[hash]!=null&&table[hash].equals(searchVal)){
                return hash;
            }
        }
        return -1;
    }
    /**
     * Look for a specified value in the set.
     * **/
    public boolean contains(java.lang.String searchVal){
        int index=findIndex(searchVal);
        if (table==null){
            return false;
        }
        else return index != -1;
    }
    /**Remove the input element from the set**/
        public boolean delete(java.lang.String toDelete){
            if (!contains(toDelete)) {
                return false;
            }
            if ((float) (size - 1) / capacity() < getLowerLoadFactor()) {
                resize(capacity()/2);
            }
            int hashcode=findIndex(toDelete);
            table[hashcode]=null;
            flags[hashcode]=true;
            size+=-1;
            return true;
        }
        /**The number of elements currently in the set**/
         public int size(){
        return size;

    }
    /**
     * capacity in class SimpleHashSet
     * **/
    public int capacity(){
        return this.table.length;

    }
    /**Clamps hashing indices to fit within the current table capacity**/
    @Override
    protected int clamp(int index) {
        for (int i=0;i<capacity();i++){
            int hash=(index+(i+(i*i))/2)& (capacity()-1);
            if (table[hash]==null){
                return hash;
            }
        }
        return -1;
    }
    /**this function resize the table if needed**/
    private void resize(int size){
        String[]cloneTable=table.clone();
        table=new String[size];
        flags=new boolean[size];
        for (String str:cloneTable){
            if(str != null ) {
                int hashcode=clamp(str.hashCode());
                table[hashcode] = (str);
            }
        }

    }
}
