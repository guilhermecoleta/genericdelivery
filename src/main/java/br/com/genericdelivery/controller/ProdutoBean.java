package br.com.genericdelivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.genericdelivery.model.entity.Categoria;
import br.com.genericdelivery.model.entity.Produto;
import br.com.genericdelivery.model.entity.Tela;
import br.com.genericdelivery.service.exceptions.CamposObrigatoriosNaoPrenchidos;
import br.com.genericdelivery.service.interfaces.ProdutoService;
import br.com.genericdelivery.view.Messages;
import br.com.genericdelivery.view.web.faces.util.FacesUtil;

@Controller("produtoBean")
public class ProdutoBean {

	@Autowired
	private ProdutoService produtoService;

	private Produto produto;

	private List<Categoria> categorias;

	public void salvar() {
		try {
			if (produto.getCodigo() != null) {
				produtoService.alterar(produto);
				FacesUtil.showMessageInfo(Messages.M6);
			} else {
				produtoService.salvar(produto);
				FacesUtil.showMessageInfo(Messages.M5);
			}
		} catch (CamposObrigatoriosNaoPrenchidos e) {
			FacesUtil.showMessageError(Messages.M2);
		}
	}

	public String redirecionarCadastro() {
		limparCampos();
		return FacesUtil.facesRedirectUrl(Tela.PRODUTO_CADASTRO);
	}

	private void limparCampos() {
		produto = new Produto();
		produto.setAtivo(true);
	}

	/*
	 * public String voltar() { return FacesUtil.facesRedirectUrl(Tela.P); }
	 */

	public Produto getProduto() {
		if (produto == null) {
			produto = new Produto();
		}

		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Categoria> getCategorias() {
		if (categorias == null || categorias.isEmpty()) {
			categorias = produtoService.listarCategorias();
		}

		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

}
