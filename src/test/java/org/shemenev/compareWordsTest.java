package org.shemenev;

import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class compareWordsTest {

    /**
     * метод должен разделять исходный список на два отдельных,
     * независимо от длины и соотношения слов в списке;
     */
    @Test
    public void dividerTest() {

        // во второй список содержимое добавляется в ОБРАТНОМ порядке!!!
        // List_1 < List_2
        ArrayList<String> testList_1 =
                new ArrayList<>(Arrays.asList("1", "пластик", "3", "ручка дверная", "каменная вата", "шуруп 3*15"));

        CompareWords.divider(testList_1);

        ArrayList<String> expected_1_first =
                new ArrayList<>(List.of("пластик"));
        ArrayList<String> expected_1_second =
                new ArrayList<>(Arrays.asList("шуруп 3*15", "каменная вата", "ручка дверная"));

        assertThat(CompareWords.list_1, is(expected_1_first));
        assertThat(CompareWords.list_2, is(expected_1_second));

        // List_1 == List_2
        ArrayList<String> testList_2 =
                new ArrayList<>(Arrays.asList("2", "ручка", "камень", "2", "ручка дверная", "каменная вата"));

        ArrayList<String> expected_2_first =
                new ArrayList<>(Arrays.asList("ручка", "камень"));
        ArrayList<String> expected_2_second =
                new ArrayList<>(Arrays.asList("каменная вата", "ручка дверная"));

        CompareWords.list_1.clear();
        CompareWords.list_2.clear();
        CompareWords.divider(testList_2);

        assertThat(CompareWords.list_1, is(expected_2_first));
        assertThat(CompareWords.list_2, is(expected_2_second));

        // List_1 > List_2
        ArrayList<String> testList_3 =
                new ArrayList<>(Arrays.asList("3", "бинт", "камень", "пластик", "2", "бумага", "камень точильный"));

        CompareWords.list_1.clear();
        CompareWords.list_2.clear();
        CompareWords.divider(testList_3);

        ArrayList<String> expected_3_first =
                new ArrayList<>(Arrays.asList("бинт", "камень", "пластик"));
        ArrayList<String> expected_3_second =
                new ArrayList<>(Arrays.asList("камень точильный", "бумага" ));

        assertThat(CompareWords.list_1, is(expected_3_first));
        assertThat(CompareWords.list_2, is(expected_3_second));

    }

    @Ignore
    @Test
    public void compareAllWordsTest() {

        //  в процессе...
    }
}