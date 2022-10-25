package br.com.residencia.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.residencia.ecommerce.entity.ItemPedido;
import br.com.residencia.ecommerce.entity.Pedido;
//import br.com.residencia.ecommerce.entity.Produto;

public interface PedidoRepository extends  JpaRepository<Pedido,Integer>{
	
	//public List<ItemPedido> findByPedido(Pedido pedido);
	public List<Pedido> findByItemPedido(ItemPedido itemPedido);
	
	//public List<ItemPedido> findByProduto(Produto produto);

	//public List<Pedido> findByItemPedido(ItemPedido itemPedido);

}
