package dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import model.Usuario;

public class UsuarioDataAccessService implements UsuarioDao {

	@Override
	public int insertUsuario(UUID id, Usuario usuario) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Usuario> selectAllUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Usuario> selectUsuarioById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteUsuarioById(UUID id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateUsuarioById(UUID id, Usuario usuario) {
		// TODO Auto-generated method stub
		return 0;
	}

}
