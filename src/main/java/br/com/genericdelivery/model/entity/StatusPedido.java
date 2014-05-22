package br.com.genericdelivery.model.entity;

public enum StatusPedido {
	AGUARDANDO("aguardando", "Aguardando Aprovação"),
	PREPARANDO("preparando", "Em preparo"),
	FINALIZADO("finalizado", "Finalizado"),
	ENCAMINHADO("encaminhado", "Saiu para a entrega"),
	ENTREGUE("entregue", "Entregue"),
	NAO_ENTREGUE("nao entregue", "Não entregue");
	
	private String status;
	private String descricao;


	private StatusPedido(String status, String descricao) {
		this.status = status;
		this.descricao = descricao;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
