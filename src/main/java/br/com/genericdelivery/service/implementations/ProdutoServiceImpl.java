package br.com.genericdelivery.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.genericdelivery.model.dao.interfaces.ProdutoDAO;
import br.com.genericdelivery.model.entity.Categoria;
import br.com.genericdelivery.model.entity.Produto;
import br.com.genericdelivery.service.exceptions.CamposObrigatoriosNaoPrenchidos;
import br.com.genericdelivery.service.exceptions.ProdutoJaCadastrado;
import br.com.genericdelivery.service.interfaces.ProdutoService;
import br.com.genericdelivery.util.RequiredFieldsValidator;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoDAO produtoDAO;

	@Override
	public void salvar(Produto produto) throws CamposObrigatoriosNaoPrenchidos, IllegalAccessException, ProdutoJaCadastrado {
		validarCampos(produto);
		produtoDAO.save(produto);
	}

	private void validarCampos(Produto produto) throws CamposObrigatoriosNaoPrenchidos, IllegalAccessException, ProdutoJaCadastrado {
		RequiredFieldsValidator.validate(produto);
		RequiredFieldsValidator.validate(produto.getCategoria());
		
		Produto prod = produtoDAO.findByDescricao(produto);
		if (prod != null){
			throw new ProdutoJaCadastrado();
		}
	}

	@Override
	public void alterar(Produto produto) throws CamposObrigatoriosNaoPrenchidos, IllegalAccessException, ProdutoJaCadastrado {
		validarCampos(produto);
		produtoDAO.update(produto);
	}

	@Override
	public List<Categoria> listarCategorias() {
		return produtoDAO.listarCategorias();
	}

	@Override
	public List<Produto> listarProdutos() {
		return produtoDAO.listarProdutos();
	}

	@Override
	public Produto findById(Integer id) {
		return produtoDAO.findProdutoById(id);
	}
}
