package br.com.genericdelivery.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.genericdelivery.model.dao.interfaces.PedidoDAO;
import br.com.genericdelivery.model.entity.Pedido;
import br.com.genericdelivery.service.exceptions.CamposObrigatoriosNaoPrenchidos;
import br.com.genericdelivery.service.interfaces.PedidoService;
import br.com.genericdelivery.util.RequiredFieldsValidator;

@Service
public class PedidoServiceImpl implements PedidoService {

	@Autowired
	private PedidoDAO pedidoDAO;
	
	@Override
	public void save(Pedido pedido) throws CamposObrigatoriosNaoPrenchidos {
		validarCampos(pedido);
		pedidoDAO.save(pedido);
	}

	@Override
	public void alterar(Pedido pedido) throws CamposObrigatoriosNaoPrenchidos {
		validarCampos(pedido);
		pedidoDAO.update(pedido);
	}
	
	private void validarCampos(Pedido pedido) throws CamposObrigatoriosNaoPrenchidos {
		boolean validate = RequiredFieldsValidator.validate(pedido);
		if (!validate) {
			throw new CamposObrigatoriosNaoPrenchidos();
		}
	}

}
