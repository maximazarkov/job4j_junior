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
        User u3 = new User("Анна", 2, null);
        Map<User, Object> userMap = new HashMap<>();
        userMap.put(u1, 2);
        userMap.put(u2, 10);
        System.out.println(u1 + " [ " + u1.hashCode() + " ]");
        System.out.println(u2 + " [ " + u2.hashCode() + " ]");
        System.out.println(u3 + " [ " + u3.hashCode() + " ]");
        System.out.println(userMap);
    }
    /* Output
    После переопределения метода hashCode() без переопределения метода equals() класса Object,
    одинаковые объекты начали получать одинакове хеш-коды
    ru.job4j.map.User@396d1165 [ 963449189 ]
    ru.job4j.map.User@396d1165 [ 963449189 ]

    Так как equals() от корневого класса не переопределен и все так же сравнивает
    сравнивает объекты по их адресам, то соотвественно он будет работать некорректо
    и с большой вероятностью выдаст на неизвестные, но одинаковые объекты false,
    соотвественно и в карте HashMap будут сохранены два одинаковых ключа с одинаковыми
    хеш-кодом.
    {ru.job4j.map.User@396d1165=2, ru.job4j.map.User@396d1165=10}

    Получается, что от одного метода hashCode() не зависит правильное определение объектов.
    Так же от него не требуется выдавать уникальные хеш-коды для объектов. Но для правильного
    определения необходимо так же переопределять метод equals().
     */

}