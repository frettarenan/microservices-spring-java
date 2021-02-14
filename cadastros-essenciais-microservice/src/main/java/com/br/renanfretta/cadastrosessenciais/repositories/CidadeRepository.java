package com.br.renanfretta.cadastrosessenciais.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.renanfretta.cadastrosessenciais.entities.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

}
