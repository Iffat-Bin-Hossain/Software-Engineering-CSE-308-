import java.util.ArrayList;
import java.util.List;

public class Stock implements Subject{
    private String name;
    private int count;
    private double price;
    private List<User> subscriberList;

    Stock(String name, int count, double price) {
        this.name = name;
        this.count = count;
        this.price = price;
        this.subscriberList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public double getPrice() {
        return price;
    }

    public List<User> getSubscriberList() {
        return subscriberList;
    }

    public void addSubscriber(User user) {
        subscriberList.add(user);
    }

    public void removeSubscriber(User user) {
        subscriberList.remove(user);
    }

    public void notifySubscribers(String message) {
        for (User subscriber : subscriberList) {
            subscriber.update(message);
        }
    }
    public synchronized void updateStock(int count, double price) {
        this.count = count;
        this.price = price;
        notifySubscribers("Stock " + name + " updated - Count: " + count + ", Price: " + price);
    }
    public void increasePrice(double amount){
        price+=amount;
    }
    public void decreasePrice(double amount){
        price-=amount;
    }
    public void changeCount(int countChange){
        count=countChange;
    }

}
