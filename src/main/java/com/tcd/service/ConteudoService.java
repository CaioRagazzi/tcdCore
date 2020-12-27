package com.tcd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.tcd.model.Conteudo;
import com.tcd.repository.ConteudoRepository;
import com.tcs.Exception.ConteudoNotFoundException;

@Service
@ComponentScan("com.tcd.repository")
public class ConteudoService {
	
	@Autowired
	private ConteudoRepository conteudoRepository;
	
	public List<Conteudo> getAllConteudo() {
		var allConteudo = conteudoRepository.findAll();
		return allConteudo;
	}
	
	public Conteudo getConteudoById(Long id) {
		var possibleConteudo = conteudoRepository.findById(id);
		
		if (possibleConteudo.isPresent()) {
			var conteudo = possibleConteudo.get();
			
			return conteudo;
		} else {
			throw new ConteudoNotFoundException();
		}
	}
	
	public void deleteConteudoById(Long id) {
		var possibleConteudo = conteudoRepository.findById(id);
		
		if (possibleConteudo.isPresent()) {
			var conteudo = possibleConteudo.get();
			
			conteudoRepository.deleteById(conteudo.getId());
		} else {
			throw new ConteudoNotFoundException();
		}
	}
	
	public Conteudo addConteudo(Conteudo conteudo) {
		var conteudoAdded = conteudoRepository.save(conteudo);
		
		return conteudoAdded;
	}
	
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

}
