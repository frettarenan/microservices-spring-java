package com.br.renanfretta.cadastrosessenciais;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.br.renanfretta.cadastrosessenciais.entities.Estado;
import com.br.renanfretta.cadastrosessenciais.repositories.EstadoRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class EstadoEndpointTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EstadoRepository repository;

	@Test
	public void findAllTest01ComResultados() throws Exception {
		Estado estado = new Estado(1L, "Acre", "AC");
		Optional<Estado> optional = Optional.of(estado);

		List<Estado> list = new ArrayList<Estado>();
		list.add(estado);

		BDDMockito.when(repository.findAll()).thenReturn(list);
		BDDMockito.when(repository.findById(1L)).thenReturn(optional);

		// -----------------------------------------------------------------------------

		mockMvc.perform(get("/estados")) //
				.andExpect(status().isOk()) //
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(jsonPath("$.length()", is(1))) //
				.andExpect(jsonPath("$.[0].id").value(1)) //
				.andExpect(jsonPath("$.[0].nome").value("Acre")); //

		/* Outra forma de implementar:
		 * @Autowired private TestRestTemplate restTemplate;
		 * 
		 * ResponseEntity<List> response = restTemplate.getForEntity("/estados/",
		 * List.class);
		 * 
		 * Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.OK.
		 * value());
		 * 
		 * Assertions.assertThat(response.getBody().size()).isEqualTo(1);
		 */
	}

}
