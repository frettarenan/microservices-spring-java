package br.com.renanfretta.msj.commons.dtos.clientes;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.renanfretta.msj.commons.dtos.cadastrosessenciais.CidadeDTO;
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
public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = -8245145127829755272L;

	@EqualsAndHashCode.Include
	private Long id;

	private String nome;

	private String sexo;

	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dataNascimento;

	private CidadeDTO cidade;

	public Integer getIdade() {
		if (getDataNascimento() == null)
			return null;
			
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

}
