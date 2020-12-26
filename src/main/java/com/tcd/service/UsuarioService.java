package com.tcd.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.tcd.model.Usuario;
import com.tcd.repository.UsuarioRepository;
import com.tcs.Exception.UsuarioNotFoundException;

@Service
@ComponentScan("com.tcd.repository")
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public Iterable<Usuario> getAllUsuario(){
		return usuarioRepository.findAll();
	}
	
	public Usuario getUsuarioById(Long id){
		var usuario = usuarioRepository.findById(id);
		
		if (usuario.isPresent()) {
			var usuarioGet = usuario.get();
			return usuarioGet;
		} else {
			throw new UsuarioNotFoundException();
		}
	}
	
	public Usuario addUsuario(Usuario usuario){
		return usuarioRepository.save(usuario);
	}
	
	public void deleteUsuario(Long id){
		var usuario = usuarioRepository.findById(id);
		
		if (usuario.isPresent()) {
			var usuarioGet = usuario.get();
			usuarioRepository.deleteById(usuarioGet.getId());
		} else {
			throw new UsuarioNotFoundException();
		}
	}
	
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
}
