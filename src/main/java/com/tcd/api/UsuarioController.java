package com.tcd.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
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

import com.tcd.model.Usuario;
import com.tcd.service.UsuarioService;

import io.swagger.annotations.ApiOperation;

@RequestMapping("api/v1/usuario")
@RestController
@ComponentScan("com.tcd.service")
@EnableCircuitBreaker
public class UsuarioController {

	@Autowired()
	private UsuarioService usuarioService;
	
	@ApiOperation(value = "Lista todos os usuarios")
	@GetMapping
	public Iterable<Usuario> getAllUsuario(){
		return usuarioService.getAllUsuario();
	}
	
	@ApiOperation(value = "Lista usuario por id")
	@GetMapping(path = "{id}")
	public Usuario getUsuarioById(@PathVariable("id") Long id){
		return usuarioService.getUsuarioById(id);
	}
	
	@ApiOperation(value = "Cria um novo usuario")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario createUsuario(@RequestBody @Valid Usuario usuario){
		return usuarioService.addUsuario(usuario);
	}
	
	@ApiOperation(value = "Deleta usuario por id")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(path = "{id}")
	public void deleteUsuarioById(@PathVariable("id") Long id){
		usuarioService.deleteUsuario(id);
	}
	
	@ApiOperation(value = "Atualiza usuario por id")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping(path = "{id}")
	public void updateUsuarioById(@PathVariable("id") Long id, @RequestBody Usuario usuario) {
		usuarioService.updateUsuario(id, usuario);
	}
}
