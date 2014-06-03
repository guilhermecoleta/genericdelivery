package br.com.genericdelivery.service.interfaces;

import java.util.List;

import br.com.genericdelivery.model.entity.Categoria;
import br.com.genericdelivery.model.entity.Produto;
import br.com.genericdelivery.service.exceptions.CamposObrigatoriosNaoPrenchidos;
import br.com.genericdelivery.service.exceptions.ProdutoJaCadastrado;

public interface ProdutoService {

	void salvar(Produto produto) throws CamposObrigatoriosNaoPrenchidos, IllegalAccessException, ProdutoJaCadastrado;

	void alterar(Produto produto) throws CamposObrigatoriosNaoPrenchidos, IllegalAccessException, ProdutoJaCadastrado;

	List<Categoria> listarCategorias();

	List<Produto> listarProdutos();

	Produto findById(Integer id);
}
