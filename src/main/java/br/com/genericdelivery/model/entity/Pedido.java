package br.com.genericdelivery.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

import br.com.genericdelivery.util.annotations.Required;

@Entity
@Table (schema="tcc", name = "pedido")
public class Pedido implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2395715489520148706L;

	@Id
	@Required
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "codigo", nullable = false)
	private Integer codigo;

	@Column(name = "data_hora", nullable = false)
	@Required
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHora;
	
	@ManyToOne
	@JoinColumn(name = "usuario", nullable = false)
	@Required
	private Usuario usuario;
	
	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	@Required
	private StatusPedido status;

	@Column(name = "ativo", nullable = true)
	@Required
	private Boolean ativo;
	
	@Column(name = "observacoes", nullable = true, length = 255)
	@Required
	private String observacoes;
	
	@OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<ProdutoPedido> produtos;
	
	public List<ProdutoPedido> getProdutos() {
		if (produtos == null) {
			produtos = new ArrayList<ProdutoPedido>();
			
		}

		return produtos;
	}

	public void setProdutos(List<ProdutoPedido> produtos) {
		this.produtos = produtos;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ativo == null) ? 0 : ativo.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result
				+ ((dataHora == null) ? 0 : dataHora.hashCode());
		result = prime * result
				+ ((observacoes == null) ? 0 : observacoes.hashCode());
		result = prime * result
				+ ((produtos == null) ? 0 : produtos.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (ativo == null) {
			if (other.ativo != null)
				return false;
		} else if (!ativo.equals(other.ativo))
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (dataHora == null) {
			if (other.dataHora != null)
				return false;
		} else if (!dataHora.equals(other.dataHora))
			return false;
		if (observacoes == null) {
			if (other.observacoes != null)
				return false;
		} else if (!observacoes.equals(other.observacoes))
			return false;
		if (produtos == null) {
			if (other.produtos != null)
				return false;
		} else if (!produtos.equals(other.produtos))
			return false;
		if (status != other.status)
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
	
	
	
}
