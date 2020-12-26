package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    private static final String EXIT = "Good bye!";
    private static final String HELLO = "Hello, dear friend.";
    private boolean notExit = true;
    private String answer = "";

    private void checkMsg(String str) {
        if (str.contains("msg=Exit")) {
            notExit = false;
            answer = EXIT;
        } else if (str.contains("msg=Hello")) {
            answer = HELLO;
        } else {
            answer = str.substring(10, (str.length() - 9));
        }
    }

    private void run() throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (notExit) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str  = in.readLine();
                    checkMsg(str);
                    while (!(str = in.readLine()).isEmpty()) {
                        System.out.println(str);
                    }
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    out.write("==========".getBytes());
                    out.write(System.lineSeparator().getBytes());
                    out.write(answer.getBytes());
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
