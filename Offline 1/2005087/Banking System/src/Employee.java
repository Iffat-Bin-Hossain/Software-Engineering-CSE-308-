public abstract class Employee {
    String name;
    String post;

     Employee(String name, String post) {
        this.name = name;
        this.post = post;
    }

    abstract void approveLoan(Bank bank);
    abstract void seeInternalFund(Bank bank);
    abstract void changeInterestRate(Bank bank, String accountType, double newIntRate);
    void Lookup(Bank bank,String customer){
        for(Account ac: bank.getAccountLists()){
            if(ac.customer.equals(customer)){
                System.out.println(customer+"'s current balance "+ac.getCurrentbalance()+"$");
                break;
            }
        }
    }
}
class ManagingDirector extends Employee {
     ManagingDirector(String name, String post) {
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
        System.out.println("Internal fund "+bank.getInternalFund());
    }
    @Override
    void changeInterestRate(Bank bank,String accountType,double newIntRate){
        bank.getMap().remove(accountType);
        bank.getMap().put(accountType,newIntRate);
        System.out.println("Interest rate changed to "+bank.getMap().get(accountType)+"% for "+accountType+" accounts");
    }

}


class Officer extends Employee {

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

class Cashier extends Employee {


    public Cashier(String name, String post) {
        super(name, post);
    }

    @Override
    void approveLoan(Bank bank) {
        System.out.println("You don't have permission for this operation");
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