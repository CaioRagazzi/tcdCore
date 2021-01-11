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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tcd.model.Genero;
import com.tcd.service.GeneroService;

import io.swagger.annotations.ApiOperation;

@RequestMapping("api/v1/genero")
@RestController
@ComponentScan("com.tcd.service")
@EnableCircuitBreaker
public class GeneroController {

	@Autowired()
	private GeneroService generoService;
	
	@ApiOperation(value = "Lista todos os generos")
	@GetMapping
	public Iterable<Genero> getAllGenero(){
		return generoService.getAllGenero();
	}
	
	@ApiOperation(value = "Lista genero por id")	
	@GetMapping(path = "{id}")
	public Genero getGeneroById(@PathVariable("id") Long id){
		return generoService.getGeneroById(id);
	}
	
	@ApiOperation(value = "Cria um novo genero")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Genero createGenero(@RequestBody @Valid Genero genero){
		return generoService.addGenero(genero);
	}
	
	@ApiOperation(value = "Deleta genero por id")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(path = "{id}")
	public void deleteGeneroById(@PathVariable("id") Long id){
		generoService.deleteGeneroById(id);
	}
	
	@ApiOperation(value = "Atualiza genero por id")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping(path = "{id}")
	public Genero updateGeneroById(@PathVariable("id") Long id, @RequestBody Genero genero) {
		return generoService.updateGeneroById(id, genero);
	}
}
