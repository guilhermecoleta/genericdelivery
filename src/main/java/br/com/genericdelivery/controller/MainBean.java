package br.com.genericdelivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.genericdelivery.model.entity.Funcionalidade;
import br.com.genericdelivery.model.entity.Permissao;
import br.com.genericdelivery.model.entity.Tela;
import br.com.genericdelivery.model.entity.Usuario;
import br.com.genericdelivery.service.interfaces.UsuarioService;
import br.com.genericdelivery.view.web.faces.util.FacesUtil;

@Controller("mainBean")
public class MainBean {

	private static final String PREFIXO_URL = "/genericdelivery" ;
	@Autowired
	private UsuarioService usuarioService;

	public Usuario getUsuarioLogado() {
		return (Usuario) FacesUtil.getSessionMap().get("usuario");
	}

	private Tela getTela(){
		String url = FacesUtil.getRequestUrl();
		return Tela.getTela(url.substring(PREFIXO_URL.length(), url.indexOf(".")));
	}

	public boolean isUsuarioLogado(Usuario usuario){
		return this.getUsuarioLogado().equals(usuario);
	}

	public boolean possuiPermissaoLeitura(){
		Tela tela = getTela();

		if(Tela.MAIN == tela){
			return false;
		}else{
			Permissao permissao = recuperarPermissao(tela);
			return permissao != null && Permissao.LEITURA.equals(permissao);
		}

	}
	
	public boolean possuiPermissaoEscrita(){
		Tela tela = getTela();

		if(Tela.MAIN == tela){
			return false;
		}else{
			Permissao permissao = recuperarPermissao(tela);
			return permissao != null && Permissao.ESCRITA.equals(permissao);
		}

	}

	private Permissao recuperarPermissao(Tela tela) {
		Funcionalidade funcionalidade = Funcionalidade.getFuncionalidade(tela);
		Permissao permissao = this.usuarioService.findPermissao(getUsuarioLogado().getId(), funcionalidade);
		return permissao;
	}
}
