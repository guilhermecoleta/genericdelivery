package br.com.genericdelivery.model.dao.interfaces;

import br.com.genericdelivery.model.entity.Pedido;

public interface PedidoDAO {

	void save(Pedido pedido);
	
	void update(Pedido pedido);
}
