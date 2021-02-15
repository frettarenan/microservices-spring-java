package br.com.renanfretta.cadastrosessenciais.resources;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.renanfretta.cadastrosessenciais.services.EstadoService;
import br.com.renanfretta.commons.dtos.cadastrosessenciais.EstadoDTO;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {
	
	@Autowired
	private EstadoService service;
	
	@GetMapping
	public ResponseEntity<List<EstadoDTO>> findAll() {
		List<EstadoDTO> list = service.findAll();
		if (list == null || list.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<EstadoDTO> findById(@PathVariable Long id) {
		try {
			EstadoDTO estadoDTO = service.findById(id);
			return ResponseEntity.ok(estadoDTO);
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping(value = "/nome/{nome}")
	public ResponseEntity<List<EstadoDTO>> findByNomeContaining(@PathVariable String nome) {
		List<EstadoDTO> list = service.findByNomeContaining(nome);
		if (list == null || list.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(list);
	}

}
