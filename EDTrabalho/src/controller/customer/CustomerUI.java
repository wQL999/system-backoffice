package controller.customer;

import javax.swing.*;
import java.awt.*;

public class CustomerUI {

    public static void makeUI() {
        JFrame mainFrame = new JFrame("Carrinho");
        mainFrame.setSize(900, 700);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.decode("#393B45"));
        GridBagConstraints gbc = new GridBagConstraints();

        JPanel welcomePanel = new JPanel();
        welcomePanel.setBackground(Color.decode("#535664"));
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipady = 50;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.weighty = 1;
        panel.add(welcomePanel, gbc);

        mainFrame.setLocationRelativeTo(null);
        mainFrame.add(panel);
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        makeUI();
    }
}
