package ru.job4j.statanalize;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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

        Map<Integer, String> prev =
                previous.stream().collect(Collectors.toMap(User::getId,  User::getName));
//        Set<Integer> preKeys = prev.keySet();
        Iterator pre = prev.keySet().iterator();

        Map<Integer, String> curr =
                current.stream().collect(Collectors.toMap(User::getId,  User::getName));
//        Set<Integer> curKeys = curr.keySet();
        Iterator cur = curr.keySet().iterator();

        int preKey = 0;
        int curKey = 0;

//        while ((preCount < prev.size()) || (curCount < curr.size())) {
        while ((pre.hasNext()) || (cur.hasNext())) {

            if (!pre.hasNext()) {
                while (cur.hasNext()) {
                    info.added++;
                    cur.next();
                }
                break;
            }

            if (!cur.hasNext()) {
                while (pre.hasNext()) {
                    info.deleted++;
                    pre.next();
                }
                break;
            }



            if ((prev.get(pre.next())).compareTo(curr.get(cur.next())) > 0) {
                info.added++;
                curCount++;
                continue;
            }

            if (previous.get(pre).id < current.get(cur).id) {
                info.deleted++;
                pre++;
                continue;
            }

            if (previous.get(pre).id == current.get(cur).id) {
                if (!previous.get(pre).name.equals(current.get(cur).name)) {
                    info.changed++;
                }
            }
            pre++;
            cur++;
        }

        return info;

    }

    Comparator<User> comp = (o1, o2) -> o1.id - o2.id;

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
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return id == user.id &&
                    Objects.equals(name, user.name);
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

        public int getAdded() {
            return added;
        }

        public int getChanged() {
            return changed;
        }

        public int getDeleted() {
            return deleted;
        }
    }
}
