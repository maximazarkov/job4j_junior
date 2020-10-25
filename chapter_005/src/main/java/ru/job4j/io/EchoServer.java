package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Pattern;

public class EchoServer {

    private boolean notExit = true;

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
                        boolean b = Pattern.matches("Bye", str);
                        if(!b) {
                            notExit = false;
                        }
                    }
                    out.write("HTTP/1.1 200 OK\r\n\\".getBytes());

                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        EchoServer es = new EchoServer();
        es.run();
    }

}
