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

import com.tcd.model.GeneroConteudo;
import com.tcd.service.GeneroConteudoService;

@RequestMapping("api/v1/generoconteudo")
@RestController
@ComponentScan("com.tcd.service")
public class GeneroConteudoController {

	@Autowired()
	private GeneroConteudoService generoConteudoService;
	
	@GetMapping
	public Iterable<GeneroConteudo> getAllGeneroConteudo(){
		return generoConteudoService.getAllGeneroConteudo();
	}
	
	@GetMapping(path = "{id}")
	public GeneroConteudo getGeneroConteudoById(@PathVariable("id") Long id){
		return generoConteudoService.getGeneroConteudoById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GeneroConteudo createGeneroConteudo(@RequestBody @Valid GeneroConteudo generoConteudo){
		return generoConteudoService.addGeneroConteudo(generoConteudo);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(path = "{id}")
	public void deleteGeneroConteudoById(@PathVariable("id") Long id){
		generoConteudoService.deleteGeneroConteudoById(id);
	}
}
