package br.com.genericdelivery.model.dao.implementation;

import org.springframework.stereotype.Repository;

import br.com.genericdelivery.model.dao.generic.JPAGenericDAO;
import br.com.genericdelivery.model.dao.interfaces.PedidoDAO;
import br.com.genericdelivery.model.entity.Pedido;

@Repository
public class PedidoDAOImpl extends JPAGenericDAO<Pedido> implements PedidoDAO {

}
