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

        String statoPartita = "";

        int port = 3000;
        ServerSocket ss = new ServerSocket(port);
        System.out.println("Server TRIS in ascolto nella porta " + port);

        Socket g1 = ss.accept();
        statoPartita = WAIT;

        BufferedReader in1 = new BufferedReader(new InputStreamReader(g1.getInputStream()));
        PrintWriter out1 = new PrintWriter(g1.getOutputStream(), true);
        out1.println(statoPartita);

        Socket g2 = ss.accept();
        statoPartita = READY;
        BufferedReader in2 = new BufferedReader(new InputStreamReader(g2.getInputStream()));
        PrintWriter out2 = new PrintWriter(g2.getOutputStream(), true);

        int board[] = {0, 0, 0, 0, 0, 0, 0, 0, 0}; 
        int turno = 1; // giocatore 1
        String info = "";
        String msg = "";
        int mossa = 0;

        out1.println(statoPartita);
        out2.println(statoPartita);

        do { 
            if(turno == 1){
                out1.println("Tocca a te: ");
                msg = in1.readLine();
                try{
                    mossa = Integer.parseInt(msg);
                    statoPartita = OK;
                    // check se ha vinto o no; da fare
                    board[mossa] = 1;
                    out1.println(statoPartita);
                    String campo = boardContent(board);
                    out1.println(campo);
                    turno = 2; // sposta il turno al giocatroe 2
                }
                catch(Exception e){
                    statoPartita = KO;
                    out1.println(statoPartita);
                }
            }
            if(statoPartita.equals(OK)){

            }
            if(turno == 2){
                out2.println("Tocca a te: ");
                msg = in2.readLine();
                try{
                    mossa = Integer.parseInt(msg);
                    statoPartita = OK;
                    // check se ha vinto o no; da fare
                    board[mossa] = 2;
                    out2.println(statoPartita);
                    String campo = boardContent(board);
                    out2.println(campo);
                    turno = 1; // sposta il turno al giocatroe 1
                }
                catch(Exception e){
                    statoPartita = KO;
                    out2.println(KO);
                }
            }
        } while (true);
        
    }

    public static boolean checkWin(int board[], int turno){

        return false;
    }

    public static String boardContent(int board[]){
        String s = "";
        for(int i = 0; i < board.length; i++){
            s+=board[i];
            s.split(",");
        }
        return s;
    }
}