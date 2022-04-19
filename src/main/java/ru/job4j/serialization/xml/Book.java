package ru.job4j.serialization.xml;

import ru.job4j.generics.Base;

import java.util.Arrays;

public class Book {
    private final String name;
    private final int pages;
    private final Author author;
    private final String[] publish;
    private final boolean read;

    public Book(String name, int pages, Author author, String[] publish, boolean read) {
        this.name = name;
        this.pages = pages;
        this.author = author;
        this.publish = publish;
        this.read = read;
    }

    @Override
    public String toString() {
        return "Book{"
                + "name=" + name
                + ", pages=" + pages
                + ", Author=" + author
                + ", publish" + Arrays.toString(publish)
                + ", read"
                + '}';
    }
}
