package org.shemenev;

import java.util.*;

/**
 * 1) разделение входящего списка на две группы
 * для дальнейшего сравнения;
 * <p>
 * 2) Вычисление сходства строк и упаковка в map
 * пар с максимальным коэффициентом схожести;
 */
public class CompareWords {

    static ArrayList<String> list_1 = new ArrayList<>();
    static ArrayList<String> list_2 = new ArrayList<>();

    /**
     * Разделяет входящий LIST на два независимых,
     * сгруппированных по содержанию списка.
     *
     * @param entryFile список с содержимым файла
     */
    public static void divider(List<String> entryFile) {

        String st = entryFile.get(0).replaceAll("\\D", "");
        int length_1 = Integer.parseInt(st) + 1;

        for (int i = 1; i < length_1; i++) {
            list_1.add(entryFile.get(i));
        }
        for (int i = entryFile.size() - 1; i > length_1; i--) {
            list_2.add(entryFile.get(i));
        }
    }

    /**
     * 1) Сравнивает каждую пару слов из списков для нахождения наиболее
     * схожих между собой строк;
     * Порог коэффициент устанавливается вручную, опытным путем.
     * 2) результат добавляет в map;
     *
     * @param list_1 первая половина списка из файла
     * @param list_2 вторая половина списка из файла
     * @return пары схожих строк и одиночные строки,
     * представленные в map;
     */
    public static HashMap<String, String> compareAllWords(List<String> list_1, List<String> list_2) {

        HashMap<String, String> mapForPairs = new HashMap<>();

        SimilarityOfWords coefficient = new SimilarityOfWords();

        double temp;              // хранит текущее значение коэффициента
        double maxForPair = 0;    // хранит наибольшее значение коэффициента

        int innerIndex = 0;       // индекс элемента из внутреннего цикла ПРИ совпадении
        List<Integer> onlyPairIndexes = new ArrayList<>();

        String x;
        String y;

        // находим коэффициент схожести у всех пар слов
        for (int i = 0; i < list_1.size(); i++) {
            x = list_1.get(i);
            for (int j = 0; j < list_2.size(); j++) {
                y = list_2.get(j);
                temp = coefficient.findSimilarity(x, y);
                if (temp > maxForPair) {
                    maxForPair = temp;
                    innerIndex = j;
                }
            }
            if (maxForPair > 0.4) {
                onlyPairIndexes.add(innerIndex);   // добавляю индексы эл-в внутреннего цикла
            }

            // добавляем в map все выявленные пары и одиночные эл-ты
            if (list_1.size() > list_2.size()) {
                if (maxForPair < 0.3) {
                    mapForPairs.put(list_1.get(i), "?");
                } else {
                    mapForPairs.put(list_1.get(i), list_2.get(innerIndex));
                }
            } else if (list_2.size() > list_1.size()) {
                if (maxForPair > 0.3) {
                    mapForPairs.put(list_1.get(i), list_2.get(innerIndex));
                } else {
                    mapForPairs.put(list_1.get(i), "?");
                }
            } else {
                if (maxForPair > 0.3) {
                    mapForPairs.put(list_1.get(i), list_2.get(innerIndex));
                } else {
                    mapForPairs.put(list_1.get(i), "?");
                }
            }
        }

        // выявляем одиночные эл-ты внутреннего цикла и добавляем их в map
        if (list_1.size() == list_2.size() || list_1.size() < list_2.size()) {
            boolean noPair = false;
            for (int i = 0; i < list_2.size(); i++) {
                for (int j = 0; j < onlyPairIndexes.size(); j++) {
                    if (list_2.get(i).equals(list_2.get(onlyPairIndexes.get(j)))) {
                        noPair = true;
                        break;
                    } else noPair = false;
                }
                if (noPair == false) {
                    mapForPairs.put(list_2.get(i), "?");
                }
            }
        }

        return mapForPairs;
    }
}
