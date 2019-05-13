package edu.eci.cvds.persistence.mybatisimpl;

import java.util.List;

import com.google.inject.Inject;

import edu.eci.cvds.entities.Laboratorio;
import edu.eci.cvds.persistence.LaboratorioDAO;
import edu.eci.cvds.persistence.mybatisimpl.mappers.LaboratorioMapper;
import edu.eci.cvds.persistence.PersistenceException;

public class MyBatisLaboratorioDAO implements LaboratorioDAO {

    @Inject
    private LaboratorioMapper laboratorioMapper;

	@Override
	public  Laboratorio buscarLaboratorioPorID(Integer id) throws PersistenceException {
		try {
			return laboratorioMapper.buscarLaboratorioPorID(id);
		} catch (Exception e) {
			throw new PersistenceException("Load all persistence error", e);
		}
	}

	@Override
	public List<Laboratorio> buscarLaboratorios() throws PersistenceException {
		try {
			return laboratorioMapper.buscarLaboratorios();
		} catch (Exception e) {
			throw new PersistenceException(e.getMessage(), e);
		}
	}

	@Override
	public void registrarLaboratorio(Laboratorio laboratorio) throws PersistenceException {
		try {
			laboratorioMapper.registrarLaboratorio(laboratorio);;
		} catch (Exception e) {
			throw new PersistenceException(e.getMessage(), e);
		}
	}

	@Override
	public int maxIdLaboratorio() throws PersistenceException {
		try {
			return laboratorioMapper.maxIdLaboratorio();
		} catch (Exception e) {
			throw new PersistenceException(e.getMessage(), e);
		}
	}
	

	@Override
	public void darBajaLaboratorio(Integer id) throws PersistenceException {
		try {
            laboratorioMapper.darBajaLaboratorio(id);;
		} catch (Exception ex) {
			throw new PersistenceException(ex.getMessage(), ex);
		}
	}


}