import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class Play {
    Board board = new Board("*", "#", "0");

    public ArrayList<String> player(ArrayList<String> boardInput) {

        ArrayList<String> moveData = new ArrayList<String>();

        Scanner playerMove = new Scanner(System.in);

        System.out.print("Piece Type: ");

        String row = playerMove.nextLine();

        if (!row.equals(board.boardMaterial1) && !row.equals(board.boardMaterial2)
                && !row.equals(board.boardMaterial3)) {
            System.out.println(
                    "You chose " + row + ". This piece does not exist! Please choose any of the following!" + " "
                            + board.boardMaterial1 + ", " + board.boardMaterial2 + ", " + board.boardMaterial3);
            player(boardInput);
        } else {
            moveData.add(row);
        }

        System.out.print("Amount: ");
        String element = playerMove.nextLine();
        if (Integer.parseInt(element) > Collections.frequency(boardInput, "* ")
                || Integer.parseInt(element) > Collections.frequency(boardInput, "# ")
                || Integer.parseInt(element) > Collections.frequency(boardInput, "0 ")) {
            System.out.println(
                    "You entered an invalid value! Please only choose as many as are left of a given Piece Type!");
            player(boardInput);
        } else {
            moveData.add(element);
        }
        return moveData;
    }

    public void win(ArrayList<String> winningBoard) {
        int counter = 0;
        for (String s : winningBoard) {
            if (s.equals(" ") || s.equals("x ") || s.equals("\n")) {
                counter++;
            }
        }
        if (counter == winningBoard.size()) {
            System.out.println("You Win!");
            System.exit(0);
        }

    }
}
