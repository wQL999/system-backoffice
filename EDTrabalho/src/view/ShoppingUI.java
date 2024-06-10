package view;

import model.Produto;
import model.TipoProduto;
import model.CartItem;
import controller.TipoProdutoController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ShoppingUI {
    private JFrame frame;
    private JComboBox<String> searchBox;
    private JTextField quantityField;
    private DefaultListModel<CartItem> cartListModel;
    private JList<CartItem> cartList;
    private Map<String, Produto> productMap;
    private JLabel totalLabel;

    public ShoppingUI() {
        productMap = loadProductsFromCSV("data/products.csv");

        frame = new JFrame("Shopping");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        searchBox = new JComboBox<>(productMap.keySet().toArray(new String[0]));
        searchBox.setEditable(true);
        topPanel.add(searchBox);

        quantityField = new JTextField("1", 5);
        topPanel.add(new JLabel("Quantity:"));
        topPanel.add(quantityField);

        JButton addButton = new JButton("Adicionar ao carrinho");
        topPanel.add(addButton);

        frame.add(topPanel, BorderLayout.NORTH);

        cartListModel = new DefaultListModel<>();
        cartList = new JList<>(cartListModel);
        frame.add(new JScrollPane(cartList), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());

        JButton finishButton = new JButton("Finalizar compra");
        bottomPanel.add(finishButton);

        JButton clearButton = new JButton("Limpar carrinho");
        bottomPanel.add(clearButton);
        
        totalLabel = new JLabel("Total: $0.00");
        bottomPanel.add(totalLabel);
        
        frame.add(bottomPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new AddButtonListener());
        finishButton.addActionListener(new FinishButtonListener());
        clearButton.addActionListener(new ClearButtonListener());

        frame.setVisible(true);
    }

    private Map<String, Produto> loadProductsFromCSV(String filePath) {
    	TipoProdutoController tpc = new TipoProdutoController();
        Map<String, Produto> products = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                long cod = Long.parseLong(values[0]);
                String nome = values[1];
                double valor = Double.parseDouble(values[2]);
                String descricao = values[3];
                long qtdEstoque = Long.parseLong(values[4]);
                TipoProduto tipo = tpc.FindById(cod);
                products.put(nome, new Produto(cod, nome, valor, descricao, qtdEstoque, tipo));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    private class AddButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String searchQuery = (String) searchBox.getSelectedItem();
            String quantityText = quantityField.getText();

            if (quantityText.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Insira a quantidade");
                return;
            }

            int quantity;
            try {
                quantity = Integer.parseInt(quantityText);
                if (quantity <= 0) {
                    JOptionPane.showMessageDialog(frame, "Insira um valor positivo para quantidade");
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Insira um número válido de quantidade");
                return;
            }

            if (productMap.containsKey(searchQuery)) {
                Produto product = productMap.get(searchQuery);
                int currentQuantityInCart = 0;

                for (int i = 0; i < cartListModel.getSize(); i++) {
                    CartItem item = cartListModel.get(i);
                    if (item.getProduct().getNome().equals(product.getNome())) {
                        currentQuantityInCart = item.getQuantity();
                        break;
                    }
                }

                int totalQuantity = currentQuantityInCart + quantity;

                if (totalQuantity <= product.getQtdEstoque()) {
                    boolean found = false;
                    for (int i = 0; i < cartListModel.getSize(); i++) {
                        CartItem item = cartListModel.get(i);
                        if (item.getProduct().getNome().equals(product.getNome())) {
                            item.setQuantity(totalQuantity);
                            cartListModel.set(i, item);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        cartListModel.addElement(new CartItem(product, quantity));
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Carência de estoque. Quantidade disponível: " + (product.getQtdEstoque() - currentQuantityInCart));
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Produto não encontrado");
            }
            updateTotal();
        }
    }


    private class FinishButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (cartListModel.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Carrinho vazio");
            } else {
                JOptionPane.showMessageDialog(frame, "Compra concluída");
                cartListModel.clear();
            }
        }
    }

    private class ClearButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            cartListModel.clear();
            clearTotal();
        }
    }

    private class CartItemRenderer extends JPanel implements ListCellRenderer<CartItem> {
        private JLabel nameLabel;
        private JLabel quantityLabel;
        private JLabel priceLabel;

        public CartItemRenderer() {
            setLayout(new BorderLayout());
            nameLabel = new JLabel();
            quantityLabel = new JLabel();
            priceLabel = new JLabel();

            JPanel textPanel = new JPanel(new GridLayout(3, 1));
            textPanel.add(nameLabel);
            textPanel.add(quantityLabel);
            textPanel.add(priceLabel);

            add(textPanel, BorderLayout.CENTER);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends CartItem> list, CartItem item, int index, boolean isSelected, boolean cellHasFocus) {
            nameLabel.setText("Product: " + item.getProduct().getNome());
            quantityLabel.setText("Quantity: " + item.getQuantity());
            priceLabel.setText("Total Price: $" + item.getTotalPrice());

            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }

            return this;
        }
    }
    
    private void updateTotal() {
        double total = 0.0;
        for (int i = 0; i < cartListModel.getSize(); i++) {
            CartItem item = cartListModel.get(i);
            total += item.getProduct().getValor() * item.getQuantity();
        }
        totalLabel.setText("Total: $" + String.format("%.2f", total));
    }
    
    private void clearTotal() {
        totalLabel.setText("Total: $0.00");
    }
    
    public static void main(String[] args) {
    	 new ShoppingUI();
    }
}
