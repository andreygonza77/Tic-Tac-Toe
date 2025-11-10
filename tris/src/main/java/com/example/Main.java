package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int port = 3000;
        Socket s = new Socket(host, port);

        final String WAIT = "WAIT";
        final String READY = "READY";
        final String OK = "OK";
        final String KO = "KO";
        final String W = "W";
        final String P = "P";
        final String L = "L";
        final String DISCONNECTED = "DISCONNECTED";

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            Scanner input = new Scanner(System.in);

            boolean giocatore1 = false;
            boolean statoPartita = false;
            do{
                String msg = in.readLine();
                switch(msg){
                    case WAIT:
                        System.out.println("In attesa di incontrare un avversario...");
                        statoPartita = true;
                        giocatore1 = true;
                        break;
                    case READY:
                        System.out.println("Avversario trovato!");
                        statoPartita = true;
                        if(giocatore1){
                            System.out.println("Tocca a te! Inserisci una mossa (0-8): ");
                            String mossa = input.nextLine();
                            out.println(mossa);
                            break;
                        }
                        else{
                            System.out.println("In attesa della mossa dell'avversario...");
                            break;
                        }
                        
                    case OK:
                        System.out.println("Mossa valida");
                        break;
                    case KO:
                        System.out.println("Mossa non valida. Fai nuovamente una mossa");
                        String mossa = input.nextLine();
                        out.println(mossa);
                        break;
                    case W:
                        System.out.println("Hai vinto! Partita conclusa");
                        statoPartita = false;
                        break;
                    case P:
                        System.out.println("Hai pareggiato. Partita conclusa");
                        statoPartita = false;
                        break;
                    case DISCONNECTED:
                        System.out.println("Avversario disconnesso. Partita conclusa");
                        statoPartita = false;
                        break;
                    default:
                        if(msg.contains(L)){
                            System.out.println("Hai perso! Partita conclusa");
                            statoPartita = false;
                            break;
                        }
                        else if (msg.contains(",")) {
                            System.out.println("Stato attuale del campo: " + msg);
                            System.out.println("Tocca a te! Inserisci una mossa (0-8): ");
                            String next = input.nextLine();
                            out.println(next);
                        }
                        else System.out.println("Messaggio sconosciuto: " + msg);

                        break;
                }
            } 
            while(statoPartita);
        }
        catch (Exception e) {
            e.getStackTrace();
        }
    
    }
}