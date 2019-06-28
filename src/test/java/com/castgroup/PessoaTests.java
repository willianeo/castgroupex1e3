package com.castgroup;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.castgroup.model.Pessoa;
import com.castgroup.service.PessoaService;
import com.castgroup.service.PessoaServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PessoaTests {

	@Autowired
	private PessoaServiceImpl pessoaService;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;
	
	@Test
	public void contextLoads() {
	}
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void savePessoa() { // teste unitario
		Pessoa pessoa = new Pessoa();
		pessoa.setCellphone("5537988888888");
		pessoa.setCity("Itaúna");
		pessoa.setId(1524);
		pessoa.setName("Karina Raquel Diniz");
		pessoa.setNeighborhood("Lourdes");
		pessoa.setPhone("32222222");
		pessoa.setState("MG");
		pessoa.setStreet("Itaúna");
		Pessoa npessoa = this.pessoaService.savePessoa(pessoa);
		System.out.println("Id pesspa: " + npessoa.getId());
		System.out.println("Nome Pessoa: " + npessoa.getName());
		
	}
	
	
	public void restClient() throws Exception { // testes de integração
//		ResultActions resultActions = null;
//		
//		// adiciona uma pessoa
//		resultActions = mockMvc.perform(post("/api/pessoa")
//			.content("{\n" + 
//					"        \"id\": 1523,\n" + 
//					"        \"name\": \"Rodrigo\",\n" + 
//					"        \"street\": \"Antônio Medeiros, 78\",\n" + 
//					"        \"neighborhood\": \"Lourdes\",\n" + 
//					"        \"city\": \"Itaúna\",\n" + 
//					"        \"state\": \"MG\",\n" + 
//					"        \"cellphone\": \"+55 37988 440 956\",\n" + 
//					"        \"phone\": \"37 3242-8281\"\n" + 
//					"    }")
//			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
//			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//			.andExpect(status().isCreated());
//		resultActions.andDo(MockMvcResultHandlers.print());
//		
//		// atualiza uma pessoa
//		resultActions = mockMvc.perform(put("/api/pessoa/{id}/", 1522)
//				.content("{\n" + 
//						"        \"id\": 1522,\n" + 
//						"        \"name\": \"Rodrigo Rodrigues Andrade\",\n" + 
//						"        \"street\": \"Antônio Medeiros, 78\",\n" + 
//						"        \"neighborhood\": \"Lourdes\",\n" + 
//						"        \"city\": \"Itaúna\",\n" + 
//						"        \"state\": \"MG\",\n" + 
//						"        \"cellphone\": \"+55 37988 440 956\",\n" + 
//						"        \"phone\": \"37 3242-8281\"\n" + 
//						"    }")
//				.contentType(MediaType.APPLICATION_JSON_UTF8)
//				.accept(MediaType.APPLICATION_JSON_UTF8))
//				.andExpect(status().isOk());
//		resultActions.andDo(MockMvcResultHandlers.print());
		
		// deleta uma pessoa
		mockMvc.perform(delete("/api/pessoa/{id}", 1084))
				.andExpect(status().isNoContent())
				.andDo(print());
		
		// recupera todas as pessoas no banco
//		mockMvc.perform(get("/api/pessoa"))
//				.andDo(print())
//				.andExpect(status().isOk());
		
		// recupera uma pessoa pelo id
//		mockMvc.perform(get("/api/pessoa/{id}", 1084))
//			.andDo(print())
//			.andExpect(status().isOk());
	}
}
