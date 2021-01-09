package com.tcd.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tcd.model.Lista;
import com.tcd.model.ListaCreateDTO;
import com.tcd.service.ListaService;

@RequestMapping("api/v1/lista")
@RestController
@ComponentScan("com.tcd.service")
@EnableCircuitBreaker
public class ListController {
	
	@Autowired
	private ListaService listaService;
	
	@GetMapping(path = "{userId}")
	public Lista[] getListaByUserId(@PathVariable("userId") long userId){
		var response = listaService.GetByUserId(userId);
		
		return response;
	}
	
	@GetMapping(path = "user/{userId}/tipo/{tipoLista}")
	public Lista getListaByTipo(@PathVariable("userId") long userId, @PathVariable("tipoLista") long tipoLista){
		var response = listaService.GetByTipoId(userId, tipoLista);
		
		return response;
	}
	
	@PostMapping
	public String addConteudo(@RequestBody ListaCreateDTO listaCreateDTO) {
		var response = listaService.addConteudo(listaCreateDTO);
		
		return response;
	}
	
	@RequestMapping(value = "", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String removeConteudo(ListaCreateDTO listaCreateDTO) {
		var response = listaService.removeConteudo(listaCreateDTO);
		
		return response;
	}

}
