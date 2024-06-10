package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class viewBackoffice extends JFrame {

	private JPanel contentPane;
	viewBackoffice frame;
	/**
	 * Launch the application.
	 */
	public void mainVis() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					viewBackoffice frame = new viewBackoffice();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the shoppingCart.
	 */
	public viewBackoffice() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("CadastraCliente");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadCliente view = new cadCliente();
				view.main(null);
			}
		});
		btnNewButton.setBounds(26, 10, 108, 50);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("ExcluirCliente");
		btnNewButton_1.setBounds(168, 10, 108, 50);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Dashboard Produtos");
		viewBackoffice t = this;
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t.setVisible(false);
				DashboardProduct d = new DashboardProduct();
				d.main(null);
			}
		});
		btnNewButton_2.setBounds(21, 114, 185, 50);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("Dashboard Tipos de Produtos");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t.setVisible(false);
				DashboardTipoProdutos d = new DashboardTipoProdutos();
				d.main(null);
			}
		});
		btnNewButton_2_1.setBounds(21, 180, 185, 50);
		contentPane.add(btnNewButton_2_1);
	}

}
