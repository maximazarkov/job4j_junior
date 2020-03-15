package ru.job4j.map;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void whenCreateTwoUserAndShowMap() {
        User u1 = new User("Иван", 1, null);
        User u2 = new User("Иван", 1, null);
        Map<User, Object> userMap = new HashMap<>();
        userMap.put(u1, 2);
        userMap.put(u2, 10);
        System.out.println(u1);
        System.out.println(u2);
        System.out.println(userMap);
    }
    /* Output
    После переопределения метода equals() без переопределения метода hashCode() класса Object,
    объекты получают разные адреса, соотвественно разные хеш-коды, т.к. метод hashCode не знает как
    обсчитывать неизвестные ему поля объектов.
    ru.job4j.map.User@d041cf
    ru.job4j.map.User@129a8472

    Ну а в итоге в карту HashMap подпадают "одинаковые" объекты с разными хеш-кодами как
    разные объекты. Для правильной работы функций сравления должны быть переопределены оба
    метода, как equals(), так и hashCode()
    {ru.job4j.map.User@129a8472=10, ru.job4j.map.User@d041cf=2}
    
     */

}