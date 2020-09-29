package filesprocessing;

import java.util.ArrayList;

public class FilterFactory {
    protected static final String FILE="file";
    protected static final String CONTAINS="contains";
    protected static final String PREFIX="prefix";
    protected static final String SUFFIX="suffix";
    protected static final String WRITABLE="writable";
    protected static final String EXECUTABLE="executable";
    protected static final String HIDDEN="hidden";
    protected static final String BETWEEN="between";
    protected static final String GREATER_THEN="greater_than";
    protected static final String SMALLER_THEN="smaller_than";
    protected static final String ALL="all";

    public static Filter createFilter(ArrayList<Object> list) {
        switch (String.valueOf(list.get(0))) {
            case GREATER_THEN  :
                return new FilterBySize(Double.parseDouble( String.valueOf(list.get(1))), String.valueOf(list.get(0)), (Boolean.valueOf( String.valueOf(list.get(2)))));
            case SMALLER_THEN:
                return new FilterBySize(Double.parseDouble( String.valueOf(list.get(1))), String.valueOf(list.get(0)), (Boolean.valueOf( String.valueOf(list.get(2)))));
            case BETWEEN:
                return new FilterBySize(Double.parseDouble( String.valueOf(list.get(1))),Double.parseDouble( String.valueOf(list.get(2))), String.valueOf(list.get(0)),(Boolean.valueOf( String.valueOf(list.get(3)))));
            case FILE:
                return new FilterByValue(String.valueOf(list.get(1)), String.valueOf(list.get(0)), (Boolean.valueOf( String.valueOf(list.get(2)))));
            case CONTAINS:
                return new FilterByValue(String.valueOf(list.get(1)), String.valueOf(list.get(0)),  (Boolean.valueOf( String.valueOf(list.get(2)))));
            case PREFIX:
                return new FilterByValue(String.valueOf(list.get(1)), String.valueOf(list.get(0)),  (Boolean.valueOf( String.valueOf(list.get(2)))));
            case SUFFIX:
                return new FilterByValue(String.valueOf(list.get(1)), String.valueOf(list.get(0)),  (Boolean.valueOf( String.valueOf(list.get(2)))));
            case EXECUTABLE:
                return new FilterByAttribute(String.valueOf(list.get(1)), String.valueOf(list.get(0)),  (Boolean.valueOf( String.valueOf(list.get(2)))));
            case HIDDEN:
                return new FilterByAttribute(String.valueOf(list.get(1)), String.valueOf(list.get(0)),  (Boolean.valueOf( String.valueOf(list.get(2)))));
            case WRITABLE:
                return new FilterByAttribute(String.valueOf(list.get(1)), String.valueOf(list.get(0)),  (Boolean.valueOf( String.valueOf(list.get(2)))));

        }
        return new FilterBySize((double) 0, String.valueOf(list.get(0)), false);
    }
}
