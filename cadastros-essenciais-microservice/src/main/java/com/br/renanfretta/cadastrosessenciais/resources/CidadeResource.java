package com.br.renanfretta.cadastrosessenciais.resources;

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

import com.br.renanfretta.cadastrosessenciais.services.CidadeService;
import com.br.renanfretta.commons.dtos.cadastrosessenciais.CidadeDTO;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeResource {

	@Autowired
	private CidadeService service;

	@GetMapping
	public ResponseEntity<List<CidadeDTO>> findAll() {
		List<CidadeDTO> list = service.findAll();
		return ResponseEntity.ok(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<CidadeDTO> findById(@PathVariable Long id) {
		CidadeDTO cidadeDTO = service.findById(id);
		return ResponseEntity.ok(cidadeDTO);
	}

	@PostMapping
	public ResponseEntity<CidadeDTO> salvar(@Valid @RequestBody CidadeDTO cidadeDTO) {
		cidadeDTO = service.save(cidadeDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(cidadeDTO);
	}

	@PutMapping
	public ResponseEntity<CidadeDTO> atualizar(@Valid @RequestBody CidadeDTO cidadeDTO) {
		cidadeDTO = service.save(cidadeDTO);
		return ResponseEntity.status(HttpStatus.OK).body(cidadeDTO);
	}

	@GetMapping(value = "/nome/{nome}")
	public ResponseEntity<List<CidadeDTO>> findByNome(@PathVariable String nome) {
		List<CidadeDTO> list = service.findByNomeContaining(nome);
		return ResponseEntity.ok(list);
	}

	@GetMapping(value = "/uf/{uf}")
	public ResponseEntity<List<CidadeDTO>> findByEstado(@PathVariable String uf) {
		List<CidadeDTO> list = service.findByUf(uf);
		return ResponseEntity.ok(list);
	}

}
