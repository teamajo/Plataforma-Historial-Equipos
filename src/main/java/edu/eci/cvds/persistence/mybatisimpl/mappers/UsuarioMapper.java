package edu.eci.cvds.persistence.mybatisimpl.mappers;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.entities.Usuario;
import edu.eci.cvds.persistence.PersistenceException;

public interface UsuarioMapper {

    public List<Usuario> buscarUsuarioPorRol(@Param("urol") String rol) throws PersistenceException;
    public List<Usuario> buscarUsuarios() throws PersistenceException;
    


}