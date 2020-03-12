package ru.job4j.generic;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

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
        assertThat(result, is("abc"));
    }

}