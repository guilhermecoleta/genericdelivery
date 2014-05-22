package br.com.genericdelivery.model.entity;


public enum Funcionalidade {
	VISITANTE("Controle Visitante",Tela.MAIN),
	ADMIN("Controle Total",Tela.MAIN, Tela.USUARIO_CADASTRO, Tela.USUARIO_LISTAGEM);
	
	private String nome;
	private Tela[] telas;
	
	
	private Funcionalidade(String nome, Tela... telas) {
		this.nome = nome;
		this.telas = telas;
	}
	
	public static Funcionalidade getFuncionalidade(Tela tela){
		for (Funcionalidade funcionalidade : Funcionalidade.values()) {
			for (Tela item : funcionalidade.getTelas()) {
				if(tela.equals(item)) return funcionalidade;
			}
		}
		return null;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public Tela[] getTelas() {
		return telas;
	}


	public void setTelas(Tela[] telas) {
		this.telas = telas;
	}
	
	
	
	
	
}
