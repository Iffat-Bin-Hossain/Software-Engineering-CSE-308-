public class ManagingDirector extends Employee {
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



