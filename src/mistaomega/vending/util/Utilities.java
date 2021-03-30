package mistaomega.vending.util;

import javax.swing.*;

public class Utilities {

    /**
     * Convert item cost in pence to item cost in pounds, return as string
     * @param value item cost in pence
     * @return string conversion of item cost in pounds
     */
    public static String currencyPrinter(int value){
        return String.valueOf((float)value/100);
    }

    /**
     * Generic ListModel initializer
     * @param list input list
     * @param <T> list item type
     * @return list model of list's type
     */
    public static<T> DefaultListModel<?> InitListModel(JList<T> list){
        if (list.getModel().getSize() == 0) {
            DefaultListModel<T> listModel = new DefaultListModel<>();
            list.setModel(listModel);
        }
        return (DefaultListModel<T>) list.getModel();
    }
}
