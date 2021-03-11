package mistaomega.vending.items;

/**
 * This class will hold both the item bought and the change given to the user
 * @param <E1>
 * @param <E2>
 */
public class Holder <E1, E2> {
    private final E1 firstItem;
    private final E2 secondItem;

    public Holder(E1 firstItem, E2 secondItem){
        this.firstItem = firstItem;
        this.secondItem = secondItem;
    }

    public E1 getFirstItem() {
        return firstItem;
    }

    public E2 getSecondItem() {
        return secondItem;
    }
}
