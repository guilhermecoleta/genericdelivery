package br.com.genericdelivery.model.dao.implementation;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.genericdelivery.model.dao.generic.JPAGenericDAO;
import br.com.genericdelivery.model.dao.interfaces.ProdutoDAO;
import br.com.genericdelivery.model.entity.Categoria;
import br.com.genericdelivery.model.entity.Produto;

@Repository
public class ProdutoDAOImpl extends JPAGenericDAO<Produto> implements ProdutoDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Categoria> listarCategorias() {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT c FROM Categoria c where c.ativo = true ");
		return (List<Categoria>) findByJPQL(sb.toString());
	}

	@Override
	public List<Produto> listarProdutos() {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT p FROM Produto p WHERE p.ativo = true");
		return (List<Produto>) findByJPQL(sb.toString());
	}

	@Override
	public Produto findProdutoById(Integer id) {
		return findById(id);
	}
}
