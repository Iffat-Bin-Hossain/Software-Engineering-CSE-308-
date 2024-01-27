import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server {
    private ServerSocket serverSocket;
    private List<UserHandler> clients;
    private List<User> userList;
    private List<Stock> stocks;

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            clients = new ArrayList<>();
            stocks = new ArrayList<>();
            userList = new ArrayList<>();
            loadAllStocksInfo();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public List<UserHandler> getClients() {
        return clients;
    }

    public List<User> getUserList() {
        return userList;
    }

    public List<Stock> getSubscribedStocks(String hostName) {
        if (getUser(hostName) != null) {
            return getUser(hostName).getSubscribedStocks();
        }
        return null;
    }

    public void start() {
        System.out.println("Server started. Waiting for clients...");
        new Thread(this::startUserInputListener).start();

        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostName());
                UserHandler clientHandler = new UserHandler(clientSocket, this);
                clients.add(clientHandler);
                new Thread(clientHandler).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void startUserInputListener() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                String userInput = reader.readLine();
                processUserInput(userInput);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void processUserInput(String userInput) {
        String[] parts = userInput.split(" ");
        if (parts.length >= 2) {
            String operation = parts[0];
            String stockName = parts[1];

            switch (operation) {
                case "I":
                    double amount = Double.parseDouble(parts[2]);
                    IncreasePrice(stockName, amount);
                    break;
                case "D":
                    double decreaseAmount = Double.parseDouble(parts[2]);
                    DecreasePrice(stockName, decreaseAmount);
                    break;
                case "C":
                    int countChange = Integer.parseInt(parts[2]);
                    ChangeCount(stockName, countChange);
                    break;
                default:
                    System.out.println("Invalid operation: " + operation);
            }
        } else {
            System.out.println("Invalid user input");
        }
    }


    void loadAllStocksInfo() {
        File obj = new File("init_stocks.txt");
        try (Scanner scn = new Scanner(obj)) {
            while (scn.hasNextLine()) {
                String info = scn.nextLine();
                String[] tokens = info.split(" ");
                String stockName = tokens[0];
                int stockCount = Integer.parseInt(tokens[1]);
                double stockPrice = Double.parseDouble(tokens[2]);
                stocks.add(new Stock(stockName, stockCount, stockPrice));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    Stock getStock(String name) {
        for (Stock stock : stocks) {
            if (stock.getName().equals(name)) {
                return stock;
            }
        }
        return null;
    }

    User getUser(String name) {
        for (User user : userList) {
            if (user.getUsername().equals(name)) {
                return user;
            }
        }
        return null;
    }

    void IncreasePrice(String stockName, double amount) {
        Stock stock = getStock(stockName);

        if (stock != null) {
            stock.increasePrice(amount);
            for (UserHandler user : clients) {
                user.sendMessage("Increased price of stock " + stockName + " by " + amount);
            }
        } else {
            System.out.println("Invalid stock name: " + stockName);
        }
    }

    void DecreasePrice(String stockName, double amount) {
        Stock stock = getStock(stockName);

        if (stock != null) {
            stock.decreasePrice(amount);
            for (UserHandler user : clients) {
                user.sendMessage("Decreased price of stock " + stockName + " by " + amount);
            }
        } else {
            System.out.println("Invalid stock name: " + stockName);
        }
    }

    void ChangeCount(String stockName, int countChange) {
        Stock stock = getStock(stockName);

        if (stock != null) {
            stock.changeCount(countChange);
            for (UserHandler user : clients) {
                user.sendMessage("Changed count of stock " + stockName + " by " + countChange);
            }
        } else {
            System.out.println("Invalid stock name: " + stockName);
        }
    }

    public synchronized void updateStock(String stockName, int count, double price) {
        Stock stock = getStock(stockName);
        if (stock != null) {
            stock.updateStock(count, price);
        }
    }

    public static void main(String[] args) throws IOException {
        int port = 12345;
        Server stockServer = new Server(port);
        stockServer.start();
    }
}
