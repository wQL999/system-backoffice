package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import model.Produto;
import model.TipoProduto;

public class TipoProdutoController {
	public List<TipoProduto> repositoryTiposProdutos = new LinkedList<TipoProduto>();

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

}