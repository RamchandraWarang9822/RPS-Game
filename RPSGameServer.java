import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class RPSGameServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket player1Socket = null;
        Socket player2Socket = null;
        Scanner player1Input = null;
        PrintWriter player1Output = null;
        Scanner player2Input = null;
        PrintWriter player2Output = null;
        
        try {
            serverSocket = new ServerSocket(12345);
            System.out.println("Server started. Waiting for players...");

            // Wait for two players to connect
            player1Socket = serverSocket.accept();
            System.out.println("Player 1 connected.");
            player2Socket = serverSocket.accept();
            System.out.println("Player 2 connected.");

            // Open input and output streams for each player
            player1Input = new Scanner(player1Socket.getInputStream());
            player1Output = new PrintWriter(player1Socket.getOutputStream(), true);
            player2Input = new Scanner(player2Socket.getInputStream());
            player2Output = new PrintWriter(player2Socket.getOutputStream(), true);

            // Start the game
            player1Output.println("Welcome! You are Player 1.");
            player2Output.println("Welcome! You are Player 2.");

            while (true) {
                player1Output.println("Your turn: Rock, Paper, or Scissors?");
                player2Output.println("Your turn: Rock, Paper, or Scissors?");

                // Get choices from players
                String player1Choice = player1Input.nextLine().toLowerCase();
                String player2Choice = player2Input.nextLine().toLowerCase();

                // Determine the winner
                String result;
                if (player1Choice.equals(player2Choice)) {
                    result = "It's a tie!";
                } else if ((player1Choice.equals("rock") && player2Choice.equals("scissors")) ||
                           (player1Choice.equals("paper") && player2Choice.equals("rock")) ||
                           (player1Choice.equals("scissors") && player2Choice.equals("paper"))) {
                    result = "Player 1 wins!";
                } else {
                    result = "Player 2 wins!";
                }

                // Send the result to both players
                player1Output.println(result);
                player2Output.println(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Close the resources in the finally block
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
                if (player1Socket != null) {
                    player1Socket.close();
                }
                if (player2Socket != null) {
                    player2Socket.close();
                }
                if (player1Input != null) {
                    player1Input.close();
                }
                if (player1Output != null) {
                    player1Output.close();
                }
                if (player2Input != null) {
                    player2Input.close();
                }
                if (player2Output != null) {
                    player2Output.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
