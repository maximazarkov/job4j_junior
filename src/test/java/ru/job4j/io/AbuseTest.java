package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.assertj.core.api.Assertions.*;

public class AbuseTest {

    @Test
    public void drop(@TempDir Path tempDir) throws IOException {
        Path source = tempDir.resolve("source.txt");
        Path target = tempDir.resolve("target.txt");
        try (PrintWriter out = new PrintWriter(source.toFile())) {
            out.println("hello foolish dude ");
        }
        Abuse.drop(source.toString(), target.toString(), List.of("foolish"));
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target.toFile()))) {
            in.lines()
                    .forEach(rsl::append);
        }
        assertThat(rsl.toString()).isEqualTo("hello dude ");
    }
}