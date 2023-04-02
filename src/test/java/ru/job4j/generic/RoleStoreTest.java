package ru.job4j.generic;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class RoleStoreTest {

    private RoleStore rs;

    @BeforeEach
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
        assertThat(result).isEqualTo("321");
    }

    @Test
    public void whenReplaceRoleStore() {
        String id = "321";
        Role newUser = new Role("555");
        boolean bus = rs.replace(id, newUser);
        assertThat(bus).isTrue();
        Role result = rs.findById("555");
        Role expected = newUser;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenDeleteRoleStore() {
        String id = "321";
        boolean bus = rs.delete(id);
        assertThat(bus).isTrue();
        assertThat(rs.findById(id)).isNull();
    }

    @Test
    public void findById() {
        String id = "321";
        Role result = rs.findById(id);
        assertThat(rs.findById(id)).isNotNull();
        assertThat(rs.findById("888")).isNull();
    }
}