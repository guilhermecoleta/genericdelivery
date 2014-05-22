package br.com.genericdelivery.service.implementations;

import java.util.Iterator;
import java.util.List;

import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.genericdelivery.model.dao.interfaces.UsuarioDAO;
import br.com.genericdelivery.model.entity.Endereco;
import br.com.genericdelivery.model.entity.Funcionalidade;
import br.com.genericdelivery.model.entity.Permissao;
import br.com.genericdelivery.model.entity.TipoLogradouro;
import br.com.genericdelivery.model.entity.Usuario;
import br.com.genericdelivery.service.exceptions.CEPInvalido;
import br.com.genericdelivery.service.exceptions.CPFJaCadastrado;
import br.com.genericdelivery.service.exceptions.CamposObrigatoriosNaoPrenchidos;
import br.com.genericdelivery.service.exceptions.EmailJaCadastrado;
import br.com.genericdelivery.service.exceptions.FacebookJaCadastrado;
import br.com.genericdelivery.service.interfaces.UsuarioService;
import br.com.genericdelivery.util.RequiredFieldsValidator;
import br.com.genericdelivery.view.web.faces.util.EnderecoByCEPUtil;
import br.com.genericdelivery.vo.UsuarioFiltroVO;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private static final String LOGRADOURO = "logradouro";
	private static final String TIPO_LOGRADOURO = "tipo_logradouro";
	private static final String RESULTADO = "resultado";
	private static final String BAIRRO = "bairro";
	private static final String CIDADE = "cidade";
	private static final String UF = "uf";

	@Autowired
	private UsuarioDAO dao;

	@Override
	public Usuario findByLoginAndPassword(String login, String password) throws CamposObrigatoriosNaoPrenchidos {
		if ((login == null || login.isEmpty()) || (password == null || password.isEmpty()))
			throw new CamposObrigatoriosNaoPrenchidos();

		return this.dao.findByLoginAndPassword(login, password);
	}

	@Override
	public Permissao findPermissao(Integer id, Funcionalidade funcionalidade) {
		return dao.findPermissao(id, funcionalidade);
	}

	@Override
	public Endereco findEndereco(Endereco endereco) throws CEPInvalido, CamposObrigatoriosNaoPrenchidos {
		String cep = endereco.getCep();

		if (!cep.trim().isEmpty()) {
			Element root = EnderecoByCEPUtil.encontraCEP(cep);
			Iterator<Element> elementIterator = root.elementIterator();

			for (Iterator<Element> i = elementIterator; i.hasNext();) {
				Element element = (Element) i.next();
				String name = element.getQualifiedName();
				String text = element.getText();

				if (RESULTADO.equals(name)) {
					if (text.equals("0")) {
						throw new CEPInvalido();
					} else if (text.equals("2")) {
						endereco.setLogradouro(null);
						endereco.setBairro(null);
					}
					endereco.setResultadoCEP(text);
				}

				if (LOGRADOURO.equals(name)) {
					if (!text.trim().isEmpty())
						endereco.setLogradouro(text);
				}
				if (TIPO_LOGRADOURO.equals(name)) {
					if (!text.trim().isEmpty())
						endereco.setTipoLogradouro(TipoLogradouro.getTipoLogradouro(text));
				}
				if (BAIRRO.equals(name)) {
					if (!text.trim().isEmpty())
						endereco.setBairro(text);
				}
				if (CIDADE.equals(name)) {
					if (!text.trim().isEmpty())
						endereco.setCidade(text);
				}
				if (UF.equals(name)) {
					if (!text.trim().isEmpty())
						endereco.setUf(text);
				}
			}
		} else {
			throw new CamposObrigatoriosNaoPrenchidos();
		}

		return endereco;

	}

	@Override
	public void alterar(Usuario usuario) throws CPFJaCadastrado, EmailJaCadastrado, FacebookJaCadastrado, CamposObrigatoriosNaoPrenchidos {
		validarCampos(usuario);
		dao.update(usuario);
	}

	@Override
	public void salvar(Usuario usuario) throws CamposObrigatoriosNaoPrenchidos, CPFJaCadastrado, EmailJaCadastrado, FacebookJaCadastrado {
		validarCampos(usuario);
		dao.save(usuario);
	}

	private void validarCampos(Usuario usuario) throws CPFJaCadastrado, EmailJaCadastrado, FacebookJaCadastrado, CamposObrigatoriosNaoPrenchidos {
		boolean validate = RequiredFieldsValidator.validate(usuario);
		validate &= RequiredFieldsValidator.validate(usuario.getEndereco());
		if (validate) {
			validarCamposUnicos(usuario);
		} else {
			throw new CamposObrigatoriosNaoPrenchidos();
		}
	}

	private void validarCamposUnicos(Usuario usuario) throws CPFJaCadastrado, EmailJaCadastrado, FacebookJaCadastrado {
		boolean existeCPF = dao.verificarCPFExistente(usuario.getCpf(), usuario.getId());
		boolean existeEmail = dao.verificarEmailExistente(usuario.getEmail(), usuario.getId());
		boolean existeFacebook = dao.verificarFacebookExistente(usuario.getFacebook(), usuario.getId());
		if (existeCPF) {
			throw new CPFJaCadastrado();
		} else if (existeEmail) {
			throw new EmailJaCadastrado();
		} else if (existeFacebook) {
			throw new FacebookJaCadastrado();
		}
	}

	@Override
	public List<Usuario> list(UsuarioFiltroVO filtroVO) {
		return dao.listar(filtroVO);
	}

	@Override
	public void remove(Usuario usuario) {
		usuario.setAtivo(false);
		dao.update(usuario);
	}
}
