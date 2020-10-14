package br.com.brasilprev.loja.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
public class HelloController {
	
	@RequestMapping("/")
	@ResponseBody
	public String hello() {
		return "Sejam bem-vindos ao WebSerice da Loja BrasilPrev";
	}

}
