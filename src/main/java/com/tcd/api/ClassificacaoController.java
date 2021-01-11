package com.tcd.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcd.model.Classificacao;
import com.tcd.service.ClassificacaoService;

import io.swagger.annotations.ApiOperation;

@RequestMapping("api/v1/classificacao")
@RestController
@ComponentScan("com.tcd.service")
public class ClassificacaoController {

	@Autowired()
	private ClassificacaoService classificacaoService;
	
	@ApiOperation(value = "Lista todas as classificações")
	@GetMapping
	public Iterable<Classificacao> getAllClassificacao(){
		return classificacaoService.getAllClassificacao();
	}
	
	@ApiOperation(value = "Lista classificação por id")
	@GetMapping(path = "{id}")
	public Classificacao getClassificacaoById(@PathVariable("id") Long id){
		return classificacaoService.getClassificacaoById(id);
	}
}
