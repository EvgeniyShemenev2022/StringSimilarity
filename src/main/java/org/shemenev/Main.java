package org.shemenev;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        Path pathToFile = null;

        if (args.length == 0) {
            System.out.println("""
                    Hello, you should specify the path to the file
                    for example:
                    C:\\\\path> java TestReader.jar -path "C:\\\\your pass\\\\input.txt"
                    Good Luck!""");
        }
        if (args[0].equals("-path")) {
            pathToFile = Path.of(args[1]);
        }

        Parser parserObj = new Parser();
        List<String> row = parserObj.parser(pathToFile);
        System.out.println(parserObj.parser(pathToFile));
        System.out.println(parserObj.parser(pathToFile).size());

         CompareWords.divider(row);

        System.out.println(CompareWords.list_1);
        System.out.println(CompareWords.list_2);

        HashMap<String, String> mapForPairs = CompareWords.compareAllWords(CompareWords.list_1, CompareWords.list_2);
        System.out.println(mapForPairs);

    }
}
