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
    private JTextField tfLoyaltyBalance;
    private JTextField tfInsertMoney;
    private JTextField tfChange;
    private JButton btnInsertMoney;
    private JButton btnCollectChange;
    private JButton btnPurchase;
    private final VendingMachine vendingMachine;


    public HomeUI(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
        btnPurchase.addActionListener(e -> handlePurchase());
        btnInsertMoney.addActionListener(e -> {
            vendingMachine.
        });
    }

    public void handlePurchase() {
        //first if there's any item left

        if (vendingMachine.getItemInv().getItemCount(vendingMachine.getSelectedItem()) <= 0) {
            throw new SoldOutException("No more of item: " + vendingMachine.getSelectedItem());
        }
        // Now let's buy with loyalty
        if (!tfLoyalty.getText().isEmpty()) {
            for (LoyaltyCard loyaltyCard : vendingMachine.getLoyaltyCards()) {
                if (loyaltyCard.getCardNumber().equals(tfLoyalty.getText())) {
                    System.out.println(vendingMachine.getSelectedItem().getPrice());
                    double discountedPrice = vendingMachine.loyaltyDiscount((double) vendingMachine.getSelectedItem().getPrice() / 100, 0.85);
                    System.out.println(discountedPrice);
                    if (loyaltyCard.getBankAccount().getBalance() >= discountedPrice) {
                        loyaltyCard.getBankAccount().removeBalance(discountedPrice);
                        tfLoyaltyBalance.setText("Loyalty Card Balance: " + loyaltyCard.getBankAccount().getBalance());
                        return;
                    }
                }

            }
        }
        tfInfo.setText("Loyalty card not found, trying to use cash");
        // Attempt to use cash balance

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

    public void initialize() {
        // Initialize the list model with a DefaultListModel for Items
        DefaultListModel<String> listModel = Utilities.InitListModel(lstItems);

        // Add the items to the items list
        for (Item i : Item.values()) {
            listModel.addElement(i + " " + i.getCode());
        }
        ButtonListener listener = new ButtonListener();

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
        btnClearLoyal.addActionListener(e -> {
            tfLoyalty.setText("");
            tfLoyaltyBalance.setText("");
        });
        btnClear.addActionListener(e -> {
            listener.setNumberString("");
            tfCodeEntry.setText("");
            tfInfo.setText("");
        });
        addChangeListener(tfLoyalty, e -> {

            btnInsertMoney.setEnabled(tfLoyalty.getText().isEmpty());

            tfLoyaltyBalance.setText("");
            if (tfLoyalty.getText().length() == 16) {
                for (LoyaltyCard loyaltyCard : vendingMachine.getLoyaltyCards()) {
                    if (tfLoyalty.getText().equals(loyaltyCard.getCardNumber())) {
                        tfLoyaltyBalance.setText("Loyalty Card Balance: " + loyaltyCard.getBankAccount().getBalance());
                        break;
                    }
                }
            }
        });
        btnSubmit.addActionListener(e -> {
            for (Item i : Item.values()) {
                if (i.getCode().equals(tfCodeEntry.getText())) {
                    try {
                        int price = vendingMachine.selectAndShow(i);
                        tfInfo.setText(i + " selected, price is: £" +
                                Utilities.currencyPrinter(price) +
                                " there are " +
                                vendingMachine.getItemInv().getItemCount(i) +
                                " left.");
                    } catch (SoldOutException exception) {
                        tfInfo.setText(exception.getMessage());
                        //exception.printStackTrace();
                    }
                }
            }
        });
    }


    /**
     * Class for listening to button inputs
     */
    class ButtonListener implements ActionListener {
        private String numberString = "";

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

        public void setNumberString(String numberString) {
            this.numberString = numberString;
        }
    }
}
