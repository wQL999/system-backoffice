package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Produto;
import model.TipoProduto;

public class ProdutoController implements ActionListener {
	public static long identityCod=1;
	
	JTextField txtNome;
	JTextField txtValor;
	JTextField txtQuantidade;
	JComboBox<String> cbTipoProduto;
	JTextArea taDescricao;
	
	public List<Produto> repositoryProdutos = new LinkedList<Produto>();
	
	public ProdutoController() {
		readProdutos();
	}
	
	public ProdutoController(JTextField txtNome, JTextField txtValor, JTextField txtQuantidade,
			JComboBox<String> cbTipoProduto, JTextArea taDescricao) {		
		super();
		readProdutos();
		this.txtNome = txtNome;
		this.txtValor = txtValor;
		this.txtQuantidade = txtQuantidade;
		this.cbTipoProduto = cbTipoProduto;
		this.taDescricao = taDescricao;
	}
	
	public List<Produto> readProdutos() {			
		repositoryProdutos.clear();
		try {
			BufferedReader fr = new BufferedReader(new FileReader("data/products.csv"));
			
			String text;
			
			try {
				while((text = fr.readLine()) != null) {
					String[] att = text.split(";");
					TipoProduto tipo = (new TipoProdutoController()).FindById(Long.parseLong(att[5]));
					repositoryProdutos.add(new Produto(Long.parseLong(att[0]), att[1], Double.parseDouble(att[2]), att[3], Long.parseLong(att[4]), tipo));				
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return repositoryProdutos;
	}

	public Produto FindByName(String nome) {				
		for(Produto p: repositoryProdutos) {
			if(p.getNome().equals(nome))
				return p;
		}
		return null;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if(cmd.equals("Adicionar")) {
			try {
				FileWriter fw = new FileWriter("data/products.csv");						
				
				TipoProdutoController contTipoProdutos = new TipoProdutoController();										
				
				repositoryProdutos.add(new Produto(ProdutoController.identityCod++, txtNome.getText(), Double.parseDouble(txtValor.getText()), taDescricao.getText(), Long.parseLong(txtQuantidade.getText()), contTipoProdutos.FindByName((String)cbTipoProduto.getSelectedItem())));
				
				for(Produto p: repositoryProdutos) {
					fw.append(p.getCod() + ";" + p.getNome() + ";" + p.getValor() + ";" + p.getDescricao() + ";" + p.getQtdEstoque() + ";" + p.getTipo().getCod() + "\n");
				}
				fw.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		} else if(cmd.equals("Limpar")){
			
		}
		
	}
}
