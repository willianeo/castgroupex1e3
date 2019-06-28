package com.castgroup;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONArray;
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

@RunWith(SpringRunner.class)
@SpringBootTest
public class OneTaskTest {

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
	public void save_data_OK() throws Exception { // teste de integração
		String base64Json = null;
		ResultActions resultActions = null;
		
		// Teste de integração End-Point 1
		base64Json = "W3siaWQiOjEsIm5hbWUiOiJNYXJpYSBkYXMgR3Jhw6dhcyIsInNhbGFyeSI6NTUwMC4wfSx7ImlkIjoyLCJuYW1lIjoiUm9kcmlnbyIsInNhbGFyeSI6NTUwMC4wfSx7ImlkIjozLCJuYW1lIjoiUm9nw6lyaW8iLCJzYWxhcnkiOjU1MDAuMH0seyJpZCI6NCwibmFtZSI6IkthcmluYSIsInNhbGFyeSI6NTUwMC4wfSx7ImlkIjo1LCJuYW1lIjoiSm/Do28gR2FicmllbCIsInNhbGFyeSI6NTUwMC4wfSx7ImlkIjo2LCJuYW1lIjoiTHXDrXMgTWlndWVsIiwic2FsYXJ5Ijo1NTAwLjB9LHsiaWQiOjcsIm5hbWUiOiJXaWxsaWFuIiwic2FsYXJ5Ijo1NTAwLjB9XQ==";
		resultActions = mockMvc.perform(post("/v1/diff/1/left")
				.content(base64Json)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());
		resultActions.andDo(MockMvcResultHandlers.print());
		
		// Teste de integração End-Point 2
		base64Json = "W3siaWQiOjEsIm5hbWUiOiJNYXJpYSBkYXMgR3Jhw6dhcyIsInNhbGFyeSI6NTUwMC4wfSx7ImlkIjoyLCJuYW1lIjoiUm9kcmlnbyIsInNhbGFyeSI6NTUwMC4wfSx7ImlkIjozLCJuYW1lIjoiUm9nw6lyaW8iLCJzYWxhcnkiOjU1MDAuMH0seyJpZCI6NCwibmFtZSI6IkthcmluYSIsInNhbGFyeSI6NTUwMC4wfSx7ImlkIjo1LCJuYW1lIjoiSm/Do28gR2FicmllbCIsInNhbGFyeSI6NTUwMC4wfSx7ImlkIjo2LCJuYW1lIjoiTHXDrXMgTWlndWVsIiwic2FsYXJ5Ijo1NTAwLjB9LHsiaWQiOjcsIm5hbWUiOiJXaWxsaWFuIiwic2FsYXJ5Ijo1NTAwLjB9XQ==";
		resultActions = mockMvc.perform(post("/v1/diff/2/right")
				.content(base64Json)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk());
		resultActions.andDo(MockMvcResultHandlers.print());
		
		// Teste de integração End-Point 3
		mockMvc.perform(get("/v1/diff/1/2"))
			.andDo(print())
			.andExpect(status().isOk());
	}
	
}
