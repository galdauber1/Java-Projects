package filesprocessing;

import java.io.File;
import java.util.function.Predicate;
/**
 * sub class of filter,that filtering by Attribute(writable,executable,hidden)
 * **/
public class FilterByAttribute extends Filter {
    protected String value;
    protected String name;
    protected Boolean neg;
    protected Predicate<File> filter;
    protected final String WRITABLE="writable";
    protected final String EXECUTABLE="executable";
    protected final String HIDDEN="hidden";
/**
 * class constructor;
 * **/
    FilterByAttribute(String value, String name, Boolean neg) {
        this.name = name;
        this.value = value;
        this.neg = neg;
        if (name.equals(WRITABLE)) {
            filter = File::canWrite;
        } else if ((name.equals(EXECUTABLE))) {
            filter = File::canExecute;
        } else if ((name.equals(HIDDEN))) {
          filter=File::isHidden;
        }

    }
    /**
     * return the predicate
     * **/
    @Override
    public Predicate<File> getFilter () {
        if(neg)
            return filter.negate();
        return filter;
    }

}
