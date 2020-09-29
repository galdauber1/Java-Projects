package filesprocessing;

import java.io.File;
import java.util.function.Predicate;
/**
 * sub class of filter,that filtering by Size(greater_than,between,smaller_than)
 * **/
public class FilterBySize extends Filter {
   protected Double value;
    protected Double value2;
    protected String name;
   protected Boolean neg;
   protected Predicate<File>filter;
   protected final String BETWEEN="between";
    protected final String GREATER_THEN="greater_than";
    protected final String SMALLER_THEN="smaller_than";
    protected final String ALL="all";
    /**
    * constructor for between
    * **/
    public FilterBySize(Double value,Double value2,String name,Boolean neg){
        this.name=name;
        this.value=value;
        this.neg=neg;
        this.value2=value2;
        if ((name.equals(BETWEEN))){
            filter = file -> ((double)file.length()/1024) >= value&&((double)file.length()/1024)<=value2;

        }
   }
   /**
    * regular constructor
    * **/
    public FilterBySize(Double value,String name,Boolean neg)
    {
        this.name=name;
        this.value=value;
        this.neg=neg;
        if (name.equals(GREATER_THEN)) {
            filter = file -> ((double)file.length()/1024) > value;
        }
        else if ((name.equals(SMALLER_THEN))){
        filter = file ->((double)file.length()/1024) < value;

    }
        else if ((name.equals(ALL))){
            filter = File::isFile;
        }
    }
    /**
     * return the predicate
     * **/
    @Override
    public Predicate<File> getFilter() {
        if(neg)
            return filter.negate();
        return filter;
    }
}




