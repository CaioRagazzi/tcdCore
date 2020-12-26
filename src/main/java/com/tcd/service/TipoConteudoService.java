package com.tcd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.tcd.repository.TipoConteudoRepository;

@Service
@ComponentScan("com.tcd.repository")
public class TipoConteudoService {
	
	@Autowired
	private TipoConteudoRepository conteudoRepository;

}
