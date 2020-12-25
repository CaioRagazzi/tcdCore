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
	
	public Usuario getUsuarioById(int id){
		var usuario = usuarioRepository.findById(id);
		
		if (!usuario.isEmpty()) {
			var usuarioGet = usuario.get();
			return usuarioGet;
		} else {
			throw new UsuarioNotFoundException();
		}
	}
	
	public Usuario addUsuario(Usuario usuario){
		return usuarioRepository.save(usuario);
	}
	
	public void deleteUsuario(int id){
		var usuario = usuarioRepository.findById(id);
		
		if (!usuario.isEmpty()) {
			var usuarioGet = usuario.get();
			usuarioRepository.deleteById(usuarioGet.getId());
		} else {
			throw new UsuarioNotFoundException();
		}
	}
	
	public Usuario updateUsuario(int id, Usuario usuario) {
		Optional<Usuario> usuarioToUpdate = usuarioRepository.findById(id);
		
		if (!usuarioToUpdate.isEmpty()) {
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
