package br.com.renanfretta.msj.clientes.resources;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.renanfretta.msj.clientes.services.ClienteService;
import br.com.renanfretta.msj.commons.dtos.clientes.ClienteDTO;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;

	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<ClienteDTO> list = service.findAll();
		if (list == null || list.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable Long id) {
		try {
			ClienteDTO clienteDTO = service.findById(id);
			return ResponseEntity.ok(clienteDTO);
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping(value = "/nome/{nome}")
	public ResponseEntity<List<ClienteDTO>> findByNomeContaining(@PathVariable String nome) {
		List<ClienteDTO> list = service.findByNomeContaining(nome);
		if (list == null || list.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(list);
	}

	@PostMapping
	public ResponseEntity<ClienteDTO> salvar(@Valid @RequestBody ClienteDTO clienteDTO) {
		clienteDTO = service.save(clienteDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteDTO);
	}

	@PutMapping
	public ResponseEntity<ClienteDTO> atualizar(@Valid @RequestBody ClienteDTO clienteDTO) {
		clienteDTO = service.save(clienteDTO);
		return ResponseEntity.status(HttpStatus.OK).body(clienteDTO);
	}
	
	@PatchMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> editarInfomacoesPreenchidas(@PathVariable Long id, @Valid @RequestBody ClienteDTO clienteDTO) {
		clienteDTO = service.editarInfomacoesPreenchidas(id, clienteDTO);
		return ResponseEntity.status(HttpStatus.OK).body(clienteDTO);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> deleteById(@PathVariable Long id) {
		ClienteDTO clienteDTO = service.deleteById(id);
		return ResponseEntity.ok(clienteDTO);
	}

}
