package mistaomega.vending.machine;

import mistaomega.vending.items.Currency;
import mistaomega.vending.items.Holder;
import mistaomega.vending.items.Item;
import mistaomega.vending.items.LoyaltyCard;
import mistaomega.vending.util.InsufficientChangeException;
import mistaomega.vending.util.SoldOutException;
import mistaomega.vending.util.Utilities;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine implements IVendingMachine {
    private final Inventory<Currency> currencyInv = new Inventory<>();
    private final Inventory<Item> itemInv = new Inventory<>();
    private final List<LoyaltyCard> loyaltyCards;
    private Item selectedItem;
    private long balance;

    public VendingMachine() {
        //Initialise with 5 of each currency type, and 5 of each item type
        for (Currency c : Currency.values()) {
            currencyInv.put(c, 5);
        }

        for (Item i : Item.values()) {
            itemInv.put(i, 5);
        }
        loyaltyCards = Utilities.generateLoyalty();
    }

    public Inventory<Item> getItemInv() {
        return itemInv;
    }

    public List<LoyaltyCard> getLoyaltyCards() {
        return loyaltyCards;
    }

    /**
     * Generate a list of currency to return to the user
     *
     * @param requiredChange amount the user needs to have returned to them
     * @return a list of currency for the user to take
     */
    public List<Currency> checkChange(double requiredChange) {
        List<Currency> change = new ArrayList<>();
        if (requiredChange > 0) {
            double balance = requiredChange;
            while (balance > 0) {
                if (balance >= Currency.TWOPOUND.getValue()
                        && currencyInv.hasItem(Currency.TWOPOUND)) {
                    change.add(Currency.TWOPOUND);
                    balance = balance - Currency.TWOPOUND.getValue();

                } else if (balance >= Currency.POUND.getValue()
                        && currencyInv.hasItem(Currency.POUND)) {
                    change.add(Currency.POUND);
                    balance = balance - Currency.POUND.getValue();

                } else if (balance >= Currency.FIFTYPENCE.getValue()
                        && currencyInv.hasItem(Currency.FIFTYPENCE)) {
                    change.add(Currency.FIFTYPENCE);
                    balance = balance - Currency.FIFTYPENCE.getValue();

                } else if (balance >= Currency.TWENTYPENCE.getValue()
                        && currencyInv.hasItem(Currency.TWENTYPENCE)) {
                    change.add(Currency.TWENTYPENCE);
                    balance = balance - Currency.TWENTYPENCE.getValue();

                } else if (balance >= Currency.TENPENCE.getValue()
                        && currencyInv.hasItem(Currency.TENPENCE)) {
                    change.add(Currency.TENPENCE);
                    balance = balance - Currency.TENPENCE.getValue();

                } else if (balance >= Currency.FIVEPENCE.getValue()
                        && currencyInv.hasItem(Currency.FIVEPENCE)) {
                    change.add(Currency.FIVEPENCE);
                    balance = balance - Currency.FIVEPENCE.getValue();

                } else if (balance >= Currency.TWOPENCE.getValue()
                        && currencyInv.hasItem(Currency.TWOPENCE)) {
                    change.add(Currency.TWOPENCE);
                    balance = balance - Currency.TWOPENCE.getValue();

                } else if (balance >= Currency.PENNY.getValue()
                        && currencyInv.hasItem(Currency.PENNY)) {
                    change.add(Currency.PENNY);
                    balance = balance - Currency.PENNY.getValue();

                } else {
                    throw new InsufficientChangeException("Insufficient change in the machine, Please try another product");
                }
            }
        }

        return change;
    }

    /**
     * Applies discount
     *
     * @param originalPrice  original price of item
     * @param discountAmount discount amount in decimal, E.g 0.85 would return an item with a 15% discount
     * @return discounted price
     */
    public double loyaltyDiscount(double originalPrice, double discountAmount) {
        return originalPrice * discountAmount;
    }


    @Override
    public int selectAndShow(Item item) {
        if (itemInv.hasItem(item)) {
            selectedItem = item;
            return selectedItem.getPrice();
        }
        throw new SoldOutException(String.format("Sold out of %s, please select another item", item.toString())); // Caught by HomeUI
    }

    @Override
    public Holder<Item, List<Currency>> collectItemAndChange() {
        return null;
    }

    @Override
    public void insertCurrency(Currency currency) {
        balance = balance + currency.getValue();
        currencyInv.add(currency);
    }

    @Override
    public List<Currency> refund() {
        return null;
    }

    @Override
    public void reset() {
    }


    public Item getSelectedItem() {
        return selectedItem;
    }
}
