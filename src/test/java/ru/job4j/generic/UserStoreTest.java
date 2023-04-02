package ru.job4j.generic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.assertj.core.api.Assertions.*;

public class UserStoreTest {

    private UserStore us;

    @BeforeEach
    public void before() {
        us = new UserStore(4);
        us.add(new User("123"));
        us.add(new User("321"));
        us.add(new User("159"));
    }

    @Test
    public void whenAddUserStore() {
        User u = us.findById("321");
        String result = u.getId();
        assertThat(result).isEqualTo("321");
    }

    @Test
    public void whenReplaceUserStore() {
        String id = "321";
        User newUser = new User("555");

        boolean bus = us.replace(id, newUser);
        assertThat(bus).isTrue();

        User result = us.findById("555");
        assertThat(result).isEqualTo(newUser);
    }

    @Test
    public void whenDeleteUserStore() {
        String id = "321";
        boolean bus = us.delete(id);
        assertThat(bus).isTrue();
        assertThat(us.findById(id)).isNull();

    }

    @Test
    public void findById() {
        String id = "321";
        User result = us.findById(id);
        assertThat(us.findById(id)).isNotNull();
        assertThat(us.findById("888")).isNull();
    }
}