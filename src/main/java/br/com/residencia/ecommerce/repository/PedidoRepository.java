package br.com.residencia.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.residencia.ecommerce.entity.ItemPedido;
import br.com.residencia.ecommerce.entity.Pedido;

public interface PedidoRepository extends  JpaRepository<Pedido,Integer>{

	public List<Pedido> findByItemPedido(ItemPedido itemPedido);
}
