package edu.eci.cvds.persistence.mybatisimpl;

import java.util.List;

import com.google.inject.Inject;

import edu.eci.cvds.entities.Laboratorio;
import edu.eci.cvds.managedbeans.LaboratorioBean;
import edu.eci.cvds.persistence.LaboratorioDAO;
import edu.eci.cvds.persistence.mybatisimpl.mappers.LaboratorioMapper;
import edu.eci.cvds.services.ServicesException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MyBatisLaboratorioDAO implements LaboratorioDAO {

    @Inject
    private LaboratorioMapper laboratorioMapper;

	@Override
	public  Laboratorio buscarLaboratorioPorID(Integer id) throws ServicesException {
            Laboratorio lab;
            try {
                lab= laboratorioMapper.buscarLaboratorioPorID(id);
                if (lab ==null){
                    throw new ServicesException(ServicesException.NO_EXISTE_LAB);
                } 
		} catch (Exception e) {
			throw new ServicesException(ServicesException.ERROR_BASE_DATOS);
		}
            return lab;
	}

	@Override
	public List<Laboratorio> buscarLaboratorios() throws ServicesException {
		try {
			return laboratorioMapper.buscarLaboratorios();
		} catch (Exception e) {
			throw new ServicesException(ServicesException.ERROR_BASE_DATOS);
		}
	}

	@Override
	public void registrarLaboratorio(Laboratorio laboratorio) throws ServicesException {
		try {
			laboratorioMapper.registrarLaboratorio(laboratorio);
		} catch (Exception e) {                       
			throw new ServicesException(ServicesException.VALORES_INVALIDOS);
		}
	}

	@Override
	public int maxIdLaboratorio() throws ServicesException {
		try {
			return laboratorioMapper.maxIdLaboratorio();
		} catch (Exception e) {
			throw new ServicesException(ServicesException.ERROR_BASE_DATOS);
		}
	}
	

	@Override
	public void darBajaLaboratorio(Integer id) throws ServicesException {
            Laboratorio lab;
            try {
                lab= laboratorioMapper.buscarLaboratorioPorID(id);
                if (lab ==null){
                    throw new ServicesException(ServicesException.NO_EXISTE_LAB);
                } 
                laboratorioMapper.darBajaLaboratorio(id);;
		} catch (Exception ex) {
			throw new ServicesException(ServicesException.ERROR_BASE_DATOS);
		}
	}


}