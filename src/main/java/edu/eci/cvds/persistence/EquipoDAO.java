package edu.eci.cvds.persistence;

import java.util.List;

import edu.eci.cvds.entities.Equipo;

public interface EquipoDAO{

    public List<Equipo> buscarEquipoPorLab(String lab) throws PersistenceException;
    public List<Equipo> buscarEquipos() throws PersistenceException;
    public void registrarEquipo(Equipo equipo) throws PersistenceException;
	public int maxIdEquipo() throws PersistenceException;


} 