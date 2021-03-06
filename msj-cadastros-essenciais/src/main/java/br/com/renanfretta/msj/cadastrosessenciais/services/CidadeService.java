package br.com.renanfretta.msj.cadastrosessenciais.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.com.renanfretta.msj.cadastrosessenciais.configs.OrikaMapper;
import br.com.renanfretta.msj.cadastrosessenciais.entities.Cidade;
import br.com.renanfretta.msj.cadastrosessenciais.repositories.CidadeRepository;
import br.com.renanfretta.msj.commons.dtos.cadastrosessenciais.CidadeDTO;

@Service
@Validated
public class CidadeService {
	
	@Autowired
	private OrikaMapper orikaMapper;
	
	@Autowired
	private CidadeRepository repository;

	public List<CidadeDTO> findAll() {
		List<Cidade> list = repository.findAll();
		List<CidadeDTO> listDTO = orikaMapper.mapAsList(list, CidadeDTO.class);
		return listDTO;
	}

	public CidadeDTO findById(Long id) {
		Cidade cidade = repository.findById(id).orElseThrow();
		CidadeDTO dto = orikaMapper.map(cidade, CidadeDTO.class);
		return dto;
	}

	public CidadeDTO save(CidadeDTO cidadeDTO) {
		Cidade cidade = orikaMapper.map(cidadeDTO, Cidade.class);
		cidade = repository.save(cidade);
		cidadeDTO = findById(cidade.getId());
		return cidadeDTO;
	}

	public List<CidadeDTO> findByNomeContaining(String nome) {
		List<Cidade> list = repository.findByNomeContaining(nome);
		List<CidadeDTO> listDTO = orikaMapper.mapAsList(list, CidadeDTO.class);
		return listDTO;
	}

	public List<CidadeDTO> findByUf(String uf) {
		List<Cidade> list = repository.findByUf(uf);
		List<CidadeDTO> listDTO = orikaMapper.mapAsList(list, CidadeDTO.class);
		return listDTO;
	}

}
