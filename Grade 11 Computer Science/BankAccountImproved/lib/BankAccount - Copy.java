import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;


public class BankAccount {
	private String accountNumber;
	private double balance;
	private double withdrawalFee;
	private double annualInterestRate;
	
	//list that will keep all transactions
	private ArrayList<Transaction> transactions= new ArrayList<>();
	
	//Accessors
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
	
	//Mutators
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public void setWithdrawalFee(double withdrawalFee) {
		this.withdrawalFee = withdrawalFee;
	}
	
	public void setAnnualInterestRate(double annualInterestRate) {
		this.annualInterestRate = annualInterestRate;
	}
	
	//Constructors
	public BankAccount(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public BankAccount(String accountNumber, double initialBalance) {
		this.accountNumber = accountNumber;
		this.balance = initialBalance;
	}
	
	public BankAccount(String accountNumber, double initialBalance, double withdrawalFee, double annualInterestRate) {
		this.accountNumber = accountNumber;
		this.balance = initialBalance;
		this.withdrawalFee = withdrawalFee;
		this.annualInterestRate = annualInterestRate;
	}
	
	//Deposite method takes time, amount, and description parameters
	public void deposit(LocalDateTime transactionTime, double amount, String description) {
		//creates transaction object and adds to transaction list
		Transaction transaction = new Transaction(transactionTime, amount, description);
		transactions.add(transaction);
		//add amount to balance
		balance += amount;
	}
	
	//Deposit method takes  amount and description parameters
	public void deposit(double amount, String description) {
		//creates transaction object and adds to list of transactions
		Transaction transaction = new Transaction(amount, description);
		transactions.add(transaction);
		//add amount to balance
		balance += amount;
	}
	
	//Deposit method takes amount parameter
	public void deposit(double amount){
		//creates transaction object and adds to list
		Transaction transaction = new Transaction(amount, null);
		transactions.add(transaction);
		//add amount to balance
		balance += amount;
	}
	
	//Withdraw method takes time, amount, and description parameters
	public void withdraw(LocalDateTime transactionTime, double amount, String description) {
		//create transaction object and adds to list of transactions
		Transaction transaction = new Transaction(transactionTime, amount, description);
		transactions.add(transaction);
		//subtracts from balance
		balance -= (amount + withdrawalFee);
	}
	
	//Withdraw method takes amount and description parameters
	public void withdraw(double amount, String description) {
		//creates transaction object and adds to list
		Transaction transaction = new Transaction(amount, description);
		transactions.add(transaction);
		//subtract from balance
		balance -= (amount + withdrawalFee);
	}
	
	//Withdraw method takes amount parameter
	public void withdraw(double amount) {
		//creates transaction object and adds to list
		Transaction transaction = new Transaction(amount, null);
		transactions.add(transaction);
		//subtract from balance
		balance -= (amount + withdrawalFee);
	}
	
	//check if balance is less than zero
	public boolean isOverDrawn() {
		if(balance < 0)
			return true;
		return false;
	}
	
	//getTransactions method takes startTime and endTime parameters
	public <T> ArrayList<Transaction> getTransactions(LocalDateTime startTime, LocalDateTime endTime){
		//create list for sorted transactions
		ArrayList<Transaction> sorted = new ArrayList<Transaction>();
		
		//remove the transactions that are outside the time range
		for(int i = 0; i<transactions.size();i++) {
			if(startTime == null && endTime == null) {
				sorted.add(transactions.get(i));
			} else if(startTime == null && (transactions.get(i).getTransactionTime().isBefore(endTime) || transactions.get(i).getTransactionTime().isEqual(endTime))) {
				sorted.add(transactions.get(i));
			} else if(endTime == null && (transactions.get(i).getTransactionTime().isAfter(startTime) || transactions.get(i).getTransactionTime().isEqual(startTime))) {
				sorted.add(transactions.get(i));
			} else if(startTime != null && endTime != null) {
				if(transactions.get(i).getTransactionTime().isBefore(endTime) && transactions.get(i).getTransactionTime().isAfter(startTime) || transactions.get(i).getTransactionTime().isEqual(startTime) || transactions.get(i).getTransactionTime().isEqual(endTime)){
					sorted.add(transactions.get(i));
				}
			}	
		}
		//System.out.println(sorted);
		
		//sort transaction items by transaction time using comparator
		sorted.sort(Comparator.comparing(Transaction :: getTransactionTime));
		
		//System.out.println(sorted);
		
		//return sorted list
		return sorted;
	}
	
	//return string with bank account details
	public String toString() {
		String bal, formattedBalance;
		
		//if balance is less than zero
		if(isOverDrawn()) {
			formattedBalance = String.format("%.02f", -balance);
			bal = "($" + formattedBalance + ")";
		}
		else { //if balance greater than or equal to 0
			formattedBalance = String.format("%.02f", balance);
			bal = "$" + formattedBalance;
		}
		
		//return string message
		return "BankAccount " + accountNumber + ": " + bal;
	}
}
