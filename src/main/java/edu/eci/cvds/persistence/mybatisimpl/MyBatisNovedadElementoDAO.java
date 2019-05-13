package edu.eci.cvds.persistence.mybatisimpl;

import java.util.List;

import com.google.inject.Inject;

import edu.eci.cvds.entities.NovedadElemento;
import edu.eci.cvds.persistence.NovedadElementoDAO;
import edu.eci.cvds.persistence.PersistenceException;
import edu.eci.cvds.persistence.mybatisimpl.mappers.NovedadElementoMapper;
import edu.eci.cvds.persistence.mybatisimpl.mappers.NovedadEquipoMapper;


public class MyBatisNovedadElementoDAO implements NovedadElementoDAO{

	@Inject
    private NovedadElementoMapper novedadElementoMapper;
	
	@Override
	public List<NovedadElemento> buscarNovedadesDeElementosPorEquipos(Integer idEquipo) throws PersistenceException {
        try {
			return novedadElementoMapper.buscarNovedadesDeElementosPorEquipos(idEquipo);
		} catch (Exception e) {
			throw new PersistenceException("Load all persistence error", e);
		}
	}
	
	@Override
	public List<NovedadElemento> buscarNovedadesDeElementosPorElementos(Integer idElemento) throws PersistenceException {
        try {
			return novedadElementoMapper.buscarNovedadesDeElementosPorElementos(idElemento);
		} catch (Exception e) {
			throw new PersistenceException("Load all persistence error", e);
		}
	}

	@Override
	public List<NovedadElemento> buscarNovedadesDeElementos() throws PersistenceException {
        try {
			return novedadElementoMapper.buscarNovedadesDeElementos();
		} catch (Exception e) {
			throw new PersistenceException(e.getMessage(), e);
		}
	}

	@Override
	public void registrarNovedadElemento(NovedadElemento novedad) throws PersistenceException {
        try {
        	novedadElementoMapper.registrarNovedadElemento(novedad);
		} catch (Exception e) {
			throw new PersistenceException(e.getMessage(), e);
		}
		
	}
	
	
}
