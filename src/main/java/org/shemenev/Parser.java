package org.shemenev;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Парсинг содержимого файла в Map по категориям,
 * которые будут сравниваться друг с другом.
 */
public class Parser {

    public HashMap<String, List<String>> map = new HashMap<>();

    /**
     * читывает файл,
     * Построчно заполняет HashMap значениями из файла.
     * Число - ключ, слова после числа - значения.
     * @param pathToFile
     * @return HashMap<String, List<String>>
     */
    public HashMap<String, List<String>> parser(Path pathToFile)  {

        String[] key = new String[1];
        try (Stream<String> lines = Files.lines(pathToFile)) {
            lines.filter(line -> !line.isEmpty())
                    .map(String::strip)
                    .peek(System.out::println)  // убрать
                    .forEach(line -> {
                        if (line.length() <= 2) {   // line.matches("\\d") - почему то не работает с первым числом???
                            key[0] = line;
                        }
                        if (map.containsKey(key[0])) {
                            map.get(key[0]).add(line);
                        } else  {
                            map.put((key[0]), Stream.of(line).collect(Collectors.toList()));
                        }
                        // Удаляю цифры, которые попали в List при первом добавлении ключа.
                        // Придумать способ элегантнее !!!
                        if (map.get(key[0]).size() > 1 &&  map.get(key[0]).get(0).equals(key[0])  ){
                            map.get(key[0]).remove(0);
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
