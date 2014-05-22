package br.com.genericdelivery.model.dao.interfaces;

import java.util.List;

import br.com.genericdelivery.model.entity.Cardapio;

public interface CardapioDAO {

	public void save(Cardapio cardapio);
	
	void update(Cardapio cardapio);

	Cardapio getCardapioAtivo();

	List<Cardapio> getCardapiosAtivos(Cardapio cardapio);
}
