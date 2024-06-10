package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import controller.customer.*;

public class ShoppingCartUI extends JFrame{
    private int amount = 0;
    private float total = 0;

    public int getAmount() { return this.amount; }
    public void setAmount(int amount) { this.amount = amount; }

    public float getTotal() { return this.total; }
    public void setTotal(float total) { this.total = total; }

    public ShoppingCartUI(int amount, float total) {
        this.amount = amount;
        this.total = total;

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

        // Items list
        JPanel listPanel = itemsList();

        // Adding panels
        add(topPanel, BorderLayout.NORTH);
        add(separator, BorderLayout.CENTER);
        add(listPanel, BorderLayout.CENTER);

        // Frame parameters
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
    }

    private JPanel buttonPanel() {
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

    private JPanel labelPanel() {
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));

        JLabel amountLabel = new JLabel("Quantidade: " + getAmount());
        amountLabel.setFont(new Font("Roboto", Font.BOLD, 24));
        amountLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel priceLabel = new JLabel("Total: R$ " + getTotal());
        priceLabel.setFont(new Font("Roboto", Font.BOLD, 24));
        priceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        labelPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        labelPanel.add(amountLabel);
        labelPanel.add(priceLabel);

        return labelPanel;
    }

    private JPanel itemsList() {
        JPanel listPanel = new JPanel(new BorderLayout());
        DefaultListModel<Integer> listModel = new DefaultListModel<>();
        JList<Integer> list = new JList<>(listModel);
        JScrollPane listScrollPane = new JScrollPane(list);
        listPanel.setBorder(new EmptyBorder(0,  50, 50, 50));
        listPanel.add(listScrollPane, BorderLayout.CENTER);

        return listPanel;
    }

    public static void main(String[] args) {
    }
}
