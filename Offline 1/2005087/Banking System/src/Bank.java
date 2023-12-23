import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
class LoanRequest {
    private Account account;
    private double ammount;

    LoanRequest(Account ac, double amount) {
        account = ac;
        this.ammount = amount;
    }

    Account getAccount() {
        return account;
    }

    double getAmmount() {
        return ammount;
    }
}



public class Bank {
    private List<Account> accountList;
    private List<Employee> employeeList;
    private List<LoanRequest> loanRequests;

    private double internalFund;
    private HashMap<String,Double> map;
     int yearCount;

    Bank() {
        this.accountList = new ArrayList<Account>();
        this.employeeList = new ArrayList<Employee>();
        this.loanRequests = new ArrayList<LoanRequest>();
        this.map=new HashMap<>();
        internalFund = 10000;
        createEmployees();
        setInterestRate();
        yearCount = 0;
        System.out.println("Bank Created;MD;S1;S2;C1;C2;C3;C4;C5 created");
    }
    void setInterestRate(){
        map.put("Student", 5.0);
        map.put("Savings",10.0);
        map.put("FixedDeposit", 15.0);
    }

    void addAccount(Account ac) {
        accountList.add(ac);
        internalFund += ac.initialDeposit;
    }

    void requestForLoan(Account ac, double amount) {
        if((ac instanceof Student && amount<=1000)||(ac instanceof Savings && amount<=10000)||(ac instanceof FixedDeposit && amount<=100000)){
            LoanRequest lr = new LoanRequest(ac, amount);
            loanRequests.add(lr);
            System.out.println("Loan request successful, sent for approval");
        }
        else{
            System.out.println("Sorry!!!Loan amount exceeds maximum limit");
        }

    }

    boolean hasAccount(String name) {
        for (Account ac : accountList) {
            if (ac.customer.equals(name)) {
                return true;
            }

        }
        return false;
    }

    void setInternalFund(double newInternalFund) {
        internalFund = newInternalFund;
    }

    double getInternalFund() {
        return internalFund;
    }

    ArrayList<Account> getAccountLists() {
        return (ArrayList<Account>) accountList;
    }

    ArrayList<Employee> getEmployeeLists() {
        return (ArrayList<Employee>) employeeList;
    }

    ArrayList<LoanRequest> getLoanRequestLists() {
        return (ArrayList<LoanRequest>) loanRequests;
    }
    HashMap<String,Double> getMap(){return  map;}

    void allLoanRequestApproved() {
        loanRequests = new ArrayList<LoanRequest>();
    }

    void createEmployees() {
        ManagingDirector MD = new ManagingDirector("MD", "ManagingDirector");
        employeeList.add(MD);
        Officer S1 = new Officer("S1", "Officer");
        employeeList.add(S1);
        Officer S2 = new Officer("S2", "Officer");
        employeeList.add(S2);
        Cashier C1 = new Cashier("C1", "Cashier");
        employeeList.add(C1);
        Cashier C2 = new Cashier("C2", "Cashier");
        employeeList.add(C2);
        Cashier C3 = new Cashier("C3", "Cashier");
        employeeList.add(C3);
        Cashier C4 = new Cashier("C4", "Cashier");
        employeeList.add(C4);
        Cashier C5 = new Cashier("C5", "Cashier");
        employeeList.add(C5);
    }
    void INC(){
        yearCount++;
        System.out.println("1 year passed");
        for(Account ac:accountList){
            String Class[] = String.valueOf(ac.getClass()).split(" ");
            double increment= ac.getCurrentbalance()*map.get(Class[1])/100;
            ac.setCurrentbalance(ac.getCurrentbalance()+increment);
            if(ac.getLoan()>0){
                double interest=ac.getLoan()*0.1;
                ac.setCurrentbalance(ac.getCurrentbalance()-interest);
            }
            if(!(ac instanceof Student)){
                ac.setCurrentbalance(ac.getCurrentbalance()-500);
            }
        }
    }
}








