import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class UserHandler implements Runnable {
    private Socket userSocket;
    private Server stockServer;
    private User user;
    private ObjectOutputStream outputStream;
    private Boolean logStatus;

    public UserHandler(Socket clientSocket, Server stockServer) {
        this.userSocket = clientSocket;
        this.stockServer = stockServer;
        this.user = new User("");
        logStatus = false;
        try {
            this.outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try (ObjectInputStream inputStream = new ObjectInputStream(userSocket.getInputStream())) {
            while (true) {
                try {
                    Object receivedObject = inputStream.readObject();

                    if (receivedObject instanceof String) {
                        String message = (String) receivedObject;
                        System.out.println("Received from client: " + message);
                        String[] command = (message.split(" "));
                        if (command[0].equalsIgnoreCase("login")) {
                            Login(command[1]);
                        } else if (command[0].equalsIgnoreCase("logout")) {
                            if (logStatus) {
                                Logout();
                            } else {
                                sendMessage("You are not logged in.");
                            }

                        } else {
                            if (logStatus) {
                                processClientMessage(message);
                            } else {
                                sendMessage("Please Log In first");
                            }
                        }


                    }
                } catch (EOFException e) {
                    System.out.println("Client disconnected: " + userSocket.getInetAddress().getHostName());
                    break;
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

     void processClientMessage(String message) {
        String[] parts = message.split(" ");
        String command = parts[0];

        switch (command) {

            case "S":
                Subscription(parts);
                break;
            case "U":
                Unsubscription(parts);
                break;
            case "V":
                View();
                break;
            default:
                System.out.println("Invalid command from client: " + message);
        }
    }

   void Login(String username) {
        user.setUsername(username);
        logStatus = true;
        stockServer.getUserList().add(user);
        String stockString="";
        for(Stock s:stockServer.getStocks()){
            stockString+="Name: "+s.getName()+"  Count: "+s.getCount()+" Price: "+s.getPrice()+"\n";
        }
        sendMessage("Welcome,"+username+"!!!\nAll Stocks:\n"+stockString+"\n");
    }

    void Logout() {
        logStatus = false;
        sendMessage("Succesfully logged out.");
    }

    void Subscription(String[] parts) {
        if (parts.length == 2) {
            String stockName = parts[1];
            Stock stock = stockServer.getStock(stockName);
            this.user.getSubscribedStocks().add(stock);
            if (stock != null) {
                User user = new User(userSocket.getInetAddress().getHostName());
                stock.addSubscriber(user);
                sendMessage("Subscribed to stock " + stockName);
            } else {
                sendMessage("Invalid stock name: " + stockName);
            }
        } else {
            sendMessage("Invalid subscription command");
        }
    }

    void Unsubscription(String[] parts) {
        if (parts.length == 2) {
            String stockName = parts[1];
            Stock stock = stockServer.getStock(stockName);
            List<Stock> subscribedList = this.user.getSubscribedStocks();
            if (stock != null) {
                User user = new User(userSocket.getInetAddress().getHostName());
                if (subscribedList.contains(stock)) {
                    stock.removeSubscriber(user);
                    subscribedList.remove(stock);
                    sendMessage("Unsubscribed from stock " + stockName);
                } else {
                    sendMessage(stockName + " is not available in your subscription");
                }

            } else {
                sendMessage("Invalid stock name: " + stockName);
            }
        } else {
            sendMessage("Invalid unsubscription command");
        }
    }

    void View() {
        List<Stock> subscribedStocks = stockServer.getSubscribedStocks(user.getUsername());
        if (!subscribedStocks.isEmpty()) {
            StringBuilder response = new StringBuilder("Subscribed stocks:");
            for (Stock stock : subscribedStocks) {
                response.append("\n").append(stock.getName()).append(" - Count: ").append(stock.getCount()).append(", Price: ").append(stock.getPrice());
            }
            sendMessage(response.toString());
        }
        else{
            sendMessage("No stock is subscribed yet");
        }
    }

    void sendMessage(String message) {
        try {
            outputStream.writeObject(message);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void closeResources() {
        try {
            outputStream.close();
            userSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
