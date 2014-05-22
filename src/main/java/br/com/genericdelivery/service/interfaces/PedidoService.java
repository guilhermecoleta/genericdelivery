package br.com.genericdelivery.service.interfaces;

import br.com.genericdelivery.model.entity.Pedido;
import br.com.genericdelivery.service.exceptions.CamposObrigatoriosNaoPrenchidos;

public interface PedidoService {

	void save(Pedido pedido) throws CamposObrigatoriosNaoPrenchidos;

	void alterar(Pedido pedido) throws CamposObrigatoriosNaoPrenchidos;

}
