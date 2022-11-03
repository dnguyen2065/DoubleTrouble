import java.util.ArrayList;

public class Board {
    String boardMaterial1;
    String boardMaterial2;
    String boardMaterial3;

    Board(String boardMaterial1, String boardMaterial2, String boardMaterial3) {
        this.boardMaterial1 = boardMaterial1;
        this.boardMaterial2 = boardMaterial2;
        this.boardMaterial3 = boardMaterial3;

    };

    public ArrayList<String> createBoard() {
        String p1 = boardMaterial1;
        String p2 = boardMaterial2;
        String p3 = boardMaterial3;
        ArrayList<String> board = new ArrayList<String>();
        int rows = 5;
        for (int i = 1; i <= rows; i++) {
            for (int j = rows; j >= i; j--) {
                // System.out.print(" ");
                board.add(" ");
            }
            for (int j = 1; j <= i; j++) {
                if (i >= 0 && i < 3) {
                    // System.out.print(p1 + " ");
                    board.add(p1 + " ");
                } else if (i >= 3 && i < 5) {
                    // System.out.print(p2 + " ");
                    board.add(p2 + " ");

                } else {
                    // System.out.print(p3 + " ");
                    board.add(p3 + " ");

                }

            }

            // System.out.println();
            board.add("\n");
        }
        return board;
    }

    public ArrayList<String> printBoard(String type, int amount, ArrayList<String> board) {
        // ArrayList<String> board = createBoard();
        int counter = 0;

        for (int i = 0; i < board.size(); i++) {
            if (counter < amount && board.get(i).equals(type + " ")) {
                counter++;
                board.set(i, "x ");
            }
        }
        for (String s : board) {
            System.out.print(s);
        }
        return board;
    }

    public void firstBoard() {
        String p1 = boardMaterial1;
        String p2 = boardMaterial2;
        String p3 = boardMaterial3;
        int rows = 5;
        for (int i = 1; i <= rows; i++) {
            for (int j = rows; j >= i; j--) {
                System.out.print(" ");
            }
            for (int j = 1; j <= i; j++) {
                if (i >= 0 && i < 3) {
                    System.out.print(p1 + " ");
                } else if (i >= 3 && i < 5) {
                    System.out.print(p2 + " ");
                } else {
                    System.out.print(p3 + " ");
                }

            }
            System.out.println();

        }
    }
}
