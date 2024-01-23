public class Savings extends Account {


    public Savings(String customer, double initialDeposit) {
        super(customer, initialDeposit);

    }
    @Override
    boolean Deposit(Bank bank, double deposit) {
        bank.setInternalFund(bank.getInternalFund() + deposit);
        setCurrentbalance(getCurrentbalance() + deposit);
        return true;
    }

    @Override
    boolean Withdraw(Bank bank, double amount) {
        if (amount > getCurrentbalance() || getCurrentbalance() - amount < 1000) {
            System.out.println("Invalid transaction; current balance " + getCurrentbalance() + "$");
            return false;
        }

        bank.setInternalFund(bank.getInternalFund() - amount);
        setCurrentbalance(getCurrentbalance() - amount);
        return  true;
    }
}