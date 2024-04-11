import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class RPSGameClient {
    public static void main(String[] args) {
        Socket socket = null;
        Scanner serverInput = null;
        PrintWriter serverOutput = null;
        Scanner userInput = null;
        
        try {
            socket = new Socket("localhost", 12345);
            serverInput = new Scanner(socket.getInputStream());
            serverOutput = new PrintWriter(socket.getOutputStream(), true);
            userInput = new Scanner(System.in);

            // Receive welcome message
            System.out.println(serverInput.nextLine());

            while (true) {
                // Receive prompt from server
                System.out.println(serverInput.nextLine());

                // Get player's choice
                String choice = userInput.nextLine();
                serverOutput.println(choice);

                // Receive and print result
                System.out.println(serverInput.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Close the resources in the finally block
            try {
                if (socket != null) {
                    socket.close();
                }
                if (serverInput != null) {
                    serverInput.close();
                }
                if (serverOutput != null) {
                    serverOutput.close();
                }
                if (userInput != null) {
                    userInput.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
