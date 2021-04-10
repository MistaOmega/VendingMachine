package mistaomega.vending.util;

import mistaomega.vending.items.BankAccount;
import mistaomega.vending.items.LoyaltyCard;

import javax.swing.*;
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Common utilities class
 *
 * @author Jack Nash
 */
public class Utilities {

    /**
     * Serializes given loyalty cards to file
     *
     * @param loyaltyCards Array of loyalty cards
     * @param path         Given path
     */
    public static void SerializeLoyalty(List<LoyaltyCard> loyaltyCards, File path) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
            objectOutputStream.writeObject(loyaltyCards);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deserializes loyalty card array from file
     *
     * @param file file to deserialize
     * @return ArrayList of loyalty cards gathered from file
     */
    @SuppressWarnings("unchecked")
    public static ArrayList<LoyaltyCard> DeserializeLoyalty(File file) {
        try {
            //Creating stream to read the object
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            ArrayList<LoyaltyCard> loyaltyCards = (ArrayList<LoyaltyCard>) objectInputStream.readObject();
            objectInputStream.close();
            return loyaltyCards;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // shouldn't be reached
    }

    /**
     * Generates a loyalty card to test loyalty card functionality
     *
     * @return collection of loyalty cards
     */
    public static List<LoyaltyCard> generateLoyalty() {
        BankAccount bankAccount = new BankAccount("John Smith", "00000000", "00-00-00", 105.50);
        LoyaltyCard loyaltyCard = new LoyaltyCard("John Smith", bankAccount);
        System.out.println(loyaltyCard.getCardNumber());
        List<LoyaltyCard> loyaltyCardList = new ArrayList<>();
        loyaltyCardList.add(loyaltyCard);
        return loyaltyCardList;
    }

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

    /**
     * Generates a random number with length "length"
     *
     * @param length number input length
     * @return generated card number
     */
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
