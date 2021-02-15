package br.com.renanfretta.cadastrosessenciais;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import br.com.renanfretta.cadastrosessenciais.entities.Cidade;
import br.com.renanfretta.cadastrosessenciais.entities.Estado;
import br.com.renanfretta.cadastrosessenciais.repositories.CidadeRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DisplayName("Cidade endpoint tests")
public class CidadeEndpointTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CidadeRepository repository;

	private Cidade cidade01 = Cidade.builder() //
			.id(79L) //
			.nome("Acrelândia") //
			.estado(Estado.builder() //
					.id(1L) //
					.nome("Acre") //
					.uf("AC") //
					.build())
			.build();

	@Nested
	@DisplayName("Method: GET Path: /cidades")
	class findAll {

		@Test
		@DisplayName("Retornando elementos corretamente")
		public void findAllComResultados() throws Exception {

			List<Cidade> list = new ArrayList<Cidade>();
			list.add(cidade01);

			BDDMockito.when(repository.findAll()).thenReturn(list);

			mockMvc.perform(get("/cidades")) //
					.andExpect(status().isOk()) //
					.andExpect(content().contentType(MediaType.APPLICATION_JSON)) //
					.andExpect(jsonPath("$.length()", is(1))) //
					.andExpect(jsonPath("$.[0].id").value(79)) //
					.andExpect(jsonPath("$.[0].nome").value("Acrelândia")) //
					.andExpect(jsonPath("$.[0].estado.id").value(1)) //
					.andExpect(jsonPath("$.[0].estado.nome").value("Acre")) //
					.andExpect(jsonPath("$.[0].estado.uf").value("AC")); //
		}

		@Test
		@DisplayName("Sem resultados")
		public void findAllSemResultado() throws Exception {

			List<Cidade> list = new ArrayList<Cidade>();

			BDDMockito.when(repository.findAll()).thenReturn(list);

			mockMvc.perform(get("/cidades")) //
					.andExpect(status().isNoContent());
		}

	}
	
	@Nested
	@DisplayName("Method: GET Path: /cidades/{id}")
	class findById {

		@Test
		@DisplayName("Retornando elementos corretamente")
		public void findByIdEncontrado() throws Exception {

			BDDMockito.when(repository.findById(79L)).thenReturn(Optional.of(cidade01));

			mockMvc.perform(get("/cidades/79")) //
					.andExpect(status().isOk()) //
					.andExpect(content().contentType(MediaType.APPLICATION_JSON)) //
					.andExpect(jsonPath("$.id").value(79)) //
					.andExpect(jsonPath("$.nome").value("Acrelândia")) //
					.andExpect(jsonPath("$.estado.id").value(1)) //
					.andExpect(jsonPath("$.estado.nome").value("Acre")) //
					.andExpect(jsonPath("$.estado.uf").value("AC")); //
		}

		@Test
		@DisplayName("Sem resultados")
		public void findByIdNaoEncontrado() throws Exception {

			BDDMockito.when(repository.findById(9999L)).thenReturn(null);

			mockMvc.perform(get("/cidades/79")) //
					.andExpect(status().isNotFound());
		}

	}
	
	@Nested
	@DisplayName("Method: GET Path: /nome/{nome}")
	class findByNomeContaining {

		@Test
		@DisplayName("Retornando elementos corretamente")
		public void findByIdEncontrado() throws Exception {
			
			List<Cidade> list = new ArrayList<Cidade>();
			list.add(cidade01);

			BDDMockito.when(repository.findByNomeContaining("acrelandia")).thenReturn(list);

			mockMvc.perform(get("/cidades/nome/acrelandia")) //
					.andExpect(status().isOk()) //
					.andExpect(content().contentType(MediaType.APPLICATION_JSON)) //
					.andExpect(jsonPath("$.[0].id").value(79)) //
					.andExpect(jsonPath("$.[0].nome").value("Acrelândia")) //
					.andExpect(jsonPath("$.[0].estado.id").value(1)) //
					.andExpect(jsonPath("$.[0].estado.nome").value("Acre")) //
					.andExpect(jsonPath("$.[0].estado.uf").value("AC")); //
		}

		@Test
		@DisplayName("Sem resultados")
		public void findByIdNaoEncontrado() throws Exception {

			BDDMockito.when(repository.findByNomeContaining("acrelandia")).thenReturn(null);

			mockMvc.perform(get("/cidades/nome/acrelandia")) //
					.andExpect(status().isNoContent());
		}

	}

}
