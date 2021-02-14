package com.br.renanfretta.cadastrosessenciais.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.renanfretta.cadastrosessenciais.entities.Estado;
import com.br.renanfretta.cadastrosessenciais.repositories.EstadoRepository;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {
	
	@Autowired
	private EstadoRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Estado>> findAll() {
		List<Estado> list = repository.findAll();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Estado> findById(@PathVariable Long id) {
		Estado estado = repository.findById(id).orElseThrow();
		return ResponseEntity.ok(estado);
	}
	
	@GetMapping(value = "/nome/{nome}")
	public ResponseEntity<List<Estado>> findByNomeContaining(@PathVariable String nome) {
		List<Estado> list = repository.findByNomeContaining(nome);
		return ResponseEntity.ok(list);
	}

}
