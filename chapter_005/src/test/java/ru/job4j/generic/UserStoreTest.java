package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class UserStoreTest {

    private UserStore us;

    @Before
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
        assertThat(result, is("321"));
    }

    @Test
    public void whenReplaceUserStore() {
        String id = "321";
        User newUser = new User("555");

        boolean bus = us.replace(id, newUser);
        assertTrue(bus);

        User result = us.findById("555");
        User expected = newUser;
        assertThat(result, is(expected));
    }

    @Test
    public void whenDeleteUserStore() {
        String id = "321";
        boolean bus = us.delete(id);
        assertTrue(bus);
        assertNull(us.findById(id));

    }

    @Test
    public void findById() {
        String id = "321";
        User result = us.findById(id);
        assertNotNull(us.findById(id));
        assertNull(us.findById("888"));
    }
}