public class Decorator implements Passenger {
    Passenger decoratedPassenger;

    Decorator(Passenger decoratedPassenger) {
        this.decoratedPassenger = decoratedPassenger;
    }

    @Override
    public void login(String name) {
        decoratedPassenger.login(name);
    }
    @Override
    public void repair() {
        decoratedPassenger.repair();
    }
    @Override
    public void work() {
        decoratedPassenger.work();
    }
    @Override
    public void logout() {
        decoratedPassenger.logout();
    }
}
