package edu.eci.cvds.persistence.mybatisimpl.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.entities.NovedadEquipo;
import edu.eci.cvds.services.ServicesException;

public interface NovedadEquipoMapper {

    public List<NovedadEquipo> buscarNovedadesDeEquiposPorEquipos(@Param("idEquipo") Integer idEquipo) throws ServicesException;
    public List<NovedadEquipo> buscarNovedadesDeEquipos() throws ServicesException;  
    public void registrarNovedadEquipo(NovedadEquipo novedad) throws ServicesException;
	
}
