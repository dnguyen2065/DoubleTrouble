// The name of the game is NIM, it was created by Charles L. Bouton, and it was commonly played in china.

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class App {

    public static void main(String[] args) throws Exception {
        Board board = new Board("*", "#", "0");
        Play player = new Play();
        ArrayList<String> playerBoard = board.createBoard();
        System.out.println("WELCOME TO DOUBLE TROUBLE!\n");
        board.firstBoard();
        System.out.println("\nRULES FOR INPUT:");
        System.out.println(
                "Assuming you know the rules, to play you must input the piece type (*, #, 0) \nand the amount of each type you would like to get rid of. Please note: The game \nwill not allow you to make false inputs. If you input a false piece nothing will \nhappen, if you input an amount to large for the amount left it will only take \naway as many as are left. Finally if you input in a negative number nothing will happen.");

        boolean first = player.choosePlayer();

        ArrayList<String> currBoard = playerBoard;
        do {
            ArrayList<String> moveData = new ArrayList<>();
            if (first) {
                moveData = player.player(currBoard);
                first = false;
            } else {

                if ((Collections.frequency(currBoard, board.boardMaterial1 + " ") == 0
                        || Collections.frequency(currBoard, board.boardMaterial2 + " ") == 0
                        || Collections.frequency(currBoard, board.boardMaterial3 + " ") == 0)
                        || (Collections.frequency(currBoard,
                                board.boardMaterial1 + " ") == Collections.frequency(currBoard,
                                        board.boardMaterial2 + " "))
                        || (Collections.frequency(currBoard,
                                board.boardMaterial2 + " ") == Collections.frequency(currBoard,
                                        board.boardMaterial3 + " "))
                        || (Collections.frequency(currBoard,
                                board.boardMaterial1 + " ") == Collections.frequency(currBoard,
                                        board.boardMaterial3 + " "))) {
                    System.out.println("The computer made strategic move: ");
                    moveData = player.strategicMove(currBoard);
                } else {
                    System.out.println("The computer made random move: ");
                    moveData = player.makeRandMove(currBoard);
                }

                first = true;
            }

            currBoard = board.printBoard(moveData.get(0), Integer.parseInt(moveData.get(1)),
                    currBoard);
            // System.out.println(board.countTypes(currBoard));

        } while (player.win(playerBoard));
        if (!first) {
            System.out.println("Congrats! You Won!!");
            System.exit(0);
        } else {
            System.out.println("Computer won, try again next time :(");
            System.exit(0);
        }
        //

        // System.out.println("Hello, World!");
    }
}
