package com.br.renanfretta.cadastrosessenciais.configs;

import org.springframework.stereotype.Component;

import com.br.renanfretta.cadastrosessenciais.entities.Cidade;
import com.br.renanfretta.cadastrosessenciais.entities.Estado;
import com.br.renanfretta.commons.dtos.cadastrosessenciais.CidadeDTO;
import com.br.renanfretta.commons.dtos.cadastrosessenciais.EstadoDTO;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Component
public class OrikaMapper {

	private static MapperFacade mapperFacade;

	public OrikaMapper() {
		if (mapperFacade != null)
			return;

		MapperFactory factory = new DefaultMapperFactory.Builder().build();

		factory.classMap(Estado.class, EstadoDTO.class) //
				.constructorA().constructorB().mapNulls(true).mapNullsInReverse(true) //
				.byDefault().register();

		factory.classMap(Cidade.class, CidadeDTO.class) //
				.constructorA().constructorB().mapNulls(true).mapNullsInReverse(true) //
				.byDefault().register();

		mapperFacade = factory.getMapperFacade();
	}

	public MapperFacade getFacade() {
		return mapperFacade;
	}

}
