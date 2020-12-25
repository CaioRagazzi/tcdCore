package com.tcd.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcd.model.Conteudo;
import com.tcd.service.ConteudoService;

@RequestMapping("api/v1/conteudo")
@RestController
@ComponentScan("com.tcd.service")
public class ConteudoController {

	@Autowired
	private ConteudoService conteudoService;
	
	@GetMapping
	public List<Conteudo> getAllConteudo(){
		var allConteudo = conteudoService.getAllConteudo();
		return allConteudo;
	}
}
