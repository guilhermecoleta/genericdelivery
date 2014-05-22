package br.com.genericdelivery.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "perfil_permissao")
public class PerfilPermissao implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2032241329136530472L;

	@Id
	@Column(name = "funcionalidade", nullable = false, length = 30)
	@Enumerated(EnumType.STRING)
	private Funcionalidade funcionalidade;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "perfil", nullable = false)
	private Perfil perfil;
	
	@Column(name = "permissao", nullable = false, length = 30)
	@Enumerated(EnumType.STRING)
	private Permissao permissao;

	public Funcionalidade getFuncionalidade() {
		return funcionalidade;
	}

	public void setFuncionalidade(Funcionalidade funcionalidade) {
		this.funcionalidade = funcionalidade;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Permissao getPermissao() {
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((funcionalidade == null) ? 0 : funcionalidade.hashCode());
		result = prime * result + ((perfil == null) ? 0 : perfil.hashCode());
		result = prime * result + ((permissao == null) ? 0 : permissao.hashCode());
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
		PerfilPermissao other = (PerfilPermissao) obj;
		if (funcionalidade != other.funcionalidade)
			return false;
		if (perfil == null) {
			if (other.perfil != null)
				return false;
		} else if (!perfil.equals(other.perfil))
			return false;
		if (permissao != other.permissao)
			return false;
		return true;
	}
	
	
}
