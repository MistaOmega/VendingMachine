package mistaomega.vending.items;

public enum Item {
    COKE("Coke", 100, "01B7"), PEPSI("Pepsi", 105, "01A1"), FANTA("fanta", 101, "01A2"), IRNBRU("irn-bru", 2105, "01B1");

    private final String name;
    private final int price;
    private final String code;

    private Item(String name, int price, String code){
        this.name = name;
        this.price = price;
        this.code = code;
    }

    public String getName(){
        return name;
    }

    public long getPrice(){
        return price;
    }

    @Override
    public String toString() {
        return name + " CODE: " + code;
    }
}
