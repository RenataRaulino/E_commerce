package br.com.residencia.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.ecommerce.dto.PedidoDTO;
import br.com.residencia.ecommerce.entity.Pedido;
import br.com.residencia.ecommerce.repository.PedidoRepository;



@Service
public class PedidoService {
	@Autowired
	PedidoRepository pedidoRepository;
	
	public List<Pedido> getAllPedidos(){
		return pedidoRepository.findAll();
	}
	
	public List<PedidoDTO> getAllPedidosDTO() {
		List<Pedido> listaPedido = pedidoRepository.findAll();
		List<PedidoDTO> listaPedidoDTO = new ArrayList<>();

		for (Pedido pedido : listaPedido) {

			PedidoDTO pedidoDTO = toDTO(pedido);

			listaPedidoDTO.add(pedidoDTO);
		}

		return listaPedidoDTO;
	}
	
	public Pedido getPedidoById(Integer id) {
		return pedidoRepository.findById(id).orElse(null);
	}
	
	public Pedido savePedido(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}
	public PedidoDTO savePedidoDTO(PedidoDTO pedidoDTO) {
		Pedido pedido = toEntidade(pedidoDTO);
		Pedido novaPedido = pedidoRepository.save(pedido);

		PedidoDTO pedidoAtualizadaDTO = toDTO(novaPedido);
		return pedidoAtualizadaDTO;
	}

	
	public Pedido updatePedido(Pedido pedido, Integer id) {
		Pedido pedidoExistenteNoBanco = getPedidoById(id);

		
		if(pedidoExistenteNoBanco!= null) {
		pedidoExistenteNoBanco.setIdPedido(pedidoExistenteNoBanco.getIdPedido());
		pedidoExistenteNoBanco.setDataPedido(pedido.getDataPedido());
		pedidoExistenteNoBanco.setDataEntrega(pedido.getDataEntrega());
		pedidoExistenteNoBanco.setDataEnvio(pedido.getDataEnvio());
		pedidoExistenteNoBanco.setStatus(pedido.getStatus());
		pedidoExistenteNoBanco.setValorTotal(pedido.getValorTotal());
		pedidoExistenteNoBanco.setCliente(pedido.getCliente());
		}
		return pedidoRepository.save(pedidoExistenteNoBanco);	
		
	}
	public PedidoDTO updatePedidoDTO(PedidoDTO pedidoDTO, Integer id) {
		Pedido pedidoExistenteNoBanco = getPedidoById(id);
		PedidoDTO pedidoAtualizadoDTO = new PedidoDTO();
		pedidoDTO.setIdPedido(id);
		
		if(pedidoExistenteNoBanco != null) {
			
			pedidoExistenteNoBanco = toEntidade(pedidoDTO);
			
			Pedido pedidoAtualizada = pedidoRepository.save(pedidoExistenteNoBanco);
			
			pedidoAtualizadoDTO = toDTO(pedidoAtualizada);
		}
		return pedidoAtualizadoDTO;
	}
	
	public Pedido deletePedido(Integer id) {
		pedidoRepository.deleteById(id);
		return getPedidoById(id);
	}
	public Pedido toEntidade(PedidoDTO pedidoDTO) {
		Pedido pedido = new Pedido();
		
		pedido.setIdPedido(pedidoDTO.getIdPedido());
		pedido.setDataPedido(pedidoDTO.getDatapedido());
		pedido.setValorTotal(pedidoDTO.getValorTotal());
		
		return pedido;
	}

	public PedidoDTO toDTO(Pedido pedido) {
		PedidoDTO pedidoDTO = new PedidoDTO();

		pedidoDTO.setIdPedido(pedido.getIdPedido());
		pedidoDTO.setDatapedido(pedido.getDataPedido());
		pedidoDTO.setValorTotal(pedido.getValorTotal());

		return pedidoDTO;
	}
	
}
