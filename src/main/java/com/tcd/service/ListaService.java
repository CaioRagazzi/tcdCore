package com.tcd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.tcd.configuration.Producer;
import com.tcd.model.BlackList;
import com.tcd.model.Lista;
import com.tcd.model.ListaCreateDTO;
import com.tcd.model.ListaRemoveDTO;

@Service
public class ListaService {
	
	private final RestTemplate restTemplate;
	private final String url = "http://localhost:8082/v1/lista";
	@Autowired
	private Producer producer;

    public ListaService() {
    	this.restTemplate = new RestTemplateBuilder().build();
    }
	
    @HystrixCommand(fallbackMethod = "GetListaByIdErrorHandling")
	public Lista[] GetListaById(long userId) {		
		String urlWithUser = url + "/{userId}";
		var response = restTemplate.getForObject(urlWithUser, Lista[].class, userId);
		
		return response;
	}
	
    @HystrixCommand(fallbackMethod = "GetListaByTipoIdIdErrorHandling")
	public Lista GetListaByTipoId(long userId, long tipoId) {		
		String urlWithUser = url + "/{userId}/{tipoId}";
		var response = restTemplate.getForObject(urlWithUser, Lista.class, userId, tipoId);
		
		return response;
	}
	
    @HystrixCommand(fallbackMethod = "AddListaErrorHandling")
	public String addLista(ListaCreateDTO listaCreateDTO) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		var response = restTemplate.postForEntity(url, listaCreateDTO, String.class);
		
		return response.toString();
	}
	
    @HystrixCommand(fallbackMethod = "RemoveListaErrorHandling")
	public String removeLista(ListaRemoveDTO listaRemoveDTO) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<ListaRemoveDTO> request = new HttpEntity<ListaRemoveDTO>(listaRemoveDTO, headers);
		
		var response = restTemplate.exchange(url, HttpMethod.DELETE, request, String.class);
		
		return response.toString();
	}
	
    @HystrixCommand(fallbackMethod = "SendAddMessageListaErrorHandling")
	public void sendAddMessageLista(String message) {
		this.producer.sendAddMessage(message);
	}
	
    @HystrixCommand(fallbackMethod = "SendRemovedMessageListaErrorHandling")
	public void sendRemovedMessageLista(String message) {
		this.producer.sendRemoveMessage(message);
	}
	
	public Lista[] GetListaByIdErrorHandling(long userId) {
		Lista[] litLista = null;
		return litLista;
	}
	
	public Lista GetListaByTipoIdIdErrorHandling(long userId, long tipoId) {		
		return new Lista();
	}
	
	public String AddListaErrorHandling(ListaCreateDTO listaCreateDTO) {
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json;
		try {
			BlackList blackList = new BlackList();
			blackList.setMethodName("AddListaErrorHandling");
			blackList.setJson(objectWriter.writeValueAsString(listaCreateDTO));
			json = objectWriter.writeValueAsString(blackList);
			this.producer.sendToBlackList(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return new String();
	}
	
	public String RemoveListaErrorHandling(ListaRemoveDTO listaRemoveDTO) {
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json;
		try {
			BlackList blackList = new BlackList();
			blackList.setMethodName("RemoveListaHandling");
			blackList.setJson(objectWriter.writeValueAsString(listaRemoveDTO));
			json = objectWriter.writeValueAsString(blackList);
			this.producer.sendToBlackList(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	public void SendAddMessageListaErrorHandling(String message) {
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json;
		try {
			BlackList blackList = new BlackList();
			blackList.setMethodName("SendAddMessageListaErrorHandling");
			blackList.setJson(objectWriter.writeValueAsString(message));
			json = objectWriter.writeValueAsString(blackList);
			this.producer.sendToBlackList(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	public void SendRemovedMessageListaErrorHandling(String message) {
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json;
		try {
			BlackList blackList = new BlackList();
			blackList.setMethodName("SendRemovedMessageListaErrorHandling");
			blackList.setJson(objectWriter.writeValueAsString(message));
			json = objectWriter.writeValueAsString(blackList);
			this.producer.sendToBlackList(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
