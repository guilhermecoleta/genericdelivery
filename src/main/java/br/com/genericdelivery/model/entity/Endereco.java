package br.com.genericdelivery.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.genericdelivery.util.annotations.Required;

@Entity
@Table(schema = "tcc", name = "endereco")
public class Endereco implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1922525322024118234L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "tipo_logradouro", nullable = false, length = 50)
	@Enumerated(EnumType.STRING)
	private TipoLogradouro tipoLogradouro;

	@Column(name = "logradouro", nullable = false, length = 50)
	private String logradouro;

	@Column(name = "numero")
	private Integer numero;

	@Column(name = "cep", nullable = false, length = 10)
	private String cep;

	@Column(name = "bairro", length = 30, nullable = false)
	private String bairro;

	@Column(name = "complemento", nullable = true, length = 50)
	private String complemento;

	@Column(name = "ponto_referencia", nullable = true, length = 50)
	private String pontoReferencia;

	@Column(name = "cidade", length = 30, nullable = false)
	private String cidade;

	@Column(name = "uf", length = 30, nullable = false)
	private String uf;

	@Transient
	private String resultadoCEP;

	@Transient
	private String numeroString;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Required
	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	@Required
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Required
	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getPontoReferencia() {
		return pontoReferencia;
	}

	public void setPontoReferencia(String pontoReferencia) {
		this.pontoReferencia = pontoReferencia;
	}

	@Required
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@Required
	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	@Required
	public TipoLogradouro getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(TipoLogradouro tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	public String getResultadoCEP() {
		return resultadoCEP;
	}

	public void setResultadoCEP(String resultadoCEP) {
		this.resultadoCEP = resultadoCEP;
	}

	public void setNumeroString(String numeroString) {
		this.numeroString = numeroString;
		if (numeroString != null && !numeroString.trim().isEmpty()) {
			this.numero = Integer.parseInt(numeroString);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + ((complemento == null) ? 0 : complemento.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((logradouro == null) ? 0 : logradouro.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((pontoReferencia == null) ? 0 : pontoReferencia.hashCode());
		result = prime * result + ((tipoLogradouro == null) ? 0 : tipoLogradouro.hashCode());
		result = prime * result + ((uf == null) ? 0 : uf.hashCode());
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
		Endereco other = (Endereco) obj;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
			return false;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (complemento == null) {
			if (other.complemento != null)
				return false;
		} else if (!complemento.equals(other.complemento))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (logradouro == null) {
			if (other.logradouro != null)
				return false;
		} else if (!logradouro.equals(other.logradouro))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (pontoReferencia == null) {
			if (other.pontoReferencia != null)
				return false;
		} else if (!pontoReferencia.equals(other.pontoReferencia))
			return false;
		if (tipoLogradouro != other.tipoLogradouro)
			return false;
		if (uf == null) {
			if (other.uf != null)
				return false;
		} else if (!uf.equals(other.uf))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Endereco [id=" + id + ", tipoLograouro=" + tipoLogradouro + ", logradouro=" + logradouro + ", numero=" + numero + ", cep=" + cep + ", bairro="
				+ bairro + ", complemento=" + complemento + ", pontoReferencia=" + pontoReferencia + ", cidade=" + cidade + ", uf=" + uf + "]";
	}

	public String getNumeroString() {
		return numeroString;
	}

}
