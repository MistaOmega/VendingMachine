package mistaomega.vending;

import mistaomega.vending.UI.HomeUI;

import javax.swing.*;



public class Main {
    public static void setLookAndFeel(){
        try {
            // Set cross-platform Java L&F (also called "Metal")
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
            System.out.println("Unsupported look and feel, standard will be used");
            e.printStackTrace();
        }
    }
    /**
     * entry point
     * @param args
     */
    public static void main(String[] args) {
        setLookAndFeel();
        SwingUtilities.invokeLater(() -> {
            HomeUI gui = new HomeUI();
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(gui.getMainPanel());
            frame.pack();
            frame.setVisible(true);
        });
    }
}
