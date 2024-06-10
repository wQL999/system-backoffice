package model;

abstract class Cliente {
	
	public String cep;
	public String endereco;
	public String complemento;
	public int numPorta;
	
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public int getNumPorta() {
		return numPorta;
	}
	public void setNumPorta(int numPorta) {
		this.numPorta = numPorta;
	}
}
