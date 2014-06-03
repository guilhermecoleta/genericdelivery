package br.com.genericdelivery.service.interfaces;

import br.com.genericdelivery.model.entity.Cardapio;
import br.com.genericdelivery.service.exceptions.CamposObrigatoriosNaoPrenchidos;

public interface CardapioService {

	void salvar(Cardapio cardapio) throws CamposObrigatoriosNaoPrenchidos, IllegalAccessException;

	Cardapio getCardapioAtivo();

	void alterar(Cardapio cardapio);

}
