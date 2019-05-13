package edu.eci.cvds.persistence.mybatisimpl.mappers;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.entities.Laboratorio;
import edu.eci.cvds.services.ServicesException;



public interface LaboratorioMapper {

    public Laboratorio buscarLaboratorioPorID(@Param("id") Integer id) throws ServicesException;
    public List<Laboratorio> buscarLaboratorios() throws ServicesException;
    
    public void registrarLaboratorio(Laboratorio laboratorio) throws ServicesException;
	public int maxIdLaboratorio() throws ServicesException;
	public void darBajaLaboratorio(@Param("id") Integer id) throws ServicesException;



}