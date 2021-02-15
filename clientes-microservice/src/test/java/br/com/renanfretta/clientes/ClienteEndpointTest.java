package br.com.renanfretta.clientes;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.renanfretta.clientes.entities.Cliente;
import br.com.renanfretta.clientes.repositories.ClienteRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DisplayName("Cliente endpoint tests")
public class ClienteEndpointTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ClienteRepository repository;

	@Autowired
	private ObjectMapper objectMapper;

	private static Cliente cliente01;

	@BeforeAll
	private static void beforeAll() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(1986, 7 - 1, 15);
		Date dataNascimento = calendar.getTime();

		cliente01 = Cliente.builder() //
				.id(1L) //
				.nome("Renan Fretta") //
				.sexo("M") //
				.dataNascimento(dataNascimento) //
				.idCidade(4660L) //
				.build();
	}

	@Nested
	@DisplayName("Method: GET Path: /clientes")
	class findAll {

		@Test
		@DisplayName("Retornando elementos corretamente")
		public void findAllComResultados() throws Exception {

			List<Cliente> list = new ArrayList<Cliente>();
			list.add(cliente01);

			BDDMockito.when(repository.findAll()).thenReturn(list);

			mockMvc.perform(get("/clientes")) //
					.andExpect(status().isOk()) //
					.andExpect(content().contentType(MediaType.APPLICATION_JSON)) //
					.andExpect(jsonPath("$.length()", is(1))) //
					.andExpect(jsonPath("$.[0].id").value(1)) //
					.andExpect(jsonPath("$.[0].nome").value("Renan Fretta")) //
					.andExpect(jsonPath("$.[0].sexo").value("M")) //
					.andExpect(jsonPath("$.[0].dataNascimento").value("1986-07-15")) //
					.andExpect(jsonPath("$.[0].cidade.id").value("4660")); //
		}

		@Test
		@DisplayName("Sem resultados")
		public void findAllSemResultado() throws Exception {

			List<Cliente> list = new ArrayList<Cliente>();

			BDDMockito.when(repository.findAll()).thenReturn(list);

			mockMvc.perform(get("/clientes")) //
					.andExpect(status().isNoContent());
		}

	}

	@Nested
	@DisplayName("Method: GET Path: /clientes/{id}")
	class findById {

		@Test
		@DisplayName("Retornando elementos corretamente")
		public void findByIdEncontrado() throws Exception {

			BDDMockito.when(repository.findById(1L)).thenReturn(Optional.of(cliente01));

			mockMvc.perform(get("/clientes/1")) //
					.andExpect(status().isOk()) //
					.andExpect(content().contentType(MediaType.APPLICATION_JSON)) //
					.andExpect(jsonPath("$.id").value(1)) //
					.andExpect(jsonPath("$.nome").value("Renan Fretta")) //
					.andExpect(jsonPath("$.sexo").value("M")) //
					.andExpect(jsonPath("$.dataNascimento").value("1986-07-15")) //
					.andExpect(jsonPath("$.cidade.id").value("4660")); //
		}

		@Test
		@DisplayName("Sem resultados")
		public void findByIdNaoEncontrado() throws Exception {

			BDDMockito.when(repository.findById(9999L)).thenReturn(null);

			mockMvc.perform(get("/clientes/1")) //
					.andExpect(status().isNotFound());
		}

		@Test
		@DisplayName("Erro: ID inválido")
		public void findByIdErro() throws Exception {

			mockMvc.perform(get("/clientes/AAAAA")) //
					.andExpect(status().isBadRequest());
		}

	}

	@Nested
	@DisplayName("Method: GET Path: /nome/{nome}")
	class findByNomeContaining {

		@Test
		@DisplayName("Retornando elementos corretamente")
		public void findByNomeContainingEncontrado() throws Exception {

			List<Cliente> list = new ArrayList<Cliente>();
			list.add(cliente01);

			BDDMockito.when(repository.findByNomeContaining("renan")).thenReturn(list);

			mockMvc.perform(get("/clientes/nome/renan")) //
					.andExpect(status().isOk()) //
					.andExpect(content().contentType(MediaType.APPLICATION_JSON)) //
					.andExpect(jsonPath("$.[0].id").value(1)) //
					.andExpect(jsonPath("$.[0].nome").value("Renan Fretta")) //
					.andExpect(jsonPath("$.[0].sexo").value("M")) //
					.andExpect(jsonPath("$.[0].dataNascimento").value("1986-07-15")) //
					.andExpect(jsonPath("$.[0].cidade.id").value("4660")); //
		}

		@Test
		@DisplayName("Sem resultados")
		public void findByNomeContainingNaoEncontrado() throws Exception {

			BDDMockito.when(repository.findByNomeContaining("acrelandia")).thenReturn(null);

			mockMvc.perform(get("/clientes/nome/maria")) //
					.andExpect(status().isNoContent());
		}

	}

	@Nested
	@DisplayName("Method: POST Path: /clientes")
	class salvar {

		@Test
		@DisplayName("Salvo com sucesso")
		public void salvarSucesso() throws Exception {

			Calendar calendar = Calendar.getInstance();
			calendar.set(1986, 7 - 1, 15);
			Date dataNascimento = calendar.getTime();

			Cliente cliente = Cliente.builder() //
					.nome("Renan Fretta") //
					.sexo("M") //
					.dataNascimento(dataNascimento) //
					.idCidade(4660L) //
					.build();

			BDDMockito.when(repository.save(cliente)).thenReturn(cliente01);
			BDDMockito.when(repository.findById(1L)).thenReturn(Optional.of(cliente01));

			mockMvc.perform(post("/clientes") //
					.contentType(MediaType.APPLICATION_JSON) //
					.content(objectMapper.writeValueAsString(cliente))) //
					.andExpect(status().isCreated()) //
					.andExpect(content().contentType(MediaType.APPLICATION_JSON)) //
					.andExpect(jsonPath("$.id").value(1)) //
					.andExpect(jsonPath("$.nome").value("Renan Fretta")) //
					.andExpect(jsonPath("$.sexo").value("M")) //
					.andExpect(jsonPath("$.dataNascimento").value("1986-07-15")) //
					.andExpect(jsonPath("$.cidade.id").value("4660")); //
		}

	}

	@Nested
	@DisplayName("Method: PUT Path: /clientes")
	class atualizar {

		@Test
		@DisplayName("Atualizado com sucesso")
		public void atualizarSucesso() throws Exception {

			Calendar calendar = Calendar.getInstance();
			calendar.set(1986, 7 - 1, 15);
			Date dataNascimento = calendar.getTime();

			Cliente cliente = Cliente.builder() //
					.id(1L) //
					.nome("Renan Fretta editado") //
					.sexo("M") //
					.dataNascimento(dataNascimento) //
					.idCidade(4660L) //
					.build();

			BDDMockito.when(repository.save(cliente)).thenReturn(cliente);
			BDDMockito.when(repository.findById(1L)).thenReturn(Optional.of(cliente));

			mockMvc.perform(put("/clientes") //
					.contentType(MediaType.APPLICATION_JSON) //
					.content(objectMapper.writeValueAsString(cliente))) //
					.andExpect(status().isOk()) //
					.andExpect(content().contentType(MediaType.APPLICATION_JSON)) //
					.andExpect(jsonPath("$.id").value(1)) //
					.andExpect(jsonPath("$.nome").value("Renan Fretta editado")) //
					.andExpect(jsonPath("$.sexo").value("M")) //
					.andExpect(jsonPath("$.dataNascimento").value("1986-07-15")) //
					.andExpect(jsonPath("$.cidade.id").value("4660")); //
		}

	}

	@Nested
	@DisplayName("Method: DELETE Path: /clientes")
	class deleteById {

		@Test
		@DisplayName("Deletado com sucesso")
		public void deleteByIdSucesso() throws Exception {

			BDDMockito.when(repository.findById(1L)).thenReturn(Optional.of(cliente01));

			mockMvc.perform(get("/clientes/1")) //
					.andExpect(status().isOk()) //
					.andExpect(content().contentType(MediaType.APPLICATION_JSON)) //
					.andExpect(jsonPath("$.id").value(1)) //
					.andExpect(jsonPath("$.nome").value("Renan Fretta")) //
					.andExpect(jsonPath("$.sexo").value("M")) //
					.andExpect(jsonPath("$.dataNascimento").value("1986-07-15")) //
					.andExpect(jsonPath("$.cidade.id").value("4660")); //
		}

		@Test
		@DisplayName("Sem resultados")
		public void deleteByIdNaoEncontrado() throws Exception {

			BDDMockito.when(repository.findById(9999L)).thenReturn(null);

			mockMvc.perform(get("/clientes/1")) //
					.andExpect(status().isNotFound());
		}

		@Test
		@DisplayName("Erro: ID inválido")
		public void deleteByIdErro() throws Exception {

			mockMvc.perform(get("/clientes/AAAAA")) //
					.andExpect(status().isBadRequest());
		}

	}

}
