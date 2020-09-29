package filesprocessing;

import java.io.File;
import java.util.Comparator;
/**
 * ORDER abstract class
 * **/
public abstract class  Order implements Comparator<File> {
    protected Comparator<File> order;
    public abstract Comparator<File> getOrder() ;

}

