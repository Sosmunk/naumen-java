package ru.naumen.collection.task3;

import java.nio.file.Path;
import java.util.*;

/**
 * <p>Написать консольное приложение, которое принимает на вход произвольный текстовый файл в формате txt.
 * Нужно собрать все встречающийся слова и посчитать для каждого из них количество раз, сколько слово встретилось.
 * Морфологию не учитываем.</p>
 * <p>Вывести на экран наиболее используемые (TOP) 10 слов и наименее используемые (LAST) 10 слов</p>
 * <p>Проверить работу на романе Льва Толстого “Война и мир”</p>
 * <b><p>Асипмтотическая сложность O(n)</p>
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class WarAndPeace {

    private static final Path WAR_AND_PEACE_FILE_PATH = Path.of("src/main/resources",
            "Лев_Толстой_Война_и_мир_Том_1,_2,_3,_4_(UTF-8).txt");

    public static void main(String[] args) {
        WarAndPeace warAndPeace = new WarAndPeace();
        WordParser wp = new WordParser(WAR_AND_PEACE_FILE_PATH);

        Map<String, Integer> wordCounter = warAndPeace.countWords(wp);

        PriorityQueue<Map.Entry<String, Integer>> top10PriorityQueue = new PriorityQueue<>(11,
                Map.Entry.comparingByValue());

        PriorityQueue<Map.Entry<String, Integer>> least10PriorityQueue = new PriorityQueue<>(11,
                (i1, i2) -> i2.getValue().compareTo(i1.getValue()));

        // Сортировка за O(1), так как сортируется по константе (10)
        for (Map.Entry<String, Integer> word : wordCounter.entrySet() ) {
            top10PriorityQueue.add(word);
            if (top10PriorityQueue.size() > 10) {
                top10PriorityQueue.poll();
            }
            least10PriorityQueue.add(word);
            if (least10PriorityQueue.size() > 10) {
                least10PriorityQueue.poll();
            }
        }
        System.out.println("Наиболее используемые 10 слов по частоте");
        new LinkedList<>(top10PriorityQueue).descendingIterator().forEachRemaining(System.out::println);
        System.out.println("\nНаименее используемые 10 слов по частоте");
        new LinkedList<>(least10PriorityQueue).descendingIterator().forEachRemaining(System.out::println);
    }

    public Map<String, Integer> countWords(WordParser wp) {
        //O(1) запись и чтение, оптимальнее чем обычная hashmap для итераций.
        LinkedHashMap<String, Integer> wordCounter = new LinkedHashMap<>();
        wp.forEachWord(word -> wordCounter.put(word, wordCounter.getOrDefault(word, 0) + 1));
        return wordCounter;
    }
}
