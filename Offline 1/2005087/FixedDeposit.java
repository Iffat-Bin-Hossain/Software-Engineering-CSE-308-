
public class FixedDeposit extends Account {
    public FixedDeposit(String customer, double initialDeposit) {
        super(customer, initialDeposit);
    }

    @Override
    boolean Deposit(Bank bank, double deposit) {

        if (deposit < 50000) {
            System.out.println("Sorry!!!Deposit amount must be at least 50000 for fixed deposit account");
            return false;
        }
        bank.setInternalFund(bank.getInternalFund() + deposit);
        setCurrentbalance(getCurrentbalance() + deposit);
        return true;
    }

    @Override
    boolean Withdraw(Bank bank, double amount) {
        if (amount > getCurrentbalance() || bank.yearCount<1) {
            System.out.println("Invalid transaction; current balance " + getCurrentbalance() + "$");
            return false;
        }
        bank.setInternalFund(bank.getInternalFund() - amount);
        setCurrentbalance(getCurrentbalance() - amount);
        return true;
    }
}