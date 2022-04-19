package ru.job4j.serialization.json;

import java.util.Arrays;

public class Cat {
    private final String name;
    private final int age;
    private final Parents parents;
    private final boolean home;
    private final String[] kittensName;

    public Cat(String name, int age, Parents parents, boolean home, String[] kittensName) {
        this.name = name;
        this.age = age;
        this.parents = parents;
        this.home = home;
        this.kittensName = kittensName;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Cat{"
                + "name=" + name
                + ", age=" + age
                + ", parents=" + parents
                + ", home" + home
                + ", kittensName" + Arrays.toString(kittensName)
                + '}';
    }
}
