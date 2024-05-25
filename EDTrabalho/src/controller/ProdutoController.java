package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.TipoProduto;

public class ProdutoController implements ActionListener {
	JTextField txtNome;
	JTextField txtValor;
	JTextField txtQuantidade;
	JComboBox<TipoProduto> cbTipoProduto;
	JTextArea taDescricao;
	
	public ProdutoController(JTextField txtNome, JTextField txtValor, JTextField txtQuantidade,
			JComboBox<TipoProduto> cbTipoProduto, JTextArea taDescricao) {
		super();
		this.txtNome = txtNome;
		this.txtValor = txtValor;
		this.txtQuantidade = txtQuantidade;
		this.cbTipoProduto = cbTipoProduto;
		this.taDescricao = taDescricao;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if(cmd.equals("Adicionar")) {
			
		} else if(cmd.equals("Limpar")){
			
		}
		
	}
}
