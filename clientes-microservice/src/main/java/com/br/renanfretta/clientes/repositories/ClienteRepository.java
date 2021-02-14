package com.br.renanfretta.clientes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.renanfretta.clientes.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	List<Cliente> findByNomeContaining(String nome);

}
