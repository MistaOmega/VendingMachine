package mistaomega.vending.items;

public class BankAccount {
    private final String accountHolder;
    private final String accountNumber;
    private final String sortCode;
    private float balance;

    public BankAccount(String accountHolder, String accountNumber, String sortCode, float balance) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.sortCode = sortCode;
        this.balance = balance;
    }

    public void removeBalance(float amount) {
        setBalance(balance - amount);
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
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
