package com.br.renanfretta.cadastrosessenciais.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.renanfretta.cadastrosessenciais.services.EstadoService;
import com.br.renanfretta.commons.dtos.cadastrosessenciais.EstadoDTO;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {
	
	@Autowired
	private EstadoService service;
	
	@GetMapping
	public ResponseEntity<List<EstadoDTO>> findAll() {
		List<EstadoDTO> list = service.findAll();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<EstadoDTO> findById(@PathVariable Long id) {
		EstadoDTO estadoDTO = service.findById(id);
		return ResponseEntity.ok(estadoDTO);
	}
	
	@GetMapping(value = "/nome/{nome}")
	public ResponseEntity<List<EstadoDTO>> findByNomeContaining(@PathVariable String nome) {
		List<EstadoDTO> list = service.findByNomeContaining(nome);
		return ResponseEntity.ok(list);
	}

}
