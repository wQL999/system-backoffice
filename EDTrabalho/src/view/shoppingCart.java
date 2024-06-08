package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.customer.*;

public class shoppingCart extends JFrame{

    public shoppingCart() {
        setTitle("Seu carrinho");
        setLayout(new BorderLayout());
        // Top Panel
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(new EmptyBorder(0, 50, 20, 0));

        // Labels
        JPanel labelPanel = labelPanel();
        topPanel.add(labelPanel, BorderLayout.WEST);


        // Buttons
        JPanel buttonPanel = buttonPanel();

        JPanel buttonVerticalPanel = new JPanel();
        buttonVerticalPanel.setLayout(new BoxLayout(buttonVerticalPanel, BoxLayout.Y_AXIS));

        buttonVerticalPanel.add(Box.createVerticalGlue());
        buttonVerticalPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        buttonVerticalPanel.add(buttonPanel);

        topPanel.add(buttonVerticalPanel, BorderLayout.EAST);

        // Separator
        JSeparator separator = new JSeparator();

        // Products list
        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> list = new JList<>(listModel);
        JScrollPane listScrollPane = new JScrollPane(list);
        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.setBorder(new EmptyBorder(0,  50, 50, 50));
        listPanel.add(listScrollPane, BorderLayout.CENTER);

        // Adding panels
        add(topPanel, BorderLayout.NORTH);
        add(separator, BorderLayout.CENTER);
        add(listPanel, BorderLayout.CENTER);

        // Frame parameters
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
    }

    private static JPanel buttonPanel() {
        Font buttonFont = new Font("Roboto", Font.BOLD, 14);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBorder(new EmptyBorder(0, 0, 0, 50));

        JButton buttonFinish = new JButton("Finalizar compra");
        buttonFinish.setFont(buttonFont);
        buttonFinish.addActionListener(new ButtonFinishAction());

        JButton buttonReturn = new JButton("Continuar comprando");
        buttonReturn.setFont(buttonFont);
        buttonReturn.addActionListener(new ButtonReturnAction());

        buttonPanel.add(buttonReturn);
        buttonPanel.add(buttonFinish);

        return buttonPanel;
    }

    private static JPanel labelPanel() {
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));

        JLabel amountLabel = new JLabel("Quantidade: ");
        amountLabel.setFont(new Font("Roboto", Font.BOLD, 24));
        amountLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel priceLabel = new JLabel("Total: ");
        priceLabel.setFont(new Font("Roboto", Font.BOLD, 24));
        priceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        labelPanel.add(priceLabel);
        labelPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        labelPanel.add(amountLabel);

        return labelPanel;
    }

    public static void main(String[] args) {
        shoppingCart shoppingCart = new shoppingCart();
        shoppingCart.setVisible(true);
    }
}
