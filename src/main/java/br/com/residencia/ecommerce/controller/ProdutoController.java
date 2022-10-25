package br.com.residencia.ecommerce.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import br.com.residencia.ecommerce.dto.ProdutoDTO;
import br.com.residencia.ecommerce.entity.Produto;
import br.com.residencia.ecommerce.exception.NoSuchElementFoundException;
import br.com.residencia.ecommerce.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	@Autowired
	ProdutoService produtoService;
	
	@GetMapping
	public ResponseEntity<List<Produto>> getAllProdutos(){
		return new ResponseEntity<>(produtoService.getAllProdutos(),
				HttpStatus.OK);
	}
	
	@GetMapping("/dto")
	public ResponseEntity<List<ProdutoDTO>> getAllProdutosDTO(){
		return new ResponseEntity<>(produtoService.getAllProdutosDTO(),
				HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> getProdutoById(@PathVariable Integer id) {
		Produto produto = new Produto();
		
		try {
			produto = produtoService.getProdutoById(id);
			return new ResponseEntity<>(produto, HttpStatus.OK);			
		}catch(Exception ex) {
			throw new NoSuchElementFoundException("Não foi encontrado resultado com o id " + id);
		}
	}
	
	@PostMapping
	public ResponseEntity<Produto> saveProduto(@RequestBody Produto produto) {
		return new ResponseEntity<>(produtoService.saveProduto(produto),
				HttpStatus.CREATED);
	}
	
	@PostMapping("/dto")
	public ResponseEntity<ProdutoDTO> saveProdutoDTO(@RequestBody ProdutoDTO produtoDTO) {
		return new ResponseEntity<>(produtoService.saveProdutoDTO(produtoDTO),
				HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Produto> updateProduto(@RequestBody Produto produto, 
			@PathVariable Integer id){
		return new ResponseEntity<>(produtoService.updateProduto(produto, id),
				HttpStatus.OK);
	}
	
	@PutMapping("/dto/{id}")
	public ResponseEntity<ProdutoDTO> updateProdutoDTO(@RequestBody ProdutoDTO produtoDTO, 
			@PathVariable Integer id){
		return new ResponseEntity<>(produtoService.updateProdutoDTO(produtoDTO, id),
				HttpStatus.OK);
	}
	
	
	@PostMapping(value="/cadastro-produto-com-foto")
	public ResponseEntity<Produto> saveProdutoFoto(
			@RequestPart ("produto") String produtoTxt,
			@RequestParam ("filename") MultipartFile file)		
	
		throws IOException{
		Produto produto = produtoService.saveProdutoFoto(produtoTxt, file);
		if(produto ==null)
			return new ResponseEntity<>(produto,HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(produto,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Produto> deleteProduto(@PathVariable Integer id) {
		Produto produto = produtoService.getProdutoById(id);
		if(null == produto)
			return new ResponseEntity<>(produto,
					HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(produtoService.deleteProduto(id),
					HttpStatus.OK);
	}

}
