package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
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
import utils.HashTable;

public class ProdutoController implements ActionListener {
	JTextField txtNome;
	JTextField txtValor;
	JTextField txtQuantidade;
	JComboBox<String> cbTipoProduto;
	JTextArea taDescricao;
	HashTable hash;
	
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
			
			hash = new HashTable();
			for(Produto p: repositoryProdutos) {
				hash.add(p);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return repositoryProdutos;
	}
	
	public void remove(long cod) {			
		repositoryProdutos.clear();
		
		String updateFile="";
		
		try {
			BufferedReader fr = new BufferedReader(new FileReader("data/products.csv"));
			
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
				
				FileWriter fw = new FileWriter("data/products.csv");
				fw.append(updateFile);
				fw.close();
				
				readProdutos();
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
		Produto p = new Produto(cod, txtNome.getText(), Double.parseDouble(txtValor.getText()), taDescricao.getText(), Long.parseLong(txtQuantidade.getText()), contTipoController.FindByName((String)cbTipoProduto.getSelectedItem()));
		
		repositoryProdutos.clear();
		
		String updateFile="";
		
		try {
			BufferedReader fr = new BufferedReader(new FileReader("data/products.csv"));
			
			String text;
			
			try {
				while((text = fr.readLine()) != null) {
					String[] att = text.split(";");
					if(text.equals("")) continue;
					
					if(!att[0].equals(String.valueOf(cod))) {
						updateFile += text + "\n";
					} else {
						updateFile += p.getCod() + ";" + p.getNome() + ";" + p.getValor() + ";" + p.getDescricao() + ";" + p.getQtdEstoque() + ";" + p.getTipo().getCod() + "\n";
					}					
				}
				
				fr.close();
				
				FileWriter fw = new FileWriter("data/products.csv");
				fw.append(updateFile);
				fw.close();
				
				readProdutos();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void subtractQtd(String nome, long qtd) throws IOException {
		Produto p = FindByName(nome);
		long cod = p.getCod();
		
        File inputFile = new File("data/products.csv");
        File tempFile = new File("data/products.csv" + ".tmp");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
        	
            String line;
            
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                try {
                    long currentId = Long.parseLong(parts[0].trim());
                    if (currentId == cod) {
                        try {
                            int qtdEstoque = Integer.parseInt(parts[5].trim());
                            qtdEstoque = (int) (qtdEstoque - qtd);
                            parts[5] = String.valueOf(qtdEstoque);
                            line = String.join(";", parts);
                        } catch (NumberFormatException e) {
                        }
                    }
                } catch (NumberFormatException e) {
                }
                writer.write(line + System.lineSeparator());
            }
        }
        tempFile.renameTo(inputFile);
    }
	


	public Produto FindByName(String nome) {				
		try {
			return hash.get(nome);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
				
				repositoryProdutos.add(new Produto(repositoryProdutos.size()+1, txtNome.getText(), Double.parseDouble(txtValor.getText()), taDescricao.getText(), Long.parseLong(txtQuantidade.getText()), contTipoProdutos.FindByName((String)cbTipoProduto.getSelectedItem())));
				
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

		txtNome.setText("");
		txtQuantidade.setText("");
		txtValor.setText("");
		taDescricao.setText("");
		cbTipoProduto.setSelectedIndex(0);
		
	}
}
