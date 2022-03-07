package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr Arsentev"));
        assertThat(config.value("surname"), is(nullValue()));
    }

    @Test
    public void whenPairWithComment() {
        String path = "./data/pair_with_comment_andnull.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Anna"));
        assertThat(config.value("date"), is("18/10"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenError() {
        String path = "./data/pair_with_error.properties";
        Config config = new Config(path);
        config.load();
        config.value("bmw");
    }
}