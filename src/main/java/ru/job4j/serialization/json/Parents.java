package ru.job4j.serialization.json;

public class Parents {
    private final String father;
    private final String mother;

    public Parents(String father, String mother) {
        this.father = father;
        this.mother = mother;
    }

    public String getFather() {
        return father;
    }

    public String getMother() {
        return mother;
    }

    @Override
    public String toString() {
        return "Parents{"
                + "father: " + father
                + "mother: " + mother
                + '}';
    }
}
