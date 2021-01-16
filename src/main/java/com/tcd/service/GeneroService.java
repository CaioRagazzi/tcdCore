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
import com.tcd.model.Genero;
import com.tcd.repository.GeneroRepository;
import com.tcs.Exception.GeneroNotFoundException;

@Service
@ComponentScan("com.tcd.repository")
public class GeneroService {
	
	@Autowired
	private GeneroRepository generoRepository;
	
	@Autowired
	private Producer producer;
	
	@HystrixCommand(fallbackMethod = "GetAllGeneroErrorHandling")
	public List<Genero> getAllGenero() {
		var allGenero = generoRepository.findAll();
		return allGenero;
	}
	
	@HystrixCommand(fallbackMethod = "GetGeneroByIdErrorHandling")
	public Genero getGeneroById(Long id) {
		var possibleGenero = generoRepository.findById(id);
		
		if (possibleGenero.isPresent()) {
			var genero = possibleGenero.get();
			
			return genero;
		} else {
			throw new GeneroNotFoundException();
		}
	}
	
	@HystrixCommand(fallbackMethod = "DeleteGeneroByIdErrorHandling")
	public void deleteGeneroById(Long id) {
		var possibleGenero = generoRepository.findById(id);
		
		if (possibleGenero.isPresent()) {
			var genero = possibleGenero.get();
			
			generoRepository.deleteById(genero.getId());
		} else {
			throw new GeneroNotFoundException();
		}
	}
	
	@HystrixCommand(fallbackMethod = "AddGeneroErrorHandling")
	public Genero addGenero(Genero genero) {
		var generoAdded = generoRepository.save(genero);
		
		return generoAdded;
	}
	
	@HystrixCommand(fallbackMethod = "UpdateGeneroByIdErrorHandling")
	public Genero updateGeneroById(Long id, Genero genero) {
		var possibleGenero = generoRepository.findById(id);
		
		if (possibleGenero.isPresent()) {
			var generoToUpdate = possibleGenero.get();
			
			generoToUpdate.setDescricao(genero.getDescricao());
			
			var generoUpdated = generoRepository.save(generoToUpdate);
			
			return generoUpdated;
		} else {
			throw new GeneroNotFoundException();
		}
	}
	
	public List<Genero> GetAllGeneroErrorHandling() {
		List<Genero> generoList = null;
		return generoList;
	}
	
	public Genero GetGeneroByIdErrorHandling(Long id) {
		return new Genero();
	}
	
	public void DeleteGeneroByIdErrorHandling(Long id) {
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json;
		try {
			BlackList blackList = new BlackList();
			blackList.setMethodName("DeleteGeneroByIdErrorHandling");
			blackList.setJson(objectWriter.writeValueAsString(id));
			json = objectWriter.writeValueAsString(blackList);
			this.producer.sendToBlackList(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	public Genero AddGeneroErrorHandling(Genero genero) {
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json;
		try {
			BlackList blackList = new BlackList();
			blackList.setMethodName("AddGeneroErrorHandling");
			blackList.setJson(objectWriter.writeValueAsString(genero));
			json = objectWriter.writeValueAsString(blackList);
			this.producer.sendToBlackList(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return new Genero();
	}
	
	public Genero UpdateGeneroByIdErrorHandling(Long id, Genero genero) {
		return new Genero();
	}
}
