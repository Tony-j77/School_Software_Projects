//Tony Jiang
//Computer Science 20 
//Henry Wise Wood High School
//2020-2021 semester 1

public class BankAccount {
    
    private String accountNumber; 
    private double balance;
    private double withdrawalFee;
    private double annualInterestRate;

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
        balance = balance + amount; // no depositing fee, so the deposited amount is directly added to balance
    }

    public void withdraw(double amount) {
        balance = balance - amount - withdrawalFee; // when withdrawing, the withdraw fee is deducted from balance as well as the actual amount withdrawn
    }

    public boolean isOverDrawn() { // checks if the user is overdrawing (overdrawing is when balance is less than 0)
        if (balance < 0) { 
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        if (balance < 0) {
            balance = (-balance);
            String result = String.format("BankAccount %s: ($%.2f)", getAccountNumber(), getBalance()); // when the balance is negative, it returns this
            return result;
        } else {
            String result = String.format("BankAccount %s: $%.2f", getAccountNumber(), getBalance()); // when the balance is positive, it returns this
            return result;
        }
    }
}
