package ru.job4j.io;

import java.util.Arrays;
import java.util.regex.Pattern;

public class ArgZip {

    private final String[] args;
    private String directory;
    private String exclude;
    private String output;

    public ArgZip(String[] args) {
        this.args = args;
        System.out.println(Arrays.toString(args));
    }

    private boolean checkBadParam(String regex, String key, String message) {
        boolean b = Pattern.matches(regex, key);
        if (!b) {
            System.out.println(message);
            return true;
        }
        return false;
    }

    public boolean valid() {
// необходимо проверить правильность формата параметров
// 1. проверить колличество аргументов (для начала, после проверки ключей это будет необязательно)
// 2. Проверка ключа -d - directory - которую мы хотим архивировать
// 3. Проверка ключа -e - exclude - исключить файлы *.xml
// 4. Проверка ключа -o - output - во что мы архивируем.
        int d = 0;
        int e = 0;
        int o = 0;

        if (args.length < 3) {
            return false;
        }
        for (String arg : args) {
            Pattern pattern = Pattern.compile("=");
            String[] key = pattern.split(arg, 2);
            if (key[0].equals("-d")) {
                d++;
                if (checkBadParam("^(([c-zC-Z]+):)|\\.(\\\\[a-zA-Z0-9_]*)*$", key[1], "Ошибка в описании пути дериктории")) {
                    return false;
                }
                directory = key[1];
            }
            if (key[0].equals("-e")) {
                e++;
                if (checkBadParam("^([a-zA-Z0-9]+)$", key[1], "Ошибка в описании пути дериктории проекта для архивации")) {
                    return false;
                }
                exclude = key[1];
            }
            if (key[0].equals("-o")) {
                o++;
                if (checkBadParam("([a-zA-Z]+)(\\.zip){1}$", key[1], "Ошибка в описании имени архива")) {
                    return false;

                }
                output = key[1];
            }
        }
        System.out.println(d);
        System.out.println(e);
        System.out.println(o);
        if ((d != 1) || (e != 1) || (o != 1)) {
            return false;
        }
        return true;
    }

    public String directory() {

        return directory;
    }

    public String exclude() {
        return exclude;
    }

    public String output() {
        return output;
    }
}