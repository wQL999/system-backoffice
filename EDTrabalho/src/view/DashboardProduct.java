package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;

import controller.ProdutoController;
import controller.TipoProdutoController;
import model.Produto;
import model.TipoProduto;
import utils.HashTable;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.color.ColorSpace;
import java.util.LinkedList;
import java.util.List;

import javax.swing.table.*;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import java.awt.Color;
import java.awt.Component;

import javax.swing.UIManager;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DashboardProduct extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel tableModel;
	private JTable table;
	private JTextField txtNome;
	private JTextField txtValor;
	private JTextField txtQuantidade;
	ProdutoController contProduto;
	int selectedIndex = -1;
	Object[][] data;
	private JTextField txtSearch;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DashboardProduct frame = new DashboardProduct();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	

	public DashboardProduct() {
		DashboardProduct t = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1017, 521);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String columns[] = {"Nome", "Descrição", "Valor", "Quantidade", "Tipo do produto"};
		contProduto = new ProdutoController();
		
		data = new Object[contProduto.repositoryProdutos.size()][5];
		
		int base=0;
		for(Produto p: contProduto.repositoryProdutos) {
			Object[] aux = {p.getNome(), p.getDescricao(), p.getValor(), p.getQtdEstoque(), p.getTipo().getNome()};
			data[base++] = aux;
		}
					
		tableModel = new DefaultTableModel();
		for(int i = 0;i < columns.length;i++) {
			tableModel.addColumn(columns[i]);
		}
		
		for(int i = 0;i < data.length;i++) {
			tableModel.addRow(data[i]);
		}
		
		table = new JTable(tableModel) {
			@Override public Component prepareRenderer(TableCellRenderer renderer,
	                int row,
	                int col){
				Component c = super.prepareRenderer(renderer, row, col);
				int selCol = table.getSelectedColumn();
				int selRow = table.getSelectedRow();
				if ( selCol != -1 && selRow != -1 ){
					if (row == selRow){
						System.out.println(selCol + ' ' + selRow);
						c.setBackground(new Color(82, 183, 233));
					}else{
						c.setBackground(Color.WHITE);
					}				
				} else {
					c.setBackground(Color.WHITE);
				}
				
				return c;
			}
		
		};
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setSurrendersFocusOnKeystroke(true);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setBounds(370, 89, 587, 372);
		contentPane.add(table);
		
		for(int c = 0;c < table.getColumnCount();c++) {
			Class<?> col_class = table.getColumnClass(c);
			table.setDefaultEditor(col_class, null);
		}
				
		table.setRowSelectionAllowed(true);
		ListSelectionModel cellSelectionModel = table.getSelectionModel();	       
		table.setColumnSelectionAllowed(true);
		table.setRowSelectionAllowed(true);
			
       
		JButton btnCreate = new JButton("Tela de novo produto");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							t.setVisible(false);
							MaintainProduct frame = new MaintainProduct();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCreate.setBounds(30, 35, 298, 44);
		contentPane.add(btnCreate);
		
		JPanel panel = new JPanel();
		panel.setBounds(30, 89, 298, 385);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Descrição:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 239, 99, 13);
		panel.add(lblNewLabel);
		
		JLabel lblValor = new JLabel("Valor:");
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblValor.setBounds(10, 86, 45, 13);
		panel.add(lblValor);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nome:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(10, 29, 45, 13);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("Quantidade em estoque:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 132, 177, 13);
		panel.add(lblNewLabel_1);
		
		JTextArea taDescricao = new JTextArea();
		taDescricao.setBounds(10, 262, 249, 71);
		
		taDescricao.setOpaque(false);
		panel.add(taDescricao);
		
		txtNome = new JTextField();
		txtNome.setBounds(10, 57, 249, 19);
		panel.add(txtNome);
		txtNome.setColumns(10);
		txtNome.setOpaque(false);
		
		txtValor = new JTextField();
		txtValor.setOpaque(false);
		txtValor.setColumns(10);
		txtValor.setBounds(10, 109, 249, 19);
		panel.add(txtValor);
		
		txtQuantidade = new JTextField();
		txtQuantidade.setOpaque(false);
		txtQuantidade.setColumns(10);
		txtQuantidade.setBounds(10, 155, 249, 19);
		panel.add(txtQuantidade);
		
		JLabel lblNewLabel_1_2 = new JLabel("Tipo de produto:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(10, 184, 177, 13);
		panel.add(lblNewLabel_1_2);
				
		TipoProdutoController contTipoProdutos = new TipoProdutoController();
		
		JComboBox<String> cbTipoProduto = new JComboBox<String>();
		
		cbTipoProduto.setBounds(10, 207, 249, 21);
		cbTipoProduto.setOpaque(false);
		for(TipoProduto tp: contTipoProdutos.repositoryTiposProdutos) {
			cbTipoProduto.addItem(tp.getNome());
		}
		
		panel.add(cbTipoProduto);
		
		JButton btnUpdate = new JButton("Salvar alterações");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contProduto = new ProdutoController(txtNome, txtValor, txtQuantidade, cbTipoProduto, taDescricao);
				
				Object[] aux = {txtNome.getText(), taDescricao.getText(),txtValor.getText(), txtQuantidade.getText(), cbTipoProduto.getSelectedItem()};
				data[selectedIndex] = aux;
				
				contProduto.update(contProduto.repositoryProdutos.get(table.getSelectedRow()).getCod());
				
				table.getModel().setValueAt(txtNome.getText(), table.getSelectedRow(), 0);
				table.getModel().setValueAt(taDescricao.getText(), table.getSelectedRow(), 1);
				table.getModel().setValueAt(txtValor.getText(), table.getSelectedRow(), 2);
				table.getModel().setValueAt(txtQuantidade.getText(), table.getSelectedRow(), 3);
				table.getModel().setValueAt(cbTipoProduto.getSelectedItem(), table.getSelectedRow(), 4);
				
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnUpdate.setBounds(10, 343, 121, 32);
		panel.add(btnUpdate);
		
		JButton btnDelete = new JButton("Excluir");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedIndex = table.getSelectedRow();									
				
				Object[][] dataAux = new Object[data.length-1][5];
				int base=0;
				
				for(int i = 0;i < data.length;i++) {
					if(i != selectedIndex) {
						dataAux[base++] = data[i];
					}
				}
				
				data = dataAux;
				tableModel.removeRow(selectedIndex);							
				
				ProdutoController p = new ProdutoController();
				p.remove(p.repositoryProdutos.get(selectedIndex).getCod());
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDelete.setBounds(178, 343, 110, 32);
		panel.add(btnDelete);
		
		JLabel lblNewLabel_2 = new JLabel("Pesquisar por nome:");
		lblNewLabel_2.setBounds(370, 10, 96, 13);
		contentPane.add(lblNewLabel_2);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(476, 7, 131, 19);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Pesquisar por tipo:");
		lblNewLabel_3.setBounds(617, 10, 96, 13);
		contentPane.add(lblNewLabel_3);
		
		JComboBox<String> cbSearchTipo = new JComboBox<String>();
		cbSearchTipo.setOpaque(false);
		cbSearchTipo.setBounds(711, 6, 249, 21);
		
		cbSearchTipo.addItem("Qualquer");
		
		for(TipoProduto tp: contTipoProdutos.repositoryTiposProdutos) {
			cbSearchTipo.addItem(tp.getNome());
		}
			
		contentPane.add(cbSearchTipo);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object aux[][] = new Object[contProduto.repositoryProdutos.size()][5];
				int base=0;
				
				for(Produto p: contProduto.repositoryProdutos) {
					if((txtSearch.getText().equals("") || p.getNome().toLowerCase().substring(0, Math.min(p.getNome().length(), txtSearch.getText().length())).equals(txtSearch.getText().toLowerCase()))
						&& (cbSearchTipo.getSelectedItem().equals("Qualquer") || cbSearchTipo.getSelectedItem().equals(p.getTipo().getNome()))) {
							aux[base++] = new Object[] {p.getNome(), p.getDescricao(), p.getValor(), p.getQtdEstoque(), p.getTipo().getNome()};
					}
				}
				
				tableModel.setRowCount(0);
				
				for(int i = 0;i < base;i++) {
					tableModel.addRow(aux[i]);
				}
				
				data = aux;
			}
		});
		btnPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnPesquisar.setBounds(836, 31, 121, 32);
		contentPane.add(btnPesquisar);
		
		JButton btnVoltar = new JButton("Voltar");
		
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t.setVisible(false);
				viewBackoffice d = new viewBackoffice();
				d.setVisible(true);
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVoltar.setBounds(30, 5, 69, 21);
		contentPane.add(btnVoltar);
		cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e){
				table.repaint();				
				
				if(table.getSelectedRow() == -1) {
					txtNome.setText("");
					txtValor.setText("");
					txtQuantidade.setText("");
					taDescricao.setText("");
					cbTipoProduto.setSelectedIndex(0);
				} else {
					Produto p = contProduto.FindByName((String)table.getValueAt(table.getSelectedRow(), 0));
					selectedIndex = table.getSelectedRow();
					
					txtNome.setText(p.getNome());
					txtValor.setText(String.valueOf(p.getValor()));
					txtQuantidade.setText(String.valueOf(p.getQtdEstoque()));
					taDescricao.setText(p.getDescricao());
					cbTipoProduto.setSelectedItem(p.getTipo().getNome());					
				}
			}
		});
	
	}
}
