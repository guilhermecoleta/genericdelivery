package br.com.genericdelivery.model.dao.implementation;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.genericdelivery.model.dao.generic.JPAGenericDAO;
import br.com.genericdelivery.model.dao.interfaces.CardapioDAO;
import br.com.genericdelivery.model.entity.Cardapio;

@Repository
public class CardapioDAOImpl extends JPAGenericDAO<Cardapio> implements CardapioDAO {
	
	@Override
	public Cardapio getCardapioAtivo(){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT c FROM Cardapio c WHERE c.ativo");
		return (Cardapio) findSingleResultByJPQL(sb.toString());
	}
	
	@Override
	public List<Cardapio> getCardapiosAtivos(Cardapio cardapio){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT c FROM Cardapio c WHERE c.ativo");
		if(cardapio.getId() != null){
			sb.append(" AND  c.id != ");
			sb.append(cardapio.getId());
		}
		return (List<Cardapio>) findByJPQL(sb.toString());
	}
	
}
