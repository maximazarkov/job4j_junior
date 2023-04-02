package ru.job4j.io;

import java.io.*;
import java.util.stream.IntStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new FileReader("even.txt"))) {
            String read;
            while ((read = in.readLine()) != null) {
                if ((Integer.parseInt(read) % 2) == 0) {
                    System.out.println("Число: " + read + " четное.");
                } else {
                    System.out.println("Число: " + read + " нечетное.");
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
