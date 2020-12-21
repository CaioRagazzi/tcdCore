package dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import model.Usuario;

public interface UsuarioDao {
	
	int insertUsuario(UUID id, Usuario usuario);
	
	default int insertUsuario(Usuario usuario) {
		UUID id = UUID.randomUUID();
		return insertUsuario(id, usuario);
	}
	
	List<Usuario> selectAllUsuarios();
	
	Optional<Usuario> selectUsuarioById(UUID id);
	
	int deleteUsuarioById(UUID id);
	
	int updateUsuarioById(UUID id, Usuario usuario);
}
