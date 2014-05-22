package br.com.genericdelivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.genericdelivery.model.entity.Tela;
import br.com.genericdelivery.model.entity.Usuario;
import br.com.genericdelivery.service.exceptions.CamposObrigatoriosNaoPrenchidos;
import br.com.genericdelivery.service.interfaces.UsuarioService;
import br.com.genericdelivery.view.Messages;
import br.com.genericdelivery.view.web.faces.util.FacesUtil;

@Controller("loginBean")
public class LoginBean {

	private String login;
	private String senha;

	@Autowired
	private UsuarioService usuarioService;

	public String login(){
		try{

			Usuario usuario = usuarioService.findByLoginAndPassword(this.login, this.senha);

			if(usuario == null){
				FacesUtil.showMessageError(Messages.M13);
			}else{
				FacesUtil.getSessionMap().put("usuario", usuario);
				return FacesUtil.facesRedirectUrl(Tela.MAIN);
			}
		}catch(CamposObrigatoriosNaoPrenchidos e){
			FacesUtil.showMessageError(Messages.M2);
		}
		return "";
	}

	public String logout() {
		FacesUtil.getSessionMap().put("usuario", null);
		return FacesUtil.facesRedirectUrl(Tela.LOGIN);
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
