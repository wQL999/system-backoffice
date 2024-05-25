package controller;

import javax.swing.*;
import java.awt.*;

public class Test extends JFrame {

    public Test() {
        // Set title of the shoppingCart
        setTitle("Custom Swing Frame");

        // Set the layout manager
        setLayout(new BorderLayout());

        // Create a panel for the top section
        JPanel topPanel = new JPanel(new BorderLayout());

        // Add a title label to the upper left corner
        JLabel titleLabel = new JLabel("Title");
        topPanel.add(titleLabel, BorderLayout.WEST);

        // Create a panel for buttons and add buttons to it
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton button1 = new JButton("Button 1");
        JButton button2 = new JButton("Button 2");
        buttonPanel.add(button1);
        buttonPanel.add(button2);

        // Add button panel to the top panel
        topPanel.add(buttonPanel, BorderLayout.EAST);

        // Add the top panel to the shoppingCart
        add(topPanel, BorderLayout.NORTH);

        // Add a horizontal separator
        JSeparator separator = new JSeparator();
        add(separator, BorderLayout.CENTER);

        // Create a list and add it to a scroll pane
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (int i = 1; i <= 10; i++) {
            listModel.addElement("Item " + i);
        }
        JList<String> list = new JList<>(listModel);
        JScrollPane listScrollPane = new JScrollPane(list);

        // Add the list scroll pane to the shoppingCart
        add(listScrollPane, BorderLayout.SOUTH);

        // Set default close operation and size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Test frame = new Test();
            frame.setVisible(true);
        });
    }
}
