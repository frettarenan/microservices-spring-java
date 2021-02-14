package com.br.renanfretta.cadastrosessenciais.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.renanfretta.cadastrosessenciais.entities.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long> {

	List<Estado> findByNomeContaining(String nome);

}
