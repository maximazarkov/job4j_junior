package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;

public class UsageEncoding {
    public String readFile(String path) throws FileNotFoundException {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            int data;
            while ((data = br.read()) > 0) {
                builder.append((char) data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public void writeDateInFile(String path, String data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            bw.write(data + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
