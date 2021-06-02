package ru.job4j.iterator;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(Arrays.asList(0, 1, 2, 3), Is.is(input));
    }

    @Test
    public void whenPredicate() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 4, 5, 6));
        Predicate<Integer> pred = f -> f % 2 != 0;
        ListUtils.removeIf(input, pred);
        assertThat(Arrays.asList(2, 4, 6), Is.is(input));
    }

    @Test
    public void whenSet() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 4, 5, 6));
        Predicate<Integer> pred = f -> f % 2 != 0;
        ListUtils.replaceIf(input, pred, 0);
        assertThat(Arrays.asList(0, 2, 4, 0, 6), Is.is(input));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 4, 5, 6));
        List<Integer> elements = new ArrayList<>(Arrays.asList(2, 3, 6));
        ListUtils.removeAll(input, elements);
        assertThat(Arrays.asList(1, 4, 5), Is.is(input));
    }

}