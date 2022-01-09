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

    }

    public void setWithdrawalFee(double fee) {

    }

    public BankAccount(String number) {

    }

    public BankAccount(String number, double balance) {

    }

    public BankAccount(String number, double balance, double fee, double rate) {

    }
 
    public void deposit(double amount) {

    }

    public void withdraw(double amount) {

    }

    public boolean isOverDrawn() {
        if (balance < 0) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {

        String result = String.format("Bankaccount %s: (%0.2f)", getAccountNumber(), getBalance());
        return result;
    }
}
