package com.tcd.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.tcd.model.Lista;
import com.tcd.model.ListaCreateDTO;

@Service
public class ListaService {
	
	private final RestTemplate restTemplate;
	private final String url = "http://localhost:8082/v1/lista";

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
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		MultiValueMap<String, Integer> map = new LinkedMultiValueMap<String, Integer>();
		map.add("userId", listaCreateDTO.getUserId());
		map.add("tipoLista", listaCreateDTO.getTipoLista());
		map.add("idConteudo", listaCreateDTO.getIdConteudo());
		
		HttpEntity<MultiValueMap<String, Integer>> request = new HttpEntity<MultiValueMap<String, Integer>>(map, headers);
		
		var response = restTemplate.postForEntity(url, request, String.class);
		
		return response.toString();
	}
	
	public String removeConteudo(ListaCreateDTO listaCreateDTO) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		MultiValueMap<String, Integer> map = new LinkedMultiValueMap<String, Integer>();
		map.add("userId", listaCreateDTO.getUserId());
		map.add("tipoLista", listaCreateDTO.getTipoLista());
		map.add("idConteudo", listaCreateDTO.getIdConteudo());
		
		HttpEntity<MultiValueMap<String, Integer>> request = new HttpEntity<MultiValueMap<String, Integer>>(map, headers);
		
		var response = restTemplate.exchange(url, HttpMethod.DELETE, request, String.class);
		
		return response.toString();
	}
}
