package filesprocessing;

import java.io.File;
import java.util.function.Predicate;
/**
 *filter abstract class
 *  **/
public  abstract  class  Filter {
    protected Predicate<File>filter;
    public   abstract Predicate<File> getFilter();

}
