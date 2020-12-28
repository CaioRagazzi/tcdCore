package com.tcd.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tcd.model.Classificacao;
import com.tcd.service.ClassificacaoService;

@RefreshScope
@RequestMapping("api/v1/classificacao")
@RestController
@ComponentScan("com.tcd.service")
public class ClassificacaoController {

	@Autowired()
	private ClassificacaoService classificacaoService;
	
	@Value("${message:default}")
	private String message;
	
	@GetMapping
	public Iterable<Classificacao> getAllClassificacao(){
		System.out.println(message);
		return classificacaoService.getAllClassificacao();
	}
	
	@GetMapping(path = "{id}")
	public Classificacao getClassificacaoById(@PathVariable("id") Long id){
		return classificacaoService.getClassificacaoById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Classificacao createClassificacao(@RequestBody @Valid Classificacao classificacao) throws InterruptedException{
		return classificacaoService.addClassificacao(classificacao);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(path = "{id}")
	public void deleteClassificacaoById(@PathVariable("id") Long id){
		classificacaoService.deleteClassificacaoById(id);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping(path = "{id}")
	public void updateClassificacaoById(@PathVariable("id") Long id, @RequestBody Classificacao classificacao) {
		classificacaoService.updateClassificacaoById(id, classificacao);
	}
}
