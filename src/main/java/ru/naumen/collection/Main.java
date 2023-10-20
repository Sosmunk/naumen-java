package ru.naumen.collection;

import ru.naumen.collection.task1.Task1;
import ru.naumen.collection.task1.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<User> l1 = List.of(new User("a", "b", "123".getBytes()),
                new User("b", "c", "123".getBytes()));
        List<User> l2 = List.of(new User("a", "b", "123".getBytes()),
                new User("1", "2", "3".getBytes()));
        System.out.println(Task1.findDuplicates(l1, l2));



    }
}
