package br.com.genericdelivery.model.dao.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import br.com.genericdelivery.model.dao.generic.JPAGenericDAO;
import br.com.genericdelivery.model.dao.interfaces.UsuarioDAO;
import br.com.genericdelivery.model.entity.Funcionalidade;
import br.com.genericdelivery.model.entity.Permissao;
import br.com.genericdelivery.model.entity.Usuario;
import br.com.genericdelivery.vo.UsuarioFiltroVO;

@Repository
public class UsuarioDAOImpl extends JPAGenericDAO<Usuario> implements UsuarioDAO {
	@Override
	public Usuario findByLoginAndPassword(String login, String password) {
		String jpql = "FROM Usuario WHERE login LIKE ? AND senha LIKE ? AND ativo = true";
		try {
			return (Usuario) findSingleResultByJPQL(jpql, login, password);
		} catch (NoResultException e) {
			return null;
		} catch (NonUniqueResultException e) {
			return null;
		}
	}

	@Override
	public Permissao findPermissao(Integer id, Funcionalidade funcionalidade) {
		StringBuilder sb = new StringBuilder("SELECT permissao FROM PerfilPermissao p");
		sb.append(" WHERE p.perfil.id = ");
		sb.append(" (SELECT u.perfil.id WHERE Usuario u");
		sb.append(" u.perfil = ?)");
		sb.append(" AND p.funcionalidade = ?");

		try {
			return (Permissao) findSingleResultByJPQL(sb.toString(), id, funcionalidade);
		} catch (NonUniqueResultException e) {
			return null;
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> listar(UsuarioFiltroVO filtroVO) {
		List<Object> params = new ArrayList<Object>();

		StringBuilder sb = new StringBuilder("SELECT u FROM Usuario u");
		sb.append(" WHERE u.ativo = true");

		if (StringUtils.isNotBlank(filtroVO.getNome())) {
			sb.append(" AND u.nome LIKE ?");
			params.add(filtroVO.getNome() + "%");
		}
		if (StringUtils.isNotBlank(filtroVO.getLogin())) {
			sb.append(" AND u.login LIKE ?");
			params.add(filtroVO.getLogin() + "%");
		}

		return (List<Usuario>) findByJPQL(sb.toString(), params.toArray());
	}

}
