//Tony Jiang
//Computer Science 20
//Henry Wise Wood High School
//2020-2021 Semester 1

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

public class BankAccount {

    private String accountNumber;
    private double balance;
    private double withdrawalFee;
    private double annualInterestRate;

    ArrayList<Transaction> transactionHistory = new ArrayList<Transaction>();

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public double getWithdrawalFee() {
        return withdrawalFee;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(double rate) {
        annualInterestRate = rate;
    }

    public void setWithdrawalFee(double fee) {
        withdrawalFee = fee;
    }

    public BankAccount(String Inumber) {
        accountNumber = Inumber;
    }

    public BankAccount(String Inumber, double Ibalance) {
        accountNumber = Inumber;
        balance = Ibalance;
    }

    public BankAccount(String Inumber, double Ibalance, double Ifee, double Irate) {
        accountNumber = Inumber;
        balance = Ibalance;
        withdrawalFee = Ifee;
        annualInterestRate = Irate;
    }

    public void deposit(double amount) {
        balance = balance + amount;

        Transaction transaction = new Transaction(amount, null);//creates transaction object and adds to list
		transactionHistory.add(transaction);
    }

    public void withdraw(double amount) {
        balance = balance - amount - withdrawalFee;

        Transaction transaction = new Transaction(amount, null);//creates transaction object and adds to list
		transactionHistory.add(transaction);
    }

    public boolean isOverDrawn() {
        if (balance < 0) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        if (balance < 0) {
            balance = (-balance);
            String result = String.format("BankAccount %s: ($%.2f)", getAccountNumber(), getBalance());
            return result;
        } else {
            String result = String.format("BankAccount %s: $%.2f", getAccountNumber(), getBalance());
            return result;
        }
    }

    public void deposit(LocalDateTime transactionTime, double amount, String description) {
        balance = balance + amount;

        Transaction transaction = new Transaction(transactionTime, amount, description);//creates transaction object and adds to list
		transactionHistory.add(transaction);


    }

    public void deposit(double amount, String description) {
        balance = balance + amount;

        Transaction transaction = new Transaction(amount, description);//creates transaction object and adds to list
		transactionHistory.add(transaction);
    }

    public void withdraw(LocalDateTime transactionTime, double amount, String description) {
        balance = balance - amount - withdrawalFee;

        Transaction transaction = new Transaction(transactionTime, amount, description); //creates transaction object and adds to list
		transactionHistory.add(transaction);

    }

    public void withdraw(double amount, String description) {
        balance = balance - amount - withdrawalFee;

        Transaction transaction = new Transaction(amount, description);//creates transaction object and adds to list
		transactionHistory.add(transaction);
    }

    public ArrayList<Transaction> getTransactions(LocalDateTime startTime, LocalDateTime endTime) {
        
        ArrayList<Transaction> sortedTransactionHistory = new ArrayList<Transaction>();

        for(int i = 0; i<transactionHistory.size();i++) { // Removes any transactions outside the specified time range
			if(startTime == null && endTime == null) {

                sortedTransactionHistory.add(transactionHistory.get(i));
                
			} else if(startTime == null && (transactionHistory.get(i).getTransactionTime().isBefore(endTime) || transactionHistory.get(i).getTransactionTime().isEqual(endTime))) {

                sortedTransactionHistory.add(transactionHistory.get(i));
                
			} else if(endTime == null && (transactionHistory.get(i).getTransactionTime().isAfter(startTime) || transactionHistory.get(i).getTransactionTime().isEqual(startTime))) {

                sortedTransactionHistory.add(transactionHistory.get(i));
                
			} else if(startTime != null && endTime != null) {

				if(transactionHistory.get(i).getTransactionTime().isBefore(endTime) && transactionHistory.get(i).getTransactionTime().isAfter(startTime) || transactionHistory.get(i).getTransactionTime().isEqual(startTime) || transactionHistory.get(i).getTransactionTime().isEqual(endTime)){

                    sortedTransactionHistory.add(transactionHistory.get(i));
                    
				}
			}	
        }
        
        sortedTransactionHistory.sort(Comparator.comparing(Transaction :: getTransactionTime)); // comparator sorts transaction into the right chronological order

        return sortedTransactionHistory; // returns an array of transaction within specified time range and listed chronologically

    }

}
