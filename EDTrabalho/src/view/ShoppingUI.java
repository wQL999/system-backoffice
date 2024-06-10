package view;

import model.Produto;
import model.TipoProduto;
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
    private JTextField searchBox;
    private JTextField quantityField;
    private DefaultListModel<String> cartListModel;
    private JList<String> cartList;
    private Map<String, Produto> productMap;

    public ShoppingUI() {
        productMap = loadProductsFromCSV();

        frame = new JFrame("Shopping");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        searchBox = new JTextField(15);
        topPanel.add(searchBox);

        JButton searchButton = new JButton("Buscar produto");
        topPanel.add(searchButton);

        quantityField = new JTextField(5);
        topPanel.add(new JLabel("Quantidade:"));
        topPanel.add(quantityField);

        JButton addButton = new JButton("Adicionar ao carrinho");
        topPanel.add(addButton);

        frame.add(topPanel, BorderLayout.NORTH);

        cartListModel = new DefaultListModel<>();
        cartList = new JList<>(cartListModel);
        frame.add(new JScrollPane(cartList), BorderLayout.CENTER);

        JButton finishButton = new JButton("Finish Purchase");
        frame.add(finishButton, BorderLayout.SOUTH);

        searchButton.addActionListener(new SearchButtonListener());
        addButton.addActionListener(new AddButtonListener());
        finishButton.addActionListener(new FinishButtonListener());

        frame.setVisible(true);
    }

    private Map<String, Produto> loadProductsFromCSV() {
        TipoProdutoController tipoProdutoController = new TipoProdutoController();

        Map<String, Produto> products = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader("data/products.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                long cod = Integer.parseInt(values[0]);
                String nome = values[1];
                double valor = Double.parseDouble(values[2]);
                String descricao = values[3];
                long qtdEstoque = Long.parseLong(values[4]);
                TipoProduto tipo = tipoProdutoController.FindById(cod);

                products.put(nome, new Produto(cod, nome, valor, descricao, qtdEstoque, tipo));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    private class SearchButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String searchQuery = searchBox.getText();
            if (productMap.containsKey(searchQuery)) {
                JOptionPane.showMessageDialog(frame, "Produto encontrado: " + searchQuery);
            } else {
                JOptionPane.showMessageDialog(frame, "Produto nao encontrado");
            }
        }
    }

    private class AddButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String searchQuery = searchBox.getText();
            int quantity = Integer.parseInt(quantityField.getText());
            if (productMap.containsKey(searchQuery)) {
                Produto product = productMap.get(searchQuery);
                if (quantity <= product.getQtdEstoque()) {
                    cartListModel.addElement(product.getNome() + " x " + quantity + " - R$" + (product.getValor() * quantity));
                } else {
                    JOptionPane.showMessageDialog(frame, "Estoque insuficiente");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Product nao encontrado");
            }
        }
    }

    private class FinishButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (cartListModel.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Carrinho vazio");
            } else {
                JOptionPane.showMessageDialog(frame, "Compra finalizada");
                cartListModel.clear();
            }
        }
    }

    public static void main(String[] args) {
        new ShoppingUI();
    }
}
