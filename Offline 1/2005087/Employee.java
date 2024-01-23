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
