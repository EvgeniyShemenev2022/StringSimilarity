package org.shemenev;

import java.io.IOException;
import java.nio.file.Path;
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

              // -path "C:\Users\Evgeniy\Documents\java.txt"
        }
        // обозначаем путь для файла output.txt
        int index = args[1].lastIndexOf("\\") + 1;
        StringBuilder stringBuilder = new StringBuilder(args[1]);
        String pathForOutput = String.valueOf(stringBuilder.replace(index, args[1].length(), "output.txt"));


        Parser parserObj = new Parser();
        List<String> row = parserObj.parser(pathToFile);
        System.out.println(parserObj.parser(pathToFile));
        System.out.println(parserObj.parser(pathToFile).size());

         CompareWords.divider(row);

        System.out.println(CompareWords.list_1);
        System.out.println(CompareWords.list_2);

        HashMap<String, String> mapForPairs = CompareWords.compareAllWords(CompareWords.list_1, CompareWords.list_2);
        System.out.println(mapForPairs);

        System.out.println(pathForOutput);
    }
}
