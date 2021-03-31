package mistaomega.vending.items;

public class BankAccount {
    public String accountHolder;
    public String accountNumber;
    public String sortCode;
    public float balance;

    public BankAccount(String accountHolder){
        this.accountHolder = accountHolder;
    }
}
