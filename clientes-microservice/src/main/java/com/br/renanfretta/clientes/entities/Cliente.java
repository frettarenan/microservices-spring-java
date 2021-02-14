package com.br.renanfretta.clientes.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cliente implements Serializable {

	private static final long serialVersionUID = -7826222066232618998L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	private String nome;

	// FIXME: Falta implementar validator para valores aceitos: M ou F
	@Length(min = 1, max = 1)
	private String sexo;

	@Temporal(TemporalType.DATE)
	private Date dataNascimento;

	private Long idCidade;

}
