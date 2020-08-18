package ru.job4j.io;

import java.io.*;
import java.util.Objects;

public class Analizy {
    String path;

    Analizy() {
    }

    public void unavailable(String source, String target) {
        String pathSource = Analizy.class.getResource("").getPath() + source;
        String pathTarget = Analizy.class.getResource("").getPath()  + target;

        StringBuilder data = new StringBuilder();

        try (BufferedReader in = new BufferedReader(new FileReader(pathSource))) {
            String lines;
            String[] tokens;
            boolean onLine = false;
            while ((lines = in.readLine()) != null) {
                tokens = lines.split("\\ ");
                if (
                        (tokens[0].equals("500")
                                || tokens[0].equals("400")
                        )
                        && !onLine) {
                    data.append(tokens[1]);
                    data.append(";");
                    onLine = true;
                }

                if (
                        (tokens[0].equals("200")
                                || tokens[0].equals("300")
                        )
                                && onLine) {
                    data.append(tokens[1]);
                    data.append(System.lineSeparator());
                    onLine = false;
                }
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
}
