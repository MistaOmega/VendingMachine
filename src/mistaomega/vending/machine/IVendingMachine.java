package mistaomega.vending.machine;

import mistaomega.vending.items.Item;

public interface IVendingMachine {
     int selectAndShow(Item item);

     void insertMoney(int money);

     int refund();

     void reset();
}
