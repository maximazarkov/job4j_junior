package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class RoleStoreTest {

    private RoleStore rs;

    @Before
    public void before() {
        rs = new RoleStore(4);
        rs.add(new Role("123"));
        rs.add(new Role("321"));
        rs.add(new Role("159"));
    }

    @Test
    public void whenAddRoleStore() {
        Role u = rs.findById("321");
        String result = u.getId();
        assertThat(result, is("321"));
    }

    @Test
    public void whenReplaceRoleStore() {
        String id = "321";
        Role newUser = new Role("555");

        boolean bus = rs.replace(id, newUser);
        assertTrue(bus);

        Role result = rs.findById("555");
        Role expected = newUser;
        assertThat(result, is(expected));
    }

    @Test
    public void whenDeleteRoleStore() {
        String id = "321";
        boolean bus = rs.delete(id);
        assertTrue(bus);
        assertNull(rs.findById(id));

    }

    @Test
    public void findById() {
        String id = "321";
        Role result = rs.findById(id);
        assertNotNull(rs.findById(id));
        assertNull(rs.findById("888"));
    }
}