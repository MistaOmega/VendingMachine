package mistaomega.vending.util;

import javax.swing.*;
import java.text.DecimalFormat;
import java.util.Random;

public class Utilities {

    /**
     * Convert item cost in pence to item cost in pounds, return as string
     *
     * @param value item cost in pence
     * @return string conversion of item cost in pounds
     */
    public static String currencyPrinter(float value) {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(value / 100);
    }

    /**
     * Generic ListModel initializer
     *
     * @param list input list
     * @param <T>  list item type
     * @return list model of list's type
     */
    public static <T> DefaultListModel<T> InitListModel(JList<T> list) {
        if (list.getModel().getSize() == 0) {
            DefaultListModel<T> listModel = new DefaultListModel<>();
            list.setModel(listModel);
        }
        return (DefaultListModel<T>) list.getModel();
    }

    /**
     * Used for validating card numbers using Luhns Algorithm
     * Assigns the check digit required to pass Luhns Algorithm
     *
     * @param number input number
     * @return digit required to pass Luhn's Algorithm
     */
    public static int assignCheckDigit(String number) {
        int sum = 0;
        int remainder = (number.length() + 1) % 2;
        for (int i = 0; i < number.length(); i++) {

            int digit = Integer.parseInt(number.substring(i, (i + 1)));

            if ((i % 2) == remainder) {
                digit = digit * 2;
                if (digit > 9) {
                    digit = (digit / 10) + (digit % 10);
                }
            }
            sum += digit;
        }

        int mod = sum % 10;

        return ((mod == 0) ? 0 : 10 - mod);
    }

    public static String generateCardNumber(int length) {
        Random random = new Random(System.currentTimeMillis());

        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int digit = random.nextInt(10);
            buffer.append(digit);
        }

        int checkDigit = assignCheckDigit(buffer.toString());
        buffer.append(checkDigit);

        return buffer.toString();
    }
}
