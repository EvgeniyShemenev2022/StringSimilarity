package org.shemenev;

/**
 * Реализуется сравнение двух строк (X, Y) и
 * вычисляется Расстояние Левенштейна;
 * <p>
 * На основе полученного расстояния
 * метод .findSimilarity() вычисляет коэффициент
 * схожести в диапазоне от 0 - 1;
 */
public class SimilarityOfWords {

    /**
     * Расстояние Левенштейна;
     * Алгоритм определяет, насколько две строки отличаются
     * друг от друга, подсчитывая минимальное количество
     * операций, необходимых для преобразования одной строки в другую;
     *
     * @param X - first word
     * @param Y - second word
     * @return int (минимальное число операций)
     */
    public int getLevenshteinDistance(String X, String Y) {
        X = X.toLowerCase();   // Ignore case
        Y = Y.toLowerCase();   // Ignore case

        int m = X.length();
        int n = Y.length();

        int[][] T = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            T[i][0] = i;
        }
        for (int j = 1; j <= n; j++) {
            T[0][j] = j;
        }

        int cost;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                cost = X.charAt(i - 1) == Y.charAt(j - 1) ? 0 : 1;
                T[i][j] = Integer.min(Integer.min(T[i - 1][j] + 1, T[i][j - 1] + 1),
                        T[i - 1][j - 1] + cost);
            }
        }
        return T[m][n];
    }

    /**
     * Реализует расстояние Левенштейна и использует
     * его для вычисления сходства между двумя строками в диапазоне [0, 1];
     *
     * @param x - first word
     * @param y - second word
     * @return double (коэффициент сходства слов)
     */
    public double findSimilarity(String x, String y) {
        if (x == null || y == null) {
            throw new IllegalArgumentException("Одно из значений равно null");
        }

        double maxLength = Double.max(x.length(), y.length());
        if (maxLength > 0) {
            return (maxLength - getLevenshteinDistance(x, y)) / maxLength;
        }
        return 1.0;
    }

}
