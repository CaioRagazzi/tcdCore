package com.tcd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import com.tcd.model.Usuario;
import com.tcd.repository.UsuarioRepository;

@Service
@ComponentScan("com.tcd.repository")
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public Iterable<Usuario> getAllUsuario(){
		return usuarioRepository.findAll();
	}
	
	public Usuario addUsuario(Usuario usuario){
		return usuarioRepository.save(usuario);
	}
}
