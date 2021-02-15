package br.com.renanfretta.clientes;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

import com.google.gson.Gson;

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

	private Gson gson = new Gson();

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

}
