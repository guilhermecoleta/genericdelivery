package br.com.genericdelivery.model.entity;

public enum Permissao {
	LEITURA("Leitura"), ESCRITA("Escrita");
	
	private String valor;

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	private Permissao(String valor) {
		this.valor = valor;
	}
	
	
	
	
}
