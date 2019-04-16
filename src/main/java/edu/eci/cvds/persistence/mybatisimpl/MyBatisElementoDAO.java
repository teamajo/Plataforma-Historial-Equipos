package edu.eci.cvds.persistence.mybatisimpl;

import java.util.List;

import com.google.inject.Inject;

import edu.eci.cvds.entities.Elemento;
import edu.eci.cvds.persistence.ElementoDAO;
import edu.eci.cvds.persistence.PersistenceException;
import edu.eci.cvds.persistence.mybatisimpl.mappers.ElementoMapper;

public class MyBatisElementoDAO implements ElementoDAO {

    @Inject
    private ElementoMapper elementoMapper;
    

	@Override
	public List<Elemento> buscarElementoPorEquipo(int idEquipo) throws PersistenceException {
		try {
			return elementoMapper.buscarElementoPorEquipo(idEquipo);
		} catch (Exception e) {
			throw new PersistenceException("Load all persistence error", e);
		}
	}
	
	@Override
	public List<Elemento> buscarElementos() throws PersistenceException {
		try {
			return elementoMapper.buscarElementos();
		} catch (Exception e) {
			throw new PersistenceException("Load all persistence error", e);
		}
    }
    
    @Override
	public void registrarElemento(Elemento elemento) throws PersistenceException {
		try {
			elementoMapper.registrarElemento(elemento);
		} catch (Exception e) {
			throw new PersistenceException("Load all persistence error", e);
		}
	}

	@Override
	public int maxId() throws PersistenceException{
		try {
			return elementoMapper.maxId();
		} catch (Exception e) {
			throw new PersistenceException("Load all persistence error", e);
		}
	}

	@Override
	public List<Elemento> elementosDisponibles() throws PersistenceException {
		try {
			return elementoMapper.elementosDisponibles();
		} catch (Exception e) {
			throw new PersistenceException("Load all persistence error", e);
		}
	}
	
	

}
