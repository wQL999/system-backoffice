package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;

import controller.ProdutoController;
import controller.TipoProdutoController;
import model.Produto;
import model.TipoProduto;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.color.ColorSpace;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import java.awt.Color;
import java.awt.Component;

import javax.swing.UIManager;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;

public class DashboardProduct extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField txtNome;
	private JTextField txtValor;
	private JTextField txtQuantidade;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1017, 521);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String columns[] = {"Nome", "Descrição", "Valor", "Quantidade", "Tipo do produto"};
		ProdutoController contProduto = new ProdutoController();
		Object data[][] = new Object[contProduto.repositoryProdutos.size()][5];
		
		int base=0;
		for(Produto p: contProduto.repositoryProdutos) {
			Object[] aux = {p.getNome(), p.getDescricao(), p.getValor(), p.getQtdEstoque(), p.getTipo().getNome()};
			data[base++] = aux;
		}
				
		table = new JTable(data, columns) {
			@Override public Component prepareRenderer(TableCellRenderer renderer,
	                int row,
	                int col){
				Component c = super.prepareRenderer(renderer, row, col);
				int selCol = table.getSelectedColumn();
				int selRow = table.getSelectedRow();
				if ( selCol != -1 && selRow != -1 ){
					if (row == selRow){
						c.setBackground(new Color(82, 183, 233));
					}else{
						c.setBackground(Color.WHITE);
					}				
				}
				return c;
			}
		
		};
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setSurrendersFocusOnKeystroke(true);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setBounds(373, 62, 587, 372);
		contentPane.add(table);
		
		for(int c = 0;c < table.getColumnCount();c++) {
			Class<?> col_class = table.getColumnClass(c);
			table.setDefaultEditor(col_class, null);
		}
				
		table.setRowSelectionAllowed(true);
		ListSelectionModel cellSelectionModel = table.getSelectionModel();	       
		table.setColumnSelectionAllowed(true);
		table.setRowSelectionAllowed(true);
			
       
		JButton btnNewButton = new JButton("Adicionar novo produto");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(20, 35, 298, 44);
		contentPane.add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBounds(20, 89, 298, 364);
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
		taDescricao.setBounds(10, 262, 249, 92);
		
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
		for(TipoProduto t: contTipoProdutos.repositoryTiposProdutos) {
			cbTipoProduto.addItem(t.getNome());
		}
		
		panel.add(cbTipoProduto);
		cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e){
				table.repaint();
				
				Produto p = contProduto.FindByName((String)table.getValueAt(table.getSelectedRow(), 0));
				
				txtNome.setText(p.getNome());
				txtValor.setText(String.valueOf(p.getValor()));
				txtQuantidade.setText(String.valueOf(p.getQtdEstoque()));
				taDescricao.setText(p.getDescricao());
				cbTipoProduto.setSelectedItem(p.getTipo().getNome());
			}
		});
	
	}
}
