package br.com.genericdelivery.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "tcc", name = "produto_cardapio")
public class ProdutoCardapio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2902434514829176011L;
	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "produto")
	private Produto produto;

	@Id
	@ManyToOne
	@JoinColumn(name = "cardapio")
	private Cardapio cardapio;

	@Column(name = "valor", nullable = false, length = 10)
	private Double valor;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Cardapio getCardapio() {
		return cardapio;
	}

	public void setCardapio(Cardapio cardapio) {
		this.cardapio = cardapio;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}
