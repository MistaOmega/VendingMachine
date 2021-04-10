package mistaomega.vending.machine;

import mistaomega.vending.items.Item;

/**
 * Interface for vending machine
 *
 * @author Jack Nash
 */
public interface IVendingMachine {
     int selectAndShow(Item item);

     void insertMoney(int money);

     int refund();

     void reset();
}
