package ru.job4j.statanalize;

import java.util.*;

public class Analize {
    /**
     * Метод возвращать статистику об изменении коллекции с временем обработки O(n).
     * Выводится отчет о том:
     * Сколько добавлено новых пользователей.
     * Сколько изменено пользователей. Изменённым считается объект в котором изменилось имя, а id осталось прежним.
     * Сколько удалено пользователей.
     * @param previous - начальные данные,
     * @param current - измененные данные.
     * @return
     */
    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info();

        if ((current.isEmpty()) && previous.isEmpty()) {
            return info;
        }

        Map<Integer, String> map = new HashMap<>();
        for (User user : previous) {
            map.put(user.id, user.name);
        }

        for (User user : current) {
            boolean checkKey = map.containsKey(user.id);
            if (checkKey) {
                if (!user.name.equals(map.get(user.id))) {
                    info.changed++;
                }
            } else {
                info.added++;
            }
        }
        info.deleted = previous.size() + info.added - current.size();

        return info;
    }

    public static class User {
        int id;
        String name;

        User(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public static class Info {
        int added;
        int changed;
        int deleted;

    }
}
