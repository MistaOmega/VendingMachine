package mistaomega.vending.items;

/**
 * An enum to hold all coin types.
 *
 * @author Jack Nash
 */
public enum Currency {
    PENNY (1),
    TWOPENCE(2),
    FIVEPENCE(5),
    TENPENCE(10),
    TWENTYPENCE(20),
    FIFTYPENCE(50),
    POUND(100),
    TWOPOUND(200),
    FIVEPOUND(500),
    TENPOUND(1000),
    TWENTYPOUND(2000),
    FIFTYPOUND(5000);

    private final int value;

    private Currency(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
