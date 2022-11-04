package org.shemenev;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Парсинг содержимого файла в Map по категориям,
 * которые будут сравниваться друг с другом.
 */
public class Parser {

    public List<String> parser(Path pathToFile) throws IOException {

        List<String> fileEntry;

        try (Stream<String> lines = Files.lines(pathToFile)) {
            fileEntry = lines.filter(line -> !line.isEmpty())
                    .map(String::strip)
                    .collect(Collectors.toList());
            return fileEntry;
        }
    }

}
