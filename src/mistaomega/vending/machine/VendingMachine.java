package mistaomega.vending.machine;

import mistaomega.vending.items.Currency;
import mistaomega.vending.items.Holder;
import mistaomega.vending.items.Item;
import mistaomega.vending.util.SoldOutException;

import java.util.List;

public class VendingMachine implements IVendingMachine {
    private final Inventory<Currency> currencyInv = new Inventory<>();
    private final Inventory<Item> itemInv = new Inventory<>();
    private long salesMade;
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
    }

    public Inventory<Item> getItemInv() {
        return itemInv;
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
}
