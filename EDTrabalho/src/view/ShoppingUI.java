package view;

import controller.ProdutoController;
import model.Produto;
import model.TipoProduto;
import model.CartItem;
import controller.TipoProdutoController;
import controller.customer.CartStack;
import controller.customer.CartQueue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
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
    private CartStack<CartItem> cartStack;

    public ShoppingUI() {
        productMap = loadProductsFromCSV("data/products.csv");

        frame = new JFrame("Shopping");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 700);
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        searchBox = new JComboBox<>(productMap.keySet().toArray(new String[0]));
        searchBox.setEditable(true);
        topPanel.add(searchBox);

        quantityField = new JTextField("1", 5);
        topPanel.add(new JLabel("Quantidade:"));
        topPanel.add(quantityField);

        JButton addButton = new JButton("Adicionar ao carrinho");
        topPanel.add(addButton);

        frame.add(topPanel, BorderLayout.NORTH);

        cartListModel = new DefaultListModel<>();
        cartList = new JList<>(cartListModel);
        cartList.setCellRenderer(new CartItemRenderer());
        frame.add(new JScrollPane(cartList), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());

        totalLabel = new JLabel("Total: R$0.00");
        bottomPanel.add(totalLabel);

        JButton removeLastButton = new JButton("Remover último item");
        bottomPanel.add(removeLastButton);

        JButton finishButton = new JButton("Finalizar compra");
        bottomPanel.add(finishButton);

        JButton clearButton = new JButton("Limpar carrinho");
        bottomPanel.add(clearButton);

        frame.add(bottomPanel, BorderLayout.SOUTH);

        cartStack = new CartStack<>();

        addButton.addActionListener(new AddButtonListener());
        removeLastButton.addActionListener(new RemoveLastButtonListener());
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
                JOptionPane.showMessageDialog(frame, "Insira uma quantidade");
                return;
            }

            int quantity;
            try {
                quantity = Integer.parseInt(quantityText);
                if (quantity <= 0) {
                    JOptionPane.showMessageDialog(frame, "Insira uma quantidade válida");
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Insira uma quantidade válida");
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
                        CartItem newItem = new CartItem(product, quantity);
                        cartListModel.addElement(newItem);
                        cartStack.push(newItem);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Falta de estoque. Quantidade disponível: " + (product.getQtdEstoque() - currentQuantityInCart));
                }

                updateTotal();
            } else {
                JOptionPane.showMessageDialog(frame, "Produto não encontrado");
            }
        }
    }

    private class RemoveLastButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (!cartStack.isEmpty()) {
                CartItem lastItem = cartStack.pop();
                for (int i = 0; i < cartListModel.getSize(); i++) {
                    CartItem item = cartListModel.get(i);
                    if (item.equals(lastItem)) {
                        if (item.getQuantity() > lastItem.getQuantity()) {
                            item.setQuantity(item.getQuantity() - lastItem.getQuantity());
                            cartListModel.set(i, item);
                        } else {
                            cartListModel.remove(i);
                        }
                        break;
                    }
                }
                updateTotal();
            } else {
                JOptionPane.showMessageDialog(frame, "Carrinho vazio");
            }
        }
    }

    private class FinishButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (cartListModel.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Carrinho vazio");
            } else {
                int result = JOptionPane.showConfirmDialog(frame, "Prosseguir para o checkout?", "Confirmar compra", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                	
                    CartQueue<CartItem> checkoutQueue = new CartQueue<>();
                    ProdutoController pc = new ProdutoController();

                    while (!cartStack.isEmpty()) {
                    	CartItem item = cartStack.pop();
                    	Produto p = item.getProduct();
                    	
                    	try {
							pc.subtractQtd(p.getNome(), item.getQuantity());
						} catch (IOException e1) {
							e1.printStackTrace();
						}   
                    	
                        checkoutQueue.enqueue(item);
                    }
                    new CheckoutScreen(checkoutQueue.toList());
                    frame.dispose();
                }
            }
        }
    }

    private class ClearButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            cartListModel.clear();
            cartStack.clear();
            clearTotal();
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

    private class CartItemRenderer extends JPanel implements ListCellRenderer<CartItem> {
        private JLabel itemLabel;

        public CartItemRenderer() {
            setLayout(new BorderLayout());
            itemLabel = new JLabel();
            itemLabel.setFont(itemLabel.getFont().deriveFont(16f));
            itemLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            add(itemLabel, BorderLayout.CENTER);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends CartItem> list, CartItem item, int index, boolean isSelected, boolean cellHasFocus) {
            itemLabel.setText(item.toString());

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
}
