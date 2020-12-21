package service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import dao.UsuarioDao;
import model.Usuario;

@Service
@ComponentScan(basePackageClasses = UsuarioDao.class)
public class UsuarioService {
	
	private final UsuarioDao usuarioDao;
	
	@Autowired
	public UsuarioService(@Qualifier("fakeDao") UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}
	
	public int addUsuario(Usuario usuario) {
		return usuarioDao.insertUsuario(usuario);
	}
	
	public List<Usuario> getAllUsuario(){
		return usuarioDao.selectAllUsuarios();
	}
	
	public Optional<Usuario> getUsuarioById(UUID id) {
		return usuarioDao.selectUsuarioById(id);
	}
	
	public int deleteUsuario(UUID id) {
		return usuarioDao.deleteUsuarioById(id);
	}
	
	public int updateUsuario(UUID id, Usuario usuario) {
		return usuarioDao.updateUsuarioById(id, usuario);
	}

}
