package com.tcd.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tcd.model.ConteudoCurtido;
import com.tcd.service.ConteudoCurtidoService;

@RequestMapping("api/v1/conteudocurtido")
@RestController
@ComponentScan("com.tcd.service")
public class ConteudoCurtidoController {

	@Autowired()
	private ConteudoCurtidoService conteudoCurtidoService;
	
	@GetMapping
	public Iterable<ConteudoCurtido> getAllConteudoCurtido(){
		return conteudoCurtidoService.getAllConteudoCurtido();
	}
	
	@GetMapping(path = "{id}")
	public ConteudoCurtido getConteudoCurtidoById(@PathVariable("id") Long id){
		return conteudoCurtidoService.getConteudoCurtidoById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ConteudoCurtido createConteudoCurtido(@RequestBody @Valid ConteudoCurtido conteudoCurtido){
		return conteudoCurtidoService.addConteudoCurtido(conteudoCurtido);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(path = "{id}")
	public void deleteConteudoCurtidoById(@PathVariable("id") Long id){
		conteudoCurtidoService.deleteConteudoCurtidoById(id);
	}
}
