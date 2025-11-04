package server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    
    private final List<Integer> cells = new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0,0,0));

    public boolean isValidMove(int move) {
        return move >= 0 && move < 9 && cells.get(move) == 0;
    }

    public void makeMove(int move, int player) {
        cells.set(move, player);
    }

    public boolean hasWon(int player) {
        int[][] wins = {
            {0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}
        };
        for (int[] w : wins) {
            if (cells.get(w[0]) == player && cells.get(w[1]) == player && cells.get(w[2]) == player)
                return true;
        }
        return false;
    }

    public boolean isDraw() {
        return !cells.contains(0);
    }

    public String serialize(String outcome) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            sb.append(cells.get(i)).append(",");
        }
        sb.append(outcome);
        return sb.toString();
    }
}

