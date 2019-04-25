package edu.eci.cvds.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;

import edu.eci.cvds.entities.Elemento;
import edu.eci.cvds.entities.Equipo;
import edu.eci.cvds.persistence.ElementoDAO;
import edu.eci.cvds.persistence.EquipoDAO;
import edu.eci.cvds.persistence.PersistenceException;
import edu.eci.cvds.services.LaboratorioServices;
import edu.eci.cvds.services.ServicesException;

public class LaboratorioServicesImpl implements LaboratorioServices {

    @Inject
    private ElementoDAO elementoDAO;
    
    @Inject
    private EquipoDAO equipoDAO;
    
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

    @Override
    public void registrarElemento(Elemento elemento) throws ServicesException{
        try {
			elementoDAO.registrarElemento(elemento);
		} catch (PersistenceException ex) {
			throw new ServicesException("Error listando elementos:" + ex.getLocalizedMessage(), ex);
		}

    }
  @Override
  public List<Elemento> elementosDisponibles() throws ServicesException{
    try {
      return elementoDAO.elementosDisponibles();
    } catch (PersistenceException ex) {
      throw new ServicesException("Error listando elementos:" + ex.getLocalizedMessage(), ex);
    }
  }
	  @Override
	  public List<Equipo> buscarEquipoPorLab(String lab) throws ServicesException{
	      try {
				return equipoDAO.buscarEquipoPorLab(lab);
			} catch (PersistenceException ex) {
				throw new ServicesException("Error listando equipos de lab:" + ex.getLocalizedMessage(), ex);
			}
	
	
	  }
	
	  @Override
	  public List<Equipo> buscarEquipos() throws ServicesException{
	      try {
				return equipoDAO.buscarEquipos();
			} catch (PersistenceException ex) {
				throw new ServicesException("Error listando equipos:" + ex.getLocalizedMessage(), ex);
			}
	
	  }
	
	  @Override
	  public void registrarEquipo(Equipo equipo) throws ServicesException{
	      try {
					equipoDAO.registrarEquipo(equipo);
					int idEquipo = maxIdEquipo();
					List<Elemento> elementos = new ArrayList<Elemento>();
					elementos.add(equipo.getTorre());
					elementos.add(equipo.getMouse());
					elementos.add(equipo.getPantalla());
					elementos.add(equipo.getTeclado());
					for (int i = 0; i< elementos.size();i++){
						registrarElemento(elementos.get(i));
						int idElemento= maxIdElemento();
						asociarEquipo( idEquipo,idElemento);
				}
				
			} catch (PersistenceException ex) {
				throw new ServicesException("Error listando equipos:" + ex.getLocalizedMessage(), ex);
			}
	
	  }
	  
  @Override
  public void asociarEquipo(int idEquipo,int id) throws ServicesException {
    try {
       elementoDAO.asociarEquipo(idEquipo,id);
    } catch (PersistenceException ex) {
      throw new ServicesException("Error listando elementos:" + ex.getLocalizedMessage(), ex);
    }
  }

	@Override
	public int maxIdEquipo() throws ServicesException {
		try {
			return equipoDAO.maxIdEquipo();
	 } catch (PersistenceException ex) {
		 throw new ServicesException("Error listando elementos:" + ex.getLocalizedMessage(), ex);
	 }
	}

	@Override
	public int maxIdElemento() throws ServicesException {
		try {
			return elementoDAO.maxIdElemento();
	 } catch (PersistenceException ex) {
		 throw new ServicesException("Error listando elementos:" + ex.getLocalizedMessage(), ex);
	 }
	}

	



}