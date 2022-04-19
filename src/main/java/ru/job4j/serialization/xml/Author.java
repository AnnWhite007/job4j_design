package ru.job4j.serialization.xml;

public class Author {
    private final String name;
    private final String birthday;

    public Author(String name, String birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Author{"
                + "name='" + name + '\''
                + "birthday='" + birthday + '\''
                + '}';
    }
}
