package model;

public class Produto {
	long cod;
	String nome;
	double valor;
	String descricao;
	long qtdEstoque;
	TipoProduto tipo;

	public long getCod() {
		return cod;
	}

	public void setCod(long cod) {
		this.cod = cod;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public long getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(long qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	public TipoProduto getTipo() {
		return tipo;
	}

	public void setTipo(TipoProduto tipo) {
		this.tipo = tipo;
	}

	public Produto(long cod, String nome, double valor, String descricao, long qtdEstoque, TipoProduto tipo) {
		this.cod = cod;
		this.nome = nome;
		this.valor = valor;
		this.descricao = descricao;
		this.qtdEstoque = qtdEstoque;
		this.tipo = tipo;
	}
}