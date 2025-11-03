package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        final String WAIT = "WAIT";
        final String READY = "READY";
        final String OK = "OK";
        final String KO = "KO";
        final String W = "W";
        final String P = "P";
        final String L = "L";
        final String DISCONNECTED = "DISCONNECTED";

        int port = 3000;
        ServerSocket ss = new ServerSocket(port);
        System.out.println("Server TRIS in ascolto nella porta " + port);

        Socket g1 = ss.accept();
        BufferedReader in1 = new BufferedReader(new InputStreamReader(g1.getInputStream()));
        PrintWriter out1 = new PrintWriter(g1.getOutputStream(), true);
        out1.println(WAIT);

        Socket g2 = ss.accept();
        BufferedReader in2 = new BufferedReader(new InputStreamReader(g2.getInputStream()));
        PrintWriter out2 = new PrintWriter(g2.getOutputStream(), true);
        out1.println(READY);
        out2.println(READY);
        int turno = 0;
        do { 
            turno++;
            Socket s = turno == 1 ? g1 : g2;
            MyThreadServer t = new MyThreadServer(s);
            t.start();
        } while (true);
    }
}