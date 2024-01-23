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