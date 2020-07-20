package ru.job4j.statanalize;

import java.sql.PreparedStatement;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Analize {
    /**
     * Метод возвращать статистику об изменении коллекции с временем обработки O(n).
     * Выводится отчет о том:
     * Сколько добавлено новых пользователей.
     * Сколько изменено пользователей. Изменённым считается объект в котором изменилось имя. а id осталось прежним.
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

        Map<Integer, String> previousMap =
                previous.stream().collect(Collectors.toMap(User::getId,  User::getName));
        Iterator<Map.Entry<Integer, String>> preIterator = previousMap.entrySet().iterator();
        HashMap.Entry<Integer, String> preEntry = null;

        Map<Integer, String> currentMap =
                current.stream().collect(Collectors.toMap(User::getId,  User::getName));
        Iterator<Map.Entry<Integer, String>> curIterator = currentMap.entrySet().iterator();
        HashMap.Entry<Integer, String> curEntry = null;

        do {
            System.out.println(info.added + " " + info.deleted + " " + info.changed);

            preEntry = setEntry(preEntry, preIterator);
            curEntry = setEntry(curEntry, curIterator);

            int tail = checkingTail(preEntry, curEntry, curIterator);
            if (tail > 0) {
                info.added += tail;
                break;
            }

            tail = checkingTail(curEntry, preEntry, preIterator);
            if (tail > 0) {
                info.deleted += tail;
                break;
            }

            if (preEntry.getKey() - curEntry.getKey() > 0) {
                info.added++;
                curEntry = null;
                continue;
            }

            if (preEntry.getKey() - curEntry.getKey() < 0) {
                info.deleted++;
                preEntry = null;
                continue;
            }

            if (preEntry.getKey() - (curEntry.getKey()) == 0) {
                if (!preEntry.getValue().equals(curEntry.getValue())) {
                    info.changed++;
                }
                curEntry = null;
                preEntry = null;
            }

        } while ((preIterator.hasNext())
                || (curIterator.hasNext())
                || curEntry != null
                || preEntry != null);

        return info;
    }

    private int checkingTail(HashMap.Entry<Integer, String> entryNull,
                             HashMap.Entry<Integer, String> entryTail,
                             Iterator<Map.Entry<Integer, String>> it) {
        int count = 0;
        HashMap.Entry<Integer, String> tail = entryTail;
        if (entryNull == null) {
            do {
                count++;
                tail = it.hasNext() ? it.next() : null;
            } while (tail != null);
        }
        return count;
    }

    private HashMap.Entry<Integer, String> setEntry(HashMap.Entry<Integer, String> entry,
                                                    Iterator<Map.Entry<Integer, String>> it) {
        HashMap.Entry<Integer, String> result = entry;
        if (entry == null) {
            if (it.hasNext()) {
                result = it.next();
            }
        }
        return result;
    }

    public static class User {
        int id;
        String name;

        User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        private int getId() {
            return id;
        }

        private String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return id == user.id && Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

    public static class Info {
        int added;
        int changed;
        int deleted;

    }
}
