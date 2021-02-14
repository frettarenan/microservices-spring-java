package com.br.renanfretta.clientes.dtos;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CidadeDTO implements Serializable {

	private static final long serialVersionUID = 7568049974376820896L;

	@EqualsAndHashCode.Include
	private Long id;
	
	private String nome;
	
	private EstadoDTO estado;
	
}
