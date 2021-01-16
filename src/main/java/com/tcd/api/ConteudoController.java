package com.tcd.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tcd.model.Conteudo;
import com.tcd.service.ConteudoService;

import io.swagger.annotations.ApiOperation;

@RequestMapping("api/v1/conteudo")
@RestController
@ComponentScan("com.tcd.service")
@EnableCircuitBreaker
public class ConteudoController {

	@Autowired
	private ConteudoService conteudoService;
	
	@ApiOperation(value = "Lista todos os conteúdos")
	@GetMapping
	public List<Conteudo> getAllConteudo(){
		var allConteudo = conteudoService.getAllConteudo();
		return allConteudo;
	}
	
	@ApiOperation(value = "Lista conteúdo por id")
	@GetMapping(path = "{id}")
	public Conteudo getConteudoById(@PathVariable("id") Long id){
		return conteudoService.getConteudoById(id);
	}
	
	@ApiOperation(value = "Cria um novo conteúdo")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Conteudo createConteudo(@RequestBody @Valid Conteudo conteudo){
		return conteudoService.addConteudo(conteudo);
	}
	
	@ApiOperation(value = "Deleta conteúdo por id")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(path = "{id}")
	public void deleteConteudoById(@PathVariable("id") Long id){
		conteudoService.deleteConteudoById(id);
	}
	
	@ApiOperation(value = "Atualiza conteúdo por id")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping(path = "{id}")
	public void updateConteudoById(@PathVariable("id") Long id, @RequestBody Conteudo conteudo) {
		conteudoService.updateConteudo(id, conteudo);
	}
}
