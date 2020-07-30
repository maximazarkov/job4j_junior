package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        Config config = new Config("app.properties");
        config.load();
        assertThat(
                config.value("hibernate.connection.username"),
                is("postgres"));
    }
}