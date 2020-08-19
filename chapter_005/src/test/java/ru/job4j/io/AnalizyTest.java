package ru.job4j.io;

import org.junit.Test;

import java.io.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class AnalizyTest {

    @Test
    public void whenTwoDoneDiapsones() {
        String path = (Analizy.class.getResource("").getPath());

        try (PrintWriter out = new PrintWriter(new FileOutputStream(path + "server.log"))) {
            out.println("200 10:56:01");
            out.println("500 10:57:01"); //onLine
            out.println("400 10:58:01");
            out.println("200 10:59:01"); //offLine
            out.println("500 11:01:02"); //onLine
            out.println("200 11:02:02"); //offLine
        } catch (Exception e) {
            e.printStackTrace();
        }

        Analizy an = new Analizy();
        an.unavailable("server.log", "unavailable.csv");

        try (
                BufferedReader in =
                        new BufferedReader(
                                new FileReader(path + "unavailable.csv"))) {
            String line;
            String lastLine = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                lastLine = line.equals("") ? lastLine : line;
            }
            assertThat(lastLine, is("11:01:02;11:02:02"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenSecondDiapsonesIsOnLine() {
        String path = (Analizy.class.getResource("").getPath());

        try (PrintWriter out = new PrintWriter(new FileOutputStream(path + "server.log"))) {
            out.println("200 10:56:01");
            out.println("500 10:57:01"); //onLine
            out.println("400 10:58:01");
            out.println("200 10:59:01"); //offLine
            out.println("500 11:01:02"); //onLine
        } catch (Exception e) {
            e.printStackTrace();
        }

        Analizy an = new Analizy();
        an.unavailable("server.log", "unavailable.csv");

        try (
                BufferedReader in =
                        new BufferedReader(
                                new FileReader(path + "unavailable.csv"))) {
            String line;
            String lastLine = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                lastLine = line.equals("") ? lastLine : line;
            }
            assertThat(lastLine, is("11:01:02;"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}