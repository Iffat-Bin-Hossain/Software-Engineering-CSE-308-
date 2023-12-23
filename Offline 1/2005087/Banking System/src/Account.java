

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

class Student extends Account {
    public Student(String customer, double initialDeposit) {
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
        if (amount > getCurrentbalance() || amount > 10000) {
            System.out.println("Invalid transaction; current balance " + getCurrentbalance() + "$");
            return false;
        }

        bank.setInternalFund(bank.getInternalFund() - amount);
        setCurrentbalance(getCurrentbalance() - amount);
        return true;
    }
}


class Savings extends Account {


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

class FixedDeposit extends Account {
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