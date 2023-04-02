package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        Config config = new Config("app.properties");
        config.load();
        assertThat(config.value("hibernate.connection.username")).isEqualTo("postgres");
    }
}