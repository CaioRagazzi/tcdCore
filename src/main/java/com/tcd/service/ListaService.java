package com.tcd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tcd.configuration.Producer;
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
	
	public Lista[] GetByUserId(long userId) {		
		String urlWithUser = url + "/{userId}";
		var response = restTemplate.getForObject(urlWithUser, Lista[].class, userId);
		
		return response;
	}
	
	public Lista GetByTipoId(long userId, long tipoId) {		
		String urlWithUser = url + "/{userId}/{tipoId}";
		var response = restTemplate.getForObject(urlWithUser, Lista.class, userId, tipoId);
		
		return response;
	}
	
	public String addConteudo(ListaCreateDTO listaCreateDTO) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		var response = restTemplate.postForEntity(url, listaCreateDTO, String.class);
		
		return response.toString();
	}
	
	public String removeConteudo(ListaRemoveDTO listaRemoveDTO) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<ListaRemoveDTO> request = new HttpEntity<ListaRemoveDTO>(listaRemoveDTO, headers);
		
		var response = restTemplate.exchange(url, HttpMethod.DELETE, request, String.class);
		
		return response.toString();
	}
	
	public void sendAddMessage(String message) {
		this.producer.sendAddMessage(message);
	}
	
	public void sendRemovedMessage(String message) {
		this.producer.sendRemoveMessage(message);
	}
}
