package com.example;

import java.io.IOException;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int port = 3000;
        Socket s = new Socket(host, port);
        try {
            MyThread client = new MyThread(s);
            client.start();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}