import java.util.ArrayList;
import java.util.List;
public class User implements Observer {
    String username;
    List<Stock> subscribedStocks;

    User(String username) {
        this.username = username;
        this.subscribedStocks = new ArrayList<>();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSubscribedStocks(List<Stock> subscribedStocks) {
        this.subscribedStocks = subscribedStocks;
    }

    String getUsername() {
        return username;
    }

    public List<Stock> getSubscribedStocks() {
        return subscribedStocks;
    }

    @Override

    public void update(String message) {
        System.out.println("User " + username + ": " + message);
    }

}
