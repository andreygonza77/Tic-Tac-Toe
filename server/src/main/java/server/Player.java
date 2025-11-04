package server;

import java.io.*;
import java.net.Socket;

public class Player{

    private final Socket socket;
    private final int id;
    private final BufferedReader in;
    private final PrintWriter out;

    public Player(Socket socket, int id) throws IOException {
        this.socket = socket;
        this.id = id;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
    }

    public int getId() {
        return id;
    }

    public String readMove() throws IOException {
        return in.readLine();
    }

    public void sendMessage(String msg) {
        out.println(msg);
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException ignored) {}
    }
}

