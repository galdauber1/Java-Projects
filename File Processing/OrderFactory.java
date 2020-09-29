package filesprocessing;

import java.util.ArrayList;
/**
 * ORDER FACTORY CLASS
 * **/
public class OrderFactory {
    public static final String ABS="abs";
    public static final String SIZE="size";
    public static final String TYPE="type";
    public static Order createOrder(ArrayList<Object>list){
        switch (String.valueOf(list.get(0))){
            case ABS:
                return new OrderAbsComparator((Boolean.valueOf( String.valueOf(list.get(1)))));
            case SIZE:
                return new OrderSizeComparator((Boolean.valueOf( String.valueOf(list.get(1)))));
            case TYPE:
                return new OrderTypeComprator((Boolean.valueOf( String.valueOf(list.get(1)))));
        }
        return new OrderAbsComparator(false);

    }
}
