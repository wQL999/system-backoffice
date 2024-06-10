package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Produto;
import model.TipoProduto;

public class TipoProdutoController implements ActionListener{
	public List<TipoProduto> repositoryTiposProdutos = new LinkedList<TipoProduto>();
	JTextField txtNome;
	JTextArea taDescricao;
	
	public TipoProdutoController(JTextField txtNome, JTextArea taDescricao) {
		this.txtNome = txtNome;
		this.taDescricao = taDescricao;
		
		readTiposProdutos();
	}
	
	public TipoProdutoController() {
		readTiposProdutos();
	}
	
	public List<TipoProduto> readTiposProdutos() {
		repositoryTiposProdutos.clear();
		try {
			BufferedReader fr = new BufferedReader(new FileReader("data/tipoProdutos.csv"));
			
			String text;
			
			try {
				while((text = fr.readLine()) != null) {
					String[] att = text.split(";");					
					repositoryTiposProdutos.add(new TipoProduto(Long.parseLong(att[0]), att[1], att[2]));
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return repositoryTiposProdutos;
	}
	
	public TipoProduto FindById(long cod) {
		for(TipoProduto p: repositoryTiposProdutos) {
			if(p.getCod() == cod)
				return p;
		}
		
		return null;
	}

	public TipoProduto FindByName(String nome) {				
		for(TipoProduto p: repositoryTiposProdutos) {
			if(p.getNome().equals(nome))
				return p;
		}
		
		return null;
	}
	
	public void remove(long cod) {			
		repositoryTiposProdutos.clear();
		
		String updateFile="";
		
		try {
			BufferedReader fr = new BufferedReader(new FileReader("data/tipoProdutos.csv"));
			
			String text;
			
			try {
				while((text = fr.readLine()) != null) {
					String[] att = text.split(";");
					if(text.equals("")) continue;
					
					if(!att[0].equals(String.valueOf(cod))) {
						updateFile += text + "\n";
					}				
				}
				
				fr.close();
				
				FileWriter fw = new FileWriter("data/tipoProdutos.csv");
				fw.append(updateFile);
				fw.close();
				
				readTiposProdutos();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void update(long cod) {
		TipoProdutoController contTipoController = new TipoProdutoController();
		TipoProduto p = new TipoProduto(cod, txtNome.getText(), taDescricao.getText());
		
		repositoryTiposProdutos.clear();
		
		String updateFile="";
		
		try {
			BufferedReader fr = new BufferedReader(new FileReader("data/tipoProdutos.csv"));
			
			String text;
			
			try {
				while((text = fr.readLine()) != null) {
					String[] att = text.split(";");
					if(text.equals("")) continue;
					
					if(!att[0].equals(String.valueOf(cod))) {
						updateFile += text + "\n";
					} else {
						updateFile += p.getCod() + ";" + p.getNome() + ";" + p.getDescricao() + "\n";
					}					
				}
				
				fr.close();
				
				FileWriter fw = new FileWriter("data/tipoProdutos.csv");
				fw.append(updateFile);
				fw.close();
				
				readTiposProdutos();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if(cmd.equals("Adicionar")) {
			try {
				FileWriter fw = new FileWriter("data/tipoProdutos.csv");															
				
				repositoryTiposProdutos.add(new TipoProduto(repositoryTiposProdutos.size()+1, txtNome.getText(), taDescricao.getText()));
				
				for(TipoProduto p: repositoryTiposProdutos) {
					fw.append(p.getCod() + ";" + p.getNome() + ";" + p.getDescricao() + "\n");
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
