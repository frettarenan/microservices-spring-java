package com.br.renanfretta.clientes.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.br.renanfretta.clientes.configs.OrikaMapper;
import com.br.renanfretta.clientes.entities.Cliente;
import com.br.renanfretta.clientes.repositories.ClienteRepository;
import com.br.renanfretta.commons.dtos.clientes.ClienteDTO;

@Service
@Validated
public class ClienteService {
	
	@Autowired
	private OrikaMapper orikaMapper;
	
	@Autowired
	private ClienteRepository repository;

	public List<ClienteDTO> findAll() {
		List<Cliente> list = repository.findAll();
		List<ClienteDTO> listDTO = orikaMapper.getFacade().mapAsList(list, ClienteDTO.class);
		return listDTO;
	}

	public ClienteDTO findById(Long id) {
		Cliente cliente = repository.findById(id).orElseThrow();
		ClienteDTO dto = orikaMapper.getFacade().map(cliente, ClienteDTO.class);
		return dto;
	}

	public ClienteDTO save(ClienteDTO clienteDTO) {
		Cliente cliente = orikaMapper.getFacade().map(clienteDTO, Cliente.class);
		repository.save(cliente);
		clienteDTO = findById(cliente.getId());
		return clienteDTO;
	}

	public List<ClienteDTO> findByNomeContaining(String nome) {
		List<Cliente> list = repository.findByNomeContaining(nome);
		List<ClienteDTO> listDTO = orikaMapper.getFacade().mapAsList(list, ClienteDTO.class);
		return listDTO;
	}

	public ClienteDTO deleteById(Long id) {
		Cliente cliente = repository.findById(id).orElseThrow();
		repository.delete(cliente);
		ClienteDTO dto = orikaMapper.getFacade().map(cliente, ClienteDTO.class);
		return dto;
	}

}