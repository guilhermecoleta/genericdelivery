package br.com.genericdelivery.service.interfaces;

import br.com.genericdelivery.model.entity.Categoria;
import br.com.genericdelivery.service.exceptions.CamposObrigatoriosNaoPrenchidos;

public interface CategoriaService {

	void salvar(Categoria categoria) throws CamposObrigatoriosNaoPrenchidos, IllegalAccessException;

}
