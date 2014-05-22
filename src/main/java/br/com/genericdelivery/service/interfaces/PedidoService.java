package br.com.genericdelivery.service.interfaces;

import java.util.List;

import br.com.genericdelivery.model.entity.Cardapio;
import br.com.genericdelivery.model.entity.Categoria;
import br.com.genericdelivery.model.entity.Pedido;
import br.com.genericdelivery.model.entity.ProdutoCardapio;
import br.com.genericdelivery.service.exceptions.CamposObrigatoriosNaoPrenchidos;

public interface PedidoService {

	void save(Pedido pedido) throws CamposObrigatoriosNaoPrenchidos;

	void alterar(Pedido pedido) throws CamposObrigatoriosNaoPrenchidos;

	List<Categoria> listarCategorias(Cardapio cardapio);


	List<ProdutoCardapio> listarProdutosCardapio(Categoria categoria,
			List<ProdutoCardapio> produtos);

}
