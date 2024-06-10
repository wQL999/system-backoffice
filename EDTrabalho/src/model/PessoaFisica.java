package model;

public class PessoaFisica extends Cliente{
	
	public String cpf;
	public String nome;
	public String celular;
	
	public PessoaFisica(String cpf, String nome, String celular, String cep, String endereco, String complemento, int numPorta) {
		this.cpf = cpf;
		this.nome = nome;
		this.celular = celular;
		this.cep = cep;
		this.endereco = endereco;
		this.complemento = complemento;
		this.numPorta = numPorta;
	}

}
