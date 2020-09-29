package filesprocessing;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
/**
 * DirectoryProcessor class mange the prgoram;
 * ***/
public class DirectoryProcessor {
    public static void main(String[] args)  {
        try {
            Parser parser;
            parser = new Parser(args[0], args[1]);
            ArrayList<Section> sections = parser.parseTosections();
            for (Section section : sections) {
                File[] filterdfiles = section.filterAndOrder();
                for (File file : filterdfiles) {
                    if (file.isFile()) {
                        System.out.println(file.getName());
                    }
                }
            }

        }catch (Type2Exception e){

        }
    }
}
