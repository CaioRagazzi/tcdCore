package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import model.Usuario;

@Repository("fakeDao")
public class FakeUsuarioDataAccessService implements UsuarioDao {
	
	private static List<Usuario> DB = new ArrayList<>();

	@Override
	public int insertUsuario(UUID id, Usuario usuario) {
		DB.add(new Usuario(id, usuario.getNome(), usuario.getLogin(), usuario.getEmail(), usuario.getSenha()));
		return 1;
	}

	@Override
	public List<Usuario> selectAllUsuarios() {
		return DB;
	}

	@Override
	public Optional<Usuario> selectUsuarioById(UUID id) {
		return DB.stream()
				.filter(usuario -> usuario.getId().equals(id))
				.findFirst();
	}

	@Override
	public int deleteUsuarioById(UUID id) {
		Optional<Usuario> usuarioMaybe =  selectUsuarioById(id);
		if (usuarioMaybe.isEmpty()) {
			return 0;			
		}
		DB.remove(usuarioMaybe.get());
		return 1;
	}

	@Override
	public int updateUsuarioById(UUID id, Usuario usuario) {
		return selectUsuarioById(id).map(user -> {
			int indexOfUsuarioToUpdate = DB.indexOf(user);
			if (indexOfUsuarioToUpdate >= 0) {
				DB.set(indexOfUsuarioToUpdate, new Usuario(id, usuario.getNome(), usuario.getLogin(), usuario.getEmail(), usuario.getSenha()));
				return 1;
			}
			return 0;
		}).orElse(0);
	}

}
