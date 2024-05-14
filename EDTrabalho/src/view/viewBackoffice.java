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
	 * Create the frame.
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
				view.mainVis();
			}
		});
		btnNewButton.setBounds(26, 89, 108, 50);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("ExcluirCliente");
		btnNewButton_1.setBounds(158, 89, 108, 50);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("ConsultarCLiente");
		btnNewButton_2.setBounds(291, 89, 108, 50);
		contentPane.add(btnNewButton_2);
	}

}
