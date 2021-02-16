package br.com.renanfretta.msj.cadastrosessenciais.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.renanfretta.msj.cadastrosessenciais.entities.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

	List<Cidade> findByNomeContaining(String nome);

	@Query("SELECT c FROM Cidade c WHERE c.estado.uf = :uf")
	List<Cidade> findByUf(String uf);

}
