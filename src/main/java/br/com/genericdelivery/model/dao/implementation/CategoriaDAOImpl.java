package br.com.genericdelivery.model.dao.implementation;

import org.springframework.stereotype.Repository;

import br.com.genericdelivery.model.dao.generic.JPAGenericDAO;
import br.com.genericdelivery.model.dao.interfaces.CategoriaDAO;
import br.com.genericdelivery.model.entity.Categoria;

@Repository
public class CategoriaDAOImpl extends JPAGenericDAO<Categoria> implements CategoriaDAO{

}
