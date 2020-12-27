package com.tcd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.tcd.model.Genero;
import com.tcd.repository.GeneroRepository;
import com.tcs.Exception.GeneroNotFoundException;

@Service
@ComponentScan("com.tcd.repository")
public class GeneroService {
	
	@Autowired
	private GeneroRepository generoRepository;
	
	public List<Genero> getAllGenero() {
		var allGenero = generoRepository.findAll();
		return allGenero;
	}
	
	public Genero getGeneroById(Long id) {
		var possibleGenero = generoRepository.findById(id);
		
		if (possibleGenero.isPresent()) {
			var genero = possibleGenero.get();
			
			return genero;
		} else {
			throw new GeneroNotFoundException();
		}
	}
	
	public void deleteGeneroById(Long id) {
		var possibleGenero = generoRepository.findById(id);
		
		if (possibleGenero.isPresent()) {
			var genero = possibleGenero.get();
			
			generoRepository.deleteById(genero.getId());
		} else {
			throw new GeneroNotFoundException();
		}
	}
	
	public Genero addGenero(Genero genero) {
		var generoAdded = generoRepository.save(genero);
		
		return generoAdded;
	}
	
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
}
