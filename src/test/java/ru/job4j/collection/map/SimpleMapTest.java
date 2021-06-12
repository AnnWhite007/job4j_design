package ru.job4j.collection.map;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class SimpleMapTest {
    @Test
    public void whenAdd() {
        Map<Integer, String> map = new SimpleMap<>();
        assertTrue(map.put(25, "Anna"));
        assertFalse(map.put(25, "Kate"));
        assertTrue(map.put(68, "Kate"));
    }

    @Test
    public void whenGetAndRemove() {
        Map<Integer, String> map = new SimpleMap<>();
        assertTrue(map.put(25, "Anna"));
        assertTrue(map.put(26, "Kate"));
        assertThat(map.get(23), is(nullValue()));
        assertThat(map.get(25), is("Anna"));
        assertTrue(map.remove(26));
        assertFalse(map.remove(75));
    }

    @Test
    public void whenIterator() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(25, "Anna");
        Iterator<Integer> it = map.iterator();
        assertThat(it.next(), is(25));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNoEl() {
        Map<Integer, String> map = new SimpleMap<>();
        assertTrue(map.put(26, "Kate"));
        assertTrue(map.remove(26));
        map.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenItetatorError() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(25, "Anna");
        Iterator<Integer> it = map.iterator();
        map.put(54, "Kate");
        it.next();
    }



}
