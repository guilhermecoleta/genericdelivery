package br.com.genericdelivery.model.dao.interfaces;

import java.util.List;

import br.com.genericdelivery.model.entity.Funcionalidade;
import br.com.genericdelivery.model.entity.Permissao;
import br.com.genericdelivery.model.entity.Usuario;
import br.com.genericdelivery.vo.UsuarioFiltroVO;

public interface UsuarioDAO {

	Usuario findByLoginAndPassword(String login, String password);

	Permissao findPermissao(Integer id, Funcionalidade funcionalidade);

	void save(Usuario usuario);

	void update(Usuario usuario);

	List<Usuario> listar(UsuarioFiltroVO filtroVO);

	void remove(Integer usuario);

}
