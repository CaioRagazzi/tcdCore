package com.tcd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.tcd.model.FilmesAssistidos;
import com.tcd.repository.FilmesAssistidosRepository;
import com.tcs.Exception.FilmesAssistidosNotFoundException;

@Service
@ComponentScan("com.tcd.repository")
public class FilmesAssistidosService {
	
	@Autowired
	private FilmesAssistidosRepository filmesAssistidosRepository;
	
	public List<FilmesAssistidos> getAllFilmesAssistidos(){
		var allFilmesAssistidos = filmesAssistidosRepository.findAll();
		
		return allFilmesAssistidos;
	}
	
	public FilmesAssistidos getFilmesAssistidosById(Long id) {
		var possibleFilmesAssistidos = filmesAssistidosRepository.findById(id);
		
		if (possibleFilmesAssistidos.isPresent()) {
			var filmesAssistidos = possibleFilmesAssistidos.get();
			
			return filmesAssistidos;
		} else {
			throw new FilmesAssistidosNotFoundException();
		}
	}
	
	public void deleteFilmesAssistidosById(Long id) {
		var possibleFilmesAssistidos = filmesAssistidosRepository.findById(id);
		
		if (possibleFilmesAssistidos.isPresent()) {
			var filmesAssistidos = possibleFilmesAssistidos.get();
			
			filmesAssistidosRepository.deleteById(filmesAssistidos.getId());
		} else {
			throw new FilmesAssistidosNotFoundException();
		}
	}
	
	public FilmesAssistidos addFilmesAssistidos(FilmesAssistidos filmesAssistidos) {
		var filmesAssistidosAdded = filmesAssistidosRepository.save(filmesAssistidos);
		
		return filmesAssistidosAdded;
	}

}
