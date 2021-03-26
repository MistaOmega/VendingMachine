package mistaomega.vending.machine;

import java.util.HashMap;
import java.util.Map;

/**
 * This class holds items like an inventory
 * @param <T>
 */
public class Inventory <T>{
    private final Map<T, Integer> inventory = new HashMap<>();

    public int getItemCount(T item){
        Integer value = inventory.get(item);
        return value == null ? 0 : value;
    }

    public void add (T item){
        int count = inventory.get(item);
        inventory.put(item, count+1);
    }

    public void remove(T item){
        if (!hasItem(item)) {
            return;
        }
        int count = inventory.get(item);
        inventory.put(item, count-1);
    }

    public boolean hasItem(T item){
        return getItemCount(item) > 0;
    }

    public void clearAll(){
        inventory.clear();
    }

    public void put(T item, int quantity) {
        inventory.put(item, quantity);
    }

}
