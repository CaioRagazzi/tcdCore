package com.tcd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.tcd.model.ConteudoCurtido;
import com.tcd.repository.ConteudoCurtidoRepository;
import com.tcs.Exception.ConteudoCurtidoNotFoundException;

@Service
@ComponentScan("com.tcd.repository")
public class ConteudoCurtidoService {
	
	@Autowired
	private ConteudoCurtidoRepository conteudoCurtidoRepository;
	
	public List<ConteudoCurtido> getAllConteudoCurtido(){
		var allConteudoCurtido = conteudoCurtidoRepository.findAll();
		
		return allConteudoCurtido;
	}
	
	public ConteudoCurtido getConteudoCurtidoById(Long id) {
		var possibleConteudoCurtido = conteudoCurtidoRepository.findById(id);
		
		if (possibleConteudoCurtido.isPresent()) {
			var conteudoCurtido = possibleConteudoCurtido.get();
			return conteudoCurtido;
		} else {
			throw new ConteudoCurtidoNotFoundException();
		}
	}
	
	public void deleteConteudoCurtidoById(Long id) {
		var possibleConteudoCurtido = conteudoCurtidoRepository.findById(id);
		
		if (possibleConteudoCurtido.isPresent()) {
			var conteudoCurtido = possibleConteudoCurtido.get();
			conteudoCurtidoRepository.deleteById(conteudoCurtido.getId());
		} else {
			throw new ConteudoCurtidoNotFoundException();
		}
	}
	
	public ConteudoCurtido addConteudoCurtido(ConteudoCurtido conteudoCurtidoParam) {
		var createdConteudoCurtido = conteudoCurtidoRepository.save(conteudoCurtidoParam);
		
		return createdConteudoCurtido;
	}

}
