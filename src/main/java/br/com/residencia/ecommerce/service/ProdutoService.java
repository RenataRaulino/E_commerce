package br.com.residencia.ecommerce.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.residencia.ecommerce.dto.ProdutoDTO;
import br.com.residencia.ecommerce.dto.imgBB.ImgBBDTO;
import br.com.residencia.ecommerce.entity.Produto;
import br.com.residencia.ecommerce.repository.ProdutoRepository;


@Service
public class ProdutoService {
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Value("${imgbb.host.url}")
	private String imgBBHostUrl;
	
	@Value("${imgbb.host.key}")
    private String imgBBHostKey;

	
	public List<Produto> getAllProdutos(){
		return produtoRepository.findAll();
	}
	
	public List<ProdutoDTO> getAllProdutosDTO() {
		List<Produto> listaProduto = produtoRepository.findAll();
		List<ProdutoDTO> listaProdutoDTO = new ArrayList<>();
		
		
		for (Produto produto : listaProduto) {

			ProdutoDTO produtoDTO = toDTO(produto);

			listaProdutoDTO.add(produtoDTO);
		}

		return listaProdutoDTO;
	}
	
	public Produto getProdutoById(Integer id) {
		return produtoRepository.findById(id).orElse(null);
	}
	
	public Produto saveProduto(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	public ProdutoDTO saveProdutoDTO(ProdutoDTO produtoDTO) {
		Produto produto = toEntidade(produtoDTO);
		Produto novoProduto = produtoRepository.save(produto);

		ProdutoDTO produtoAtualizadaDTO = toDTO(novoProduto);
		return produtoAtualizadaDTO;
	}

	
	public Produto updateProduto(Produto produto, Integer id) {
		Produto produtoExistenteNoBanco = getProdutoById(id);
	
		
		if(produtoExistenteNoBanco!= null) {
		produtoExistenteNoBanco.setIdProduto(produtoExistenteNoBanco.getIdProduto());
		produtoExistenteNoBanco.setNome(produto.getNome());
		produtoExistenteNoBanco.setDescricao(produto.getDescricao());
		produtoExistenteNoBanco.setQtdEstoque(produto.getQtdEstoque());
		produtoExistenteNoBanco.setDataCadastro(produto.getDataCadastro());
		produtoExistenteNoBanco.setValorUnitario(produto.getValorUnitario());
		produtoExistenteNoBanco.setImagem(produto.getImagem());
		produtoExistenteNoBanco.setCategoria(produto.getCategoria());
		
	
		}
		return produtoRepository.save(produtoExistenteNoBanco);
	}
	
	public ProdutoDTO updateProdutoDTO(ProdutoDTO produtoDTO, Integer id) {
		Produto produtoExistenteNoBanco = getProdutoById(id);
		ProdutoDTO produtoAtualizadaDTO = new ProdutoDTO();
		produtoDTO.setIdProduto(id);
		if(produtoExistenteNoBanco != null) {
			
			produtoExistenteNoBanco = toEntidade(produtoDTO);
			
			Produto produtoAtualizada = produtoRepository.save(produtoExistenteNoBanco);
			
			produtoAtualizadaDTO = toDTO(produtoAtualizada);
			
		}
		return produtoAtualizadaDTO;
	}

	
	public Produto deleteProduto(Integer id) {
		produtoRepository.deleteById(id);
		return getProdutoById(id);
	}
	
	private Produto toEntidade(ProdutoDTO produtoDTO) {
		Produto produto = new Produto();
		
		produto.setIdProduto(produtoDTO.getIdProduto());
		produto.setNome(produtoDTO.getNomeProduto());
		produto.setImagemFileName(produtoDTO.getImagemFileName());
		produto.setImagemNome(produtoDTO.getImagemNome());
		produto.setImagemUrl(produtoDTO.getImagemUrl());
			
		
		
		return produto;
	}

	private ProdutoDTO toDTO(Produto produto) {
		ProdutoDTO produtoDTO = new ProdutoDTO();

		produtoDTO.setIdProduto(produto.getIdProduto());
		produtoDTO.setNomeProduto(produto.getNome());
		produtoDTO.setImagemFileName(produto.getImagemFileName());
		produtoDTO.setImagemNome(produto.getImagemNome());
		produtoDTO.setImagemUrl(produto.getImagemUrl());
		
		
			
		return produtoDTO;
	}
	
	public Produto saveProdutoFoto(String produtoTxt, MultipartFile file) 
			 throws IOException {
					
					RestTemplate restTemplate = new RestTemplate();
					String serverUrl = imgBBHostUrl + imgBBHostKey;
					
					HttpHeaders headers = new HttpHeaders();
					headers.setContentType(MediaType.MULTIPART_FORM_DATA);
					
					MultiValueMap<String, String> fileMap = new LinkedMultiValueMap<>();
					
					ContentDisposition contentDisposition = ContentDisposition
							.builder("form-data")
							.name("image")
							.filename(file.getOriginalFilename())
							.build();
					
					fileMap.add(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());
					
					HttpEntity<byte[]> fileEntity = new HttpEntity<>(file.getBytes(), fileMap);
					
					MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
					body.add("image", fileEntity);
					
					HttpEntity<MultiValueMap<String, Object>> requestEntity =
							new HttpEntity<>(body, headers);
					
					ResponseEntity<ImgBBDTO> response = null;
					ImgBBDTO imgDTO = new ImgBBDTO();
					Produto novoProduto = new Produto(); 
					try {
						response = restTemplate.exchange(
								serverUrl,
								HttpMethod.POST,
								requestEntity,
								ImgBBDTO.class);
						
						imgDTO = response.getBody();
						System.out.println("ImgBBDTO: " + imgDTO.getData().toString());
					} catch (HttpClientErrorException e) {
						e.printStackTrace();
					}
					
					//Converte os dados da produto recebidos no formato String em Entidade
					//  Coleta os dados da imagem, após upload via API, e armazena na Entidade Produto
					if(null != imgDTO) {
						Produto produtoFromJson = convertProdutoFromStringJson(produtoTxt);
						produtoFromJson.setImagemFileName(imgDTO.getData().getImage().getFilename());
						produtoFromJson.setImagemNome(imgDTO.getData().getTitle());
						produtoFromJson.setImagemUrl(imgDTO.getData().getUrl());
						novoProduto = produtoRepository.save(produtoFromJson);
	
					}		
					return novoProduto;
				}	
					
		
				private Produto convertProdutoFromStringJson(String produtoJson) {
					Produto produto = new Produto();
					
					try {
						ObjectMapper objectMapper = new ObjectMapper();
						produto = objectMapper.readValue(produtoJson, Produto.class);
					} catch (IOException err) {
						//System.out.printf("Ocorreu um erro ao tentar converter a string json para um instância da entidade Produto", err.toString());
						err.getStackTrace();
					}
					
					return produto;
				
		}
	
}
