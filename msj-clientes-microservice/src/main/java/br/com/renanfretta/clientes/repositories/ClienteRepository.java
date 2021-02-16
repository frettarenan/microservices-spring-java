package br.com.renanfretta.clientes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renanfretta.clientes.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	List<Cliente> findByNomeContaining(String nome);

}
