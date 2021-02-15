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

import br.com.renanfretta.cadastrosessenciais.entities.Estado;
import br.com.renanfretta.cadastrosessenciais.repositories.EstadoRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DisplayName("Estado endpoint tests")
public class EstadoEndpointTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EstadoRepository repository;

	private Estado estado01 = Estado.builder() //
			.id(1L) //
			.nome("Acre") //
			.uf("AC") //
			.build();

	@Nested
	@DisplayName("Method: GET Path: /estados")
	class findAll {

		@Test
		@DisplayName("Retornando elementos corretamente")
		public void findAllComResultados() throws Exception {

			List<Estado> list = new ArrayList<Estado>();
			list.add(estado01);

			BDDMockito.when(repository.findAll()).thenReturn(list);

			mockMvc.perform(get("/estados")) //
					.andExpect(status().isOk()) //
					.andExpect(content().contentType(MediaType.APPLICATION_JSON)) //
					.andExpect(jsonPath("$.length()", is(1))) //
					.andExpect(jsonPath("$.[0].id").value(1)) //
					.andExpect(jsonPath("$.[0].nome").value("Acre")) //
					.andExpect(jsonPath("$.[0].uf").value("AC")); //
		}

		@Test
		@DisplayName("Sem resultados")
		public void findAllSemResultado() throws Exception {

			List<Estado> list = new ArrayList<Estado>();

			BDDMockito.when(repository.findAll()).thenReturn(list);

			mockMvc.perform(get("/estados")) //
					.andExpect(status().isNoContent());
		}

	}

	@Nested
	@DisplayName("Method: GET Path: /estados/{id}")
	class findById {

		@Test
		@DisplayName("Retornando elementos corretamente")
		public void findByIdEncontrado() throws Exception {

			BDDMockito.when(repository.findById(1L)).thenReturn(Optional.of(estado01));

			mockMvc.perform(get("/estados/1")) //
					.andExpect(status().isOk()) //
					.andExpect(content().contentType(MediaType.APPLICATION_JSON)) //
					.andExpect(jsonPath("$.id").value(1)) //
					.andExpect(jsonPath("$.nome").value("Acre")) //
					.andExpect(jsonPath("$.uf").value("AC")); //
		}

		@Test
		@DisplayName("Sem resultados")
		public void findByIdNaoEncontrado() throws Exception {

			BDDMockito.when(repository.findById(9999L)).thenReturn(null);

			mockMvc.perform(get("/estados/1")) //
					.andExpect(status().isNotFound());
		}

	}
	
	@Nested
	@DisplayName("Method: GET Path: /nome/{nome}")
	class findByNomeContaining {

		@Test
		@DisplayName("Retornando elementos corretamente")
		public void findByIdEncontrado() throws Exception {
			
			List<Estado> list = new ArrayList<Estado>();
			list.add(estado01);

			BDDMockito.when(repository.findByNomeContaining("acre")).thenReturn(list);

			mockMvc.perform(get("/estados/nome/acre")) //
					.andExpect(status().isOk()) //
					.andExpect(content().contentType(MediaType.APPLICATION_JSON)) //
					.andExpect(jsonPath("$.[0].id").value(1)) //
					.andExpect(jsonPath("$.[0].nome").value("Acre")) //
					.andExpect(jsonPath("$.[0].uf").value("AC")); //
		}

		@Test
		@DisplayName("Sem resultados")
		public void findByIdNaoEncontrado() throws Exception {

			BDDMockito.when(repository.findByNomeContaining("acre")).thenReturn(null);

			mockMvc.perform(get("/estados/nome/acre")) //
					.andExpect(status().isNoContent());
		}

	}

}
