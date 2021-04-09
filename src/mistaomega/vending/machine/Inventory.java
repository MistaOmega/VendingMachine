package mistaomega.vending.machine;

import java.util.HashMap;
import java.util.Map;

/**
 * This class holds items like an inventory
 *
 * @param <T> Inventory type
 */
public class Inventory<T> {
    private final Map<T, Integer> inventory = new HashMap<>();

    /**
     * Get amount of item stored
     *
     * @param item Item to check
     * @return count of item "item"
     */
    public int getItemCount(T item) {
        Integer value = inventory.get(item);
        return value == null ? 0 : value;
    }

    /**
     * Adds item to inventory
     *
     * @param item Item to add
     */
    public void add(T item) {
        int count = inventory.get(item);
        inventory.put(item, count + 1);
    }

    /**
     * remove item from inventory
     *
     * @param item item to remove
     */
    public void remove(T item) {
        if (!hasItem(item)) {
            return;
        }
        int count = inventory.get(item);
        inventory.put(item, count - 1);
    }

    /**
     * Check if item is present
     *
     * @param item Item to check
     * @return True if item is present
     */
    public boolean hasItem(T item) {
        return getItemCount(item) > 0;
    }

    /**
     * Wipe whole inventory
     */
    public void clearAll() {
        inventory.clear();
    }

    /**
     * Put item in with given quantity
     *
     * @param item     Item to add
     * @param quantity Amount to add
     */
    public void put(T item, int quantity) {
        inventory.put(item, quantity);
    }

}
