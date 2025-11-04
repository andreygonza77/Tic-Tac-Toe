package server;

import java.io.IOException;

public class Game extends Thread {
    private final Player p1;
    private final Player p2;
    private final Board board = new Board();
    private boolean running = true;

    public Game(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    @Override
    public void run() {
        Player current = p1;
        Player other = p2;

        while (running) {
            try {
                String moveStr = current.readMove();
                if (moveStr == null) {
                    other.sendMessage("DISCONNECTED");
                    break;
                }

                int move;
                try {
                    move = Integer.parseInt(moveStr);
                } catch (NumberFormatException e) {
                    current.sendMessage("KO");
                    continue;
                }

                if (!board.isValidMove(move)) {
                    current.sendMessage("KO");
                    continue;
                }

                board.makeMove(move, current.getId());

                if (board.hasWon(current.getId())) {
                    current.sendMessage("W");
                    other.sendMessage(board.serialize("L"));
                    running = false;
                } else if (board.isDraw()) {
                    current.sendMessage("P");
                    other.sendMessage(board.serialize("P"));
                    running = false;
                } else {
                    current.sendMessage("OK");
                    other.sendMessage(board.serialize(""));
                    //Cambio turno
                    Player tmp = current;
                    current = other;
                    other = tmp;
                }

            } catch (IOException e) {
                other.sendMessage("DISCONNECTED");
                break;
            }
        }
        p1.close();
        p2.close();
    }
}

