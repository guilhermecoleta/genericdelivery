package br.com.genericdelivery.service.interfaces;

import java.util.List;

import br.com.genericdelivery.model.entity.Categoria;
import br.com.genericdelivery.model.entity.Produto;
import br.com.genericdelivery.service.exceptions.CamposObrigatoriosNaoPrenchidos;

public interface ProdutoService {

	void salvar(Produto produto) throws CamposObrigatoriosNaoPrenchidos;

	void alterar(Produto produto) throws CamposObrigatoriosNaoPrenchidos;

	List<Categoria> listarCategorias();

	List<Produto> listarProdutos();

	Produto findById(Integer id);
}
