package com.tcd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.tcd.configuration.Producer;
import com.tcd.model.BlackList;
import com.tcd.model.GeneroConteudo;
import com.tcd.repository.GeneroConteudoRepository;
import com.tcs.Exception.GeneroConteudoNotFoundException;

@Service
@ComponentScan("com.tcd.repository")
public class GeneroConteudoService {

	@Autowired
	private GeneroConteudoRepository generoConteudoRepository;
	
	@Autowired
	private Producer producer;
	
	@HystrixCommand(fallbackMethod = "GetAllGeneroConteudoErrorHandling")
	public List<GeneroConteudo> getAllGeneroConteudo(){
		var allGeneroConteudo = generoConteudoRepository.findAll();
		
		return allGeneroConteudo;
	}
	
	@HystrixCommand(fallbackMethod = "GetGeneroConteudoByIdErrorHandling")
	public GeneroConteudo getGeneroConteudoById(Long id) {
		var possibleGeneroConteudo = generoConteudoRepository.findById(id);
		
		if (possibleGeneroConteudo.isPresent()) {
			var generoConteudo = possibleGeneroConteudo.get();
			
			return generoConteudo;
		} else {
			throw new GeneroConteudoNotFoundException();
		}
	}
	
	@HystrixCommand(fallbackMethod = "AddGeneroConteudoErrorHandling")
	public GeneroConteudo addGeneroConteudo(GeneroConteudo generoConteudo) {
		var generoConteudoAdded = generoConteudoRepository.save(generoConteudo);
		
		return generoConteudoAdded;
	}
	
	@HystrixCommand(fallbackMethod = "DeleteGeneroConteudoByIdErrorHandling")
	public void deleteGeneroConteudoById(Long id) {
		var possibleGeneroConteudo = generoConteudoRepository.findById(id);
		
		if (possibleGeneroConteudo.isPresent()) {
			var generoConteudo = possibleGeneroConteudo.get();
			
			generoConteudoRepository.deleteById(generoConteudo.getId());
		} else {
			throw new GeneroConteudoNotFoundException();
		}
	}
	
	public List<GeneroConteudo> GetAllGeneroConteudoErrorHandling(){
		List<GeneroConteudo> generoConteudoList = null;
		return generoConteudoList;
	}
	
	public GeneroConteudo GetGeneroConteudoByIdErrorHandling(Long id) {
		return new GeneroConteudo();
	}
	
	public GeneroConteudo AddGeneroConteudoErrorHandling(GeneroConteudo generoConteudo) {
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json;
		try {
			BlackList blackList = new BlackList();
			blackList.setMethodName("AddGeneroConteudoErrorHandling");
			blackList.setJson(objectWriter.writeValueAsString(generoConteudo));
			json = objectWriter.writeValueAsString(blackList);
			this.producer.sendToBlackList(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return new GeneroConteudo();
	}
	
	public void DeleteGeneroConteudoByIdErrorHandling(Long id) {
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json;
		try {
			BlackList blackList = new BlackList();
			blackList.setMethodName("DeleteGeneroConteudoByIdErrorHandling");
			blackList.setJson(objectWriter.writeValueAsString(id));
			json = objectWriter.writeValueAsString(blackList);
			this.producer.sendToBlackList(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
