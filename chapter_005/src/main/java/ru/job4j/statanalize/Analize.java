package ru.job4j.statanalize;

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

            if (preEntry == null) {
                if (preIterator.hasNext()) {
                    preEntry = preIterator.next();
                }
            }
            if (curEntry == null) {
                if (curIterator.hasNext()) {
                    curEntry = curIterator.next();
                }
            }

            if (preEntry == null) {
                do {
                    info.added++;
                    curEntry = curIterator.hasNext() ? curIterator.next() : null;
                } while (curEntry != null);
                break;
            }

            if (curEntry == null) {
                do {
                    info.deleted++;
                    preEntry = preIterator.hasNext() ? preIterator.next() : null;
                } while (preEntry != null);
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

//    private HashMap.Entry<Integer, String> setPreEntry(HashMap it) {
//        if (preEntry == null) {
//            if (preIterator.hasNext()) {
//                preEntry = preIterator.next();
//            }
//        }
//        if (curEntry == null) {
//            if (curIterator.hasNext()) {
//                curEntry = curIterator.next();
//            }
//        }
//
//    }

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
