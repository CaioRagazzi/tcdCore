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
import com.tcd.model.TipoConteudo;
import com.tcd.repository.TipoConteudoRepository;
import com.tcs.Exception.TipoConteudoNotFoundException;

@Service
@ComponentScan("com.tcd.repository")
public class TipoConteudoService {
	
	@Autowired
	private Producer producer;
	
	@Autowired
	private TipoConteudoRepository tipoConteudoRepository;
	
	@HystrixCommand(fallbackMethod = "GetAllTipoConteudoErrorHandling")
	public List<TipoConteudo> getAllTipoConteudo() {
		var allTipoConteudo = tipoConteudoRepository.findAll();
		return allTipoConteudo;
	}
	
	public List<TipoConteudo> GetAllTipoConteudoErrorHandling() {
		List<TipoConteudo> tipoConteudoList = null;
		
		return tipoConteudoList;
	}
	
	@HystrixCommand(fallbackMethod = "GetTipoConteudoByIdErrorHandling")
	public TipoConteudo getTipoConteudoById(Long id) {
		var possibleTipoConteudo = tipoConteudoRepository.findById(id);
		
		if (possibleTipoConteudo.isPresent()) {
			var tipoConteudo = possibleTipoConteudo.get();
			
			return tipoConteudo;
		} else {
			throw new TipoConteudoNotFoundException();
		}
	}
	
	public TipoConteudo GetTipoConteudoByIdErrorHandling(Long id) {
		return new TipoConteudo();
	}
	
	@HystrixCommand(fallbackMethod = "DeleteGeneroByIdErrorHandling")
	public void deleteGeneroById(Long id) {
		var possibleTipoConteudo = tipoConteudoRepository.findById(id);
		
		if (possibleTipoConteudo.isPresent()) {
			var tipoConteudo = possibleTipoConteudo.get();
			
			tipoConteudoRepository.deleteById(tipoConteudo.getId());
		} else {
			throw new TipoConteudoNotFoundException();
		}
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
	
	@HystrixCommand(fallbackMethod = "AddTipoConteudoErrorHandling")
	public TipoConteudo addTipoConteudo(TipoConteudo tipoConteudo) {
		var tipoConteudoAdded = tipoConteudoRepository.save(tipoConteudo);
		
		return tipoConteudoAdded;
	}
	
	public TipoConteudo AddTipoConteudoErrorHandling(TipoConteudo tipoConteudo) {
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json;
		try {
			BlackList blackList = new BlackList();
			blackList.setMethodName("AddTipoConteudoErrorHandling");
			blackList.setJson(objectWriter.writeValueAsString(tipoConteudo));
			json = objectWriter.writeValueAsString(blackList);
			this.producer.sendToBlackList(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return new TipoConteudo();
	}
	
	@HystrixCommand(fallbackMethod = "UpdateGeneroByIdErrorHandling")
	public TipoConteudo updateGeneroById(Long id, TipoConteudo tipoConteudo) {
		var possibleTipoConteudo = tipoConteudoRepository.findById(id);
		
		if (possibleTipoConteudo.isPresent()) {
			var tipoConteudoToUpdate = possibleTipoConteudo.get();
			
			tipoConteudoToUpdate.setDescricao(tipoConteudo.getDescricao());
			
			var tipoConteudoUpdated = tipoConteudoRepository.save(tipoConteudoToUpdate);
			
			return tipoConteudoUpdated;
		} else {
			throw new TipoConteudoNotFoundException();
		}
	}
	
	public TipoConteudo UpdateGeneroByIdErrorHandling(Long id, TipoConteudo tipoConteudo) {
		return new TipoConteudo();
	}

}
