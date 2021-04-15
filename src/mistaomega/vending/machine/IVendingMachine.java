package mistaomega.vending.machine;

import mistaomega.vending.items.Item;

/**
 * Interface for vending machine
 *
 * @author Jack Nash
 */
public interface IVendingMachine {
     float selectAndShow(Item item);

     void insertMoney(float money);

     float refund();

     void reset();
}
