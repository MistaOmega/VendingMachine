package mistaomega.vending.machine;

import mistaomega.vending.items.Currency;
import mistaomega.vending.items.Holder;
import mistaomega.vending.items.Item;

import java.util.List;

public class VendingMachine implements IVendingMachine{
    @Override
    public long selectAndShow(Item item) {
        return 0;
    }

    @Override
    public Holder<Item, List<Currency>> collectItemAndChange() {
        return null;
    }

    @Override
    public void insertCurrency(Currency currency) {

    }

    @Override
    public List<Currency> refund() {
        return null;
    }

    @Override
    public void reset() {

    }
}
