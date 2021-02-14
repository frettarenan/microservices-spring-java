package br.com.renanfretta.clientes.configs;

import org.springframework.stereotype.Component;

import br.com.renanfretta.clientes.entities.Cliente;
import br.com.renanfretta.commons.dtos.clientes.ClienteDTO;

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

		factory.classMap(Cliente.class, ClienteDTO.class) //
				.constructorA().constructorB().mapNulls(true).mapNullsInReverse(true) //
				.field("idCidade", "cidade.id") //
				.byDefault().register();

		mapperFacade = factory.getMapperFacade();
	}

	public MapperFacade getFacade() {
		return mapperFacade;
	}

}
