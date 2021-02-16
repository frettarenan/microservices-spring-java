package br.com.renanfretta.commons.dtos.cadastrosessenciais;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CidadeDTO implements Serializable {

	private static final long serialVersionUID = 7568049974376820896L;

	@EqualsAndHashCode.Include
	private Long id;
	
	private String nome;
	
	private EstadoDTO estado;
	
}
