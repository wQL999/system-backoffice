package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JPasswordField;

import controller.ProdutoController;
import controller.TipoProdutoController;
import model.TipoProduto;

public class MaintainTipoProdutos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MaintainProduct frame = new MaintainProduct();
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
	public MaintainTipoProdutos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setBounds(22, 10, 45, 13);
		contentPane.add(lblNewLabel);
		
		txtNome = new JTextField();
		txtNome.setBounds(22, 33, 96, 19);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Descrição:");
		lblNewLabel_1.setBounds(22, 86, 80, 13);
		contentPane.add(lblNewLabel_1);
		
		JTextArea taDescricao = new JTextArea();
		taDescricao.setBounds(22, 109, 390, 97);
		contentPane.add(taDescricao);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(70, 212, 85, 21);
		contentPane.add(btnAdicionar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(208, 212, 85, 21);
		contentPane.add(btnLimpar);
		
		TipoProdutoController pCont = new TipoProdutoController(txtNome, taDescricao);
		
		btnAdicionar.addActionListener(pCont);
		btnLimpar.addActionListener(pCont);
	}
}
