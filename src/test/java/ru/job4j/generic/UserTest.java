package ru.job4j.generic;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

/**
 * @author Azarkov Maxim
 * @version $Id$
 * @since 0.1
 */
public class UserTest {
    @Test
    public void whetGetIdUser() {
        User u = new User("abc");
        String result = u.getId();
        assertThat(result).isEqualTo("abc");
    }

}