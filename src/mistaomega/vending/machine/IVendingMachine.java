package mistaomega.vending.machine;

import mistaomega.vending.items.Currency;
import mistaomega.vending.items.Holder;
import mistaomega.vending.items.Item;

import java.util.List;

public interface IVendingMachine {
    public long selectAndShow(Item item);
    public Holder<Item, List<Currency>> collectItemAndChange();
    public void insertCurrency(Currency currency);
    public List<Currency> refund();
    public void reset();
}
