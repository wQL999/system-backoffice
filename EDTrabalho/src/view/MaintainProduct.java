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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MaintainProduct extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
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
	public MaintainProduct() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setBounds(10, 38, 45, 13);
		contentPane.add(lblNewLabel);
		
		txtNome = new JTextField();
		txtNome.setBounds(10, 61, 96, 19);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Descrição:");
		lblNewLabel_1.setBounds(213, 10, 80, 13);
		contentPane.add(lblNewLabel_1);
		
		txtValor = new JTextField();
		txtValor.setColumns(10);
		txtValor.setBounds(10, 113, 96, 19);
		contentPane.add(txtValor);
		
		JLabel lblValor = new JLabel("Valor:");
		lblValor.setBounds(10, 90, 45, 13);
		contentPane.add(lblValor);
		
		txtQuantidade = new JTextField();
		txtQuantidade.setBounds(10, 165, 96, 19);
		contentPane.add(txtQuantidade);
		txtQuantidade.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Quantidade em estoque:");
		lblNewLabel_2.setBounds(10, 142, 117, 13);
		contentPane.add(lblNewLabel_2);
		
		JTextArea taDescricao = new JTextArea();
		taDescricao.setBounds(213, 33, 192, 80);
		contentPane.add(taDescricao);
		
		JLabel lblNewLabel_2_1 = new JLabel("Categoria  do produto");
		lblNewLabel_2_1.setBounds(213, 140, 117, 13);
		contentPane.add(lblNewLabel_2_1);
		
		TipoProdutoController contTipoProdutos = new TipoProdutoController();
		
		JComboBox<String> cbTipoProduto = new JComboBox<String>();
		
		for(TipoProduto t: contTipoProdutos.repositoryTiposProdutos) {
			cbTipoProduto.addItem(t.getNome());
		}
		
		cbTipoProduto.setBounds(213, 163, 192, 21);
		contentPane.add(cbTipoProduto);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(70, 212, 85, 21);
		contentPane.add(btnAdicionar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(208, 212, 85, 21);
		contentPane.add(btnLimpar);
		
		ProdutoController pCont = new ProdutoController(txtNome, txtValor, txtQuantidade, cbTipoProduto, taDescricao);
		
		JButton btnVoltar = new JButton("Voltar");
		MaintainProduct t = this;
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t.setVisible(false);
				DashboardProduct d = new DashboardProduct();
				d.setVisible(true);
			}
		});
		btnVoltar.setBounds(10, 6, 74, 22);
		contentPane.add(btnVoltar);
		
		btnAdicionar.addActionListener(pCont);
		btnLimpar.addActionListener(pCont);
	}
}
