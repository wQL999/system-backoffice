package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import controller.ProdutoController;
import controller.TipoProdutoController;
import model.Produto;
import model.TipoProduto;

public class DashboardTipoProdutos extends JFrame {

	private static final long serialVersionUID = 1L;	
	private JPanel contentPane;
	private DefaultTableModel tableModel;
	private JTable table;
	private JTextField txtNome;	
	int selectedIndex = -1;
	Object[][] data;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DashboardTipoProdutos frame = new DashboardTipoProdutos();
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
	public DashboardTipoProdutos() {
		DashboardTipoProdutos t = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1017, 521);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String columns[] = {"Nome", "Descrição"};
		TipoProdutoController contTipoProduto = new TipoProdutoController();
		
		data = new Object[contTipoProduto.repositoryTiposProdutos.size()][2];
		
		int base=0;
		for(TipoProduto p: contTipoProduto.repositoryTiposProdutos) {
			Object[] aux = {p.getNome(), p.getDescricao()};
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
			
       
		JButton btnCreate = new JButton("Tela de novo tipo");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							t.setVisible(false);
							MaintainTipoProdutos frame = new MaintainTipoProdutos();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCreate.setBounds(20, 62, 298, 44);
		contentPane.add(btnCreate);
		
		JPanel panel = new JPanel();
		panel.setBounds(20, 127, 298, 347);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Descrição:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 97, 99, 13);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nome:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(10, 29, 45, 13);
		panel.add(lblNewLabel_1_1);
		
		JTextArea taDescricao = new JTextArea();
		taDescricao.setBounds(10, 120, 249, 71);
		
		taDescricao.setOpaque(false);
		panel.add(taDescricao);
		
		txtNome = new JTextField();
		txtNome.setBounds(10, 57, 249, 19);
		panel.add(txtNome);
		txtNome.setColumns(10);
		txtNome.setOpaque(false);
		
		
		JButton btnUpdate = new JButton("Salvar alterações");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TipoProdutoController contTipoProduto = new TipoProdutoController(txtNome, taDescricao);
				
				Object[] aux = {txtNome.getText(), taDescricao.getText()};
				data[selectedIndex] = aux;
				
				contTipoProduto.update(contTipoProduto.repositoryTiposProdutos.get(table.getSelectedRow()).getCod());
				
				table.getModel().setValueAt(txtNome.getText(), table.getSelectedRow(), 0);
				table.getModel().setValueAt(taDescricao.getText(), table.getSelectedRow(), 1);								
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnUpdate.setBounds(10, 311, 121, 32);
		panel.add(btnUpdate);
		
		JButton btnDelete = new JButton("Excluir");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedIndex = table.getSelectedRow();									
				
				Object[][] dataAux = new Object[data.length-1][2];
				int base=0;
				
				for(int i = 0;i < data.length;i++) {
					if(i != selectedIndex) {
						dataAux[base++] = data[i];
					}
				}
				
				data = dataAux;
				tableModel.removeRow(selectedIndex);							
				
				TipoProdutoController p = new TipoProdutoController();
				p.remove(p.repositoryTiposProdutos.get(selectedIndex).getCod());
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDelete.setBounds(178, 311, 110, 32);
		panel.add(btnDelete);
				
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t.setVisible(false);
				viewBackoffice d = new viewBackoffice();
				d.setVisible(true);
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVoltar.setBounds(23, 10, 80, 23);
		contentPane.add(btnVoltar);
		cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e){
				table.repaint();				
				
				if(table.getSelectedRow() == -1) {
					txtNome.setText("");					
					taDescricao.setText("");					
				} else {
					TipoProduto p = contTipoProduto.FindByName((String)table.getValueAt(table.getSelectedRow(), 0));
					selectedIndex = table.getSelectedRow();
					
					txtNome.setText(p.getNome());					
					taDescricao.setText(p.getDescricao());									
				}
			}
		});
	
	}

}
