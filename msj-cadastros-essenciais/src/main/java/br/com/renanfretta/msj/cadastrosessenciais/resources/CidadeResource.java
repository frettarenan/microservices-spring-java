package br.com.renanfretta.msj.cadastrosessenciais.resources;

import java.util.List;
import java.util.NoSuchElementException;

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

import br.com.renanfretta.msj.cadastrosessenciais.services.CidadeService;
import br.com.renanfretta.msj.commons.dtos.cadastrosessenciais.CidadeDTO;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeResource {

	@Autowired
	private CidadeService service;

	@GetMapping
	public ResponseEntity<List<CidadeDTO>> findAll() {
		List<CidadeDTO> list = service.findAll();
		if (list == null || list.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<CidadeDTO> findById(@PathVariable Long id) {
		try {
			CidadeDTO cidadeDTO = service.findById(id);
			return ResponseEntity.ok(cidadeDTO);
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping(value = "/nome/{nome}")
	public ResponseEntity<List<CidadeDTO>> findByNomeContaining(@PathVariable String nome) {
		List<CidadeDTO> list = service.findByNomeContaining(nome);
		if (list == null || list.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(value = "/uf/{uf}")
	public ResponseEntity<List<CidadeDTO>> findByUf(@PathVariable String uf) {
		List<CidadeDTO> list = service.findByUf(uf);
		if (list == null || list.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(list);
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

}
