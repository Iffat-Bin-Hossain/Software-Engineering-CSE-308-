public class Imposter extends  Decorator {
    Imposter(Passenger decoratedPassenger) {
        super(decoratedPassenger);
    }
    @Override
    public void login(String name) {
        super.login(name);
        System.out.println("We won’t tell anyone; you are an imposter.");
    }

    @Override
    public void repair() {
        super.repair();
        System.out.println("Damaging the spaceship.");
    }

    @Override
    public void work() {
        super.work();
        System.out.println("Trying to kill a crewmate.");
        System.out.println("Successfully killed a crewmate.");
    }

    @Override
    public void logout() {
        super.logout();
        System.out.println("See you again Comrade Imposter.");
    }

}
