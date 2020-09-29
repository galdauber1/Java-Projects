/**
 * A superclass for implementations of hash-sets implementing the SimpleSet interface.
 * **/
public abstract class SimpleHashSet extends java.lang.Object
 implements SimpleSet{
    protected  static  float DEFAULT_HIGHER_CAPACITY=0.75f; //Describes the higer load factor of a
    // newly created hash set
    protected  static  float DEFAULT_LOWER_CAPACITY=0.25f; //Describes the lower load factor
    // of a newly created hash set
    protected static  int INITIAL_CAPACITY=16;//Describes the capacity of a newly created hash set
    protected float higherCapacity;
    protected float lowerCapacity;
    /**
     * Constructs a new hash set with the default capacities given in DEFAULT_LOWER_CAPACITY and
     * DEFAULT_HIGHER_CAPACITY
     * **/
    SimpleHashSet(){
        this.higherCapacity=DEFAULT_HIGHER_CAPACITY;
        this.lowerCapacity=DEFAULT_LOWER_CAPACITY;
    }
    /**
     * Constructs a new hash set with capacity INITIAL_CAPACITY
     * **/
    protected SimpleHashSet(float upperLoadFactor, float lowerLoadFactor){
        this.lowerCapacity=lowerLoadFactor;
        this.higherCapacity=upperLoadFactor;
    }
    /**
     * The current capacity (number of cells) of the table.
     * **/
    public abstract int capacity();
    /**
     * Clamps hashing indices to fit within the current table capacity
     * **/
    protected  abstract int clamp(int index);

    /**
     * The lower load factor of the table.
     * **/
    protected float getLowerLoadFactor(){
        return lowerCapacity;

    }
    /**
     * The higher load factor of the table.
     * **/
    protected float getUpperLoadFactor(){
        return higherCapacity;
    }







}
