import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

public class SimpleSetPerformanceAnalyzer {
    /** The number needed to convert the nano seconds to milli seconds.*/
    private static final int MILLI_SECONDS = 1000000;
    /** The number of iteration needed for LinkedList's for check running.*/
    private static final int LINKED_LIST_ITERTIONS = 7000;
    /** The number of iteration needed for any structure (excluding LinkedList) for check running.*/
    private static final int ITERATIONS = 70000;
    /** The number of iteration needed for warm .**/
    private static final int WARM_UP = 70000;
    /** The index of LinkedList structure in the SimpleSet list.*/
    private static final int LINKED_LIST = 3;
    private static final String STRING_FOR_CONTAIN="-13170890158";
    private static final String HI="hi";
    private static final String TWENTY_THREE="23";
    private static final String MSG1="Adding all the words from";
    public static void main(String[] args) {
        SimpleSet[] SimpleSetArray = new SimpleSet[5];
        buildArray(SimpleSetArray);
        analyzer("src/data1.txt", SimpleSetArray);
        containAnalyzer(SimpleSetArray, HI);
        containAnalyzer(SimpleSetArray, STRING_FOR_CONTAIN);
        analyzer("src/data2.txt", SimpleSetArray);
        containAnalyzer(SimpleSetArray, TWENTY_THREE);
        containAnalyzer(SimpleSetArray, HI);
    }
    private static void buildArray(SimpleSet[] SimpleSetArray) {
        SimpleSetArray[0] = new OpenHashSet();
        SimpleSetArray[1] = new ClosedHashSet();
        SimpleSetArray[2] = new CollectionFacadeSet(new TreeSet<>());
        SimpleSetArray[3] = new CollectionFacadeSet(new LinkedList<>());
        SimpleSetArray[4] = new CollectionFacadeSet(new HashSet<>());
    }
    /**
     * this function return the adding times
     * @param data The data we want to convert into a list of strings.
     * @param simpleSetArray array of simpelsets.
     */
    private static void analyzer(String data, SimpleSet[] simpleSetArray){
        String[] stringsArray = Ex4Utils.file2array(data);
        for (SimpleSet simpleSet : simpleSetArray) {
            long timeBefore = System.nanoTime();
            if (stringsArray != null) {
                for (String string : stringsArray) {
                    simpleSet.add(string);
                }
            }
            long difference = ((System.nanoTime() - timeBefore) / MILLI_SECONDS);
            System.out.println(MSG1 + data + " took " + difference + " m.s in "
                    + simpleSet.toString());
        }
    }

    /**
     * prints the contain time of specific string after warm up
     * @param simpleSetArray array of simpelsets.
     * @param string The string we want to check.
     */
    private static void containAnalyzer(SimpleSet[] simpleSetArray, String string){
        for (int i = 0; i<simpleSetArray.length; i ++){
            if (i != LINKED_LIST){
                WarmUp(simpleSetArray[i], string);
                measureTheContainFunction(simpleSetArray[i], string, ITERATIONS);
                // The measure  for LinkedList.
            } if (i == LINKED_LIST){
                measureTheContainFunction(simpleSetArray[i], string,
                        LINKED_LIST_ITERTIONS);
            }
        }
    }

    /**
     * warm up func
     * @param simpleSet  structure for warm up.
     * @param string The string .
     */
    private static void WarmUp(SimpleSet simpleSet, String string) {
        for (int i = 0; i < WARM_UP; i ++){
            simpleSet.contains(string);
        }
    }

    /**
     *prints the contain time of specific string after warm up
     * @param simpleSet The structure we want to check.
     * @param string The string.
     * @param iteration num of iterations .
     */
    private static void measureTheContainFunction(SimpleSet simpleSet, String string,
                                                         int iteration) {
        long timeBefore = System.nanoTime();
        for (int i = 0; i < iteration; i ++){
            simpleSet.contains(string);
        }
        long time = (System.nanoTime() - timeBefore)/ iteration;
        System.out.println("Checks if the structure contain the string " + string +
                " took " + time + " n.s");
    }
}
