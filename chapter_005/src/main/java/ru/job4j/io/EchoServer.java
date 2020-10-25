package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

public class EchoServer {

    private static final String EXIT = "Good bye!";
    private static final String HELLO = "Hello, dear friend.";
    private boolean notExit = true;
    private String answer = "";
    private static final Charset UTF_8 = StandardCharsets.UTF_8;
    private static final Charset ASCII = StandardCharsets.US_ASCII;

    private void checkMsg(String str) {
        boolean b = str.contains("msg=Exit");
        if (b) {
            notExit = false;
            answer = EXIT;
        }
        b = str.contains("msg=Hello");
        if (b) {
            answer = HELLO;
        } else {
            answer = str;
        }
    }

    private void run() throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (notExit) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str;
                    while (!(str = in.readLine()).isEmpty()) {

                        System.out.println(str);

                        checkMsg(str);

                    }
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    out.write("==========".getBytes());
                    out.write(System.lineSeparator().getBytes());
                    out.write(answer.getBytes(UTF_8));
//                    out.write(answer.getBytes(ASCII));
                    out.write(System.lineSeparator().getBytes());
                    out.write("==========".getBytes());

                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        EchoServer es = new EchoServer();
        es.run();
    }

}
