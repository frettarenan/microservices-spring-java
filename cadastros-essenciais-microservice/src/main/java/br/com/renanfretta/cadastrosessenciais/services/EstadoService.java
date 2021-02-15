package br.com.renanfretta.cadastrosessenciais.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.com.renanfretta.cadastrosessenciais.configs.OrikaMapper;
import br.com.renanfretta.cadastrosessenciais.entities.Estado;
import br.com.renanfretta.cadastrosessenciais.repositories.EstadoRepository;
import br.com.renanfretta.commons.dtos.cadastrosessenciais.EstadoDTO;

@Service
@Validated
public class EstadoService {
	
	@Autowired
	private OrikaMapper orikaMapper;
	
	@Autowired
	private EstadoRepository repository;

	public List<EstadoDTO> findAll() {
		List<Estado> list = repository.findAll();
		List<EstadoDTO> listDTO = orikaMapper.getFacade().mapAsList(list, EstadoDTO.class);
		return listDTO;
	}

	public EstadoDTO findById(Long id) {
		Estado estado = repository.findById(id).orElseThrow();
		EstadoDTO dto = orikaMapper.getFacade().map(estado, EstadoDTO.class);
		return dto;
	}

	public List<EstadoDTO> findByNomeContaining(String nome) {
		List<Estado> list = repository.findByNomeContaining(nome);
		List<EstadoDTO> listDTO = orikaMapper.getFacade().mapAsList(list, EstadoDTO.class);
		return listDTO;
	}

}