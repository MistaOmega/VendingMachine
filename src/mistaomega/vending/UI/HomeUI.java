package mistaomega.vending.UI;

import mistaomega.vending.items.Item;
import mistaomega.vending.machine.VendingMachine;
import mistaomega.vending.util.SoldOutException;
import mistaomega.vending.util.Utilities;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private final VendingMachine vendingMachine;


    public HomeUI(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    public void initialize() {
        // Initialize the list model with a DefaultListModel for Items
        DefaultListModel<String> listModel = Utilities.InitListModel(lstItems);

        // Add the items to the items list
        for (Item i : Item.values()){
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

    public JPanel getMainPanel() {
        return mainPanel;
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
