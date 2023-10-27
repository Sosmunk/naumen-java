package ru.naumen.collection.task1;

import java.util.*;

/**
 * Дано:
 * <pre>
 * public class User {
 *     private String username;
 *     private String email;
 *     private byte[] passwordHash;
 *     …
 * }
 * </pre>
 * Нужно написать утилитный метод
 * <pre>
 * public static List<User> findDuplicates(Collection<User> collA, Collection<User> collB);
 * </pre>
 * <p>который возвращает дубликаты пользователей, которые есть в обеих коллекциях.</p>
 * <p>Одинаковыми считаем пользователей, у которых совпадают все 3 поля: username,
 * email, passwordHash. Дубликаты внутри коллекций collA, collB можно не учитывать.</p>
 * <p>Метод должен быть оптимален по производительности.</p>
 * <p>Пользоваться можно только стандартными классами Java SE.
 * Коллекции collA, collB изменять запрещено.</p>
 *
 * См. {@link User}
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class Task1 {

    /**
     * Возвращает дубликаты пользователей, которые есть в обеих коллекциях <br>
     * Асимптотическая сложность : O(n) <br>
     * Задача проверена на паре
     */
    public static List<User> findDuplicates(Collection<User> collA, Collection<User> collB) {
        // Хранит в себе уникальные значения, доступ по ключу O(1)
        Set<User> uniqueFirst = new HashSet<>(collA);
        List<User> duplicates = new ArrayList<>();
        for (User user : collB) {
            if (uniqueFirst.contains(user)) {
                duplicates.add(user);
            }
        }
        return duplicates;
    }
}
