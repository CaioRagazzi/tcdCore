package com.tcd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.tcd.model.GeneroConteudo;
import com.tcd.repository.GeneroConteudoRepository;
import com.tcs.Exception.GeneroConteudoNotFoundException;

@Service
@ComponentScan("com.tcd.repository")
public class GeneroConteudoService {

	@Autowired
	private GeneroConteudoRepository generoConteudoRepository;
	
	public List<GeneroConteudo> getAllGeneroConteudo(){
		var allGeneroConteudo = generoConteudoRepository.findAll();
		
		return allGeneroConteudo;
	}
	
	public GeneroConteudo getGeneroConteudoById(Long id) {
		var possibleGeneroConteudo = generoConteudoRepository.findById(id);
		
		if (possibleGeneroConteudo.isPresent()) {
			var generoConteudo = possibleGeneroConteudo.get();
			
			return generoConteudo;
		} else {
			throw new GeneroConteudoNotFoundException();
		}
	}
	
	public GeneroConteudo addGeneroConteudo(GeneroConteudo generoConteudo) {
		var generoConteudoAdded = generoConteudoRepository.save(generoConteudo);
		
		return generoConteudoAdded;
	}
	
	public void deleteGeneroConteudoById(Long id) {
		var possibleGeneroConteudo = generoConteudoRepository.findById(id);
		
		if (possibleGeneroConteudo.isPresent()) {
			var generoConteudo = possibleGeneroConteudo.get();
			
			generoConteudoRepository.deleteById(generoConteudo.getId());
		} else {
			throw new GeneroConteudoNotFoundException();
		}
	}
}
