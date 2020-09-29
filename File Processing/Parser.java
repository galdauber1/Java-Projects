package filesprocessing;
import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * this class parse the txt file into sections
 * **/

public class Parser {
    protected String commandfile;
    protected String sourcedir;
    protected List<File> files;
    private List<String> BAD_ARRAY = Arrays.asList("greater_than", "between", "smaller_than", "file",
            "contains", "prefix", "suffix", "writable", "executable", "hidden", "all");

    /**
     * constructor
     **/
    Parser(String sourcedir, String commandfile) {
        this.commandfile = commandfile;
        this.sourcedir = sourcedir;
        this.files = Arrays.asList(new File(sourcedir).listFiles());
    }

    /**
     * this method read the file into array of string(function from ex4)
     **/
    private String[] readFile(String fileName) throws Type2Exception {
        List<String> fileContent = new ArrayList<String>();
        // Reader object for reading the file
        BufferedReader reader = null;
        try {
            // Open a reader
            reader = new BufferedReader(new FileReader(fileName));

            // Read the first line
            String line = reader.readLine();

            // Go over the rest of the file
            while (line != null) {
                // Add the line to the list
                fileContent.add(line);

                // Read the next line
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: The file: " + fileName + " is not found.");
            return null;
        } catch (IOException e) {
            System.err.println("ERROR: An IO error occurred.");
            return null;
        } finally {
            // Try to close the file
            try {
                if (reader != null)
                    reader.close();
                else
                    return null;
            } catch (IOException e) {
                System.err.println("ERROR: Could not close the file " + fileName + ".");
            }
        }

        // Convert the list to an array and return the array
        for (int i = 0; i < fileContent.size() - 1; i++) {
            if (fileContent.get(i).equals("ORDER") && fileContent.get(i + 1).equals("FILTER")) {
                fileContent.add(i + 1, "");
            }
        }
        if (fileContent.size() > 0)
            if (fileContent.get(fileContent.size() - 1).equals("ORDER")) {
                fileContent.add("");
                String[] result = new String[fileContent.size() + 1];
                fileContent.toArray(result);
                return result;
            }
        if (fileContent.size() > 0) {
            if (fileContent.get(fileContent.size() - 1).equals("FILTER") || !fileContent.get((0)).
                    equals("FILTER")) {
                System.err.println("ERROR: ");
                throw new Type2Exception("ERROR: ");
            }
        }
        String[] result = new String[fileContent.size()];
        fileContent.toArray(result);
        return result;
    }
    /**
     * split each line into the name,value and boolean(not) of the filter/order
     * and return list of objects;
     * **/
    private List<Object> NameAndValues(String line, int i) {
        ArrayList<Object> list = new ArrayList<>();
        String[] splitLine = line.split("#");
        list.add(splitLine[0]);
        if (splitLine[0].equals("between")) {
            list.add(splitLine[1]);
            list.add(splitLine[2]);
            if (3 < splitLine.length && !splitLine[3].equals("NOT") || Double.parseDouble(splitLine[1]) < 0||
                    Double.parseDouble(splitLine[2]) < 0 || Double.parseDouble(splitLine[1])
                    > Double.parseDouble(splitLine[2])) {
                System.err.println("Warning in line" + " " + i);
                list.add(0, splitLine[0] = "all");
            }
            if (3 < splitLine.length && splitLine[3].equals("NOT")) {
                list.add(true);
            } else {
                list.add(false);
            }
        } else if (splitLine[0].equals("greater_than") || splitLine[0].equals("smaller_than")) {
            list.add(splitLine[1]);
            if (2 < splitLine.length && !splitLine[2].equals("NOT") || Double.parseDouble(splitLine[1]) < 0){
                System.err.println("Warning in line" + " " + i);
                list.add(0, splitLine[0] = "all");

            }
            if (2 < splitLine.length && splitLine[2].equals("NOT")) {
                list.add(true);
            } else {
                list.add(false);
            }
        } else if (splitLine[0].equals("file") || splitLine[0].equals("prefix") || splitLine[0].
                equals("suffix") || splitLine[0].equals("contains")) {
            if (splitLine.length > 1)
                list.add(splitLine[1]);
            if (2 < splitLine.length && splitLine[2].equals("NOT")) {
                list.add(true);
            } else {
                list.add(false);
            }
        } else if (splitLine[0].equals("writable") || splitLine[0].equals("executable") ||
                splitLine[0].equals("hidden")) {
            list.add(splitLine[1]);
            if (2 < splitLine.length && splitLine[2].equals("YES")) {
                list.add(true);
            } else {
                list.add(false);
            }
        } else if (splitLine[0].equals("abs") || splitLine[0].equals("type") || splitLine[0].equals("size")) {
            if (1 < splitLine.length && splitLine[1].equals("REVERSE")) {
                list.add(true);
            } else {
                list.add(false);
            }
        }
        return list;

    }
    /**
     * this function return a section that contains the specific filter and order for this section
     * **/
    protected ArrayList<Section> parseTosections() throws Type2Exception {
        String[] fileToSections = readFile(commandfile);
        ArrayList<Section> sections = new ArrayList<>();
        Filter filter = new Filter() {
            @Override
            public Predicate<File> getFilter() {
                return null;
            }
        };
        Order comprator = new Order() {
            @Override
            public Comparator<File> getOrder() {
                return null;
            }

            @Override
            public int compare(File o1, File o2) {
                return 0;
            }
        };
        int counter = 0;
        for (int i = 0; i <= fileToSections.length - 4; i += 4) {
            if (i != 0 && fileToSections[i - 1].equals("")) {
                counter++;
            }
            if (fileToSections[i].equals("FILTER")) {
                if (fileToSections.length >= i + 1) {
                    if (fileToSections[i + 1] == (null)) {
                        System.err.println("ERROR:ORDER sub-section missing");
                        throw new Type2Exception("ERROR:ORDER sub-section missing");
                    }
                }
                if (i + 1 < fileToSections.length) {
                    ArrayList<Object> nameAndValues = (ArrayList<Object>) NameAndValues(fileToSections[i + 1],
                            i + -(counter) + 2);
                    if (nameAndValues == null || fileToSections[i + 1].equals("FILTER") ||
                            fileToSections[i + 1].equals("ORDER") || !fileToSections[i + 1].equals("all") &&
                            !fileToSections[i + 1].contains("#")) {
                        System.err.println("Warning in line " + (i - (counter) + 2));
                        nameAndValues.add(0, "all");
                    } else {
                        filter = FilterFactory.createFilter(nameAndValues);
                        nameAndValues = new ArrayList<Object>();
                    }
                }
            }
            if (fileToSections[i + 2].equals("ORDER")) {
                if (i + 3 < fileToSections.length && fileToSections[i + 3].equals("ORDER") ||
                        ((((!fileToSections[i + 3].equals("abs")) && !fileToSections[i + 3].equals("type"))
                                && !fileToSections[i + 3].equals("size")) && !fileToSections[i + 3]
                                .equals("")) && !fileToSections[i + 3].equals("abs#REVERSE") &&
                                !fileToSections[i + 3].equals
                                ("type#REVERSE") && !fileToSections[i + 3].equals("size#REVERSE")) {
                    System.err.println("Warning in line " + ((i + 3) - (counter) + 1));

                }
                if (i + 3 < fileToSections.length) {
                    ArrayList<Object> nameAndValues = (ArrayList<Object>) NameAndValues(fileToSections[i + 3]
                            , i - (counter + 2));
                    if (nameAndValues == null) {
                    } else {
                        comprator = OrderFactory.createOrder(nameAndValues);
                        nameAndValues = new ArrayList<Object>();
                    }
                }
            }
            int size = files.size();
            sections.add(new Section(filter, comprator, files.toArray(new File[size])));
            filter = new Filter() {
                @Override
                public Predicate<File> getFilter() {
                    return null;
                }
            };
            comprator = new OrderAbsComparator(false);

        }
        return sections;


    }
}