 package edu.eci.cvds.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;

import org.mybatis.guice.transactional.Transactional;

import edu.eci.cvds.entities.Elemento;
import edu.eci.cvds.entities.Equipo;
import edu.eci.cvds.entities.Tipo;
import edu.eci.cvds.entities.NovedadElemento;
import edu.eci.cvds.entities.NovedadEquipo;
import edu.eci.cvds.persistence.ElementoDAO;
import edu.eci.cvds.persistence.EquipoDAO;
import edu.eci.cvds.persistence.NovedadElementoDAO;
import edu.eci.cvds.persistence.NovedadEquipoDAO;
import edu.eci.cvds.persistence.PersistenceException;
import edu.eci.cvds.services.LaboratorioServices;
import edu.eci.cvds.services.ServicesException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LaboratorioServicesImpl implements LaboratorioServices {

    @Inject
    private ElementoDAO elementoDAO;
    
    @Inject
    private EquipoDAO equipoDAO; 
    
    @Inject
    private NovedadEquipoDAO novedadEquipoDAO;
    
    @Inject
    private NovedadElementoDAO novedadElementoDAO;
    
    @Override
    public List<Elemento> buscarElementoPorEquipo(int idEquipo) throws ServicesException{
        try {
			return equipoDAO.buscarEquipoPorId(idEquipo).getComponets();
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
    public Elemento buscarElemento(Integer id) throws ServicesException{
        try {
                  return elementoDAO.buscarElemento(id);
        } catch (PersistenceException ex) {
                throw new ServicesException("Error listando elementos:" + ex.getLocalizedMessage(), ex);
        }

    }

    @Override
    public void registrarElemento(Elemento elemento) throws ServicesException{
        try {
            elementoDAO.registrarElemento(elemento);
            java.util.Date fechaActual = new java.util.Date();
            NovedadElemento novedad = new NovedadElemento(null,"novedad elmentpo registrar",elemento.getIdEquipo(),maxIdElemento(),fechaActual,"se registro el elemento","admin");
            novedadElementoDAO.registrarNovedadElemento(novedad);
            
	} catch (PersistenceException ex) {
            throw new ServicesException("Error listando elementos:" + ex.getLocalizedMessage(), ex);
	}

    }
  @Override
  public List<Elemento> elementosDisponiblesPorTipo(String tipo) throws ServicesException{
    try {
      return elementoDAO.elementosDisponiblesPorTipo(tipo);
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
	  public Equipo buscarEquipoPorId(Integer idEquipo) throws ServicesException{
	      try {
				return equipoDAO.buscarEquipoPorId(idEquipo);
			} catch (PersistenceException ex) {
				throw new ServicesException("Error listando equipos:" + ex.getLocalizedMessage(), ex);
			}
	
	  }
	
	@Override
        @Transactional
	public void registrarEquipo(Equipo equipo) throws ServicesException{
            try {
                equipoDAO.registrarEquipo(equipo);
                int idEquipo = maxIdEquipo();
                List<Elemento> elementos = new ArrayList<Elemento>();
                int idElemento= 0;
                elementos.add(equipo.getTorre());
                elementos.add(equipo.getMouse());
                elementos.add(equipo.getPantalla());
                elementos.add(equipo.getTeclado());
                java.util.Date fechaActual = new java.util.Date();

                for (Elemento e:elementos){
                    if(e.getId()==null){
                        registrarElemento(e);
                        idElemento= maxIdElemento();
                    }else{
                        idElemento=e.getId();
                    }
                    asociarEquipo( idEquipo,idElemento,e.getTipo());
                }
                NovedadEquipo novedadeq = new NovedadEquipo(null,"novedad equipo registar",idEquipo,fechaActual,"se registro el equipo","admin");
                novedadEquipoDAO.registrarNovedadEquipo(novedadeq);
            } catch (PersistenceException ex) {
                 throw new ServicesException("Error listando equipos:" + ex.getLocalizedMessage(), ex);
            }
	  }
	  
  @Override
  public void asociarEquipo(int idEquipo,int id,Tipo tipo) throws ServicesException {
    try {
            Boolean flag = false;
            java.util.Date fechaActual = new java.util.Date();            
            List<Elemento> elementos = buscarEquipoPorId(idEquipo).getComponets();
            NovedadEquipo novedad = new NovedadEquipo(null,"novedad equipo asociacion", idEquipo,fechaActual,"se Asocio el equipo","admin");
            NovedadElemento novedadel = new NovedadElemento(null,"novedad elmentpo asociar",idEquipo,id,fechaActual,"se asocio el elemento","admin");
            novedadElementoDAO.registrarNovedadElemento(novedadel); 
            for (Elemento e:elementos){ 
                
                if(e!=null && e.getId()!=null ){
                    if (e.getTipo() == tipo){
                        desAsociarElemento(e.getId());                                                   
                    }
                }
            }
           elementoDAO.asociarEquipo(idEquipo, id);          
           novedadEquipoDAO.registrarNovedadEquipo(novedad);
    } catch (PersistenceException ex) {
        Logger.getLogger(LaboratorioServicesImpl.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
	@Override
  public void desAsociarElemento(int id) throws ServicesException {
    try {
       java.util.Date fechaActual = new java.util.Date();
       NovedadEquipo novedad = new NovedadEquipo(null,"novedad equipo desasociacion", id,fechaActual,"se desasocio el equipo","admin");
       novedadEquipoDAO.registrarNovedadEquipo(novedad);
       elementoDAO.desAsociarElemento(id);
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

	@Override
	public List<NovedadEquipo> buscarNovedadesPorEquipo(int idEquipo) throws ServicesException {
        try {
			return novedadEquipoDAO.buscarNovedadesDeEquiposPorEquipos(idEquipo);
		} catch (PersistenceException ex) {
			throw new ServicesException("Error listando novedades de equipo:" + ex.getLocalizedMessage(), ex);
		}
	}

	@Override
	public List<NovedadEquipo> buscarNovedadesDeEquipos() throws ServicesException {
        try {
			return novedadEquipoDAO.buscarNovedadesDeEquipos();
		} catch (PersistenceException ex) {
			throw new ServicesException("Error listando novedades:" + ex.getLocalizedMessage(), ex);
		}
	}

	@Override
	public void registrarNovedadEquipo(NovedadEquipo novedad) throws ServicesException {
        try {
        	novedadEquipoDAO.registrarNovedadEquipo(novedad);
		} catch (PersistenceException ex) {
			throw new ServicesException("Error registrando novedades:" + ex.getLocalizedMessage(), ex);
		}
		
	}

	@Override
	public List<NovedadElemento> buscarNovedadesDeElementosPorEquipos(int idEquipo) throws ServicesException {
	    try {
			return novedadElementoDAO.buscarNovedadesDeElementosPorEquipos(idEquipo);
		} catch (PersistenceException ex) {
			throw new ServicesException("Error listando novedades de equipos:" + ex.getLocalizedMessage(), ex);
		}
	}

	@Override
	public List<NovedadElemento> buscarNovedadesDeElementosPorElementos(int idElemento) throws ServicesException {
	    try {
			return novedadElementoDAO.buscarNovedadesDeElementosPorElementos(idElemento);
		} catch (PersistenceException ex) {
			throw new ServicesException("Error listando novedades de equipos:" + ex.getLocalizedMessage(), ex);
		}
	}
	
	@Override
	public List<NovedadElemento> buscarNovedadesDeElementos() throws ServicesException {
	    try {
			return novedadElementoDAO.buscarNovedadesDeElementos();
		} catch (PersistenceException ex) {
			throw new ServicesException("Error listando novedades:" + ex.getLocalizedMessage(), ex);
		}
	}
	
	@Override
	public void registrarNovedadElemento(NovedadElemento novedad) throws ServicesException {
	    try {
	    	novedadElementoDAO.registrarNovedadElemento(novedad);
		} catch (PersistenceException ex) {
			throw new ServicesException("Error registrando novedades:" + ex.getLocalizedMessage(), ex);
		}
		
	}

	@Override
	public void darBajaEquipo(int id) throws ServicesException {
		try {
			equipoDAO.darBajaEquipo(id);
		} catch (PersistenceException ex) {
			throw new ServicesException("Error registrando novedades:" + ex.getLocalizedMessage(), ex);
		}
	}

	@Override
	public void darBajaElemento(int id) throws ServicesException {
		try {
			
			elementoDAO.darBajaElemento(id);
			
			
		} catch (PersistenceException ex) {
			throw new ServicesException("Error registrando novedades:" + ex.getLocalizedMessage(), ex);
		}
	}
	



}