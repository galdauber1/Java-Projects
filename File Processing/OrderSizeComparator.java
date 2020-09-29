package filesprocessing;

import java.io.File;
import java.util.Comparator;
/**
 * suB class that ordering by size
 * **/
public class OrderSizeComparator extends Order implements Comparator<File> {
    protected Comparator<File> order;
    protected Boolean isRev;
    /**
     * constructor
     * **/
    OrderSizeComparator(Boolean isRev){
        this.isRev=isRev;
        this.order=this::compare;
    }
    /***
     * implements the compare method
     * **/
    public int compare(File f1, File f2) {
        if (f1.length()>f2.length()){return 1;}
        else  if (f1.length()==f2.length()){return f1.getAbsolutePath().compareTo(f2.getAbsolutePath());}
        else return -1;
    }
    @Override
    /**
     * return the comparator
     * **/
    public Comparator<File> getOrder() {
        if (isRev){
            return this.order.reversed();
        }
        return this.order;

    }
}
