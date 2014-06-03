package br.com.genericdelivery.service.interfaces;

import java.util.List;

import br.com.genericdelivery.model.entity.Endereco;
import br.com.genericdelivery.model.entity.Funcionalidade;
import br.com.genericdelivery.model.entity.Permissao;
import br.com.genericdelivery.model.entity.Usuario;
import br.com.genericdelivery.service.exceptions.CEPInvalido;
import br.com.genericdelivery.service.exceptions.CamposObrigatoriosNaoPrenchidos;
import br.com.genericdelivery.vo.UsuarioFiltroVO;

public interface UsuarioService {

	Usuario findByLoginAndPassword(String login, String password) throws CamposObrigatoriosNaoPrenchidos;

	Permissao findPermissao(Integer id, Funcionalidade funcionalidade);

	Endereco findEndereco(Endereco endereco) throws CEPInvalido, CamposObrigatoriosNaoPrenchidos;

	void salvar(Usuario usuario) throws CamposObrigatoriosNaoPrenchidos, IllegalAccessException ;

	void alterar(Usuario usuario) throws CamposObrigatoriosNaoPrenchidos, IllegalAccessException ;

	List<Usuario> list(UsuarioFiltroVO filtroVO);

	void remove(Usuario usuario);

}
