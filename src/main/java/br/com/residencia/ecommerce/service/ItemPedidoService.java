package br.com.residencia.ecommerce.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.residencia.ecommerce.entity.ItemPedido;
import br.com.residencia.ecommerce.repository.ItemPedidoRepository;

@Service
public class ItemPedidoService {
	@Autowired
	ItemPedidoRepository itemItemPedidoRepository;
	
	public List<ItemPedido> getAllItemPedidos(){
		return itemItemPedidoRepository.findAll();
	}
	
	public ItemPedido getItemPedidoById(Integer id) {
		return itemItemPedidoRepository.findById(id).orElse(null);
	}
	
	public ItemPedido saveItemPedido(ItemPedido itemItemPedido) {
		return itemItemPedidoRepository.save(itemItemPedido);
	}
	
	
	public ItemPedido updateItemPedido(ItemPedido itemItemPedido, Integer id) {
		ItemPedido itemItemPedidoExistenteNoBanco = getItemPedidoById(id);

		itemItemPedidoExistenteNoBanco.setQuantidade(itemItemPedido.getQuantidade());
		itemItemPedidoExistenteNoBanco.setPrecoVenda(itemItemPedido.getPrecoVenda());
		itemItemPedidoExistenteNoBanco.setPercentualDesconto(itemItemPedido.getPercentualDesconto());
		itemItemPedidoExistenteNoBanco.setValorBruto(itemItemPedido.getValorBruto());
		itemItemPedidoExistenteNoBanco.setValorLiquido(itemItemPedido.getValorLiquido());
		itemItemPedidoExistenteNoBanco.setPedido(itemItemPedido.getPedido());
		itemItemPedidoExistenteNoBanco.setProduto(itemItemPedido.getProduto());
		
		return itemItemPedidoRepository.save(itemItemPedidoExistenteNoBanco);	
		
	}
	
	public ItemPedido deleteItemPedido(Integer id) {
		itemItemPedidoRepository.deleteById(id);
		return getItemPedidoById(id);
	}
	
}
