package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(String path) {
        /** Механизм получения ссылки на файл через рефлексию. В переменной path, полученной
         * как параметр конструктора, получаем имя файла, который лежит в папке src/main/resources
         * после компиляции файл появится в папке target, соответственно будет выполняться и через
         * psvm и через тесты, и через тревис
         */
        this.path = Objects.requireNonNull(Config.class.getClassLoader().getResource(path)).getFile();
    }

    public void load() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            String lines;
            String[] tokens;
            while ((lines = read.readLine()) != null) {
                tokens = lines.split("\\=");

                /** проигнорим битые строки:
                 * пустые строки length() == 0
                 * комментарии '#'
                 * строки, не содержащие знака "="
                 */
                if ((tokens[0].length() == 0)
                        || (tokens[0].charAt(0) == '#')
                        || (tokens.length < 2)) {
                    continue;
                }
                values.put(tokens[0], tokens[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}
