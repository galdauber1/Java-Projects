package filesprocessing;

import java.io.File;
import java.util.Comparator;
/**
 * sub class that ordering by type implements comparator
 * **/
public class  OrderTypeComprator extends Order implements Comparator<File> {
    protected Comparator<File> order;
    protected Boolean isRev;
    OrderTypeComprator(Boolean isRev){
        this.isRev=isRev;
            this.order=this::compare;
        }
        /**
         * implements the compare method
         * **/
    public int compare(File f1, File f2){
       int diffrence=(getExtesion(f1.getName()).compareTo(getExtesion(f2.getName())));
       if (diffrence==0){return f1.getAbsolutePath().compareTo(f2.getAbsolutePath());}
       return diffrence;
    }
    private String getExtesion(String filename){
        if (!filename.contains(".")){return "" ;}
        int last=filename.lastIndexOf(".");
        if (last==filename.length()){return "";}
        return filename.substring(last+1,filename.length());}
    @Override
    public Comparator<File> getOrder() {
        if (isRev){
            return this.order.reversed();
        }
        return this.order;

    }
}
