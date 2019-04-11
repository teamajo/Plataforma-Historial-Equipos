package edu.eci.cvds.persistence;

import java.util.List;

import edu.eci.cvds.entities.Usuario;

public interface UsuarioDAO{

    public List<Usuario> buscarUsuarioPorRol(String rol) throws PersistenceException;
    public List<Usuario> buscarUsuarios() throws PersistenceException;



} 