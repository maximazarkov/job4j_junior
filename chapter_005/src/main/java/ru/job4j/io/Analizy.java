package ru.job4j.io;

import java.io.*;
import java.util.Objects;

public class Analizy {
    String path;

    Analizy() {
        this.path = Objects.requireNonNull(System.class.getClassLoader()
                .getResource("")).getPath(); // NPE
    }

    public void unavailable(String source, String target) {
//        String pathSource = Objects.requireNonNull(System.class.getClassLoader().getResource(source)).getFile(); // NPE
//        String pathSource = Analizy.class.getResource(source).getFile();  // NPE
        String pathSource = this.path + source;
        String pathTarget = Analizy.class.getResource("").getPath()  + target;

        StringBuilder data = new StringBuilder();

        try (BufferedReader in = new BufferedReader(new FileReader(pathSource))) {
            String lines;
            while ((lines = in.readLine()) != null) {
                data.append(lines);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (PrintWriter out = new PrintWriter(new FileOutputStream(pathTarget))) {
            out.println(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
//        // Следующая конструкция выдает NPE в методе getResources() пока по непонятным причинам.
//        String path = System.class.getClassLoader().getResource(File.separator).getPath();
        String path = (Analizy.class.getResource("").getPath());
        try (PrintWriter out = new PrintWriter(new FileOutputStream(path + "unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Analizy an = new Analizy();
        an.unavailable("server.log", "unavailable.csv");
    }
}
