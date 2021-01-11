package com.tcd.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
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

import com.tcd.model.GeneroConteudo;
import com.tcd.service.GeneroConteudoService;

import io.swagger.annotations.ApiOperation;

@RequestMapping("api/v1/generoconteudo")
@RestController
@ComponentScan("com.tcd.service")
@EnableCircuitBreaker
public class GeneroConteudoController {

	@Autowired()
	private GeneroConteudoService generoConteudoService;
	
	@ApiOperation(value = "Lista todos os generos com conteudo")
	@GetMapping
	public Iterable<GeneroConteudo> getAllGeneroConteudo(){
		return generoConteudoService.getAllGeneroConteudo();
	}
	
	@ApiOperation(value = "Lista genero com conteudo por id")
	@GetMapping(path = "{id}")
	public GeneroConteudo getGeneroConteudoById(@PathVariable("id") Long id){
		return generoConteudoService.getGeneroConteudoById(id);
	}
	
	@ApiOperation(value = "Cria um novo genero com conteudo")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GeneroConteudo createGeneroConteudo(@RequestBody @Valid GeneroConteudo generoConteudo){
		return generoConteudoService.addGeneroConteudo(generoConteudo);
	}
	
	@ApiOperation(value = "Delete genero com conteudo por id")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(path = "{id}")
	public void deleteGeneroConteudoById(@PathVariable("id") Long id){
		generoConteudoService.deleteGeneroConteudoById(id);
	}
}
