package br.com.genericdelivery.controller;

import java.util.List;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.genericdelivery.model.entity.Cardapio;
import br.com.genericdelivery.model.entity.Categoria;
import br.com.genericdelivery.model.entity.Pedido;
import br.com.genericdelivery.model.entity.Produto;
import br.com.genericdelivery.model.entity.ProdutoCardapio;
import br.com.genericdelivery.model.entity.Tela;
import br.com.genericdelivery.service.exceptions.CamposObrigatoriosNaoPrenchidos;
import br.com.genericdelivery.service.interfaces.CardapioService;
import br.com.genericdelivery.service.interfaces.PedidoService;
import br.com.genericdelivery.service.interfaces.ProdutoService;
import br.com.genericdelivery.view.Messages;
import br.com.genericdelivery.view.web.faces.util.FacesUtil;

@Controller
public class PedidoBean {

	private Pedido pedido;

	private Produto produto;

	private Double valor;

	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private CardapioService cardapioService;

	private Cardapio cardapio;

	private List<Produto> produtos;

	private TreeNode produtosNode;


	public void salvar(){
		try{
			if(pedido.getCodigo() != null){
				pedidoService.alterar(pedido);
				FacesUtil.showMessageInfo(Messages.M6);
			}else{
				pedidoService.save(pedido);
				FacesUtil.showMessageInfo(Messages.M5);
			}
		} catch (CamposObrigatoriosNaoPrenchidos e) {
			FacesUtil.showMessageError(Messages.M2);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

	}


	private void limparCampos() {
		pedido = new Pedido();
		pedido.setAtivo(true);
		cardapio = cardapioService.getCardapioAtivo();
	}

	public String redirecionarCadastro() {
		limparCampos();
		return FacesUtil.facesRedirectUrl(Tela.PEDIDO_CADASTRO);
	}


	public Pedido getPedido() {
		if (pedido == null) {
			pedido = new Pedido();

		}

		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
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


	public Cardapio getCardapio() {
		return cardapio;
	}


	public void setCardapio(Cardapio cardapio) {
		this.cardapio = cardapio;
	}


	public TreeNode getProdutosNode() {
		if (produtosNode == null) {
			Cardapio cardapio = cardapioService.getCardapioAtivo();
			List<Categoria> categorias = pedidoService.listarCategorias(cardapio);
			
			produtosNode = new DefaultTreeNode(null, null);
			for (Categoria categoria : categorias) {
				TreeNode pai = new DefaultTreeNode(categoria, produtosNode);
				
				List<ProdutoCardapio> produtos = pedidoService.listarProdutosCardapio(categoria, cardapio.getProdutos());
				
				for (ProdutoCardapio produtoCardapio : produtos) {
					TreeNode filho = new DefaultTreeNode(produtoCardapio, pai);
				}
			}

		}

		return produtosNode;
	}
	

	public void setProdutosNode(TreeNode produtosNode) {
		this.produtosNode = produtosNode;
	}
}
