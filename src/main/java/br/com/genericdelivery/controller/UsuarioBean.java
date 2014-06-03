package br.com.genericdelivery.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.genericdelivery.model.entity.Endereco;
import br.com.genericdelivery.model.entity.Perfil;
import br.com.genericdelivery.model.entity.Tela;
import br.com.genericdelivery.model.entity.Usuario;
import br.com.genericdelivery.service.exceptions.CEPInvalido;
import br.com.genericdelivery.service.exceptions.CamposObrigatoriosNaoPrenchidos;
import br.com.genericdelivery.service.interfaces.UsuarioService;
import br.com.genericdelivery.view.Messages;
import br.com.genericdelivery.view.web.faces.util.FacesUtil;
import br.com.genericdelivery.vo.UsuarioFiltroVO;

@Controller("usuarioBean")
public class UsuarioBean {

	private UsuarioFiltroVO usuarioFiltroVO;

	@Autowired
	private UsuarioService usuarioService;

	private Usuario usuario;

	private String confirmacaoSenha;

	private Boolean disableEndereco;

	private List<Perfil> perfis;

	private List<Usuario> usuarios;

	public String editar(Usuario usuario) {
		this.usuario = usuario;
		return FacesUtil.facesRedirectUrl(Tela.USUARIO_CADASTRO);
	}

	public void excluir(Usuario usuario) {
		usuarioService.remove(usuario);
		buscar();
	}

	public void preencheEndereco() {
		try {
			usuario.setEndereco(usuarioService.findEndereco(usuario.getEndereco()));
			desabilitaEndereco(usuario.getEndereco());
		} catch (CamposObrigatoriosNaoPrenchidos e) {
			usuario.setEndereco(null);
			FacesUtil.showMessageError(Messages.M2);
		} catch (CEPInvalido e) {
			usuario.setEndereco(null);
			FacesUtil.showMessageError(Messages.M16);
		}
	}

	public void buscar() {
		usuarios = usuarioService.list(usuarioFiltroVO);
	}

	public void salvar() {

		try {
			if (usuario.getId() != null) {
				usuarioService.alterar(usuario);
				FacesUtil.showMessageInfo(Messages.M6);
			} else {
				if (usuario.getSenha().equals(this.confirmacaoSenha)) {
					usuarioService.salvar(usuario);
					FacesUtil.showMessageInfo(Messages.M5);
				} else {
					FacesUtil.showMessageError(Messages.M4);
				}
			}
		} catch (CamposObrigatoriosNaoPrenchidos e) {
			FacesUtil.showMessageError(Messages.M2);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public String voltar() {
		return FacesUtil.facesRedirectUrl(Tela.USUARIO_LISTAGEM);
	}

	private void limparCampos() {
		usuario = null;
		confirmacaoSenha = null;
		disableEndereco = null;
	}

	private void desabilitaEndereco(Endereco endereco) {
		if (endereco.getResultadoCEP().equals("2")) {
			this.disableEndereco = false;
		} else {
			this.disableEndereco = true;
		}
	}

	public String redirecionarListagem() {
		return FacesUtil.facesRedirectUrl(Tela.USUARIO_LISTAGEM);
	}

	public String redirecionarCadastro() {
		limparCampos();
		return FacesUtil.facesRedirectUrl(Tela.USUARIO_CADASTRO);
	}

	public Usuario getUsuario() {
		if (usuario == null) {
			usuario = new Usuario();
			usuario.setAtivo(Boolean.TRUE);
		}

		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}

	public Boolean getDisableEndereco() {

		if (disableEndereco == null) {
			disableEndereco = Boolean.TRUE;
		}

		return disableEndereco;
	}

	public void setDisableEndereco(Boolean disableEndereco) {
		this.disableEndereco = disableEndereco;
	}

	public Boolean getRenderEndereco() {
		String resultadoCEP = getUsuario().getEndereco().getResultadoCEP();
		return resultadoCEP != null && !resultadoCEP.equals("0");
	}

	public List<Perfil> getPerfis() {
		if (perfis == null) {
			perfis = new ArrayList<>();
			// perfis.add(new Perfil(1));// FIXME
		}

		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	public UsuarioFiltroVO getUsuarioFiltroVO() {
		if (this.usuarioFiltroVO == null) {
			usuarioFiltroVO = new UsuarioFiltroVO();
		}

		return usuarioFiltroVO;
	}

	public void setUsuarioFiltroVO(UsuarioFiltroVO usuarioFiltroVO) {
		this.usuarioFiltroVO = usuarioFiltroVO;
	}

	public List<Usuario> getUsuarios() {
		if (this.usuarios == null) {
			this.usuarios = new ArrayList<>();
		}

		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}
