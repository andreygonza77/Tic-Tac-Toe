package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class MyThread extends Thread {
    private Socket g;
    public MyThread(Socket g){
        this.g = g;
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
            BufferedReader in = new BufferedReader(new InputStreamReader(g.getInputStream()));
            PrintWriter out = new PrintWriter(g.getOutputStream(), true);
            Scanner input = new Scanner(System.in);
            
            boolean g1 = false;
            boolean partitaFinita = false;
            do { 
                String msg = in.readLine();
                if(msg == null) break;

                msg = msg.trim();
                out.println(msg);

                switch (msg) {
                    case WAIT:
                        g1 = true;
                        out.println(WAIT + ", sei G1");
                        break;
                    case READY:
                        out.println(READY + ", G2 connesso");
                        if(g1){
                            out.println("Tocca a te [0-8]");
                            int mossa = input.nextInt();
                            out.println("Mossa: " + mossa);
                        }
                        else{
                            out.println("Attesa mossa G1");
                        }
                        break;
                    case OK:
                        out.println(OK + ", mossa accettata");
                        break;
                    case KO:
                        out.println(KO + ", mossa NON accettata");
                        out.println("Tocca a te [0-8]");
                        int mossa = input.nextInt();
                        out.println("Mossa: " + mossa);
                        break;
                    case W:
                        out.println("Hai vinto!");
                        partitaFinita = true;
                        break;
                    case P:
                        out.println("Pareggio!");
                        partitaFinita = true;
                        break;
                    case L:
                        out.println("Hai perso!");
                        partitaFinita = true;
                        break;
                    case DISCONNECTED:
                        out.println("Disconessione: partita finita");
                        partitaFinita = true;
                        break;
                    default:
                        out.println("Errore: msg non valido");
                        break;
                }
            } 
            while (!partitaFinita);
            g.close();
        }

        catch(Exception e){
            e.getStackTrace();
        }
    }
}
