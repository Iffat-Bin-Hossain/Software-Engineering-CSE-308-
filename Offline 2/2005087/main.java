import java.util.Scanner;

public class main {
    static void showMenu() {
        System.out.println("MENU:");
        System.out.println("1.Chocolate Shake");
        System.out.println("2.Coffee Shake");
        System.out.println("3.Strawberry Shake");
        System.out.println("4.Vanilla Shake");
        System.out.println("5.Zero Shake");
        System.out.println("6.None");
    }

    static void customizeShake(ShakeBuilder sBuilder) {

        while (true) {
            System.out.println("Want to customize your Order???");
            System.out.println("1.yes");
            System.out.println("2.No");
            Scanner scn = new Scanner(System.in);
            int yesNo = Integer.parseInt(scn.nextLine());
            if (yesNo == 1) {

                System.out.println("CUSTOMIZATION:");
                System.out.println("1.Make Shake Lactose Free");
                System.out.println("2.Add Candy On Top");
                System.out.println("3.Add Cookies On Top");
                System.out.printf("Add anyone:");
                int customizationNo = Integer.parseInt(scn.nextLine());

                if (customizationNo == 1) {
                    sBuilder.makeShakeLactoseFree();
                } else if (customizationNo == 2) {
                    sBuilder.addCandyOnTop();
                } else if (customizationNo == 3) {
                    sBuilder.addCookiesOnTop();
                } else {
                    System.out.println("Sorry!!!This customization option is not available");
                }
            } else {
                break;
            }
        }

    }

    static Shake MyShake(int shakeNo, ShakeBuilder sBuilder) {
        Shake myShake=new Shake();
        ShakeDirector sDirector = new ShakeDirector(sBuilder);
        if (shakeNo == 1) {
            myShake = sDirector.ChocolateShake();
        } else if (shakeNo == 2) {
            myShake = sDirector.CoffeeShake();
        } else if (shakeNo == 3) {
            myShake = sDirector.StrawberryShake();
        } else if (shakeNo == 4) {
            myShake = sDirector.VanillaShake();
        } else {
            myShake = sDirector.ZeroShake();
        }
        return myShake;
    }

    static void takeOrder(Order order, ShakeBuilder sBuilder) {
        while (true) {
            sBuilder = new ShakeBuilder();
            showMenu();
            System.out.printf("Enter your choice:");
            Scanner scn = new Scanner(System.in);
            int shakeNo = Integer.parseInt(scn.nextLine());
            if (shakeNo >= 1 && shakeNo <= 5) {
                customizeShake(sBuilder);
                Shake myShake = MyShake(shakeNo, sBuilder);
                order.addOrder(myShake);
            } else {
                if (order.getOrderList().isEmpty()) {
                    System.out.println("You have to order at least one item");
                    continue;
                }
                System.out.println("Press E to close your order.");
                break;

            }
        }
    }


    public static void main(String[] args) {

        System.out.println("Welcome to our Restaurent,Sir!!!");
        System.out.println("Press O to place a new order.");
        ShakeBuilder sBuilder=null;
        Order order = null;
        Scanner scn = new Scanner(System.in);
        boolean orderRunning = false;
        boolean back = false;

        while (scn.hasNextLine()) {
            String command = scn.nextLine();
            if (command.equalsIgnoreCase("O")) {
                if (!orderRunning || back) {
                    if (!back) {
                        order = new Order();
                        orderRunning = true;
                        System.out.println("New Order Openned!!!");
                    }
                    takeOrder(order, sBuilder);
                } else {
                    System.out.println("Sorry!!!An order is ongoing.Try Later!!!");
                    System.out.println("Do you want to add more?");
                    System.out.println("1.Yes");
                    System.out.println("2.No");
                    if (Integer.parseInt(scn.nextLine()) == 1) {
                        back = true;
                        takeOrder(order, sBuilder);
                    } else {
                        System.out.println("Press E to close your order.");
                    }
                }
            } else if (command.equalsIgnoreCase("E")) {
                if(!orderRunning){
                    System.out.println("NO order has openned");
                    continue;
                }
                orderRunning = false;
                order.showOrder();
                System.out.println("Order Closed!!!");

            }
            else{
                break;
            }

        }


    }
}
