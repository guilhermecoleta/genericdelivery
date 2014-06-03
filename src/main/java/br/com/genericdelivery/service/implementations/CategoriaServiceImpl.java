package br.com.genericdelivery.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.genericdelivery.model.dao.interfaces.CategoriaDAO;
import br.com.genericdelivery.model.entity.Categoria;
import br.com.genericdelivery.service.exceptions.CamposObrigatoriosNaoPrenchidos;
import br.com.genericdelivery.service.interfaces.CategoriaService;
import br.com.genericdelivery.util.RequiredFieldsValidator;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaDAO categoriaDAO;
	
	@Override
	public void salvar(Categoria categoria) throws CamposObrigatoriosNaoPrenchidos, IllegalAccessException{
		RequiredFieldsValidator.validate(categoria);
		categoriaDAO.save(categoria);
	}
	
	
}
