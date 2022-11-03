import java.util.ArrayList;

public class App {
    private static GameStatus status = GameStatus.START;

    public static void main(String[] args) throws Exception {
        Board board = new Board("*", "#", "0");
        Play player = new Play();
        ArrayList<String> playerBoard = board.createBoard();
        switch (status) {
            case START:
                System.out.println("WELCOME TO DOUBLE TROUBLE!\n");
                board.firstBoard();
                System.out.println("\nRULES FOR INPUT:");
                System.out.println(
                        "Assuming you know the rules please input the row (1 being the top row and 5 \nbeing the bottom) and which element on the row, (1 being the leftmost element \nand incrementing by one thereafter until the end of the row)");
            default:
                do {
                    ArrayList<String> move = player.player(playerBoard);
                    playerBoard = board.printBoard(move.get(0), Integer.parseInt(move.get(1)),
                            playerBoard);
                } while (true);

                // player.win(playerBoard);

        }

        // System.out.println("Hello, World!");
    }
}
