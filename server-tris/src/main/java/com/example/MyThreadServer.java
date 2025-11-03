package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MyThreadServer extends Thread{
    private Socket s;
    public MyThreadServer(Socket s){
        this.s = s;
    }

    final String WAIT = "WAIT";
    final String READY = "READY";
    final String OK = "OK";
    final String KO = "KO";
    final String W = "W";
    final String P = "P";
    final String L = "L";
    final String DISCONNECTED = "DISCONNECTED";

    public void run(){
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);

            out.println("Welcome: Tic-Tac-Toe");
            do{
                
            }
            while(true);
        }
        catch(Exception e){
            e.getStackTrace();
        }
    }
}
