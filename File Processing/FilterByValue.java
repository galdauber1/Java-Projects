package filesprocessing;
import java.io.File;
import java.util.function.Predicate;
/**
 *   sub class of filter,that filtering by Attribute(file,contains,prefix,suffix)
 * **/
public class FilterByValue extends Filter {
    protected String value;
    protected String name;
    protected Boolean neg;
    protected Predicate<File> filter;
    protected final String FILE="file";
    protected final String CONTAINS="contains";
    protected final String PREFIX="prefix";
    protected final String SUFFIX="suffix";
/**
 * construcor
 * **/
    FilterByValue(String value, String name, Boolean neg) {
        this.name = name;
        this.value = value;
        this.neg = neg;
        if (name.equals(FILE)) {
            filter = file -> file.getName().equals(value);
        } else if ((name.equals(CONTAINS))) {
            filter = file -> file.getName().contains(value);
        } else if ((name.equals(PREFIX))) {
            filter = file -> file.getName().startsWith(value);
        }
     else if((name.equals(SUFFIX)))

    {
        filter = file -> file.getName().endsWith(value);
    }

}
    /**
     * return the predicate
     * **/
    @Override
    public Predicate<File> getFilter () {
        if (neg){
            return filter.negate();
        }
        return filter;
    }
}
