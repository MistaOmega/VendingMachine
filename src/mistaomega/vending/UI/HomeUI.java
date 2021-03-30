package mistaomega.vending.UI;

import mistaomega.vending.items.Item;
import mistaomega.vending.util.Utilities;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    private JButton btnA;
    private JButton btnB;
    private JButton btnC;
    private JList<Item> lstItems;
    private JTextField tfCodeEntry;
    private JButton btnClear;


    public HomeUI() {

    }

    public void initialize(){
        // Initialize the list model with a DefaultListModel for Items
        DefaultListModel<Item> listModel = (DefaultListModel<Item>) Utilities.InitListModel(lstItems);

        // Add the items to the items list
        for (Item i : Item.values()){
            listModel.addElement(i);
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
        btnA.addActionListener(listener);
        btnB.addActionListener(listener);
        btnC.addActionListener(listener);
        btnClear.addActionListener(e -> {
            listener.setNumberString("");
            tfCodeEntry.setText("");
        });

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

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
                } else if (e.getSource() == btnA) {
                    numberString += "A";
                    tfCodeEntry.setText(numberString);
                } else if (e.getSource() == btnB) {
                    numberString += "B";
                    tfCodeEntry.setText(numberString);
                } else if (e.getSource() == btnC) {
                    numberString += "C";
                    tfCodeEntry.setText(numberString);
                }
            }
        }

        public void setNumberString(String numberString) {
            this.numberString = numberString;
        }
    }
}
