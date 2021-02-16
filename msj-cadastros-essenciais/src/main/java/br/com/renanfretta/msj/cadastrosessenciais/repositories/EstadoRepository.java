package br.com.renanfretta.msj.cadastrosessenciais.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renanfretta.msj.cadastrosessenciais.entities.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long> {

	List<Estado> findByNomeContaining(String nome);

}
