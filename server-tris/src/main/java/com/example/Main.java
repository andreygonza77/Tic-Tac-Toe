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
                    
                    // check se ha vinto o no; da fare
                    if(board[mossa] == 1 || board[mossa] == 2 || mossa < 0 || mossa > 8){
                        statoPartita = KO;
                        out1.println(statoPartita);
                        break;
                    }

                    statoPartita = OK;
                    board[mossa] = 1;
                    if(statoPartita == OK){
                        if(checkWin(board, turno)){
                            statoPartita = W;
                            out1.println(statoPartita);
                            statoPartita = DISCONNECTED;
                        }
                        else if(checkDraw(board, turno)){
                            statoPartita = P;
                            out1.println(statoPartita);
                            statoPartita = DISCONNECTED;
                        }
                    }
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
                mossa = Integer.parseInt(msg);
                    
                    // check se ha vinto o no; da fare
                    if(board[mossa] == 1 || board[mossa] == 2 || mossa < 0 || mossa > 8){
                        statoPartita = KO;
                        out2.println(statoPartita);
                        break;
                    }

                    statoPartita = OK;
                    board[mossa] = 2;
                    if(statoPartita == OK){
                        if(checkWin(board, turno)){
                            statoPartita = W;
                            out2.println(statoPartita);
                            statoPartita = DISCONNECTED;
                        }
                        else if(checkDraw(board, turno)){
                            statoPartita = P;
                            out2.println(statoPartita);
                            statoPartita = DISCONNECTED;
                        }
                    }
                    out2.println(statoPartita);
                    String campo = boardContent(board);
                    out2.println(campo);
                    turno = 1; // sposta il turno al giocatroe 2
            }
        } while (statoPartita == DISCONNECTED);
    }

    public static boolean checkWin(int board[], int turno){
        boolean b = false;
        if(board[0] == turno && board[1] == turno && board[2] == turno){
            b = true;
        } else if(board[3] == turno && board[4] == turno && board[5] == turno){
            b = true;
        }
        else if(board[6] == turno && board[7] == turno && board[8] == turno){
            b = true;
        }
        else if(board[0] == turno && board[3] == turno && board[6] == turno){
            b = true;
        }
        else if(board[1] == turno && board[4] == turno && board[7] == turno){
            b = true;
        }
        else if(board[2] == turno && board[5] == turno && board[8] == turno){
            b = true;
        }
        else if(board[0] == turno && board[4] == turno && board[8] == turno){
            b = true;
        } else if(board[2] == turno && board[4] == turno && board[6] == turno){
            b = true;
        }
        return b;
    }

    public static boolean checkDraw(int board[], int turno){
        for(int i = 0; i<9; i++){
            if(board[i] == 0){
                return false;
            }
        }
        return true;
    }
    public static String boardContent(int board[]){
        String s = "";
        for(int i = 0; i < board.length; i++){
            s+=board[i] + ",";
        }
        return s;
    }
}