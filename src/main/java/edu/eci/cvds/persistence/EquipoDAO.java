package edu.eci.cvds.persistence;

import java.util.List;


import edu.eci.cvds.entities.Equipo;

public interface EquipoDAO{

    public List<Equipo> buscarEquipoPorLab(String lab) throws PersistenceException;
    public List<Equipo> buscarEquipos() throws PersistenceException;
    public void registrarEquipo(Equipo equipo) throws PersistenceException;
	public int maxIdEquipo() throws PersistenceException;
	public Equipo buscarEquipoPorId(int idEquipo) throws PersistenceException;
    public void darBajaEquipo(int id) throws PersistenceException;
    public void asociarEquipoAlab(int idEquipo,int id)throws PersistenceException ;
    public void desAsociarEquipoAlab(int idEquipo) throws PersistenceException;
    
    


} 