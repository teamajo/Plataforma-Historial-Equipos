package edu.eci.cvds.persistence.mybatisimpl;

import java.util.List;

import com.google.inject.Inject;

import edu.eci.cvds.entities.Usuario;
import edu.eci.cvds.persistence.UsuarioDAO;
import edu.eci.cvds.persistence.PersistenceException;
import edu.eci.cvds.persistence.mybatisimpl.mappers.UsuarioMapper;

public class MyBatisUsuarioDAO implements UsuarioDAO {

    @Inject
    private UsuarioMapper usuarioMapper;
    

	@Override
	public List<Usuario> buscarUsuarioPorRol(String rol) throws PersistenceException {
		try {
			return usuarioMapper.buscarUsuarioPorRol(rol);
		} catch (Exception e) {
			throw new PersistenceException("Load all persistence error", e);
		}
	}
	
	@Override
	public List<Usuario> buscarUsuarios() throws PersistenceException {
		try {
			return usuarioMapper.buscarUsuarios();
		} catch (Exception e) {
			throw new PersistenceException("Load all persistence error", e);
		}
    }

}
