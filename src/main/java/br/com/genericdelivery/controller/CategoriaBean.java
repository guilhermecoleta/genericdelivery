package br.com.genericdelivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.genericdelivery.model.entity.Categoria;
import br.com.genericdelivery.model.entity.Tela;
import br.com.genericdelivery.service.exceptions.CamposObrigatoriosNaoPrenchidos;
import br.com.genericdelivery.service.interfaces.CategoriaService;
import br.com.genericdelivery.view.Messages;
import br.com.genericdelivery.view.web.faces.util.FacesUtil;

@Controller
public class CategoriaBean {

	@Autowired
	private CategoriaService categoriaService;
	
	private Categoria categoria;
	
	public String redirecionarCadastro(){
		limparCampos();
		return FacesUtil.facesRedirectUrl(Tela.CATEGORIA_CADASTRO);
	}
	
	public void salvar(){
		try {
			categoriaService.salvar(categoria);
			FacesUtil.showMessageError(Messages.M5);
		} catch (CamposObrigatoriosNaoPrenchidos e) {
			FacesUtil.showMessageError(Messages.M2);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	private void limparCampos(){
		categoria = new Categoria();
		categoria.setAtivo(true);
	}
	
	public Categoria getCategoria() {
		if (categoria == null) {
			categoria = new Categoria();
			
		}

		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
}
