package ru.job4j.io;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class AnalizyTest {

    @Test
    public void unavailable() {
        try (BufferedReader in = new BufferedReader(new FileReader("unavailable.csv"))) {
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
//            in.println("15:01:30;15:02:32");
//            in.println("15:10:30;23:12:32");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}