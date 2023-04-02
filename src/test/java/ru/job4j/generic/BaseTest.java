package ru.job4j.generic;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

/**
 * @author Azarkov Maxim
 * @version $Id$
 * @since 0.1
 */
public class BaseTest {

    @Test
    public void whenGetIdBase() {
        Base b = new Base("123") {
            @Override
            public String getId() {
                return super.getId();
            }
        };
        String result = b.getId();
        assertThat(result).isEqualTo("123");
    }
}