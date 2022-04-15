package ru.job4j.serialization.json;

public class Parents {
    private final String dadName;
    private final String momName;

    public Parents(String dadName, String momName) {
        this.dadName = dadName;
        this.momName = momName;
    }

    @Override
    public String toString() {
        return "Parents{"
                + "mother=" + momName
                + "father=" + dadName
                + '}';
    }
}
