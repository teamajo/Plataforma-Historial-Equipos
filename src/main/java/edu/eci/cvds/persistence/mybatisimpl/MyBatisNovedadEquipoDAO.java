package edu.eci.cvds.persistence.mybatisimpl;

import java.util.List;

import com.google.inject.Inject;

import edu.eci.cvds.entities.NovedadEquipo;
import edu.eci.cvds.persistence.NovedadEquipoDAO;
import edu.eci.cvds.persistence.mybatisimpl.mappers.NovedadEquipoMapper;
import edu.eci.cvds.services.ServicesException;


public class MyBatisNovedadEquipoDAO implements NovedadEquipoDAO{

	@Inject
    private NovedadEquipoMapper novedadEquipoMapper;
	
	@Override
	public List<NovedadEquipo> buscarNovedadesDeEquiposPorEquipos(Integer idEquipo) throws ServicesException {
        try {
			return novedadEquipoMapper.buscarNovedadesDeEquiposPorEquipos(idEquipo);
		} catch (Exception e) {
			throw new ServicesException("Load all persistence error", e);
		}
	}

	@Override
	public List<NovedadEquipo> buscarNovedadesDeEquipos() throws ServicesException {
        try {
			return novedadEquipoMapper.buscarNovedadesDeEquipos();
		} catch (Exception e) {
			throw new ServicesException(e.getMessage(), e);
		}
	}

	@Override
	public void registrarNovedadEquipo(NovedadEquipo novedad) throws ServicesException {
        try {
        	novedadEquipoMapper.registrarNovedadEquipo(novedad);
		} catch (Exception e) {
			throw new ServicesException(e.getMessage(), e);
		}
		
	}
	
	
}
