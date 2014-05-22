package br.com.genericdelivery.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "produto_pedido")
public class ProdutoPedido implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4672568553421754002L;

	@Id
	@ManyToOne
	@JoinColumn(name = "produto", nullable = false)
	private Produto produto;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "pedido", nullable = false)
	private Pedido pedido;
	
	@Column(name = "quantidade", nullable = false)
	private Double quantidade;
	
	@Column(name = "valor", nullable = false)
	private Double valor;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	
}
