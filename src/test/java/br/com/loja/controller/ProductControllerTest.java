package br.com.loja.controller;

import static org.junit.Assert.*;

import java.math.BigDecimal;
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

import br.com.loja.controller.form.ProductForm;
import br.com.loja.controller.form.UserForm;
import br.com.loja.model.Products;
import br.com.loja.util.JsonUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;
	 
	
	@Test
	public void getBadRequestUnrecognizedData() throws Exception  {
		URI uri = new URI("/prodcuts");
		ProductForm productForm = new ProductForm("Camisa","Algodão",new BigDecimal("0.2"),"M");
		String json = JsonUtil.toGson(productForm);
		mockMvc.perform(MockMvcRequestBuilders.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is(403));
	}
	
	


}
