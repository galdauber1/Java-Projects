public class CollectionFacadeSet extends java.lang.Object implements SimpleSet
/**
 * Wraps an underlying Collection and serves to both simplify its API and give it a common
 * type with the implemented SimpleHashSets.
 * **/
{
    protected java.util.Collection<java.lang.String> collection;
    /**
     * Creates a new facade wrapping the specified collection.
     * **/
    CollectionFacadeSet(java.util.Collection<java.lang.String> collection){
        this.collection=collection;
    }
    /**
     * Add a specified element to the collection if it's not already in it.
     * **/
    public boolean add(java.lang.String newValue){
        if (!collection.contains(newValue)) {
            return collection.add(newValue);
        }
        return false;
    }
    /***
     * Look for a specified value in the collection.
     * */
    public boolean contains(java.lang.String searchVal){
        return collection.contains(searchVal);
    }
    /**
     * Remove the input element from the collection.
     * **/
    public boolean delete(java.lang.String toDelete){
        return collection.remove(toDelete);
    }
    /**
     * The number of elements currently in the collection.
     * **/
    public int size(){
        return collection.size();
    }






}
