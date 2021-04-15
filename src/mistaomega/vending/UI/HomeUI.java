package mistaomega.vending.UI;

import mistaomega.vending.items.Item;
import mistaomega.vending.items.LoyaltyCard;
import mistaomega.vending.machine.VendingMachine;
import mistaomega.vending.util.SoldOutException;
import mistaomega.vending.util.Utilities;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.util.Objects;

/**
 * UI controller class
 *
 * @author Jack Nash
 */
public class HomeUI {
    private JLabel TitleLabel;
    private JButton btn2;
    private JButton btn6;
    private JButton btn7;
    private JButton btn8;
    private JButton btn5;
    private JButton btn9;
    private JButton btn1;
    private JButton btn4;
    private JButton btn3;
    private JPanel mainPanel;
    private JButton btn0;
    private JButton btnA;
    private JButton btnB;
    private JList<String> lstItems;
    private JTextField tfCodeEntry;
    private JButton btnClear;
    private JTextField tfLoyalty;
    private JButton btnClearLoyal;
    private JButton btnSubmit;
    private JTextField tfInfo;
    private JTextField tfBalance;
    private JTextField tfInsertMoney;
    private JTextField tfChange;
    private JButton btnInsertMoney;
    private JButton btnCollectChange;
    private JButton btnPurchase;
    private JLabel lblOut;
    private JLabel lblBal;
    private final VendingMachine vendingMachine;

    /**
     * Constructor
     *
     * @param vendingMachine vending machine the UI communicates with
     */
    public HomeUI(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;

        // handle money input
        btnInsertMoney.addActionListener(e -> {
            try {
                vendingMachine.insertMoney(Float.parseFloat(tfInsertMoney.getText()));
                if (vendingMachine.getBalance() > 0) {
                    tfLoyalty.setEnabled(false);
                    tfBalance.setText("£" + Utilities.currencyPrinter(vendingMachine.getBalance()));
                }
            } catch (NumberFormatException numberFormatException) {
                tfInfo.setText("Numbers only when inserting money, dumb dumb");
            }
        });
        // change collection
        btnCollectChange.addActionListener(e -> {
            tfInfo.setText(Utilities.currencyPrinter(vendingMachine.refund()) + " returned to you ");
            tfLoyalty.setEnabled(true);
        });
    }

    /**
     * This function is taken from StackOverflow, by user "Boann"
     *
     * @param text           any text component, such as a {@link JTextField}
     *                       or {@link JTextArea}
     * @param changeListener a listener to receive {@link ChangeEvent}s
     *                       when the text is changed; the source object for the events
     *                       will be the text component
     * @throws NullPointerException if either parameter is null
     * @see <a href="https://stackoverflow.com/a/27190162">Change Listener Code</a>
     * @see <a href="https://stackoverflow.com/users/964243/boann">Boann's Profile</a>
     * <p>
     * Installs a listener to receive notification when the text of any
     * {@code JTextComponent} is changed. Internally, it installs a
     * {@link DocumentListener} on the text component's {@link Document},
     * and a {@link ChangeListener} on the text component to detect
     * if the {@code Document} itself is replaced.
     */
    public static void addChangeListener(JTextComponent text, ChangeListener changeListener) {
        Objects.requireNonNull(text);
        Objects.requireNonNull(changeListener);
        DocumentListener dl = new DocumentListener() {
            private int lastChange = 0, lastNotifiedChange = 0;

            @Override
            public void insertUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                lastChange++;
                SwingUtilities.invokeLater(() -> {
                    if (lastNotifiedChange != lastChange) {
                        lastNotifiedChange = lastChange;
                        changeListener.stateChanged(new ChangeEvent(text));
                    }
                });
            }
        };
        text.addPropertyChangeListener("document", (PropertyChangeEvent e) -> {
            Document d1 = (Document) e.getOldValue();
            Document d2 = (Document) e.getNewValue();
            if (d1 != null) d1.removeDocumentListener(dl);
            if (d2 != null) d2.addDocumentListener(dl);
            dl.changedUpdate(null);
        });
        Document d = text.getDocument();
        if (d != null) d.addDocumentListener(dl);
    }


    public JPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * Handle the purchase of an item
     *
     * @param listener ButtonListener so I can clear the item code later
     */
    public void handlePurchase(ButtonListener listener) {
        //first if there's any item left

        if (vendingMachine.getItemInv().getItemCount(vendingMachine.getSelectedItem()) <= 0) { // this shouldn't ever be reached.
            throw new SoldOutException("No more of item: " + vendingMachine.getSelectedItem());
        }
        // Now let's buy with loyalty
        if (!tfLoyalty.getText().isEmpty()) {
            for (LoyaltyCard loyaltyCard : vendingMachine.getLoyaltyCards()) {
                Item item = vendingMachine.getSelectedItem();
                float itemPrice = item.getPrice();
                if (loyaltyCard.getCardNumber().equals(tfLoyalty.getText())) {
                    float discountedPrice = vendingMachine.loyaltyDiscount(itemPrice / 100, 0.85f);
                    System.out.println(discountedPrice);
                    if (loyaltyCard.getBankAccount().getBalance() >= discountedPrice) {
                        loyaltyCard.getBankAccount().removeBalance(discountedPrice);
                        tfBalance.setText("£" + Utilities.currencyPrinter(loyaltyCard.getBankAccount().getBalance()) + " 15% Discount Applied");
                        vendingMachine.getItemInv().remove(item);
                        tfInfo.setText("Purchase completed successfully, enjoy!");
                    }
                    else{
                        tfInfo.setText("Insufficient balance on loyalty card, insert money to purchase");
                        return;
                    }
                }

            }
        } else {
            tfInfo.setText("Loyalty card not found, trying to use cash");
            // Attempt to use cash balance
            if (vendingMachine.getBalance() <= vendingMachine.getSelectedItem().getPrice() / 100) {
                tfInfo.setText("Insufficient balance, insert money to purchase");
            }
            else {
                boolean purchased = vendingMachine.purchaseItem(vendingMachine.getSelectedItem(), vendingMachine.getSelectedItem().getPrice() / 100);
                if (purchased) {
                    tfInfo.setText("Purchase completed successfully, enjoy!");
                }
            }
        }
        tfCodeEntry.setText("");
        listener.setNumberString("");
        vendingMachine.reset();
    }

    /**
     * Init class
     */
    public void initialize() {
        // Initialize the list model with a DefaultListModel for Items
        DefaultListModel<String> listModel = Utilities.InitListModel(lstItems);

        // Add the items to the items list
        for (Item i : Item.values()) {
            listModel.addElement(i + " " + i.getCode());
        }
        ButtonListener listener = new ButtonListener(); // keypad listener

        btn1.addActionListener(listener);
        btn2.addActionListener(listener);
        btn3.addActionListener(listener);
        btn4.addActionListener(listener);
        btn5.addActionListener(listener);
        btn6.addActionListener(listener);
        btn7.addActionListener(listener);
        btn8.addActionListener(listener);
        btn9.addActionListener(listener);
        btn0.addActionListener(listener);
        btnA.addActionListener(listener);
        btnB.addActionListener(listener);

        //clear loyalty info button
        btnClearLoyal.addActionListener(e -> {
            tfLoyalty.setText("");
            tfBalance.setText("");
        });
        // clear drink button listener
        btnClear.addActionListener(e -> {
            listener.setNumberString("");
            tfCodeEntry.setText("");
            tfInfo.setText("");
        });

        // textfield text changed listener for loyalty card entry
        addChangeListener(tfLoyalty, e -> {
            btnInsertMoney.setEnabled(tfLoyalty.getText().isEmpty()); // turn off cash if trying to use loyalty
            tfBalance.setText(""); // balance empty
            if (tfLoyalty.getText().length() == 16) {
                for (LoyaltyCard loyaltyCard : vendingMachine.getLoyaltyCards()) { // get loyalty card and balance and display
                    if (tfLoyalty.getText().equals(loyaltyCard.getCardNumber())) {
                        tfBalance.setText("£" + Utilities.currencyPrinter(loyaltyCard.getBankAccount().getBalance()) + " 15% Discount Applied");
                        break;
                    }
                }
            }
        });

        // button handle for submit button
        btnSubmit.addActionListener(e -> {
            for (Item i : Item.values()) { // get all items
                if (i.getCode().equals(tfCodeEntry.getText())) { // check if item code is the same as the item
                    try {
                        float price = vendingMachine.selectAndShow(i); // select item
                        tfInfo.setText(i + " selected, price is: £" + // display info
                                Utilities.currencyPrinter(price/100) +
                                " there are " +
                                vendingMachine.getItemInv().getItemCount(i) +
                                " left.");
                    } catch (SoldOutException exception) {
                        tfInfo.setText(exception.getMessage()); // display if sold out
                        //exception.printStackTrace();
                    }
                }
            }
        });
        btnPurchase.addActionListener(e -> handlePurchase(listener));

    }

    /**
     * Class for listening to button inputs
     */
    class ButtonListener implements ActionListener {
        private String numberString = "";

        /**
         * Action for handling keypad input
         * maximum of a 4 digit code
         *
         * @param e button press
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (numberString.length() < 4) {
                if (e.getSource() == btn1) {
                    numberString += "1";
                    tfCodeEntry.setText(numberString);
                } else if (e.getSource() == btn2) {
                    numberString += "2";
                    tfCodeEntry.setText(numberString);
                } else if (e.getSource() == btn3) {
                    numberString += "3";
                    tfCodeEntry.setText(numberString);
                } else if (e.getSource() == btn4) {
                    numberString += "4";
                    tfCodeEntry.setText(numberString);
                } else if (e.getSource() == btn5) {
                    numberString += "5";
                    tfCodeEntry.setText(numberString);
                } else if (e.getSource() == btn6) {
                    numberString += "6";
                    tfCodeEntry.setText(numberString);
                } else if (e.getSource() == btn7) {
                    numberString += "7";
                    tfCodeEntry.setText(numberString);
                } else if (e.getSource() == btn8) {
                    numberString += "8";
                    tfCodeEntry.setText(numberString);
                } else if (e.getSource() == btn9) {
                    numberString += "9";
                    tfCodeEntry.setText(numberString);
                } else if (e.getSource() == btn0) {
                    numberString += "0";
                    tfCodeEntry.setText(numberString);
                } else if (e.getSource() == btnA) {
                    numberString += "A";
                    tfCodeEntry.setText(numberString);
                } else if (e.getSource() == btnB) {
                    numberString += "B";
                    tfCodeEntry.setText(numberString);
                }
            }
        }

        /**
         * for resetting or hardcoding changes to the number string that limits code size input
         *
         * @param numberString input string
         */
        public void setNumberString(String numberString) {
            this.numberString = numberString;
        }
    }
}
