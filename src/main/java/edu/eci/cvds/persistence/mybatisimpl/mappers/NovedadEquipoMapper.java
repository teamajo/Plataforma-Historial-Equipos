package edu.eci.cvds.persistence.mybatisimpl.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.entities.NovedadEquipo;
import edu.eci.cvds.persistence.PersistenceException;

public interface NovedadEquipoMapper {

    public List<NovedadEquipo> buscarNovedadesDeEquiposPorEquipos(@Param("idEquipo") int idEquipo) throws PersistenceException;
    public List<NovedadEquipo> buscarNovedadesDeEquipos() throws PersistenceException;  
    public void registrarNovedadEquipo(NovedadEquipo novedad) throws PersistenceException;
	
}
