package br.com.biblioteca.modelo;

import java.io.Serializable;

public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String rua;
	private String bairro;
	private String cidade;
	private String uf;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUF(String uF) {
		uf = uF;
	}
	
	@Override
	public String toString() {
		return getRua()+", "+getBairro()+", "+getCidade()+"/"+getUf();
	}

}
