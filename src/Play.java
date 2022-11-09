import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.chrono.IsoEra;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Random;

import javax.swing.SingleSelectionModel;

public class Play {

    Board board = new Board("*", "#", "0");

    public boolean choosePlayer() throws IOException {

        boolean var = false;
        while (!var) {
            InputStreamReader isrchoice = new InputStreamReader(System.in);
            BufferedReader buf = new BufferedReader(isrchoice);
            System.out.print("To choose who goes first please enter 'm' for me or 'b' for bot: ");
            String choice = buf.readLine();

            if (choice.equals("m")) {
                var = true;
                return true;
            } else if (choice.equals("b")) {
                var = true;
                return false;
            } else {
                var = false;
            }
        }
        return true;
    }

    public ArrayList<String> makeRandMove(ArrayList<String> currboard) {
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> randin = new ArrayList<>();
        list.add(board.boardMaterial1);
        list.add(board.boardMaterial2);
        list.add(board.boardMaterial3);
        Random randChoice = new Random();
        String typeChoice = list.get(randChoice.nextInt(list.size()));
        randin.add(typeChoice);
        String numchoice = " ";

        if (typeChoice.equals(board.boardMaterial1)) {
            numchoice = String
                    .valueOf(randChoice.nextInt(Collections.frequency(currboard, board.boardMaterial1 + " ")) + 1);
            // randin.add(numchoice);
        } else if (typeChoice.equals(board.boardMaterial2)) {
            numchoice = String
                    .valueOf(randChoice.nextInt(Collections.frequency(currboard, board.boardMaterial2 + " ")) + 1);
            // randin.add(numchoice);
        } else if (typeChoice.equals(board.boardMaterial3)) {
            numchoice = String
                    .valueOf(randChoice.nextInt(Collections.frequency(currboard, board.boardMaterial3 + " ")) + 1);
            // randin.add(numchoice);
        }
        randin.add(numchoice);

        return randin;
    }

    public ArrayList<String> strategicMove(ArrayList<String> currBoard) {
        ArrayList<String> move = new ArrayList<>();
        String piece = " ";
        String amt = "1";
        int t1 = Collections.frequency(currBoard, board.boardMaterial1 + " ");
        int t2 = Collections.frequency(currBoard, board.boardMaterial2 + " ");
        int t3 = Collections.frequency(currBoard, board.boardMaterial3 + " ");

        if (t1 == 0) {
            if (t2 > t3) {
                amt = String.valueOf(t2 - t3);
                piece = board.boardMaterial2;
            }
            if (t3 > t2) {
                amt = String.valueOf(t3 - t2);
                piece = board.boardMaterial3;
            }

        } else if (t2 == 0) {
            if (t1 > t3) {
                amt = String.valueOf(t1 - t3);
                piece = board.boardMaterial1;
            }
            if (t3 > t1) {
                amt = String.valueOf(t3 - t1);
                piece = board.boardMaterial3;
            }
        } else if (t3 == 0) {
            if (t2 > t1) {
                amt = String.valueOf(t2 - t1);
                piece = board.boardMaterial2;
            }
            if (t1 > t2) {
                amt = String.valueOf(t1 - t2);
                piece = board.boardMaterial1;
            }
        } else if ((t1 == t2) && (t3 != 0)) {
            amt = String.valueOf(t3);
            piece = board.boardMaterial3;
        } else if ((t2 == t3) && (t1 != 0)) {
            amt = String.valueOf(t1);
            piece = board.boardMaterial1;
        } else if ((t1 == t3) && (t2 != 0)) {
            amt = String.valueOf(t2);
            piece = board.boardMaterial2;
        } else if ((t1 == t2) && (t3 == 0)) {
            amt = String.valueOf(t1);
            piece = board.boardMaterial1;
            System.out.println("EDGE");
        } else if ((t2 == t3) && (t1 == 0)) {
            amt = String.valueOf(t2);
            piece = board.boardMaterial2;
            System.out.println("EDGE");

        } else if ((t1 == t3) && (t2 == 0)) {
            amt = String.valueOf(t3);
            piece = board.boardMaterial3;
            System.out.println("EDGE");
        } else {
            makeRandMove(currBoard);
        }
        ArrayList<String> list = new ArrayList<>();
        if (t1 != 0) {
            list.add(board.boardMaterial1);
        }
        if (t2 != 0) {
            list.add(board.boardMaterial2);
        }
        if (t3 != 0) {
            list.add(board.boardMaterial3);
        }
        Random randChoice = new Random();
        String typeChoice = list.get(randChoice.nextInt(list.size()));
        if (amt.equals(" ") || piece.equals(" ")) {
            amt = "1";

            piece = typeChoice;
        }
        System.out.println("Piece: " + piece + " Amount: " + amt);
        move.add(piece);
        move.add(amt);

        return move;

    }

    public ArrayList<String> player(ArrayList<String> boardInput) throws IOException {

        ArrayList<String> moveData = new ArrayList<String>();

        InputStreamReader isrPiece = new InputStreamReader(System.in);
        BufferedReader playerMovePiece = new BufferedReader(isrPiece);

        InputStreamReader isrAmount = new InputStreamReader(System.in);
        BufferedReader playerMoveAmount = new BufferedReader(isrAmount);
        boolean var = false;
        while (!var) {
            System.out.print("Piece Type: ");

            String piece = playerMovePiece.readLine();

            moveData.add(piece);
            System.out.print("Amount: ");
            String amount = playerMoveAmount.readLine();
            moveData.add(amount);
            if (piece.equals(board.boardMaterial1)
                    && (Collections.frequency(boardInput, board.boardMaterial1 + " ") >= Integer.parseInt(amount))) {
                var = true;
            } else if (piece.equals(board.boardMaterial2)
                    && (Collections.frequency(boardInput, board.boardMaterial2 + " ") >= Integer.parseInt(amount))) {
                var = true;
            } else if (piece.equals(board.boardMaterial3)
                    && (Collections.frequency(boardInput, board.boardMaterial3 + " ") >= Integer.parseInt(amount))) {
                var = true;
            } else {
                System.out.println(
                        "Wrong input please only input (*, #, or 0) for Piece Type, and only however many are left for each given piece for Amount.");
            }
        }

        return moveData;

    }

    public boolean win(ArrayList<String> winningBoard) {
        int counter = 0;
        for (String s : winningBoard) {
            if (s.equals(" ") || s.equals("x ") || s.equals("\n")) {
                counter++;
            }
        }
        if (counter == winningBoard.size()) {
            return false;
        } else {
            return true;
        }

    }
}
