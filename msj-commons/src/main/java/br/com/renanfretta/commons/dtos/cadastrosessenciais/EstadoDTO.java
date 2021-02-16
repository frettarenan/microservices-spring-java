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
public class EstadoDTO implements Serializable {
	
	private static final long serialVersionUID = 6830324586282104433L;
	
	@EqualsAndHashCode.Include
	private Long id;

	private String nome;
	
	private String uf;

}
