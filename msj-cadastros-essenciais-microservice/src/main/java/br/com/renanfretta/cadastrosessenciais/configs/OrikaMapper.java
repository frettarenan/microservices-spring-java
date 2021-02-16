package br.com.renanfretta.cadastrosessenciais.configs;

import org.springframework.stereotype.Component;

import br.com.renanfretta.cadastrosessenciais.entities.Cidade;
import br.com.renanfretta.cadastrosessenciais.entities.Estado;
import br.com.renanfretta.commons.configs.OrikaMapperBase;
import br.com.renanfretta.commons.dtos.cadastrosessenciais.CidadeDTO;
import br.com.renanfretta.commons.dtos.cadastrosessenciais.EstadoDTO;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Component
public class OrikaMapper extends OrikaMapperBase {

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

}
