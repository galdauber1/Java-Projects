package filesprocessing;

import java.io.File;
import java.util.Comparator;
/**
 * suc class that ordering by abs
 * **/
public class OrderAbsComparator extends Order implements Comparator<File> {
    protected Comparator<File> order;
    protected Boolean isRev;
    OrderAbsComparator(Boolean isRev){
        this.isRev=isRev;
            order=this::compare;
    }
    /**
     * compare method
     * **/
    public int compare(File f1, File f2){
        return f1.getAbsolutePath().compareTo(f2.getAbsolutePath());
    }
    /**
     * returns the comprator;
     * **/
    @Override
    public Comparator<File> getOrder() {
        if (isRev){
            return this.order.reversed();
        }
        return this.order;

    }
}
