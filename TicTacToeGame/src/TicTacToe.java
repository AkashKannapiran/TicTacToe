import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String rePlay = "y";

        while (rePlay.equals("y")) { // This loop runs until user decides to stop playing

            int play;
            char[][] board = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};

            while (true) { // This loop continues to run until the user provides a valid entry.
                System.out.print("How would you like to play?\n 1 vs 1 press: 1\n 1 vs computer press: 2 - ");
                play = scan.nextInt();
                scan.nextLine();

                if (play == 1 || play == 2) { // checks for a valid entry.
                    break;
                } else {
                    System.out.println(play + "is not a valid move");
                    System.out.println("Just enter\n 1 - to play with your friend\n 2 - to play with computer");
                }
            }

            boolean gameOver = false;
            char player = 'X';
            int entryCount = 0;

            if (play == 1) { // 1 vs 1
                while (!gameOver) { // this loop runs till the game is over
                    printBoard(board); // prints Tic Tac Toe board
                    System.out.print(" Enter player \"" + player + "\" move(1-3 for row space 1-3 for column): ");
                    int row = scan.nextInt() - 1;
                    int col = scan.nextInt() - 1;
                    scan.nextLine();
                    System.out.println();

                    if (board[row][col] == ' ') {
                        board[row][col] = player; // stores the user input
                        entryCount++;
                        gameOver = hasWon(board, player); // checks hasWon method for win
                        if (gameOver) { // Print who won the game
                            if (player == 'X') {
                                printBoard(board);
                                System.out.println("Player 1 \"" + player + "\" has Won!!");
                            }
                            if (player == 'O') {
                                printBoard(board);
                                System.out.println("Player 2 \"" + player + "\" has Won!!");
                            }
                        } else { // players will switch until someone wins
                            player = (player == 'X') ? 'O' : 'X';
                        }
                        if (entryCount == 9) { // If no one won and all the spaces are filled. So game tied
                            System.out.println("Game Tied");
                            printBoard(board);
                            break;
                        }
                    } else { // if there is any wrong move
                        System.out.println("Invalid move. Try again!!");
                    }
                }
            } else if (play == 2) { // 1 vs computer
                while (!gameOver) { // this loop runs till the game is over
                    printBoard(board); // prints Tic Tac Toe board
                    int row = 0;
                    int col = 0;
                    if (player == 'X') {
                        System.out.print(" Enter player \"" + player + "\" move(1-3 for row space 1-3 for column): ");
                        row = scan.nextInt() - 1;
                        col = scan.nextInt() - 1;
                        scan.nextLine();
                        System.out.println();
                    } else if (player == 'O') {
                        Random rand = new Random(); // for computer random input
                        row = rand.nextInt(3); // gives random number between 0, 1 and 2
                        col = rand.nextInt(3);
                        System.out.println("computer played: " + (row+1) + "th row and " + (col+1) + "th column");
                    }
                    if (board[row][col] == ' ') {
                        board[row][col] = player; // stores the user input
                        entryCount++;
                        gameOver = hasWon(board, player); // checks hasWon method for win
                        if (gameOver) { // Print who won the game
                            if (player == 'X') {
                                printBoard(board);
                                System.out.println("Player 1 \"" + player + "\" has Won!!");
                            }
                            if (player == 'O') {
                                printBoard(board);
                                System.out.println("Computer \"" + player + "\" has Won!!");
                            }
                        }
                        else { // players will switch until someone wins
                            player = (player == 'X') ? 'O' : 'X';
                        }
                        if (entryCount == 9) { // If no one won and all the spaces are filled. So game tied
                            System.out.println("Game Tied");
                            printBoard(board);
                            break;
                        }
                    }
                    else { // if there is any wrong move
                        System.out.println("Invalid move. Try again!!");
                    }
                }
            }

            System.out.println("Play again (y/n): ");
            rePlay = scan.nextLine().toLowerCase();

            if (!rePlay.equals("y")) { // checks play again (yes / no)
                System.out.println("Thanks for playing!!");
                break;
            }
        }
        scan.close();
    }

    private static void printBoard(char[][] board) {
        System.out.println(board[0][0] + " | " + board[0][1] + " | " + board[0][2]);
        System.out.println("--+---+--");
        System.out.println(board[1][0] + " | " + board[1][1] + " | " + board[1][2]);
        System.out.println("--+---+--");
        System.out.println(board[2][0] + " | " + board[2][1] + " | " + board[2][2]);

    }

    private static boolean hasWon(char[][] board, char player) {
        // checks rows for win
        for (int row = 0; row < board.length; row++) {
            if (board[row][0] == player && board[row][1] == player && board[row][2] == player) {
                return true;
            }
        }
        // checks column for win
        for (int col = 0; col < board[0].length; col++) {
            if (board[0][col] == player && board[1][col] == player && board[2][col] == player) {
                return true;
            }
        }
        // checks diagonal(\) for win
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        // checks diagonal(/) for win
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }
        return false;
    }
}