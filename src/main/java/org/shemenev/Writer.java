package org.shemenev;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Производится запись переданной map в файл
 * с названием output.txt
 * в ту же директорию, из которой считывался файл input.txt
 */
public class Writer {

    /**
     * записывает содержимое map в файл
     *
     * @param path директория в которую будет записан файл;
     * @param map  список всех совпавших и одиночных слов;
     */
    public static void writeToFile(String path, HashMap<String, String> map) {

        File file = new File(path);
        BufferedWriter bw = null;

        try {
            bw = new BufferedWriter(new FileWriter(file));

            int count = 0;
            for (Map.Entry<String, String> entry : map.entrySet()) {
                bw.write(count++ + ") " + entry.getKey() + " : " + entry.getValue());
                bw.newLine();
            }
            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error writing file");
        } finally {
            try {
                bw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
