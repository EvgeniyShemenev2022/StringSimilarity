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
                    --
                    --
                    Hello, you should specify the path to the input.txt file
                    for example:
                    
                    java -jar C:\\path\\StringSimilarity-1.0.jar -path "C:\\pass\\input.txt"
                    
                    Good Luck!
                    
                    """);
            return;
        }

        try {
            if (args[0].equals("-path")) {
                pathToFile = Path.of(args[1]);

                // обозначаем путь для файла output.txt
                int index = args[1].lastIndexOf("\\") + 1;
                StringBuilder stringBuilder = new StringBuilder(args[1]);
                String pathForOutput = String.valueOf(stringBuilder.replace(index, args[1].length(), "output.txt"));

                // переносим содержимое файла в List
                Parser parserObj = new Parser();
                List<String> row = parserObj.parser(pathToFile);

                // делим список на части, сравниваем и помещаем в map
                CompareWords.divider(row);
                HashMap<String, String> mapForPairs = CompareWords.compareAllWords(CompareWords.list_1, CompareWords.list_2);

                // записываем содержимое map в файл output.txt
                Writer.writeToFile(pathForOutput, mapForPairs);

                System.out.println("""
                        --
                        Output.txt was created in the same directory as input file.
                        program completed.
                        
                        """);
            } else {
                System.out.println("Please, take sure you enter -path + path_to_input_file");
            }
        } catch (Exception e) {
            throw new IllegalStateException("Не удалось считать файл: " + pathToFile + "\\n" + e);
        }

    }
}
