package com.br.renanfretta.clientes.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.renanfretta.clientes.entities.Cliente;
import com.br.renanfretta.clientes.repositories.ClienteRepository;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
	
    /*  
    Cadastrar cliente
    Consultar cliente pelo nome
    Consultar cliente pelo Id
    Remover cliente
    Alterar o nome do cliente */

	@Autowired
	private ClienteRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Cliente>> findAll() {
		List<Cliente> list = repository.findAll();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable Long id) {
		Cliente cliente = repository.findById(id).orElseThrow();
		return ResponseEntity.ok(cliente);
	}
	
	@PostMapping
	public ResponseEntity<Cliente> salvar(@Valid @RequestBody Cliente cliente) {
		cliente = repository.save(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
	}
	
	@PutMapping
	public ResponseEntity<Cliente> atualizar(@Valid @RequestBody Cliente cliente) {
		cliente = repository.save(cliente);
		return ResponseEntity.status(HttpStatus.OK).body(cliente);
	}
	
	@GetMapping(value = "/nome/{nome}")
	public ResponseEntity<List<Cliente>> findByNome(@PathVariable String nome) {
		List<Cliente> list = repository.findByNomeContaining(nome);
		return ResponseEntity.ok(list);
	}
	
}
