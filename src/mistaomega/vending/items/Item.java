package mistaomega.vending.items;

public enum Item {
    COKE("Coke", 100), PEPSI("Pepsi", 105), FANTA("fanta", 101), IRNBRU("irn-bru", 2105);

    private final String name;
    private final int price;

    private Item(String name, int price){
        this.name = name;
        this.price = price;
    }

    public String getName(){
        return name;
    }

    public long getPrice(){
        return price;
    }

    @Override
    public String toString() {
        return name;
    }
}
