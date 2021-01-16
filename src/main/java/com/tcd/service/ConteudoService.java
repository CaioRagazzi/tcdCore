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
import com.tcd.model.Conteudo;
import com.tcd.repository.ConteudoRepository;
import com.tcs.Exception.ConteudoNotFoundException;

@Service
@ComponentScan("com.tcd.repository")
public class ConteudoService {
	
	@Autowired
	private ConteudoRepository conteudoRepository;
	
	@Autowired
	private Producer producer;
	
	@HystrixCommand(fallbackMethod = "GetAllErrorHandling")
	public List<Conteudo> getAllConteudo() {
		var allConteudo = conteudoRepository.findAll();
		return allConteudo;
	}
	
	@HystrixCommand(fallbackMethod = "GetConteudoByIdErrorHandling")
	public Conteudo getConteudoById(Long id) {
		var possibleConteudo = conteudoRepository.findById(id);
		
		if (possibleConteudo.isPresent()) {
			var conteudo = possibleConteudo.get();
			
			return conteudo;
		} else {
			throw new ConteudoNotFoundException();
		}
	}
	
	@HystrixCommand(fallbackMethod = "DeleteConteudoByIdErrorHandling")
	public void deleteConteudoById(Long id) {
		var possibleConteudo = conteudoRepository.findById(id);
		
		if (possibleConteudo.isPresent()) {
			var conteudo = possibleConteudo.get();
			
			conteudoRepository.deleteById(conteudo.getId());
		} else {
			throw new ConteudoNotFoundException();
		}
	}
	
	@HystrixCommand(fallbackMethod = "AddConteudoErrorHandling")
	public Conteudo addConteudo(Conteudo conteudo) {
		var conteudoAdded = conteudoRepository.save(conteudo);
		
		return conteudoAdded;
	}
	
	@HystrixCommand(fallbackMethod = "UpdateConteudoErrorHandling")
	public Conteudo updateConteudo(Long id, Conteudo conteudo) {
		var possibleConteudo = conteudoRepository.findById(id);
		
		if (possibleConteudo.isPresent()) {
			var conteudoToUpdate = possibleConteudo.get();
			
			conteudoToUpdate.setDataLancamento(conteudo.getDataLancamento());
			conteudoToUpdate.setDescricao(conteudo.getDescricao());
			conteudoToUpdate.setIdTipoConteudo(conteudo.getIdTipoConteudo());
			conteudoToUpdate.setQuantidadeVisualizacao(conteudo.getQuantidadeVisualizacao());
			conteudoToUpdate.setTitulo(conteudo.getTitulo());
			
			var conteudoUpdated = conteudoRepository.save(conteudoToUpdate);
			
			return conteudoUpdated;
		} else {
			throw new ConteudoNotFoundException();
		}
	}
	
	public List<Conteudo> GetAllErrorHandling() {
		List<Conteudo>  conteudoList = null;
		return conteudoList;
	}
	
	public Conteudo GetConteudoByIdErrorHandling(Long id) {
		return new Conteudo();
	}
	
	public void DeleteConteudoByIdErrorHandling(Long id) {
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json;
		try {
			BlackList blackList = new BlackList();
			blackList.setMethodName("DeleteConteudoByIdErrorHandling");
			blackList.setJson(objectWriter.writeValueAsString(id));
			json = objectWriter.writeValueAsString(blackList);
			this.producer.sendToBlackList(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	public Conteudo AddConteudoErrorHandling(Conteudo conteudo) {
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json;
		try {
			BlackList blackList = new BlackList();
			blackList.setMethodName("AddConteudoErrorHandling");
			blackList.setJson(objectWriter.writeValueAsString(conteudo));
			json = objectWriter.writeValueAsString(blackList);
			this.producer.sendToBlackList(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return new Conteudo();
	}
	
	public Conteudo UpdateConteudoErrorHandling(Long id, Conteudo conteudo) {
		return new Conteudo();
	}

}
