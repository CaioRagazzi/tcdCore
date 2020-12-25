package com.tcd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.tcd.model.Conteudo;
import com.tcd.repository.ConteudoRepository;

@Service
@ComponentScan("com.tcd.repository")
public class ConteudoService {
	
	@Autowired
	private ConteudoRepository conteudoRepository;
	
	public List<Conteudo> getAllConteudo() {
		var allConteudo = conteudoRepository.findAll();
		return allConteudo;
	}

}
