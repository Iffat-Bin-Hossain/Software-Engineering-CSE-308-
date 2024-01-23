import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Account currrentAccount = null;
        Employee currentEmployee = null;
        String RECORD_FILE = "CustomerList.txt";
        Bank bank = new Bank();
        BufferedReader br = new BufferedReader(new FileReader(RECORD_FILE));
        BufferedWriter bw = new BufferedWriter(new FileWriter(RECORD_FILE, true));
        while (true) {
            String fline = br.readLine();
            if (fline == null) break;
            else {
                String[] fileToken = fline.split(" ");
                Account ac;
                if (fileToken[1].equalsIgnoreCase("Student")) {
                    ac = new Student(fileToken[0], Double.parseDouble(fileToken[2]));
                } else if (fileToken[1].equalsIgnoreCase("Savings")) {
                    ac = new Savings(fileToken[0], Double.parseDouble(fileToken[2]));
                } else {
                    ac = new FixedDeposit(fileToken[0], Double.parseDouble(fileToken[2]));
                }
                bank.addAccount(ac);
            }
        }


        Scanner scn = new Scanner(System.in);
        while (scn.hasNextLine()) {
            bw = new BufferedWriter(new FileWriter(RECORD_FILE, true));
            String command = scn.nextLine();
            String[] tokens = command.split(" ");
            if (tokens[0].equalsIgnoreCase("Create")) {
                String customer = tokens[1];
                String accountType = tokens[2];
                double firstDeposit = Double.parseDouble(tokens[3]);
                if (bank.hasAccount(customer)) {
                    System.out.println("Sorry!!!This account has already exist");
                    continue;
                }


                if (accountType.equalsIgnoreCase("FixedDeposit")) {
                    if (firstDeposit < 100000) {
                        System.out.println("Sorry!!!Fixed deposit requires at least 100000$ as initial deposit");
                        continue;
                    } else {
                        currrentAccount = new FixedDeposit(customer, firstDeposit);
                    }
                } else if (accountType.equalsIgnoreCase("Student")) {
                    currrentAccount = new Student(customer, firstDeposit);
                } else {
                    currrentAccount = new Savings(customer, firstDeposit);
                }

                bw.write(customer + " " + accountType + " " + firstDeposit + "\n");
                bank.addAccount(currrentAccount);
                System.out.println(accountType + " account for " + customer + " created;initial balance " + firstDeposit + "$");

            } else if (tokens[0].equalsIgnoreCase("Deposit")) {
                double deposit = Double.parseDouble(tokens[1]);
                if (currrentAccount.Deposit(bank, deposit)) {
                    bw = new BufferedWriter(new FileWriter(RECORD_FILE, false));
                    for (Account ac : bank.getAccountLists()) {
                        String Class[] = String.valueOf(ac.getClass()).split(" ");
                        bw.write(ac.customer + " " + Class[1] + " " + ac.getCurrentbalance());
                        bw.newLine();
                    }
                    System.out.println(deposit + "$ deposited;current balance " + currrentAccount.getCurrentbalance());
                }

            } else if (tokens[0].equalsIgnoreCase("Withdraw")) {
                double amount = Double.parseDouble(tokens[1]);
                if (currrentAccount.Withdraw(bank, amount)) {
                    bw = new BufferedWriter(new FileWriter(RECORD_FILE, false));
                    for (Account ac : bank.getAccountLists()) {
                        String Class[] = String.valueOf(ac.getClass()).split(" ");
                        bw.write(ac.customer + " " + Class[1] + " " + ac.getCurrentbalance());
                        bw.newLine();
                    }
                    System.out.println(amount + "$ withdrawn;current balance " + currrentAccount.getCurrentbalance());
                }

            } else if (tokens[0].equalsIgnoreCase("Query")) {
                currrentAccount.Query();
            } else if (tokens[0].equalsIgnoreCase("Request")) {
                double amount = Double.parseDouble(tokens[1]);
                bank.requestForLoan(currrentAccount, amount);
            } else if (tokens[0].equalsIgnoreCase("Close")) {
                if (currrentAccount != null) {
                    System.out.println("Transaction Closed for " + currrentAccount.customer);
                    currrentAccount = null;
                } else if (currentEmployee != null) {
                    System.out.println("Operations for " + currentEmployee.name + " closed");
                    currentEmployee = null;
                }

            } else if (tokens[0].equalsIgnoreCase("Open")) {
                boolean empOn = false;
                String user = tokens[1];
                for (Employee emp : bank.getEmployeeLists()) {
                    if (emp.name.equals(user)) {
                        currentEmployee = emp;
                        empOn = true;
                        System.out.printf(currentEmployee.name + " active");
                        break;
                    }
                }
                if (empOn) {
                    if (currentEmployee instanceof ManagingDirector || currentEmployee instanceof Officer) {
                        if (bank.getLoanRequestLists().size() > 0) {
                            System.out.printf(",there are loan approvals pending");
                        }
                    }
                    System.out.println();
                } else {
                    for (Account ac : bank.getAccountLists()) {
                        if (ac.customer.equals(user)) {
                            currrentAccount = ac;
                            System.out.println("Welcome back," + user);
                            break;
                        }
                    }
                }
            } else if (tokens[0].equalsIgnoreCase("Approve") && tokens[1].equalsIgnoreCase("Loan")) {
                    currentEmployee.approveLoan(bank);

            } else if (tokens[0].equalsIgnoreCase("See")) {
                currentEmployee.seeInternalFund(bank);
            } else if (tokens[0].equalsIgnoreCase("Lookup")) {
                String customer = tokens[1];
                currentEmployee.Lookup(bank, customer);

            } else if (tokens[0].equalsIgnoreCase("Change")) {
                String accountType = tokens[1];
                double newIntRate = Double.parseDouble(tokens[2]);
                currentEmployee.changeInterestRate(bank, accountType, newIntRate);
            } else if (tokens[0].equalsIgnoreCase("INC")) {
                bank.INC();

                bw = new BufferedWriter(new FileWriter(RECORD_FILE, false));
                for (Account ac : bank.getAccountLists()) {
                    String Class[] = String.valueOf(ac.getClass()).split(" ");
                    bw.write(ac.customer + " " + Class[1] + " " + ac.getCurrentbalance());
                    bw.newLine();
                }
            } else {
                break;
            }

            br.close();
            bw.close();
        }
    }
}