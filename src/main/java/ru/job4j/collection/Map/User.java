package ru.job4j.collection.Map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 1. Создать модель User
 * 2. Без переопределения equals и hashCode
 * 3. Переопределить только hashCode
 * 4. Переопределить только equals
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

    public static void main(String[] args) {
        User first = new User("Ivan", 3, new GregorianCalendar(2014, 12, 21));
        User second = new User("Ivan", 3, new GregorianCalendar(2014, 12, 21));

        Map<User, Object> map = new HashMap<User, Object>();
        map.put(first, new Object());
        map.put(second, new Object());

        for (Map.Entry entry : map.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }
    }
/*
    @Override
    public int hashCode() {
        int result = name == null ? 0 : name.hashCode();
        result = result + children;
        int resultCal = birthday == null ? 0 : birthday.hashCode();
        result = result + resultCal;
        return result;
    } */

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User that = (User) o;

        if (name.equals(that.name)) {
            return false;
        }
        if (children != that.children) {
            return false;
        }
        return birthday.equals(that.birthday);
    }
}
