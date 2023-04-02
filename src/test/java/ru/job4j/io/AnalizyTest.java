package ru.job4j.io;

import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.io.*;
import java.nio.file.Path;

public class AnalizyTest {

    @Test
    public void whenTwoDoneDiapsones(@TempDir Path tempDir) {
        Path server = tempDir.resolve("server.log");
        Path unavailable = tempDir.resolve("unavailable.csv");
        try (PrintWriter out = new PrintWriter(new FileOutputStream(server.toFile()))) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Analizy an = new Analizy();
        an.unavailable(server.toString(), unavailable.toString());

        try (
                BufferedReader in =
                        new BufferedReader(
                                new FileReader(unavailable.toFile()))) {
            String line;
            String lastLine = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                lastLine = line.equals("") ? lastLine : line;
            }
            assertThat(lastLine).isEqualTo("11:01:02;11:02:02");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**        String path = (Analizy.class.getResource("").getPath());
     */
    @Test
    public void whenSecondDiapsonesIsOnLine(@TempDir Path tempDir) {
        Path server = tempDir.resolve("server.log");
        Path unavailable = tempDir.resolve("unavailable.csv");
        try (PrintWriter out = new PrintWriter(new FileOutputStream(server.toFile()))) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:02");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Analizy an = new Analizy();
        an.unavailable(server.toString(), unavailable.toString());

        try (
                BufferedReader in =
                        new BufferedReader(
                                new FileReader(unavailable.toFile()))) {
            String line;
            String lastLine = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                lastLine = line.equals("") ? lastLine : line;
            }
            assertThat(lastLine).isEqualTo("11:01:02;");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}