package edu.eci.cvds.persistence.mybatisimpl;

import java.util.List;

import com.google.inject.Inject;

import edu.eci.cvds.entities.NovedadElemento;
import edu.eci.cvds.persistence.NovedadElementoDAO;
import edu.eci.cvds.persistence.mybatisimpl.mappers.NovedadElementoMapper;
import edu.eci.cvds.services.ServicesException;


public class MyBatisNovedadElementoDAO implements NovedadElementoDAO{

	@Inject
    private NovedadElementoMapper novedadElementoMapper;
	
	@Override
	public List<NovedadElemento> buscarNovedadesDeElementosPorEquipos(Integer idEquipo) throws ServicesException {
        try {
			return novedadElementoMapper.buscarNovedadesDeElementosPorEquipos(idEquipo);
		} catch (Exception e) {
			throw new ServicesException("Load all persistence error", e);
		}
	}
	
	@Override
	public List<NovedadElemento> buscarNovedadesDeElementosPorElementos(Integer idElemento) throws ServicesException {
        try {
			return novedadElementoMapper.buscarNovedadesDeElementosPorElementos(idElemento);
		} catch (Exception e) {
			throw new ServicesException("Load all persistence error", e);
		}
	}

	@Override
	public List<NovedadElemento> buscarNovedadesDeElementos() throws ServicesException {
        try {
			return novedadElementoMapper.buscarNovedadesDeElementos();
		} catch (Exception e) {
			throw new ServicesException(e.getMessage(), e);
		}
	}

	@Override
	public void registrarNovedadElemento(NovedadElemento novedad) throws ServicesException {
        try {
        	novedadElementoMapper.registrarNovedadElemento(novedad);
		} catch (Exception e) {
			throw new ServicesException(e.getMessage(), e);
		}
		
	}
	
	
}
