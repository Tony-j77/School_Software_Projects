import java.time.LocalDateTime;
import java.util.ArrayList;

public class BankAccount {
	// variables
	private String accountNumber;
	private double balance;
	private double withdrawalFee;
	private double annualInterestRate;
	
	private ArrayList<Transaction> transactionsList;
	// get variables
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
	// mutators
	public void setAnnualInterestRate(double number) {
		this.annualInterestRate = number;
	}
	
	public void setWithdrawalFee(double number) {
		this.withdrawalFee = number;
	}
	// constructors
	public BankAccount(String accountNumb) {
		this.accountNumber = accountNumb;
		this.transactionsList = new ArrayList<Transaction>(0);
	}
	
	public BankAccount(String accountNumb, double initialBal) {
		this.accountNumber = accountNumb;
		this.balance = initialBal;
		this.transactionsList = new ArrayList<Transaction>(0);
	}
	
	public BankAccount(String accountNumb, double initialBal, double withdrawFee, double annInterest) {
		this.accountNumber = accountNumb;
		this.balance = initialBal;
		this.withdrawalFee = withdrawFee;
		this.annualInterestRate = annInterest;
		this.transactionsList = new ArrayList<Transaction>(0);
	}
	// lazy functions to use
	private void addToTransactionList(Transaction transaction) {
		int isAdded = 0;
		int transPos = this.transactionsList.size();
		LocalDateTime transTime = transaction.getTransactionTime();
		if (transactionsList.isEmpty()) {
			ArrayList<Transaction> transList = new ArrayList<Transaction>();
			transList.add(transaction);
			this.transactionsList=transList;
		} else {
			for (Transaction num : this.transactionsList) {
				if (isAdded == 0 && transTime.isBefore(num.getTransactionTime())) {
					isAdded = 1;
					transPos = this.transactionsList.indexOf(num);
					// slot in the transaction right after the one you compared it to
				}
			}
			this.transactionsList.add(transPos, transaction);
			
		}
	}
	
	// public methods
	public void deposit(double amount) {
		this.balance += amount;
		
		Transaction newTransaction;
		newTransaction = new Transaction(amount, "deposit");
		addToTransactionList(newTransaction);
	}
	
	public void withdraw(double amount) {
		this.balance -= (amount + this.withdrawalFee);
		Transaction newTransaction;
		newTransaction = new Transaction(amount, "withdrawl");
		addToTransactionList(newTransaction);
		
	}
	public boolean isOverDrawn() {
		if (this.balance < 0) {
			return true;
		} else {
			return false;
		}
	}

	public String toString() {
		
		String result = "0";
		
		if(this.balance >= 0) {
			result = "$" + String.format("%.2f", this.balance);
		} else {
			this.balance *= -1;
			result = "($" + String.format("%.2f", this.balance) + ")";
		}
		return "BankAccount " + accountNumber + ": " + result; 
		
	}


	public void deposit(LocalDateTime transactionTime, double amount, String description) {
		this.balance += amount;
		
		Transaction newTransaction;
		newTransaction = new Transaction(transactionTime ,amount, description);
		addToTransactionList(newTransaction);
	}	
	
	public void deposit(double amount, String description) {
		this.balance += amount;
		
		Transaction newTransaction;
		newTransaction = new Transaction(amount, description);
		addToTransactionList(newTransaction);
		
	}
	
	public void withdraw(LocalDateTime transactionTime, double amount, String description) {
		this.balance -= (amount + this.withdrawalFee);
		
		Transaction newTransaction;
		newTransaction = new Transaction(transactionTime, amount, description);
		addToTransactionList(newTransaction);
	}
	
	public void withdraw(double amount, String description) {
		this.balance -= (amount + this.withdrawalFee);
		
		Transaction newTransaction;
		newTransaction = new Transaction(amount, description);
		addToTransactionList(newTransaction);
	}
	
	
	//note to self: start time == old time and endTime == new time
	public ArrayList<Transaction> getTransactions(LocalDateTime startTime, LocalDateTime endTime) {
		ArrayList<Transaction> outputTrans = new ArrayList<Transaction>(0);
		
		if (startTime != null && endTime != null) {
			for (Transaction num : this.transactionsList) {
				if ((num.getTransactionTime().isAfter(startTime) == true || num.getTransactionTime().isEqual(startTime)) && (num.getTransactionTime().isBefore(endTime) || num.getTransactionTime().isEqual(endTime))) {
					outputTrans.add(num);
				}
 			}
		} else if (startTime == null && endTime != null) {
			for (Transaction num : this.transactionsList) {
				if ((num.getTransactionTime().isBefore(endTime) || num.getTransactionTime().isEqual(endTime))) {
					outputTrans.add(num);
				}
			}
		} else if (startTime != null && endTime == null) {
			for (Transaction num : this.transactionsList) {
				if ((num.getTransactionTime().isAfter(startTime) == true || num.getTransactionTime().isEqual(startTime))) {
					outputTrans.add(num);
				}
			}
		} else {
			for (Transaction num: this.transactionsList) {
				outputTrans.add(num);
			}
		}
		return outputTrans;
	}
}
