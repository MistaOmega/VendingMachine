package mistaomega.vending.items;

public class BankAccount {
    private final String accountHolder;
    private final String accountNumber;
    private final String sortCode;
    private double balance;

    public BankAccount(String accountHolder, String accountNumber, String sortCode, double balance) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.sortCode = sortCode;
        this.balance = balance;
    }

    public void removeBalance(double amount) {
        setBalance(balance - amount);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getSortCode() {
        return sortCode;
    }
}
