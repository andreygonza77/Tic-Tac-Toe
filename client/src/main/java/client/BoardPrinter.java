package client;

public class BoardPrinter {
    public static void printBoard(String boardMsg) {
        String[] cells = boardMsg.split(",");
        System.out.println("--");
        for (int i = 0; i < 9; i++) {
            String mark = switch (cells[i]) {
                case "1" -> "X";
                case "2" -> "O";
                default -> " ";
            };
            System.out.print("| " + mark + " ");
            if (i % 3 == 2) System.out.println("|\n");
        }
    }
}

