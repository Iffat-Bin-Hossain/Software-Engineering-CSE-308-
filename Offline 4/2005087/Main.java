import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            Scanner scanner = new Scanner(System.in);
            new Thread(() -> {
                try {
                    while (true) {
                        Object serverResponse = inputStream.readObject();
                        if (serverResponse instanceof String) {
                            String responseMessage = (String) serverResponse;
                            System.out.println("From server: " + responseMessage);
                        }
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }).start();
            new Thread(() -> {
                try {
                    while (true) {
                        String userMessage = scanner.nextLine();
                        outputStream.writeObject(userMessage);
                        outputStream.flush();
                        if (userMessage.equalsIgnoreCase("logout")) {
                            break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
