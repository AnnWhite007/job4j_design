package ru.job4j.collection.Map;

import java.util.Calendar;

/**
 * 1. Создать модель User
 */

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;

    }
}
