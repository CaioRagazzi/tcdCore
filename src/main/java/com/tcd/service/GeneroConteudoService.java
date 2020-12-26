package com.tcd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.tcd.repository.GeneroConteudoRepository;

@Service
@ComponentScan("com.tcd.repository")
public class GeneroConteudoService {

	@Autowired
	private GeneroConteudoRepository conteudoRepository;
}
