package edu.eci.cvds.services.impl;

import java.util.List;

import com.google.inject.Inject;

import edu.eci.cvds.entities.Elemento;
import edu.eci.cvds.persistence.ElementoDAO;
import edu.eci.cvds.persistence.PersistenceException;
import edu.eci.cvds.services.LaboratorioServices;
import edu.eci.cvds.services.ServicesException;

public class LaboratorioServicesImpl implements LaboratorioServices {

    @Inject
    private ElementoDAO elementoDAO;
    
    @Override
    public List<Elemento> buscarElementoPorEquipo(int idEquipo) throws ServicesException{
        try {
			return elementoDAO.buscarElementoPorEquipo(idEquipo);
		} catch (PersistenceException ex) {
			throw new ServicesException("Error listando elementos de equipo:" + ex.getLocalizedMessage(), ex);
		}


    }

    @Override
    public List<Elemento> buscarElementos() throws ServicesException{
        try {
			return elementoDAO.buscarElementos();
		} catch (PersistenceException ex) {
			throw new ServicesException("Error listando elementos:" + ex.getLocalizedMessage(), ex);
		}

    }


}