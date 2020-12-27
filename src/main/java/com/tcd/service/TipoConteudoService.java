package com.tcd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.tcd.model.TipoConteudo;
import com.tcd.repository.TipoConteudoRepository;
import com.tcs.Exception.TipoConteudoNotFoundException;

@Service
@ComponentScan("com.tcd.repository")
public class TipoConteudoService {
	
	@Autowired
	private TipoConteudoRepository tipoConteudoRepository;
	
	public List<TipoConteudo> getAllTipoConteudo() {
		var allTipoConteudo = tipoConteudoRepository.findAll();
		return allTipoConteudo;
	}
	
	public TipoConteudo getTipoConteudoById(Long id) {
		var possibleTipoConteudo = tipoConteudoRepository.findById(id);
		
		if (possibleTipoConteudo.isPresent()) {
			var tipoConteudo = possibleTipoConteudo.get();
			
			return tipoConteudo;
		} else {
			throw new TipoConteudoNotFoundException();
		}
	}
	
	public void deleteGeneroById(Long id) {
		var possibleTipoConteudo = tipoConteudoRepository.findById(id);
		
		if (possibleTipoConteudo.isPresent()) {
			var tipoConteudo = possibleTipoConteudo.get();
			
			tipoConteudoRepository.deleteById(tipoConteudo.getId());
		} else {
			throw new TipoConteudoNotFoundException();
		}
	}
	
	public TipoConteudo addTipoConteudo(TipoConteudo tipoConteudo) {
		var tipoConteudoAdded = tipoConteudoRepository.save(tipoConteudo);
		
		return tipoConteudoAdded;
	}
	
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

}
