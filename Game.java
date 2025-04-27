import java.util.Random;
import java.util.Scanner;

public class Game {
    private Board board;
    private Scanner scan = new Scanner(System.in);
    private String playerName;
    private char playerSymbol;
    private char computerSymbol;

    // Constructor now takes user symbol and computer symbol as parameters
    public Game(String playerName, char playerSymbol, char computerSymbol, Board board) {
        this.playerName = playerName;
        this.playerSymbol = playerSymbol;
        this.computerSymbol = computerSymbol;
        this.board = board;
    }

    public void gamestart() {
        boolean isP1Turn = true;  // Track turns (player vs computer)

        while (true) {
            board.display();  // Show current board state
            char currentSymbol = isP1Turn ? playerSymbol : computerSymbol;

            // Player's turn
            if (isP1Turn) {
                System.out.println("Player " + playerName + "'s turn (Symbol: " + currentSymbol + ")");
                playerMove(currentSymbol);
            } else {  // Computer's turn
                System.out.println("Computer's turn (Symbol: " + currentSymbol + ")");
                computerMove(currentSymbol);
            }

            // Check win condition
            if (checkWin(currentSymbol)) {
                board.display();
                System.out.println("Player " + (isP1Turn ? playerName : "Computer") + " wins!");
                break;
            }

            // Check draw condition
            if (checkDraw()) {
                board.display();
                System.out.println("It's a draw!");
                break;
            }

            // Switch turns
            isP1Turn = !isP1Turn;
        }
    }

    private void playerMove(char currentSymbol) {
        int row, col;
        while (true) {
            System.out.print("Enter row and column (1-3 1-3): ");
            row = scan.nextInt()-1;
            col = scan.nextInt()-1;

            if (row < 0 || row >= 3 || col < 0 || col >= 3 || board.grid[row][col] != ' ') {
                System.out.println("Invalid move. Try again.");
                continue;
            }
            board.grid[row][col] = currentSymbol;
            break;
        }
    }

    private void computerMove(char currentSymbol) {
        Random rand = new Random();
        int row, col;
        while (true) {
            row = rand.nextInt(3);
            col = rand.nextInt(3);

            if (board.grid[row][col] == ' ') {
                board.grid[row][col] = currentSymbol;
                break;
            }
        }
    }

    private boolean checkWin(char symbol) {
        return checkRows(symbol) || checkColumns(symbol) || checkDiagonals(symbol);
    }

    private boolean checkRows(char symbol) {
        for (int i = 0; i < 3; i++) {
            if (board.grid[i][0] == symbol && board.grid[i][1] == symbol && board.grid[i][2] == symbol) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumns(char symbol) {
        for (int i = 0; i < 3; i++) {
            if (board.grid[0][i] == symbol && board.grid[1][i] == symbol && board.grid[2][i] == symbol) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonals(char symbol) {
        return (board.grid[0][0] == symbol && board.grid[1][1] == symbol && board.grid[2][2] == symbol) ||
                (board.grid[0][2] == symbol && board.grid[1][1] == symbol && board.grid[2][0] == symbol);
    }

    private boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.grid[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}
