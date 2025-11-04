package client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 12345;

        try (Socket socket = new Socket(host, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connesso al server Tic Tac Toe");

            String message;
            while ((message = in.readLine()) != null) {
                if (message.equals("WAIT")) {
                    System.out.println("In attesa di un altro giocatore...");
                } else if (message.equals("READY")) {
                    System.out.println("Partita pronta! Attendi il tuo turno...");
                } else if (message.equals("OK")) {
                    System.out.println("Mossa accettata. Attendi l’avversario...");
                } else if (message.equals("KO")) {
                    System.out.println("Mossa non valida. Riprova:");
                    System.out.print("Tua mossa (0–8): ");
                    out.println(scanner.nextLine());
                } else if (message.equals("W")) {
                    System.out.println("Hai vinto!");
                    break;
                } else if (message.equals("P")) {
                    System.out.println("Pareggio!");
                    break;
                } else if (message.equals("DISCONNECTED")) {
                    System.out.println("L’altro giocatore si è disconnesso.");
                    break;
                } else if (message.contains(",")) {
                    BoardPrinter.printBoard(message);
                    if (message.endsWith("L")) {
                        System.out.println("Hai perso!");
                        break;
                    } else if (message.endsWith("P")) {
                        System.out.println("Pareggio!");
                        break;
                    } else {
                        System.out.print("Tuo turno (0–8): ");
                        out.println(scanner.nextLine());
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("Errore: " + e.getMessage());
        }
    }
}
