package edu.eci.cvds.persistence;

import java.util.List;

import edu.eci.cvds.entities.Laboratorio;

public interface LaboratorioDAO {

    public List<Laboratorio> buscarLaboratorios() throws PersistenceException;
    public Laboratorio buscarLaboratorioPorID(Integer id) throws PersistenceException;
    public void registrarLaboratorio(Laboratorio laboratorio) throws PersistenceException;
	public int maxIdLaboratorio() throws PersistenceException;
	public void darBajaLaboratorio(Integer id) throws PersistenceException;

}