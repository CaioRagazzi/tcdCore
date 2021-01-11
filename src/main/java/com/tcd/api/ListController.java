package com.tcd.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.tcd.model.Lista;
import com.tcd.model.ListaCreateDTO;
import com.tcd.model.ListaRemoveDTO;
import com.tcd.service.ListaService;

import io.swagger.annotations.ApiOperation;

@RequestMapping("api/v1/lista")
@RestController
@ComponentScan("com.tcd.service")
@EnableCircuitBreaker
public class ListController {
	
	@Autowired
	private ListaService listaService;
	
	@ApiOperation(value = "Lista lista por ID de usuário (método síncrono para o serviço de lista)")
	@GetMapping(path = "{userId}")
	public Lista[] getListaByUserId(@PathVariable("userId") long userId){
		var response = listaService.GetByUserId(userId);
		
		return response;
	}
	
	@ApiOperation(value = "Lista lista por ID de usuário e tipo de lista (método síncrono para o serviço de lista)")
	@GetMapping(path = "user/{userId}/tipo/{tipoLista}")
	public Lista getListaByTipo(@PathVariable("userId") long userId, @PathVariable("tipoLista") long tipoLista){
		var response = listaService.GetByTipoId(userId, tipoLista);
		
		return response;
	}
	
	@ApiOperation(value = "Cria uma nova lista (método assíncrono para o serviço de lista)")
	@PostMapping(path = "lista")
	@ResponseStatus(HttpStatus.CREATED)
	public void addLista(@RequestBody ListaCreateDTO listaTeste){
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json;
		try {
			json = objectWriter.writeValueAsString(listaTeste);
			listaService.sendAddMessageLista(json);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@ApiOperation(value = "Delete uma lista (método assíncrono para o serviço de lista)")
	@RequestMapping(path = "lista", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public void removeLista(@RequestBody ListaRemoveDTO listaTeste){
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json;
		try {
			json = objectWriter.writeValueAsString(listaTeste);
			listaService.sendRemovedMessageLista(json);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
