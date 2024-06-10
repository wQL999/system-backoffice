package view;

import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ClienteController;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;

public class cadCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNome;
	private JLabel lblCPF;
	private JLabel lblCelular;
	private JLabel lblCNPJ;
	private JLabel lblCEP;
	private JLabel lblEndereco;
	private JLabel lblComplemento;
	private JLabel lblNumPorta;
	private JLabel lblEmail;
	private JLabel lblNomeFantasia;
	private JLabel lblTelefone;	
	private JTextField txtNome;
	private JTextField txtCPF;
	private JTextField txtCelular;
	private JTextField txtCEP;
	private JTextField txtEndereco;
	private JTextField txtComplemento;
	private JTextField txtNumporta;
	private JTextField txtNomeFantasia;
	private JTextField txtEmail;
	private JTextField txtCNPJ;
	private JTextField txtTelefone;
	private JRadioButton rdbtnPF;
	private JRadioButton rdbtnPJ;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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

	/**
	 * Create the frame.
	 */
	public cadCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 525, 301);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		ButtonGroup bg = new ButtonGroup();
		rdbtnPF = new JRadioButton("Pessoa Física");
		rdbtnPF.setSelected(true);
		rdbtnPF.setBounds(22, 6, 103, 21);
		contentPane.add(rdbtnPF);
		
		rdbtnPJ = new JRadioButton("Pessoa Jurídica");
		rdbtnPJ.setBounds(126, 6, 103, 21);
		contentPane.add(rdbtnPJ);
		
		bg.add(rdbtnPF);
		bg.add(rdbtnPJ);
		RadioButtonLis rdLis = new RadioButtonLis();
		rdbtnPF.addItemListener(rdLis);
		rdbtnPJ.addItemListener(rdLis);

		lblCEP = new JLabel("CEP:");
		lblCEP.setBounds(22, 136, 45, 13);
		contentPane.add(lblCEP);
		
		txtCEP = new JTextField();
		txtCEP.setBounds(22, 159, 96, 19);
		contentPane.add(txtCEP);
		txtCEP.setColumns(10);
		
		lblEndereco = new JLabel("Endereco:");
		lblEndereco.setBounds(146, 136, 56, 13);
		contentPane.add(lblEndereco);
		
		txtEndereco = new JTextField();
		txtEndereco.setBounds(146, 159, 96, 19);
		contentPane.add(txtEndereco);
		txtEndereco.setColumns(10);
		
		lblComplemento = new JLabel("Complemento:");
		lblComplemento.setBounds(271, 136, 96, 13);
		contentPane.add(lblComplemento);
		
		txtComplemento = new JTextField();
		txtComplemento.setBounds(271, 159, 96, 19);
		contentPane.add(txtComplemento);
		txtComplemento.setColumns(10);
		
		lblNumPorta = new JLabel("Numero Porta:");
		lblNumPorta.setBounds(398, 136, 90, 13);
		contentPane.add(lblNumPorta);
		
		txtNumporta = new JTextField();
		txtNumporta.setBounds(398, 159, 96, 19);
		contentPane.add(txtNumporta);
		txtNumporta.setColumns(10);
		
		lblNomeFantasia = new JLabel("Nome Fantasia:");
		lblNomeFantasia.setVisible(false);
		lblNomeFantasia.setBounds(22, 33, 96, 13);
		contentPane.add(lblNomeFantasia);
		
		txtNomeFantasia = new JTextField();
		txtNomeFantasia.setVisible(false);
		txtNomeFantasia.setBounds(22, 56, 96, 19);
		contentPane.add(txtNomeFantasia);
		txtNomeFantasia.setColumns(10);
		
		lblCNPJ = new JLabel("CNPJ: ");
		lblCNPJ.setVisible(false);
		lblCNPJ.setBounds(146, 33, 45, 13);
		contentPane.add(lblCNPJ);
		
		txtCNPJ = new JTextField();
		txtCNPJ.setVisible(false);
		txtCNPJ.setColumns(10);
		txtCNPJ.setBounds(146, 56, 96, 19);
		contentPane.add(txtCNPJ);
		
		lblTelefone = new JLabel("Telefone:");
		lblTelefone.setVisible(false);
		lblTelefone.setBounds(271, 33, 96, 13);
		contentPane.add(lblTelefone);
		
		txtTelefone = new JTextField();
		txtTelefone.setVisible(false);
		txtTelefone.setBounds(271, 56, 96, 19);
		contentPane.add(txtTelefone);
		txtTelefone.setColumns(10);
		
		lblEmail = new JLabel("Email:");
		lblEmail.setVisible(false);
		lblEmail.setBounds(398, 33, 45, 13);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setVisible(false);
		txtEmail.setBounds(398, 56, 96, 19);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		lblNome = new JLabel("Nome:");
		lblNome.setBounds(22, 33, 45, 13);
		contentPane.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(22, 56, 96, 19);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		lblCPF = new JLabel("CPF: ");
		lblCPF.setBounds(146, 33, 45, 13);
		contentPane.add(lblCPF);
		
		txtCPF = new JTextField();
		txtCPF.setColumns(10);
		txtCPF.setBounds(146, 56, 96, 19);
		contentPane.add(txtCPF);
		
		lblCelular = new JLabel("Celular:");
		lblCelular.setBounds(271, 33, 45, 13);
		contentPane.add(lblCelular);
		
		txtCelular = new JTextField();
		txtCelular.setBounds(271, 56, 96, 19);
		contentPane.add(txtCelular);
		txtCelular.setColumns(10);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(126, 225, 103, 21);
		contentPane.add(btnAdicionar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(271, 225, 85, 21);
		contentPane.add(btnLimpar);
		
		ClienteController cCont = new ClienteController(
				txtNome,
				txtCPF,
				txtCelular,
				txtCNPJ,
				txtTelefone,
				txtNomeFantasia,
				txtEmail,
				txtCEP,
				txtEndereco,
				txtComplemento,
				txtNumporta
		);
		
		btnAdicionar.addActionListener(cCont);
		btnLimpar.addActionListener(cCont);
		
		
		
	}
	
	private class RadioButtonLis implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			JRadioButton btn = (JRadioButton) e.getSource();
			
			if(btn == rdbtnPF) {
				
				//turn on
				lblNome.setVisible(true);
				lblCPF.setVisible(true);
				lblCelular.setVisible(true);
				txtNome.setVisible(true);
				txtCPF.setVisible(true);
				txtCelular.setVisible(true);
			
				//turn off
				lblNomeFantasia.setVisible(false);
				lblCNPJ.setVisible(false);
				lblTelefone.setVisible(false);
				lblEmail.setVisible(false);
				txtNomeFantasia.setVisible(false);
				txtCNPJ.setVisible(false);
				txtTelefone.setVisible(false);
				txtEmail.setVisible(false);
				
				txtCPF.setText("");
				txtNome.setText("");
				txtCelular.setText("");
				txtCNPJ.setText("");
				txtNomeFantasia.setText("");
				txtTelefone.setText("");
				txtEmail.setText("");
				txtCEP.setText("");
				txtEndereco.setText("");
				txtComplemento.setText("");
				txtNumporta.setText("");
			} else if(btn == rdbtnPJ){
				//turn off
				lblNome.setVisible(false);
				lblCPF.setVisible(false);
				lblCelular.setVisible(false);
				txtNome.setVisible(false);
				txtCPF.setVisible(false);
				txtCelular.setVisible(false);
			
				//turn on
				lblNomeFantasia.setVisible(true);
				lblCNPJ.setVisible(true);
				lblTelefone.setVisible(true);
				lblEmail.setVisible(true);
				txtNomeFantasia.setVisible(true);
				txtCNPJ.setVisible(true);
				txtTelefone.setVisible(true);
				txtEmail.setVisible(true);
				
				txtCPF.setText("");
				txtNome.setText("");
				txtCelular.setText("");
				txtCNPJ.setText("");
				txtNomeFantasia.setText("");
				txtTelefone.setText("");
				txtEmail.setText("");
				txtCEP.setText("");
				txtEndereco.setText("");
				txtComplemento.setText("");
				txtNumporta.setText("");
			}
		}
	}
	
}
