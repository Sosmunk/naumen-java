package ru.naumen.collection.task3;

import java.nio.file.Path;
import java.util.*;

/**
 * <p>Написать консольное приложение, которое принимает на вход произвольный текстовый файл в формате txt.
 * Нужно собрать все встречающийся слова и посчитать для каждого из них количество раз, сколько слово встретилось.
 * Морфологию не учитываем.</p>
 * <p>Вывести на экран наиболее используемые (TOP) 10 слов и наименее используемые (LAST) 10 слов</p>
 * <p>Проверить работу на романе Льва Толстого “Война и мир”</p>
 * <b><p>Асипмтотическая сложность O(nlog(n))</p>
 * На паре было сказано, что алгоритм медленный. <br>
 * Какие оптимизации в данном случае возможны? <br>
 * Так или иначе нужно хранить словарь частот слов, сортировать их по убыванию</b>
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class WarAndPeace {

    private static final Path WAR_AND_PEACE_FILE_PATH = Path.of("src/main/resources",
            "Лев_Толстой_Война_и_мир_Том_1,_2,_3,_4_(UTF-8).txt");

    public static void main(String[] args) {
        WordParser wp = new WordParser(WAR_AND_PEACE_FILE_PATH);

        //Самая оптимальная структура данных для подсчета частот слов, O(1) запись и чтение
        Map<String, Integer> wordCounter = countWords(wp);

        // Нужно быстро обращаться к первым 10 и последним 10 элементам по индексу, поэтому используется List
        List<Map.Entry<String, Integer>> entryList = wordCounter
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .toList();

        List<Map.Entry<String, Integer>> top10 = entryList.
                subList(0, Math.min(entryList.size(), 10));

        List<Map.Entry<String, Integer>> least10 = entryList
                .subList(entryList.size()-Math.min(entryList.size(), 10),
                        entryList.size());

        System.out.println(top10);
        System.out.println(least10);
    }

    public static Map<String, Integer> countWords(WordParser wp) {
        HashMap<String, Integer> wordCounter = new HashMap<>();
        wp.forEachWord(word -> wordCounter.put(word, wordCounter.getOrDefault(word, 0) + 1));
        return wordCounter;
    }
}
