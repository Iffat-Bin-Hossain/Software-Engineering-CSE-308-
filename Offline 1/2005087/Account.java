

public abstract class Account {
    String customer;

    double initialDeposit;
    private double currentbalance;
    double loan;


    public Account(String customer, double initialDeposit) {
        this.customer = customer;
        this.initialDeposit = initialDeposit;
        this.currentbalance = initialDeposit;
        loan = 0;
    }


    void setCurrentbalance(double newBalance) {
        currentbalance = newBalance;
    }

    double getCurrentbalance() {
        return currentbalance;
    }

    void setLoan(double loan) {
        this.loan = loan;
    }

    double getLoan() {
        return loan;
    }

    abstract boolean Deposit(Bank bank, double deposit);

    abstract boolean Withdraw(Bank bank, double amount);
    void Query(){
        System.out.printf("Current balance " + getCurrentbalance()+"$");
        if (getLoan() > 0) {
            System.out.printf(",loan " + getLoan()+"$");
        }
        System.out.println();
    }
}

