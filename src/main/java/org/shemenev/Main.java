package org.shemenev;

import java.io.IOException;
import java.nio.file.Path;

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

        Parser parser = new Parser();
        System.out.println(parser.parser(pathToFile));

        System.out.println(parser.map.size());
    }
}
