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

import com.tcd.model.FilmesAssistidos;
import com.tcd.service.FilmesAssistidosService;

@RequestMapping("api/v1/filmesassistidos")
@RestController
@ComponentScan("com.tcd.service")
public class FilmesAssistidosController {

	@Autowired()
	private FilmesAssistidosService filmesAssistidosService;
	
	@GetMapping
	public Iterable<FilmesAssistidos> getAllFilmesAssistidos(){
		return filmesAssistidosService.getAllFilmesAssistidos();
	}
	
	@GetMapping(path = "{id}")
	public FilmesAssistidos getFilmesAssistidosById(@PathVariable("id") Long id){
		return filmesAssistidosService.getFilmesAssistidosById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FilmesAssistidos createFilmesAssistidos(@RequestBody @Valid FilmesAssistidos filmesAssistidos){
		return filmesAssistidosService.addFilmesAssistidos(filmesAssistidos);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(path = "{id}")
	public void deleteFilmesAssistidosById(@PathVariable("id") Long id){
		filmesAssistidosService.deleteFilmesAssistidosById(id);
	}
}
