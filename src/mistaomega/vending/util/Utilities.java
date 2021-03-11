package mistaomega.vending.util;

public class Utilities {

    /**
     * Convert item cost in pence to item cost in pounds, return as string
     * @param value item cost in pence
     * @return string conversion of item cost in pounds
     */
    public static String currencyPrinter(int value){
        return String.valueOf((float)value/100);
    }
}
