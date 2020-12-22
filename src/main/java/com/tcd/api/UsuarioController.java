package com.tcd.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcd.model.Usuario;
import com.tcd.service.UsuarioService;

@RequestMapping("api/v1/usuario")
@RestController
@ComponentScan("com.tcd.service")
public class UsuarioController {

	@Autowired()
	private UsuarioService usuarioService;
	
	@GetMapping
	public Iterable<Usuario> getAllUsuario(){
		return usuarioService.getAllUsuario();
	}
	
	@PostMapping
	public Usuario createUsuario(@RequestBody @Valid Usuario usuario){
		return usuarioService.addUsuario(usuario);
	}
}
