package model;

public class PessoaJuridica extends Cliente {
	public String cnpj;
	public String nomeFantasia;
	public String telefone;
	public String email;
	
	public PessoaJuridica(String cnpj, String nomeFantasia, String telefone, String email, String cep, String endereco, String complemento, int numPorta) {
		this.cnpj = cnpj;
		this.nomeFantasia = nomeFantasia;
		this.telefone = telefone;
		this.email = email;
		this.cep = cep;
		this.endereco = endereco;
		this.complemento = complemento;
		this.numPorta = numPorta;
	}
}
