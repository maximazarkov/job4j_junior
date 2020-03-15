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
    Не зависимо то того, что поля объектов u1 и u2 одинаковые, создается два разных объекта,
    которые имеют свой уникальный hashCode.
    ru.job4j.map.User@d041cf
    ru.job4j.map.User@129a8472

    Проблема в том, что классы u1 и u2 унаследованы от общего корневого класса Object, а для
    генерирования хеш-кода объекта используется реализация hashCode() этого класса.
    По умолчанию hashCode() возвращает адрес объекта. Таким образом хеш-код экземпляра
    u1 не совпадает с хеш-кодом u2.
    Так же не переопределен метод equals() класса Object. Данный метод по умолчанию сводится
    к сравнению адресов, по этому объекты u1 и u2 с одинаковым содержимым не будут равны.
    По сути, hashCode() и equals() просто не знают как правильно обрабатывать неизвестный
    объект и обрабатывает самым простым способом - заложенным в Object.
    По этой причине в HashMap сохраняется два объекта с "одинаковым" ключем, но при этом имеющими
    разные хеш-коды.
    {ru.job4j.map.User@129a8472=10, ru.job4j.map.User@d041cf=2}
     */

}