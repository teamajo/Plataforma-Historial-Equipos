package edu.eci.cvds.persistence;

import java.util.List;


import edu.eci.cvds.entities.Equipo;

public interface EquipoDAO{

    public List<Equipo> buscarEquipoPorLab(String lab) throws PersistenceException;
    public List<Equipo> buscarEquipos() throws PersistenceException;
    public void registrarEquipo(Equipo equipo) throws PersistenceException;
	public Integer maxIdEquipo() throws PersistenceException;
	public Equipo buscarEquipoPorId(Integer idEquipo) throws PersistenceException;
    public void darBajaEquipo(Integer id) throws PersistenceException;
    public void asociarEquipoAlab(Integer idEquipo,Integer id)throws PersistenceException ;
    public void desAsociarEquipoAlab(Integer idEquipo) throws PersistenceException;
    public void buscarEquiposDisponibles() throws PersistenceException;
    
    


} 