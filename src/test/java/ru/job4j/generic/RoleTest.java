package ru.job4j.generic;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

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
        assertThat(result).isEqualTo("345");
    }

}