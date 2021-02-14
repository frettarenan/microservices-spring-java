package com.br.renanfretta.commons.dtos.cadastrosessenciais;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EstadoDTO implements Serializable {
	
	private static final long serialVersionUID = 6830324586282104433L;
	
	@EqualsAndHashCode.Include
	private Long id;

	private String nome;
	
	private String uf;

}
