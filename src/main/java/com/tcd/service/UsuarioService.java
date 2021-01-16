package com.tcd.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.tcd.configuration.Producer;
import com.tcd.model.BlackList;
import com.tcd.model.Usuario;
import com.tcd.repository.UsuarioRepository;
import com.tcs.Exception.UsuarioNotFoundException;

@Service
@ComponentScan("com.tcd.repository")
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private Producer producer;

	@HystrixCommand(fallbackMethod = "GetAllUsuarioErrorHandling")
	public Iterable<Usuario> getAllUsuario(){
		return usuarioRepository.findAll();
	}
	
	public Iterable<Usuario> GetAllUsuarioErrorHandling(){
		List<Usuario> usuarioList = null;
		return usuarioList;
	}
	
	@HystrixCommand(fallbackMethod = "GetUsuarioByIdErrorHandling")
	public Usuario getUsuarioById(Long id){
		var usuario = usuarioRepository.findById(id);
		
		if (usuario.isPresent()) {
			var usuarioGet = usuario.get();
			return usuarioGet;
		} else {
			throw new UsuarioNotFoundException();
		}
	}
	
	public Usuario GetUsuarioByIdErrorHandling(Long id){
		return new Usuario();
	}
	
	@HystrixCommand(fallbackMethod = "AddUsuarioErrorHandling")
	public Usuario addUsuario(Usuario usuario){
		return usuarioRepository.save(usuario);
	}
	
	public Usuario AddUsuarioErrorHandling(Usuario usuario){
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json;
		try {
			BlackList blackList = new BlackList();
			blackList.setMethodName("AddUsuarioErrorHandling");
			blackList.setJson(objectWriter.writeValueAsString(usuario));
			json = objectWriter.writeValueAsString(blackList);
			this.producer.sendToBlackList(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return new Usuario();
	}
	
	@HystrixCommand(fallbackMethod = "DeleteUsuarioErrorHandling")
	public void deleteUsuario(Long id){
		var usuario = usuarioRepository.findById(id);
		
		if (usuario.isPresent()) {
			var usuarioGet = usuario.get();
			usuarioRepository.deleteById(usuarioGet.getId());
		} else {
			throw new UsuarioNotFoundException();
		}
	}
	
	public void DeleteUsuarioErrorHandling(Long id){
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json;
		try {
			BlackList blackList = new BlackList();
			blackList.setMethodName("DeleteUsuarioErrorHandling");
			blackList.setJson(objectWriter.writeValueAsString(id));
			json = objectWriter.writeValueAsString(blackList);
			this.producer.sendToBlackList(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	@HystrixCommand(fallbackMethod = "UpdateUsuarioErrorHandling")
	public Usuario updateUsuario(Long id, Usuario usuario) {
		Optional<Usuario> usuarioToUpdate = usuarioRepository.findById(id);
		
		if (usuarioToUpdate.isPresent()) {
			Usuario usuarioUpdate = usuarioToUpdate.get();
			usuarioUpdate.setEmail(usuario.getEmail());
			usuarioUpdate.setLogin(usuario.getLogin());
			usuarioUpdate.setNome(usuario.getNome());
			usuarioUpdate.setSenha(usuario.getSenha());
			
			Usuario usuarioUpdated = usuarioRepository.save(usuarioUpdate);
			
			return usuarioUpdated;
		} else {
			throw new UsuarioNotFoundException();
		}
	}
	
	public Usuario UpdateUsuarioErrorHandling(Long id, Usuario usuario) {
		return new Usuario();
	}
}
