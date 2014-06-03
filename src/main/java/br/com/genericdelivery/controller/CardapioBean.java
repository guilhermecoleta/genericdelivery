package br.com.genericdelivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.genericdelivery.model.entity.Cardapio;
import br.com.genericdelivery.model.entity.Produto;
import br.com.genericdelivery.model.entity.ProdutoCardapio;
import br.com.genericdelivery.model.entity.Tela;
import br.com.genericdelivery.model.entity.Usuario;
import br.com.genericdelivery.service.exceptions.CamposObrigatoriosNaoPrenchidos;
import br.com.genericdelivery.service.interfaces.CardapioService;
import br.com.genericdelivery.service.interfaces.ProdutoService;
import br.com.genericdelivery.view.Messages;
import br.com.genericdelivery.view.web.faces.util.FacesUtil;

@Controller("cardapioBean")
public class CardapioBean {

	private Cardapio cardapio;

	private List<Produto> produtos;

	private Produto produto;

	private Double valor;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private CardapioService cardapioService;

	public String redirecionarCadastro() {
		limpar();
		return FacesUtil.facesRedirectUrl(Tela.CARDAPIO_CADASTRO);
	}

	public void salvar() {
		try {
			cardapioService.salvar(cardapio);
			FacesUtil.showMessageInfo(Messages.M5);
		} catch (CamposObrigatoriosNaoPrenchidos e) {
			FacesUtil.showMessageError(Messages.M2);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public void excluir(Produto produto) {
		getCardapio().getProdutos().remove(produto);
	}

	public void addProduto() {
		if (valor != null) {
			ProdutoCardapio produtoCardapio = new ProdutoCardapio();
			produto = produtoService.findById(produto.getCodigo());
			produtoCardapio.setProduto(produto);
			produtoCardapio.setCardapio(cardapio);
			produtoCardapio.setValor(valor);
			cardapio.getProdutos().add(produtoCardapio);
		}
	}

	private void limpar() {
		cardapio = new Cardapio();
		cardapio.setAtivo(true);
		Usuario usuario = (Usuario) FacesUtil.getSessionMap().get("usuario");
		cardapio.setUsuario(usuario);
	}
	
	public void ativarCardapio(Cardapio cardapio){
		cardapio.setAtivo(true);
		cardapioService.alterar(cardapio);
	}
	
	public void desativarCardapio(Cardapio cardapio){
		cardapio.setAtivo(false);
		cardapioService.alterar(cardapio);
	}

	public Cardapio getCardapio() {
		if (cardapio == null) {
			cardapio = new Cardapio();
			cardapio.setAtivo(true);
		}

		return cardapio;
	}

	public void setCardapio(Cardapio cardapio) {
		this.cardapio = cardapio;
	}

	public List<Produto> getProdutos() {
		produtos = produtoService.listarProdutos();

		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Produto getProduto() {
		if (produto == null) {
			produto = new Produto();

		}

		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}
