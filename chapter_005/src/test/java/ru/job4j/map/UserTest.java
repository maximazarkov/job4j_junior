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
        userMap.put(u3, 15);
        System.out.println(u1 + " [ " + u1.hashCode() + " ]");
        System.out.println(u2 + " [ " + u2.hashCode() + " ]");
        System.out.println(u3 + " [ " + u3.hashCode() + " ]");
        System.out.println(userMap);
    }
    /* Output
    После переопределения метода hashCode(), для получения хеш-кода объекта стало известно
    какие поля участвуют в расчете хеш-кода. По этому, по сути разным обьектам, но имеющим
    одинаковое содеражание полей присваивается одинаковый хеш-код. В расчет уже не берется
    базовый алгоритм, заложенный в классе Object.

    ru.job4j.map.User@396d1165 [ 963449189 ]
    ru.job4j.map.User@396d1165 [ 963449189 ]
    ru.job4j.map.User@2c67071d [ 744949533 ]

    Соотвествено, если так же павильно переопределить метод equals(), то данный метод будет
    иметь представление, как сравнить два разных объекта с одинаковым значением полей.
    И в результате, когда метод equals получает два "разных" объекта, но имеющих одинаковое
    содержимое, а соотвественно и одинаковый хеш-код, получают один и тот же ключ.
    Если ключа в карте еще нет, то пара ключ значение вносится в карту, если ключ уже существует,
    то значение, привязанное к данному ключ переписывается не смотря на то, что передается в разных объектах
    В примере видно что первых два одинаовы ключа были перезаписаны (2 изменено на 10), а новый получился уникальным и был
    добавлен как вторая пара (значение 15).
    {ru.job4j.map.User@396d1165=10, ru.job4j.map.User@2c67071d=15}
    
     */

}