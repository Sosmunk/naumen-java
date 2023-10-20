package ru.naumen.collection.task3;

import java.nio.file.Path;
import java.util.*;

/**
 * <p>Написать консольное приложение, которое принимает на вход произвольный текстовый файл в формате txt.
 * Нужно собрать все встречающийся слова и посчитать для каждого из них количество раз, сколько слово встретилось.
 * Морфологию не учитываем.</p>
 * <p>Вывести на экран наиболее используемые (TOP) 10 слов и наименее используемые (LAST) 10 слов</p>
 * <p>Проверить работу на романе Льва Толстого “Война и мир”</p>
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class WarAndPeace {

    private static final Path WAR_AND_PEACE_FILE_PATH = Path.of("src/main/resources",
            "Лев_Толстой_Война_и_мир_Том_1,_2,_3,_4_(UTF-8).txt");

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String inputPath = in.nextLine();
        Path txtPath;
        if (inputPath.isEmpty()) {
            txtPath = WAR_AND_PEACE_FILE_PATH;
        } else {
            txtPath = Path.of(inputPath);
        }


        WordParser wp = new WordParser(txtPath);

        HashMap<String, Integer> wordCounter = countWords(wp);

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

    public static HashMap<String, Integer>  countWords(WordParser wp) {
        HashMap<String, Integer> wordCounter = new HashMap<>();
        wp.forEachWord(w -> {
            if (!wordCounter.containsKey(w)) {
                wordCounter.put(w, 1);
            } else {
                wordCounter.put(w, wordCounter.get(w) + 1);
            }
        });
        return wordCounter;
    }
}
