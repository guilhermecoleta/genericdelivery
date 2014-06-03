package br.com.genericdelivery.service.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.genericdelivery.model.dao.interfaces.PedidoDAO;
import br.com.genericdelivery.model.entity.Cardapio;
import br.com.genericdelivery.model.entity.Categoria;
import br.com.genericdelivery.model.entity.Pedido;
import br.com.genericdelivery.model.entity.ProdutoCardapio;
import br.com.genericdelivery.service.exceptions.CamposObrigatoriosNaoPrenchidos;
import br.com.genericdelivery.service.interfaces.PedidoService;
import br.com.genericdelivery.util.RequiredFieldsValidator;

@Service
public class PedidoServiceImpl implements PedidoService {

	@Autowired
	private PedidoDAO pedidoDAO;

	@Override
	public void save(Pedido pedido) throws CamposObrigatoriosNaoPrenchidos, IllegalAccessException {
		validarCampos(pedido);
		pedidoDAO.save(pedido);
	}

	@Override
	public void alterar(Pedido pedido) throws CamposObrigatoriosNaoPrenchidos, IllegalAccessException {
		validarCampos(pedido);
		pedidoDAO.update(pedido);
	}

	private void validarCampos(Pedido pedido) throws CamposObrigatoriosNaoPrenchidos, IllegalAccessException {
		RequiredFieldsValidator.validate(pedido);
		RequiredFieldsValidator.validate(pedido.getUsuario());
	}

	@Override
	public List<Categoria> listarCategorias(Cardapio cardapio){
		List<ProdutoCardapio> produtos = cardapio.getProdutos();

		List<Categoria> categorias = new ArrayList<Categoria>();

		for (ProdutoCardapio prod : produtos) {
			if(categorias != null && !categorias.isEmpty()){
				if(!categorias.contains(prod.getProduto().getCategoria())){
					categorias.add(prod.getProduto().getCategoria());
				}
			}else{
				categorias.add(prod.getProduto().getCategoria());
			}
		}
		return categorias;
	}

	@Override
	public List<ProdutoCardapio> listarProdutosCardapio(Categoria categoria, List<ProdutoCardapio> produtos){
		List<ProdutoCardapio> list = new ArrayList<ProdutoCardapio>();
		list.addAll(produtos);

		for (ProdutoCardapio produtoCardapio : produtos) {
			if(!produtoCardapio.getProduto().getCategoria().equals(categoria)){
				list.remove(produtoCardapio);
			}
		}
		return list;
	}

}
