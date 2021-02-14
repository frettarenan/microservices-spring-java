package com.br.renanfretta.cadastrosessenciais.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.renanfretta.cadastrosessenciais.entities.Cidade;
import com.br.renanfretta.cadastrosessenciais.repositories.CidadeRepository;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeResource {
	
    /* Cadastrar cidade OK
    Consultar cidade pelo nome ok
    Consultar cidade pelo estado ok
    
    
    Cadastrar cliente
    Consultar cliente pelo nome
    Consultar cliente pelo Id
    Remover cliente
    Alterar o nome do cliente */

	@Autowired
	private CidadeRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Cidade>> findAll() {
		List<Cidade> list = repository.findAll();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cidade> findById(@PathVariable Long id) {
		Cidade cidade = repository.findById(id).orElseThrow();
		return ResponseEntity.ok(cidade);
	}
	
	@PostMapping
	public ResponseEntity<Cidade> salvar(Cidade cidade) {
		cidade = repository.save(cidade);
		return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
	}
	
	@PutMapping
	public ResponseEntity<Cidade> atualizar(Cidade cidade) {
		cidade = repository.save(cidade);
		return ResponseEntity.status(HttpStatus.OK).body(cidade);
	}
	
	@GetMapping(value = "/nome/{nome}")
	public ResponseEntity<List<Cidade>> findByNome(@PathVariable String nome) {
		List<Cidade> list = repository.findByNomeContaining(nome);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(value = "/uf/{uf}")
	public ResponseEntity<List<Cidade>> findByEstado(@PathVariable String uf) {
		List<Cidade> list = repository.findByUf(uf);
		return ResponseEntity.ok(list);
	}
	
}
