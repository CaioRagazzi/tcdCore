package api;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.lang.NonNull;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.Usuario;
import service.UsuarioService;

@RequestMapping("api/v1/usuario")
@RestController
@ComponentScan(basePackageClasses = UsuarioService.class)
public class UsuarioController {

	private final UsuarioService usuarioService;

	@Autowired()
	public UsuarioController(UsuarioService usuarioService) {
		super();
		this.usuarioService = usuarioService;
	}
	
	@PostMapping
	public void addUsuario(@Valid @NonNull @RequestBody Usuario usuario) {
		usuarioService.addUsuario(usuario);
	}
	
	@GetMapping
	public List<Usuario> getAllUsuario(){
		return usuarioService.getAllUsuario();
	}
	
	@GetMapping(path = "{id}")
	public Usuario getUsuarioById(@PathVariable("id") UUID id) {
		return usuarioService.getUsuarioById(id).orElse(null);
	}
	
	@DeleteMapping(path = "{id}")
	public void deleteUsuarioById(@PathVariable("id") UUID id) {
		usuarioService.deleteUsuario(id);
	}
	
	@PutMapping(path = "{id}")
	public void updateUsuarioById(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody Usuario usuario) {
		usuarioService.updateUsuario(id, usuario);
	}
}
