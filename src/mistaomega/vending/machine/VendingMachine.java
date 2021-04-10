package mistaomega.vending.machine;

import mistaomega.vending.items.Item;
import mistaomega.vending.items.LoyaltyCard;
import mistaomega.vending.util.SoldOutException;
import mistaomega.vending.util.Utilities;

import java.util.List;

/**
 * Vending machine implementation class, this handles item storage and balance management
 *
 * @author Jack Nash
 */
public class VendingMachine implements IVendingMachine {
    private final Inventory<Item> itemInv = new Inventory<>();
    private final List<LoyaltyCard> loyaltyCards;
    private Item selectedItem;
    private int balance;

    /**
     * Constructor
     */
    public VendingMachine() {
        //Initialise with 5 of each item type

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
     * Applies discount
     *
     * @param originalPrice  original price of item
     * @param discountAmount discount amount in decimal, E.g 0.85 would return an item with a 15% discount
     * @return discounted price
     */
    public double loyaltyDiscount(double originalPrice, double discountAmount) {
        return originalPrice * discountAmount;
    }


    /**
     * Check item stock and return to user
     *
     * @param item Item to check
     * @return Item price, or exception if sold out
     */
    @Override
    public int selectAndShow(Item item) {
        if (itemInv.hasItem(item)) {
            selectedItem = item;
            return selectedItem.getPrice();
        }
        throw new SoldOutException(String.format("Sold out of %s, please select another item", item.toString())); // Caught by HomeUI
    }

    /**
     * add money to balance
     *
     * @param money how much to add
     */
    @Override
    public void insertMoney(int money) {
        balance += money;
    }

    /**
     * Refund balance to user
     *
     * @return balance
     */
    @Override
    public int refund() {
        int returnAmount = balance;
        balance = 0;
        return returnAmount;
    }

    /**
     * Reset selected item
     */
    @Override
    public void reset() {
        selectedItem = null;
    }

    public long getBalance() {
        return balance;
    }

    /**
     * Purchase item, called by HomeUI
     *
     * @param item  Item to purchase
     * @param price Price of item
     * @return Boolean if purchased with no error
     */
    public boolean purchaseItem(Item item, double price) {
        try {
            this.getItemInv().remove(item);
            double roundOff = (double) Math.round(price * 100) / 100;
            balance -= (roundOff); // handled in pennies here, so I multiply by 100
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public Item getSelectedItem() {
        return selectedItem;
    }
}
