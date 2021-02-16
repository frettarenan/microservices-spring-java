package br.com.renanfretta.msj.clientes.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.com.renanfretta.msj.clientes.configs.OrikaMapper;
import br.com.renanfretta.msj.clientes.entities.Cliente;
import br.com.renanfretta.msj.clientes.repositories.ClienteRepository;
import br.com.renanfretta.msj.commons.dtos.clientes.ClienteDTO;

@Service
@Validated
public class ClienteService {
	
	@Autowired
	private OrikaMapper orikaMapper;
	
	@Autowired
	private ClienteRepository repository;

	public List<ClienteDTO> findAll() {
		List<Cliente> list = repository.findAll();
		List<ClienteDTO> listDTO = orikaMapper.mapAsList(list, ClienteDTO.class);
		return listDTO;
	}

	public ClienteDTO findById(Long id) {
		Cliente cliente = repository.findById(id).orElseThrow();
		ClienteDTO dto = orikaMapper.map(cliente, ClienteDTO.class);
		return dto;
	}

	public ClienteDTO save(ClienteDTO clienteDTO) {
		Cliente cliente = orikaMapper.map(clienteDTO, Cliente.class);
		cliente = repository.save(cliente);
		clienteDTO = findById(cliente.getId());
		return clienteDTO;
	}

	public List<ClienteDTO> findByNomeContaining(String nome) {
		List<Cliente> list = repository.findByNomeContaining(nome);
		List<ClienteDTO> listDTO = orikaMapper.mapAsList(list, ClienteDTO.class);
		return listDTO;
	}
	
	public ClienteDTO editarInfomacoesPreenchidas(Long id, ClienteDTO clienteDTO) {
		Cliente cliente = repository.findById(id).orElseThrow();
		
		if (clienteDTO.getNome() != null)
			cliente.setNome(clienteDTO.getNome());
		
		if (clienteDTO.getSexo() != null)
			cliente.setSexo(clienteDTO.getSexo());
		
		if (clienteDTO.getDataNascimento() != null)
			cliente.setDataNascimento(clienteDTO.getDataNascimento());
		
		if (clienteDTO.getCidade() != null && clienteDTO.getCidade().getId() != null)
			cliente.setIdCidade(clienteDTO.getCidade().getId());
		
		cliente = repository.save(cliente);
		
		clienteDTO = findById(cliente.getId());
		return clienteDTO;
	}

	public ClienteDTO deleteById(Long id) {
		Cliente cliente = repository.findById(id).orElseThrow();
		repository.delete(cliente);
		ClienteDTO dto = orikaMapper.map(cliente, ClienteDTO.class);
		return dto;
	}

}
