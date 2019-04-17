package edu.eci.cvds.persistence;

import java.util.List;

import edu.eci.cvds.entities.Elemento;

public interface ElementoDAO{

    public List<Elemento> buscarElementoPorEquipo(int idEquipo) throws PersistenceException;
    public List<Elemento> buscarElementos() throws PersistenceException;
    public void registrarElemento(Elemento elemento) throws PersistenceException;
	public List<Elemento> elementosDisponibles() throws PersistenceException;
	public void adiccionarEquipo(int idEquipo,int id)throws PersistenceException ;


} 