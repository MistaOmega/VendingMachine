package mistaomega.vending.machine;

import mistaomega.vending.items.Currency;
import mistaomega.vending.items.Holder;
import mistaomega.vending.items.Item;

import java.util.List;

public interface IVendingMachine {
     long selectAndShow(Item item);
     Holder<Item, List<Currency>> collectItemAndChange();
     void insertCurrency(Currency currency);
     List<Currency> refund();
     void reset();
}
