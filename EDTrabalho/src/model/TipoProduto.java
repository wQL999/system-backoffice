package model;

public class TipoProduto {	
	long cod;
	String nome;
	String descricao;
	
	public TipoProduto(long cod, String nome, String descricao) {
		this.cod = cod;
		this.nome = nome;
		this.descricao = descricao;
	}
	
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
