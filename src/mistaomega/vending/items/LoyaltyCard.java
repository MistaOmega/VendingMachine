package mistaomega.vending.items;

import mistaomega.vending.util.Utilities;

public class LoyaltyCard {
    private final String cardNumber;
    private final BankAccount bankAccount;
    private String cardOwner;

    public LoyaltyCard(String cardOwner, BankAccount bankAccount) {
        this.cardOwner = cardOwner;
        this.bankAccount = bankAccount;
        this.cardNumber = Utilities.generateCardNumber(15);
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardOwner() {
        return cardOwner;
    }

    public void setCardOwner(String cardOwner) {
        this.cardOwner = cardOwner;
    }

}
