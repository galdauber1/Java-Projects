import java.util.LinkedList;
/**
 * a hash-set based on chaining
 * **/
public class OpenHashSet extends SimpleHashSet {
    protected LinkedListWrapper[]table;
    protected int size=0;
    /**
     * this function creates the wrapper array by capacity.
     * **/
    private  void createArray(int capacity){
        table= new LinkedListWrapper[capacity];
        for (int i =0;i<capacity;i++){
            table[i]=new LinkedListWrapper();
        }

    }
    /**
     * A default constructor.
     * **/
    public OpenHashSet(){
        super();
        createArray(INITIAL_CAPACITY);
    }
    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
     * **/
   public OpenHashSet(float upperLoadFactor, float lowerLoadFactor){
        super(upperLoadFactor,lowerLoadFactor);
        createArray(INITIAL_CAPACITY);

    }
    /**
     * Data constructor - builds the hash set by adding the elements one by one.
     * **/
    public OpenHashSet(java.lang.String[] data){
        super();
        createArray(INITIAL_CAPACITY);
        for (String str:data){
            add(str);
        }
    }
    /**
     * Add a specified element to the set if it's not already in it.
     * **/
    public boolean add(java.lang.String newValue){
        if (contains(newValue)) {
            return false;
        }
        if ((float)(size+1)/capacity() > getUpperLoadFactor()){
            resize(capacity()*2);

        }
        int hashcode=clamp(newValue.hashCode());
        table[hashcode].list.add(newValue);
        size++;
        return true;
    }
    /**
     * Look for a specified value in the set.
     * **/
    public boolean contains(java.lang.String searchVal){
        int hashcode=clamp(searchVal.hashCode());
            return table[hashcode].list.contains(searchVal);
    }
    /**
     *Remove the input element from the set.
     * **/
    public boolean delete(java.lang.String toDelete) {
        System.out.println(toDelete);
        if (!contains(toDelete)) {
            return false;
        }
        if ((float) (size-1) / capacity() < getLowerLoadFactor()) {
            resize(capacity()/2);
        }
        int hashcode=clamp(toDelete.hashCode());
        table[hashcode].list.remove(toDelete);
        size+=-1;
        return true;
    }
    /**
     * The number of elements currently in the set
     * **/
    public int size(){
        return size;
    }
    /**
     * The current capacity (number of cells) of the table.
     * **/
    public int capacity(){
        return this.table.length;
    }
    /**
     * Clamps hashing indices to fit within the current table capacity
     * **/
    @Override
    protected int clamp(int index) {
        return index&(capacity()-1);
    }
    /**
     *  this function resize the table if needed
     * **/
    private void resize(int cap) {
        LinkedListWrapper[]cloneTable=table.clone();
        createArray(cap);
        for (LinkedListWrapper linkedwrapper:cloneTable){
            LinkedList<String> linkedlist=linkedwrapper.list;
            for (String str:linkedlist){
                int hashcode=clamp(str.hashCode());
                table[hashcode].list.add(str);
            }
        }
    }
}

