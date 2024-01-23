public class Cashier extends Employee {


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