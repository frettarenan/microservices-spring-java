package com.br.renanfretta.cadastrosessenciais.dtos;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CidadeDTO implements Serializable {
	
	private static final long serialVersionUID = 6911892286218945093L;

	@EqualsAndHashCode.Include
	private Long id;
	
	private String nome;
	
	private EstadoDTO estado;
	
}
