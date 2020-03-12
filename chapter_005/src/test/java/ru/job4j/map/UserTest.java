package ru.job4j.map;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void whenCreateTwoUserAndShowMap() {
        User u1 = new User("Иван", 1, null);
        User u2 = new User("Роман", 2, null);
        Map<User, Object> userMap = new HashMap<>();
        userMap.put(u1, 2);
        userMap.put(u2, 10);
        System.out.println(u1);
        System.out.println(u2);
        System.out.println(userMap);
    }

}