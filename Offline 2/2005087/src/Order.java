import java.util.ArrayList;
import java.util.List;

public class Order {
   float totalCost;
    private List<Shake> orderList ;
    Order(){
        totalCost=0;
        orderList = new ArrayList<>();
    }
   void  addOrder(Shake newShake){
        orderList.add(newShake);
        totalCost+=newShake.getExtraCost()+newShake.getBasePrice();
       System.out.println(newShake.getName()+" is added to your cart");
    }
    List<Shake> getOrderList(){
       return orderList;
    }
    void showOrder(){
        System.out.println("Your Cart:");
       for(Shake s:orderList){
           s.printDetails();
           System.out.println("---------------------------------------------------------");
       }
        System.out.println("Total Fair:"+totalCost);
    }
}
