package ru.job4j.serialization.json;

public class Parents {
    private final String father;
    private final String mother;

    public Parents(String father, String mother) {
        this.father = father;
        this.mother = mother;
    }

    @Override
    public String toString() {
        return "father: " + father
                + "mother: " + mother
                + '}';
    }
}
