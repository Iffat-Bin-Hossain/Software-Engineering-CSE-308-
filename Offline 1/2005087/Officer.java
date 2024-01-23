
public class Officer extends Employee {

    public Officer(String name, String post) {
        super(name, post);
    }

    @Override
    void approveLoan(Bank bank) {
        for (LoanRequest lr : bank.getLoanRequestLists()) {
            Account currAcc = lr.getAccount();
            currAcc.setLoan(currAcc.getLoan() + lr.getAmmount());
            currAcc.setCurrentbalance(currAcc.getCurrentbalance() + lr.getAmmount());
            bank.setInternalFund(bank.getInternalFund() - lr.getAmmount());
            System.out.println("Loan request for " + currAcc.customer + " approved.");
        }
        bank.allLoanRequestApproved();
    }

    @Override
    void seeInternalFund(Bank bank) {
        System.out.println("You don't have permission for this operation");
    }
    @Override
    void changeInterestRate(Bank bank,String accountType,double newIntRate){
        System.out.println("You don't have permission for this operation");
    }
}