package edu.eci.cvds.persistence.mybatisimpl.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.entities.NovedadElemento;
import edu.eci.cvds.persistence.PersistenceException;

public interface NovedadElementoMapper {

    public List<NovedadElemento> buscarNovedadesDeElementosPorEquipos(@Param("idEquipo") Integer idEquipo) throws PersistenceException;
    public List<NovedadElemento> buscarNovedadesDeElementosPorElementos(@Param("idElemento") Integer idElemento) throws PersistenceException;
    public List<NovedadElemento> buscarNovedadesDeElementos() throws PersistenceException;  
    public void registrarNovedadElemento(NovedadElemento novedad) throws PersistenceException;
	
}
