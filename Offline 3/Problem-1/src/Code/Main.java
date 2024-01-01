import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Passenger crewmate = null;
        while (true) {
            Scanner scn = new Scanner(System.in);
            String command = scn.nextLine();
            String[] tokens = command.split(" ");
            if (tokens[0].equalsIgnoreCase("login")) {
                if (tokens[1].substring(0,4).equalsIgnoreCase("Crew")) {
                    crewmate = new Crewmate();
                    crewmate.login(tokens[1]);
                }
                if (tokens[1].substring(0, 3).equalsIgnoreCase("Imp")) {
                    crewmate = new Imposter(new Crewmate());
                    crewmate.login(tokens[1]);
                }

            } else if (tokens[0].equalsIgnoreCase("repair")) {
                crewmate.repair();
            } else if (tokens[0].equalsIgnoreCase("work")) {
                crewmate.work();
            } else if (tokens[0].equalsIgnoreCase("logout")) {
                crewmate.logout();
            } else {
                break;
            }

        }
    }
}
