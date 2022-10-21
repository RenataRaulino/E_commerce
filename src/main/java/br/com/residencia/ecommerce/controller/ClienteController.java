package br.com.residencia.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.residencia.ecommerce.entity.Cliente;
import br.com.residencia.ecommerce.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	@Autowired
	ClienteService categoriaService;
	
	@GetMapping
	public ResponseEntity<List<Cliente>> getAllClientes(){
		return new ResponseEntity<>(categoriaService.getAllClientes(),
				HttpStatus.OK);
	}
	


	@GetMapping("/{id}")
	public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id) {
		Cliente categoria = categoriaService.getClienteById(id);
		if(null != categoria)
			return new ResponseEntity<>(categoria,
					HttpStatus.OK);
		else
			return new ResponseEntity<>(categoria,
					HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	public ResponseEntity<Cliente> saveCliente(@RequestBody Cliente categoria) {
		return new ResponseEntity<>(categoriaService.saveCliente(categoria),
				HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> updateCliente(@RequestBody Cliente categoria, 
			@PathVariable Integer id){
		return new ResponseEntity<>(categoriaService.updateCliente(categoria, id),
				HttpStatus.OK);
	}
	
	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Cliente> deleteCliente(@PathVariable Integer id) {
		Cliente categoria = categoriaService.getClienteById(id);
		if(null == categoria)
			return new ResponseEntity<>(categoria,
					HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(categoriaService.deleteCliente(id),
					HttpStatus.OK);
	}

}
