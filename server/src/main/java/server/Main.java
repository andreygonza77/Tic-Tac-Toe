package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    private static final int PORT = 12345;

    public static void main(String[] args) {
        System.out.println("Server Tic Tac Toe in ascolto sulla porta " + PORT);
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            Socket player1 = serverSocket.accept();
            System.out.println("Giocatore 1 connesso.");
            Player p1 = new Player(player1, 1);

            p1.sendMessage("WAIT");

            Socket player2 = serverSocket.accept();
            System.out.println("Giocatore 2 connesso.");
            Player p2 = new Player(player2, 2);

            p1.sendMessage("READY");
            p2.sendMessage("READY");

            new Game(p1, p2).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}