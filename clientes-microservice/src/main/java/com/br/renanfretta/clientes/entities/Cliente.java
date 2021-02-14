package com.br.renanfretta.clientes.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Length;

import com.br.renanfretta.clientes.dtos.CidadeDTO;

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

	public Integer getIdade() {
		Calendar now = Calendar.getInstance();
		Calendar dob = Calendar.getInstance();
		dob.setTime(getDataNascimento());
		
		if (dob.after(now))
			throw new IllegalArgumentException("Can't be born in the future");
		
		int year1 = now.get(Calendar.YEAR);
		int year2 = dob.get(Calendar.YEAR);
		int age = year1 - year2;
		int month1 = now.get(Calendar.MONTH);
		int month2 = dob.get(Calendar.MONTH);
		if (month2 > month1) {
			age--;
		} else if (month1 == month2) {
			int day1 = now.get(Calendar.DAY_OF_MONTH);
			int day2 = dob.get(Calendar.DAY_OF_MONTH);
			if (day2 > day1) {
				age--;
			}
		}
		return age;
	}
	
	private Long idCidade;

	/*
	 * FIXME: deixar as entidades somente representando o modelo de dados e criar
	 * este tipo de estutura utilizando DTOs de retorno, com SQLs que populam
	 * automaticamente em DTO Observação: feito desta forma, pois é uma
	 * implementação para fins da aplicação dos conhecimentos literários
	 */
	@Transient
	private CidadeDTO cidade;

}
