package br.com.genericdelivery.model.dao.interfaces;

import java.util.List;

import br.com.genericdelivery.model.entity.Categoria;
import br.com.genericdelivery.model.entity.Produto;

public interface ProdutoDAO {
	void save(Produto produto);

	void update(Produto produto);

	List<Categoria> listarCategorias();

	List<Produto> listarProdutos();

	Produto findProdutoById(Integer id);

	Produto findByDescricao(Produto produto);
}
