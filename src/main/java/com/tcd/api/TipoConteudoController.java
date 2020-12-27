package com.tcd.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.tcd.model.TipoConteudo;
import com.tcd.service.TipoConteudoService;

@RequestMapping("api/v1/tipoconteudo")
@RestController
@ComponentScan("com.tcd.service")
public class TipoConteudoController {

	@Autowired()
	private TipoConteudoService tipoConteudoService;
	
	@GetMapping
	public Iterable<TipoConteudo> getAllTipoConteudo(){
		return tipoConteudoService.getAllTipoConteudo();
	}
	
	@GetMapping(path = "{id}")
	public TipoConteudo getTipoConteudoById(@PathVariable("id") Long id){
		return tipoConteudoService.getTipoConteudoById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TipoConteudo createTipoConteudo(@RequestBody @Valid TipoConteudo tipoConteudo){
		return tipoConteudoService.addTipoConteudo(tipoConteudo);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(path = "{id}")
	public void deleteTipoConteudoById(@PathVariable("id") Long id){
		tipoConteudoService.deleteGeneroById(id);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping(path = "{id}")
	public TipoConteudo updateTipoConteudoById(@PathVariable("id") Long id, @RequestBody TipoConteudo tipoConteudo) {
		return tipoConteudoService.updateGeneroById(id, tipoConteudo);
	}
}