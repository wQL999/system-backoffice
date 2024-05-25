package controller.customer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class shoppingCart extends JFrame{

    public shoppingCart() {
        setTitle("Seu carrinho");

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(new EmptyBorder(0, 50, 20, 0));

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));

        JLabel amountLabel = new JLabel("Quantidade: ");
        amountLabel.setFont(new Font("Roboto", Font.BOLD, 24));
        amountLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel priceLabel = new JLabel("Valor: ");
        priceLabel.setFont(new Font("Roboto", Font.BOLD, 24));
        priceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        labelPanel.add(priceLabel);
        labelPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        labelPanel.add(amountLabel);

        topPanel.add(labelPanel, BorderLayout.WEST);

        JPanel buttonVerticalPanel = new JPanel();
        buttonVerticalPanel.setLayout(new BoxLayout(buttonVerticalPanel, BoxLayout.Y_AXIS));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBorder(new EmptyBorder(0, 0, 0, 50));
        JButton buttonFinish = new JButton("Finalizar compra");
        buttonFinish.setFont(new Font("Roboto", Font.BOLD, 14));
        JButton buttonShop = new JButton("Continuar comprando");
        buttonShop.setFont(new Font("Roboto", Font.BOLD, 14));
        buttonPanel.add(buttonFinish);
        buttonPanel.add(buttonShop);

        buttonVerticalPanel.add(Box.createVerticalGlue());
        buttonVerticalPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        buttonVerticalPanel.add(buttonPanel);
        topPanel.add(buttonVerticalPanel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        JSeparator separator = new JSeparator();
        add(separator, BorderLayout.CENTER);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> list = new JList<>(listModel);
        JScrollPane listScrollPane = new JScrollPane(list);

        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.setBorder(new EmptyBorder(0,  50, 50, 50));
        listPanel.add(listScrollPane, BorderLayout.CENTER);

        add(listPanel, BorderLayout.CENTER);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
    }
    public static void main(String[] args) {
        shoppingCart shoppingCart = new shoppingCart();
        shoppingCart.setVisible(true);
    }
}
