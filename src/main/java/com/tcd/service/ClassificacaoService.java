package com.tcd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.tcd.model.Classificacao;
import com.tcd.repository.ClassificacaoRepository;
import com.tcs.Exception.ClassificacaoNotFoundException;

@Service
@ComponentScan("com.tcd.repository")
public class ClassificacaoService {
	
	@Autowired
	private ClassificacaoRepository classificacaoRepository;

	
	public List<Classificacao> getAllClassificacao() {
		var allClassificacao = classificacaoRepository.findAll();
		
		return allClassificacao;
	}
	
	public Classificacao getClassificacaoById(Long id) {
		var possibleClassificacao = classificacaoRepository.findById(id);
		
		if (possibleClassificacao.isPresent()) {
			var classificacao = possibleClassificacao.get();
			return classificacao;
		} else {
			throw new ClassificacaoNotFoundException();
		}
	}
	
	public void deleteClassificacaoById(Long id) {
		var possibleClassificacao = classificacaoRepository.findById(id);
		
		if (possibleClassificacao.isPresent()) {
			var classificacao = possibleClassificacao.get();
			classificacaoRepository.deleteById(classificacao.getId());
		} else {
			throw new ClassificacaoNotFoundException();
		}
	}
	
	public Classificacao updateClassificacaoById(Long id, Classificacao classificacaoParam) {
		var possibleClassificacao = classificacaoRepository.findById(id);
		
		if (possibleClassificacao.isPresent()) {
			var classificacao = possibleClassificacao.get();

			classificacao.setDescricao(classificacaoParam.getDescricao());
			
			var returningClassificacao = classificacaoRepository.save(classificacao);
			
			return returningClassificacao;			
		} else {
			throw new ClassificacaoNotFoundException();
		}
	}
	
	@HystrixCommand(fallbackMethod = "ErrorHandling")
	public Classificacao addClassificacao(Classificacao classificacao) throws InterruptedException {
		var returningClassificacao = classificacaoRepository.save(classificacao);
		return returningClassificacao;
	}
	
	public Classificacao ErrorHandling(Classificacao classificacao) {
		var fakeClassificacao = new Classificacao();
		return fakeClassificacao;
	}
}
