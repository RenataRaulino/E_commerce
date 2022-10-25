package br.com.residencia.ecommerce.dto;

import java.util.List;

public class ProdutoDTO {

	private Integer idProduto;
	private String nomeProduto;
	private List<ItemPedidoDTO> listaItensPedidosProdutosDTO;
	private String imagemNome;
	private String imagemFileName;
	private String imagemUrl;

	public ProdutoDTO() {
	}
	
	public String getImagemNome() {
		return imagemNome;
	}
	public void setImagemNome(String imagemNome) {
		this.imagemNome = imagemNome;
	}
	public String getImagemFileName() {
		return imagemFileName;
	}
	public void setImagemFileName(String imagemFileName) {
		this.imagemFileName = imagemFileName;
	}
	public String getImagemUrl() {
		return imagemUrl;
	}
	public void setImagemUrl(String imagemUrl) {
		this.imagemUrl = imagemUrl;
	}
	
	
	
	public Integer getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public List<ItemPedidoDTO> getListaItensPedidosProdutosDTO() {
		return listaItensPedidosProdutosDTO;
	}

	public void setListaItensPedidosProdutosDTO(List<ItemPedidoDTO> listaItensPedidosProdutosDTO) {
		this.listaItensPedidosProdutosDTO = listaItensPedidosProdutosDTO;
	}
	
	
}
