package edu.eci.cvds.persistence.mybatisimpl.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.entities.NovedadElemento;
import edu.eci.cvds.services.ServicesException;

public interface NovedadElementoMapper {

    public List<NovedadElemento> buscarNovedadesDeElementosPorEquipos(@Param("idEquipo") Integer idEquipo) throws ServicesException;
    public List<NovedadElemento> buscarNovedadesDeElementosPorElementos(@Param("idElemento") Integer idElemento) throws ServicesException;
    public List<NovedadElemento> buscarNovedadesDeElementos() throws ServicesException;  
    public void registrarNovedadElemento(NovedadElemento novedad) throws ServicesException;
	
}
