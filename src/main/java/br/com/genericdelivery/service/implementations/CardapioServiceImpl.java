package br.com.genericdelivery.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.genericdelivery.model.dao.interfaces.CardapioDAO;
import br.com.genericdelivery.model.entity.Cardapio;
import br.com.genericdelivery.service.exceptions.CamposObrigatoriosNaoPrenchidos;
import br.com.genericdelivery.service.interfaces.CardapioService;
import br.com.genericdelivery.util.RequiredFieldsValidator;

@Service
public class CardapioServiceImpl implements CardapioService {

	@Autowired
	private CardapioDAO cardapioDAO;

	@Override
	public void salvar(Cardapio cardapio) throws CamposObrigatoriosNaoPrenchidos {
		validate(cardapio);
		desativarCardapios(cardapio);
		cardapioDAO.save(cardapio);
	}
	
	@Override
	public void alterar(Cardapio cardapio){
		cardapioDAO.update(cardapio);
	}

	private void validate(Cardapio cardapio) throws CamposObrigatoriosNaoPrenchidos {
		boolean validate = RequiredFieldsValidator.validate(cardapio);
		if (!validate) {
			throw new CamposObrigatoriosNaoPrenchidos();
		}
	}
	
	@Override
	public Cardapio getCardapioAtivo(){
		return cardapioDAO.getCardapioAtivo();
	}
	
	private void desativarCardapios(Cardapio cardapio){
		List<Cardapio> cardapios = cardapioDAO.getCardapiosAtivos(cardapio);
		for (Cardapio c : cardapios) {
			c.setAtivo(false);
			cardapioDAO.update(c);
		}
	}

}
