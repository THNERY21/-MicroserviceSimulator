package br.com.brasilprev.loja.controller;

import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.brasilprev.loja.controller.form.UserForm;
import br.com.brasilprev.loja.util.JsonUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AuthenticationControllerTest {

	@Autowired
	private MockMvc mockMvc;
	 
	@Test
	public void getBadRequestUnrecognizedData() throws Exception  {
		URI uri = new URI("/auth");
		UserForm userForm = new UserForm("thnery233","teste123");
		String json = JsonUtil.toGson(userForm);
		mockMvc.perform(MockMvcRequestBuilders.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is(400));
	}

}
