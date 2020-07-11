package ru.job4j.statanalize;

import java.util.List;

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
        int pre = 0;
        int cur = 0;

        if ((current.isEmpty()) && previous.isEmpty()) {
            return info;
        }

        while ((pre < previous.size()) || (cur < current.size())) {

            if (pre == previous.size()) {
                info.added += current.size() - cur;
                break;
            }

            if (cur == current.size()) {
                info.deleted += previous.size() - pre;
                break;
            }

            if ((previous.get(pre).id > current.get(cur).id)) {
                info.added++;
                cur++;
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
