package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;
import controller.ClienteController;
import model.PessoaFisica;
import model.PessoaJuridica;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class DashboardCustomer extends JFrame {
	private static final long serialVersionUID = 1L;
	ClienteController customerCont;
	private DefaultTableModel tableModel;
	private JTable table;
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtCPF;
	private JTextField txtCelular;
	private JTextField txtCEP;
	private JTextField txtEndereco;
	private JTextField txtComplemento;
	private JTextField txtNumporta;
	private JTextField txtEmail;
	private JRadioButton rdbtnPF;
	private JRadioButton rdbtnPJ;
	private JLabel lblCelular;
	private JLabel lblCPF;
	private JLabel lblNome;
	private JLabel lblCEP;
	private JLabel lblEndereco;
	private JLabel lblComplemento;
	private JLabel lblNumporta;
	private JLabel lblEmail;
	private JTextField txtNomeFantasia;
	private JTextField txtCNPJ;
	private JTextField txtTelefone;
	private JLabel lblCNPJ;
	private JLabel lblNomeFantasia;
	private JLabel lblTelefone;
	private Object[][] data;
	private String[] columns;
	private int selectedIndex = 0;

	String columnsPF[] = {"CPF", "Nome", "Celular", "CEP", "Endereco", "Complemento", "Numero Porta"};
	String columnsPJ[] = {"CNPJ", "Nome Fantasia", "Telefone", "Email", "CEP", "Endereco", "Complemento", "Numero Porta"};

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DashboardCustomer frame = new DashboardCustomer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DashboardCustomer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1017, 521);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		customerCont = new ClienteController();

		JPanel panel = new JPanel();
		panel.setBounds(20, 89, 298, 385);
		contentPane.add(panel);
		panel.setLayout(null);

		rdbtnPF = new JRadioButton("Pessoa Física");
		rdbtnPF.setSelected(true);
		rdbtnPF.setBounds(28, 6, 103, 21);
		panel.add(rdbtnPF);

		rdbtnPJ = new JRadioButton("Pessoa Jurídica");
		rdbtnPJ.setBounds(156, 6, 103, 21);
		panel.add(rdbtnPJ);

		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnPF);
		bg.add(rdbtnPJ);

        rdbtnPF.addActionListener(e -> updateTable());
        rdbtnPJ.addActionListener(e -> updateTable());

		tableModel = new DefaultTableModel();
		columns = columnsPF; // Initialize with PF columns
		for (String column : columns) {
			tableModel.addColumn(column);
		}

		data = new Object[customerCont.customerPF.size()][columns.length];
		int base = 0;
		for (PessoaFisica ppf : customerCont.customerPF) {
			Object[] aux = {ppf.cpf, ppf.nome, ppf.celular, ppf.cep, ppf.endereco, ppf.complemento, ppf.numPorta};
			data[base++] = aux;
		}
		for (Object[] row : data) {
			tableModel.addRow(row);
		}

		table = new JTable(tableModel) {
			@Override public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
				Component c = super.prepareRenderer(renderer, row, col);
				int selCol = table.getSelectedColumn();
				int selRow = table.getSelectedRow();
				if (selCol != -1 && selRow != -1) {
					if (row == selRow) {
						c.setBackground(new Color(82, 183, 233));
					} else {
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

		for (int c = 0; c < table.getColumnCount(); c++) {
			Class<?> col_class = table.getColumnClass(c);
			table.setDefaultEditor(col_class, null);
		}

		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(true);
		table.setRowSelectionAllowed(true);

		JButton btnCreate = new JButton("Tela de novo cliente");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							cadCliente frame = new cadCliente();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCreate.setBounds(20, 35, 298, 44);
		contentPane.add(btnCreate);

		JButton btnUpdate = new JButton("Salvar alterações");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerCont = new ClienteController(txtNome, txtCPF, txtCelular, txtCNPJ, txtTelefone, txtNomeFantasia, txtEmail, txtCEP, txtEndereco, txtComplemento, txtNumporta);
				if (rdbtnPF.isSelected()) {
					Object[] aux = {txtCPF.getText(), txtNome.getText(), txtCelular.getText(), txtCEP.getText(), txtEndereco.getText(), txtComplemento.getText(), txtNumporta.getText()};
					data[selectedIndex] = aux;
					customerCont.update(customerCont.customerPF.get(table.getSelectedRow()).cpf, true);
					for (int i = 0; i < aux.length; i++) {
						table.getModel().setValueAt(aux[i], table.getSelectedRow(), i);
					}
				} else if (rdbtnPJ.isSelected()) {
					Object[] aux = {txtCNPJ.getText(), txtNomeFantasia.getText(), txtTelefone.getText(), txtEmail.getText(), txtCEP.getText(), txtEndereco.getText(), txtComplemento.getText(), txtNumporta.getText()};
					data[selectedIndex] = aux;
					customerCont.update(customerCont.customerPJ.get(table.getSelectedRow()).cnpj, false);
					for (int i = 0; i < aux.length; i++) {
						table.getModel().setValueAt(aux[i], table.getSelectedRow(), i);
					}
				}
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnUpdate.setBounds(10, 343, 121, 32);
		panel.add(btnUpdate);

		JButton btnDelete = new JButton("Excluir");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnPF.isSelected()) {
					customerCont.remove(customerCont.customerPF.get(selectedIndex).cpf, true);
					tableModel.removeRow(selectedIndex);
				} else if (rdbtnPJ.isSelected()) {
					customerCont.remove(customerCont.customerPJ.get(selectedIndex).cnpj, false);
					tableModel.removeRow(selectedIndex);
				}
				resetFields();
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDelete.setBounds(178, 343, 110, 32);
		panel.add(btnDelete);

		lblCelular = new JLabel("Celular:");
		lblCelular.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCelular.setBounds(10, 81, 99, 13);
		panel.add(lblCelular);

		lblCPF = new JLabel("CPF:");
		lblCPF.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCPF.setBounds(10, 58, 133, 13);
		panel.add(lblCPF);

		lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNome.setBounds(10, 35, 133, 13);
		panel.add(lblNome);

		lblCEP = new JLabel("CEP:");
		lblCEP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCEP.setBounds(10, 127, 63, 13);
		panel.add(lblCEP);

		lblEndereco = new JLabel("Endereço:");
		lblEndereco.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEndereco.setBounds(10, 150, 81, 13);
		panel.add(lblEndereco);

		lblComplemento = new JLabel("Complemento:");
		lblComplemento.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblComplemento.setBounds(10, 173, 110, 13);
		panel.add(lblComplemento);

		lblNumporta = new JLabel("Número Porta:");
		lblNumporta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNumporta.setBounds(10, 196, 110, 13);
		panel.add(lblNumporta);

		lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail.setBounds(10, 104, 63, 13);
		panel.add(lblEmail);

		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(153, 34, 135, 19);
		panel.add(txtNome);

		txtCPF = new JTextField();
		txtCPF.setColumns(10);
		txtCPF.setBounds(153, 57, 135, 19);
		txtCPF.setEnabled(false);
		panel.add(txtCPF);

		txtCelular = new JTextField();
		txtCelular.setColumns(10);
		txtCelular.setBounds(153, 80, 135, 19);
		panel.add(txtCelular);

		txtCEP = new JTextField();
		txtCEP.setColumns(10);
		txtCEP.setBounds(153, 126, 135, 19);
		panel.add(txtCEP);

		txtEndereco = new JTextField();
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(153, 149, 135, 19);
		panel.add(txtEndereco);

		txtComplemento = new JTextField();
		txtComplemento.setColumns(10);
		txtComplemento.setBounds(153, 172, 135, 19);
		panel.add(txtComplemento);

		txtNumporta = new JTextField();
		txtNumporta.setColumns(10);
		txtNumporta.setBounds(153, 195, 135, 19);
		panel.add(txtNumporta);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(153, 103, 135, 19);
		panel.add(txtEmail);

		lblNomeFantasia = new JLabel("Nome Fantasia:");
		lblNomeFantasia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNomeFantasia.setBounds(10, 35, 133, 13);
		panel.add(lblNomeFantasia);

		lblCNPJ = new JLabel("CNPJ:");
		lblCNPJ.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCNPJ.setBounds(10, 58, 133, 13);
		panel.add(lblCNPJ);

		lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTelefone.setBounds(10, 81, 99, 13);
		panel.add(lblTelefone);

		txtNomeFantasia = new JTextField();
		txtNomeFantasia.setColumns(10);
		txtNomeFantasia.setBounds(153, 34, 135, 19);
		panel.add(txtNomeFantasia);

		txtCNPJ = new JTextField();
		txtCNPJ.setColumns(10);
		txtCNPJ.setEnabled(false);
		txtCNPJ.setBounds(153, 57, 135, 19);
		panel.add(txtCNPJ);

		txtTelefone = new JTextField();
		txtTelefone.setColumns(10);
		txtTelefone.setBounds(153, 80, 135, 19);
		panel.add(txtTelefone);

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {

                    selectedIndex = table.getSelectedRow();
                    String selectedValue = table.getValueAt(selectedIndex, 0).toString();
                    if(rdbtnPF.isSelected()) {
                    	PessoaFisica pf = customerCont.findPF(selectedValue);
                    	txtCPF.setText(pf.cpf);
                    	txtNome.setText(pf.nome);
                    	txtCelular.setText(pf.celular);
                    	txtCEP.setText(pf.cep);
                    	txtEndereco.setText(pf.endereco);
                    	txtComplemento.setText(pf.complemento);
                    	txtNumporta.setText(Integer.toString(pf.numPorta));
                    } else {
                    	PessoaJuridica pj = customerCont.findPJ(selectedValue);
                    	txtCNPJ.setText(pj.cnpj);
                    	txtNomeFantasia.setText(pj.nomeFantasia);
                    	txtTelefone.setText(pj.telefone);
                    	txtEmail.setText(pj.email);
                    	txtCEP.setText(pj.cep);
                    	txtEndereco.setText(pj.endereco);
                    	txtComplemento.setText(pj.complemento);
                    	txtNumporta.setText(Integer.toString(pj.numPorta));
                    }
                    
                } else {
                	resetFields();
                }
            }
        });

		updateTable();
	}

    private void updateTable() {
        tableModel.setRowCount(0); 
        tableModel.setColumnCount(0);

        if (rdbtnPF.isSelected()) {
        	displayFields(true);
            for (String column : columnsPF) {
                tableModel.addColumn(column);
            }
            for (PessoaFisica ppf : customerCont.customerPF) {
                Object[] row = {
                    ppf.cpf.toString(), 
                    ppf.nome, 
                    ppf.celular, 
                    ppf.cep, 
                    ppf.endereco, 
                    ppf.complemento, 
                    Integer.toString(ppf.numPorta)
                };
                tableModel.addRow(row);
            }
        } else if (rdbtnPJ.isSelected()) {
        	displayFields(false);
            for (String column : columnsPJ) {
                tableModel.addColumn(column);
            }
            for (PessoaJuridica ppj : customerCont.customerPJ) {
                Object[] row = {
                    ppj.cnpj.toString(), 
                    ppj.nomeFantasia, 
                    ppj.telefone, 
                    ppj.email, 
                    ppj.cep, 
                    ppj.endereco, 
                    ppj.complemento, 
                    Integer.toString(ppj.numPorta)
                };
                tableModel.addRow(row);
            }
        }

        table.setModel(tableModel); 
    }

	private void displayFields(boolean isPF) {
		lblNome.setVisible(isPF);
		lblCPF.setVisible(isPF);
		lblCelular.setVisible(isPF);
		lblNomeFantasia.setVisible(!isPF);
		lblCNPJ.setVisible(!isPF);
		lblTelefone.setVisible(!isPF);
		lblEmail.setVisible(!isPF);

		txtNome.setVisible(isPF);
		txtCPF.setVisible(isPF);
		txtCelular.setVisible(isPF);
		txtNomeFantasia.setVisible(!isPF);
		txtCNPJ.setVisible(!isPF);
		txtTelefone.setVisible(!isPF);
		txtEmail.setVisible(!isPF);
	}

	private void resetFields() {
		txtNome.setText("");
		txtCPF.setText("");
		txtCelular.setText("");
		txtNomeFantasia.setText("");
		txtCNPJ.setText("");
		txtTelefone.setText("");
		txtEmail.setText("");
		txtCEP.setText("");
		txtEndereco.setText("");
		txtComplemento.setText("");
		txtNumporta.setText("");
	}
}