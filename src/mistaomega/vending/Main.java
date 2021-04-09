package mistaomega.vending;

import mistaomega.vending.UI.HomeUI;
import mistaomega.vending.items.BankAccount;
import mistaomega.vending.items.LoyaltyCard;
import mistaomega.vending.machine.VendingMachine;

import javax.swing.*;
import java.util.List;

public class Main {
    public static void setLookAndFeel(){
        try {
            // Set cross-platform Java L&F (also called "Metal")
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Unsupported look and feel, standard will be used");
            e.printStackTrace();
        }
    }

    public List<LoyaltyCard> generateLoyalty() {
        BankAccount bankAccount = new BankAccount("John Smith", "00000000", "00-00-00", 105.50);

        return null;
    }

    /**
     * entry point
     *
     * @param args
     */
    public static void main(String[] args) {
        setLookAndFeel();
        SwingUtilities.invokeLater(() -> {
            HomeUI gui = new HomeUI(new VendingMachine());
            gui.initialize();
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(gui.getMainPanel());
            frame.pack();
            frame.setVisible(true);
        });
    }
}
