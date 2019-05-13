package edu.eci.cvds.persistence.mybatisimpl.mappers;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.entities.Laboratorio;
import edu.eci.cvds.persistence.PersistenceException;


public interface LaboratorioMapper {

    public Laboratorio buscarLaboratorioPorID(@Param("id") Integer id) throws PersistenceException;
    public List<Laboratorio> buscarLaboratorios() throws PersistenceException;
    
    public void registrarLaboratorio(Laboratorio laboratorio) throws PersistenceException;
	public int maxIdLaboratorio() throws PersistenceException;
	public void darBajaLaboratorio(@Param("id") Integer id) throws PersistenceException;



}