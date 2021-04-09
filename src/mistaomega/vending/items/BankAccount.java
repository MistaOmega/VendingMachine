package mistaomega.vending.items;

public class BankAccount {
    public String accountHolder;
    public String accountNumber;
    public String sortCode;
    public double balance;

    public BankAccount(String accountHolder, String accountNumber, String sortCode, double balance) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.sortCode = sortCode;
        this.balance = balance;
    }
}
