package br.com.genericdelivery.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(schema = "tcc", name = "cardapio")
public class Cardapio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2011581397801625004L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false)
	private Integer id;

	@Column(nullable = true, name = "descricao", length = 50)
	private String descricao;

	@Column(name = "data_hora", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHora;

	@Column(name = "ativo", nullable = false)
	private Boolean ativo;

	@OneToMany(mappedBy = "cardapio", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<ProdutoCardapio> produtos;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario", nullable = false)
	private Usuario usuario;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataHora() {
		if (dataHora == null)
			dataHora = new Date();
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public Boolean getAtivo() {
		if (ativo == null)
			ativo = true;
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public List<ProdutoCardapio> getProdutos() {
		if (produtos == null) {
			produtos = new ArrayList<ProdutoCardapio>();
		}

		return produtos;
	}

	public void setProdutos(List<ProdutoCardapio> produtos) {
		this.produtos = produtos;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
