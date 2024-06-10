package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.CartItem;

class CheckoutScreen {
    private JFrame frame;

    public CheckoutScreen(List<CartItem> cartItems) {
        frame = new JFrame("Checkout");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        DefaultListModel<String> checkoutListModel = new DefaultListModel<>();
        JList<String> checkoutList = new JList<>(checkoutListModel);
        double total = 0;
        for (CartItem item : cartItems) {
            checkoutListModel.addElement(item.toString()); // Mostra o item de forma linear
            total += item.getTotalPrice();
        }

        frame.add(new JScrollPane(checkoutList), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        JLabel totalLabel = new JLabel("Total Purchase: $" + String.format("%.2f", total));
        bottomPanel.add(totalLabel, BorderLayout.NORTH);

        JButton finishButton = new JButton("Finalizar");
        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Compra concluída");
                frame.dispose();
            }
        });

        bottomPanel.add(finishButton, BorderLayout.SOUTH);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}