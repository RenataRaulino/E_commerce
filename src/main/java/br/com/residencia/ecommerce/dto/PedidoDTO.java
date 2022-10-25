package br.com.residencia.ecommerce.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public class PedidoDTO {

	private Integer idPedidoDTO;
	private Instant datapedido;
	private BigDecimal valorTotal;
	private List<ItemPedidoDTO> listaItensPedidosProdutosDTO;
		
	public PedidoDTO() {
	}
	
	public Integer getIdPedido() {
		return idPedidoDTO;
	}
	public void setIdPedido(Integer idPedido) {
		this.idPedidoDTO = idPedido;
	}
	public Instant getDatapedido() {
		return datapedido;
	}
	public void setDatapedido(Instant datapedido) {
		this.datapedido = datapedido;
	}
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Integer getIdPedidoDTO() {
		return idPedidoDTO;
	}

	public void setIdPedidoDTO(Integer idPedidoDTO) {
		this.idPedidoDTO = idPedidoDTO;
	}

	public List<ItemPedidoDTO> getListaItensPedidosProdutosDTO() {
		return listaItensPedidosProdutosDTO;
	}

	public void setListaItensPedidosProdutosDTO(List<ItemPedidoDTO> listaItensPedidosProdutosDTO) {
		this.listaItensPedidosProdutosDTO = listaItensPedidosProdutosDTO;
	}

	
}
