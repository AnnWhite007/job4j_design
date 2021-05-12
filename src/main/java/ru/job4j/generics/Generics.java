package ru.job4j.generics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 0. Что такое обобщенные типы (generics)
 * 1. WildCard.
 * 2. Bounded Wildcard
 * 3. Lower bounded wcard
 * Необработанные типы ("сырые типы"). Обозначаются они также как и generics в скобках <>,
 * в которых проставляются заглавные латинские символы, зарезервированные специально для этих целей символы
 */

public class Generics {
    public static void main(String[] args) {
        Generics gen = new Generics();
        List<Animal> first = new ArrayList<>();
        List<Predator> second = new ArrayList<>();
        List<Tiger> third = new ArrayList<>();
        first.add(new Animal());
        second.add(new Predator());
        third.add(new Tiger());

        gen.printObject(first);
        gen.printObject(second);
        gen.printObject(third);
        System.out.println();

        //gen.printBoundedWildCard(first);
        gen.printBoundedWildCard(second);
        gen.printBoundedWildCard(third);
        System.out.println();

        gen.printLowerBoundedWildCard(first);
        gen.printLowerBoundedWildCard(second);
        //gen.printLowerBoundedWildCard(third);
    }

    // 1-ый метод - работает без ограничений, т.е. в него можно передавать коллекцию, которая хранит любые типы.
    // public void printObject(List<Object> list) {
    //     for (Iterator<Object> it = list.iterator(); it.hasNext(); ) {
    public void printObject(List<?> list) {
        for (Iterator<?> it = list.iterator(); it.hasNext(); ) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);

        }
    }

    // 2-ой метод - должен иметь ограничение сверху и ограничиваться классом Predator.
    // public void printBoundedWildCard(List<Predator> list) {
    //    for (Iterator<Predator> it = list.iterator(); it.hasNext(); ) {
    public void printBoundedWildCard(List<? extends Predator> list) {
        for (Iterator<? extends Predator> it = list.iterator(); it.hasNext(); ) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    // 3-ий метод - должен иметь ограничение снизу и ограничиваться классом Predator.
    // public void printLowerBoundedWildCard(List<Predator> list) {
    //    for (Iterator<Predator> it = list.iterator(); it.hasNext(); ) {
    public void printLowerBoundedWildCard(List<? super Predator> list) {
        for (Iterator<? super  Predator> it = list.iterator(); it.hasNext(); ) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }
}