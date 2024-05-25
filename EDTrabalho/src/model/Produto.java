package model;

public class Produto {
	public long cod;
	public String nome;
	public double valor;
	public String descricao;
	public long qtdEstoque;
	public TipoProduto tipo;
	
	public Produto(long cod, String nome, double valor, String descricao, long qtdEstoque, TipoProduto tipo) {		
		this.cod = cod;
		this.nome = nome;
		this.valor = valor;
		this.descricao = descricao;
		this.qtdEstoque = qtdEstoque;
		this.tipo = tipo;
	}
}
