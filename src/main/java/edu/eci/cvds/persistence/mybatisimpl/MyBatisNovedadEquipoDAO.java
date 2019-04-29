package edu.eci.cvds.persistence.mybatisimpl;

import java.util.List;

import com.google.inject.Inject;

import edu.eci.cvds.entities.NovedadEquipo;
import edu.eci.cvds.persistence.NovedadEquipoDAO;
import edu.eci.cvds.persistence.PersistenceException;
import edu.eci.cvds.persistence.mybatisimpl.mappers.NovedadEquipoMapper;


public class MyBatisNovedadEquipoDAO implements NovedadEquipoDAO{

	@Inject
    private NovedadEquipoMapper novedadEquipoMapper;
	
	@Override
	public List<NovedadEquipo> buscarNovedadesDeEquiposPorEquipos(int idEquipo) throws PersistenceException {
        try {
			return novedadEquipoMapper.buscarNovedadesDeEquiposPorEquipos(idEquipo);
		} catch (Exception e) {
			throw new PersistenceException("Load all persistence error", e);
		}
	}

	@Override
	public List<NovedadEquipo> buscarNovedadesDeEquipos() throws PersistenceException {
        try {
			return novedadEquipoMapper.buscarNovedadesDeEquipos();
		} catch (Exception e) {
			throw new PersistenceException(e.getMessage(), e);
		}
	}

	@Override
	public void registrarNovedadEquipo(NovedadEquipo novedad) throws PersistenceException {
        try {
        	novedadEquipoMapper.registrarNovedadEquipo(novedad);
		} catch (Exception e) {
			throw new PersistenceException(e.getMessage(), e);
		}
		
	}
	
	
}
