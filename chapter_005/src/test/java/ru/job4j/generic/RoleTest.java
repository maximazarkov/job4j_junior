package ru.job4j.generic;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * @author Azarkov Maxim
 * @version $Id$
 * @since 0.1
 */
public class RoleTest {

    @Test
    public void whenGetIdRole() {
        Role r = new Role("345");
        String result = r.getId();
        assertThat(result, is("345"));
    }

}