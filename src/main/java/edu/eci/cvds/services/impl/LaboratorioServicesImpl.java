 package edu.eci.cvds.services.impl;

import java.util.List;

import com.google.inject.Inject;

import org.mybatis.guice.transactional.Transactional;

import edu.eci.cvds.entities.Elemento;
import edu.eci.cvds.entities.Equipo;
import edu.eci.cvds.entities.Laboratorio;
import edu.eci.cvds.entities.NovedadElemento;
import edu.eci.cvds.entities.NovedadEquipo;
import edu.eci.cvds.persistence.ElementoDAO;
import edu.eci.cvds.persistence.EquipoDAO;
import edu.eci.cvds.persistence.LaboratorioDAO;
import edu.eci.cvds.persistence.NovedadElementoDAO;
import edu.eci.cvds.persistence.NovedadEquipoDAO;
import edu.eci.cvds.persistence.PersistenceException;
import edu.eci.cvds.services.LaboratorioServices;
import edu.eci.cvds.services.ServicesException;
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
    private LaboratorioDAO laboratorioDAO;
    
    @Inject
    private NovedadElementoDAO novedadElementoDAO;
    
    @Override
    public List<Elemento> buscarElementoPorEquipo(Integer idEquipo) throws ServicesException{
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
            NovedadElemento novedad = new NovedadElemento(null,"novedad elmento registrar",elemento.getIdEquipo(),maxIdElemento(),fechaActual,"se registro el elemento","admin");
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
      Integer idEquipo = maxIdEquipo();
      Integer idElemento= 0;
      java.util.Date fechaActual = new java.util.Date();
      List<Elemento> elementos=equipo.getComponets(); 
      for (Elemento e:elementos){
        if(e.getId()==null){
          registrarElemento(e);
          idElemento= maxIdElemento();
        }else{
          idElemento=e.getId();

            
        }
        asociarEquipo( idEquipo,buscarElemento(idElemento));  // No deberia enviar el Tipo
      }
        NovedadEquipo novedadeq = new NovedadEquipo(null,"novedad equipo registar",idEquipo,fechaActual,"se registro el equipo","admin");
        novedadEquipoDAO.registrarNovedadEquipo(novedadeq);
      } catch (PersistenceException ex) {
          throw new ServicesException("Error listando equipos:" + ex.getLocalizedMessage(), ex);
      }
	  }
	  
        
    /// El tipo se deberia sacar del ID , que pasa si el ID es correcto y el tipo no ? al llamar al metodo ???
        // Siguiendo los comentarios de Registrar deberia llegar el elemento asociarEquipo(idEquipo,e) { 
  @Override
  public void asociarEquipo(Integer idEquipo,Elemento el) throws ServicesException {
    try {
      Boolean flag = false;
      // tipo = servicios
      java.util.Date fechaActual = new java.util.Date();      
      Equipo equi = buscarEquipoPorId(idEquipo);
      List<Elemento> elementos = equi.getComponets();
      NovedadEquipo novedad = new NovedadEquipo(null,"novedad equipo asociacion", idEquipo,fechaActual,"se Asocio el equipo","admin");
      NovedadElemento novedadel = new NovedadElemento(null,"novedad elmento asociar",idEquipo,el.getId(),fechaActual,"sPe asocio el elemento","admin");
      
      if(equi.isActivo() && el.isActivo()){
        for (Elemento e:elementos){ 
          if(e!=null && e.getId()!=null ){
            if (e.getTipo() == el.getTipo()){
              desAsociarElemento(e.getId());                                                   
            }
          }
        }
        elementoDAO.asociarEquipo(idEquipo, el.getId());          
        novedadElementoDAO.registrarNovedadElemento(novedadel); 
        // De esto habra un test.
        novedadEquipoDAO.registrarNovedadEquipo(novedad);
      }
        
    } catch (PersistenceException ex) {
        Logger.getLogger(LaboratorioServicesImpl.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
	@Override
  public void desAsociarElemento(Integer id) throws ServicesException {
    try {
       java.util.Date fechaActual = new java.util.Date();
       Integer idEquipo = buscarElemento(id).getIdEquipo();
       NovedadEquipo novedad = new NovedadEquipo(null,"novedad equipo desasociacion", idEquipo,fechaActual,"se desasocio el equipo","admin");
       elementoDAO.desAsociarElemento(id);
       novedadEquipoDAO.registrarNovedadEquipo(novedad);
    } catch (PersistenceException ex) {
      Logger.getLogger(LaboratorioServicesImpl.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

	@Override
	public Integer maxIdEquipo() throws ServicesException {
		try {
			return equipoDAO.maxIdEquipo();
	 } catch (PersistenceException ex) {
		 throw new ServicesException("Error listando elementos:" + ex.getLocalizedMessage(), ex);
	 }
	}

	@Override
	public Integer maxIdElemento() throws ServicesException {
		try {
			return elementoDAO.maxIdElemento();
	 } catch (PersistenceException ex) {
		 throw new ServicesException("Error listando elementos:" + ex.getLocalizedMessage(), ex);
	 }
	}

	@Override
	public List<NovedadEquipo> buscarNovedadesPorEquipo(Integer idEquipo) throws ServicesException {
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
	public List<NovedadElemento> buscarNovedadesDeElementosPorEquipos(Integer idEquipo) throws ServicesException {
	    try {
			return novedadElementoDAO.buscarNovedadesDeElementosPorEquipos(idEquipo);
		} catch (PersistenceException ex) {
			Logger.getLogger(LaboratorioServicesImpl.class.getName()).log(Level.SEVERE, null, ex);throw new ServicesException("Error listando novedades de equipos:" + ex.getLocalizedMessage(), ex);
		}
	}

	@Override
	public List<NovedadElemento> buscarNovedadesDeElementosPorElementos(Integer idElemento) throws ServicesException {
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
	public void darBajaEquipo(Integer id) throws ServicesException {
	try {
      //java.util.Date fechaActual = new java.util.Date();
      //NovedadEquipo novedad = new NovedadEquipo(null,"novedad equipo darBajaEquipo", id,fechaActual,"se dio de baja el equipo","admin");
      //novedadEquipoDAO.registrarNovedadEquipo(novedad);
            equipoDAO.darBajaEquipo(id);
        } catch (PersistenceException ex) {
                throw new ServicesException("Error registrando novedades:" + ex.getLocalizedMessage(), ex);
        }
	}

	@Override
	public void darBajaElemento(Integer id) throws ServicesException {
		try {
        //java.util.Date fechaActual = new java.util.Date();
        //NovedadElemento novedadel = new NovedadElemento(null,"novedad elmento darBajaElemento",buscarElemento(id).getIdEquipo(),id,fechaActual,"se asocio el elemento","admin");
        //novedadElementoDAO.registrarNovedadElemento(novedadel); 
        if (buscarElemento(id).getIdEquipo()==null){
          elementoDAO.darBajaElemento(id);
        }

		} catch (PersistenceException ex) {
			throw new ServicesException("Error registrando novedades:" + ex.getLocalizedMessage(), ex);
		}
  }
  @Override
	public List<Laboratorio> buscarLaboratorios() throws ServicesException {
		try {
        return laboratorioDAO.buscarLaboratorios();

		} catch (PersistenceException ex) {
			throw new ServicesException("Error registrando novedades:" + ex.getLocalizedMessage(), ex);
		}
  }
  
  @Override
	public Laboratorio buscarLaboratorioPorID(Integer id) throws ServicesException {
		try {
        return laboratorioDAO.buscarLaboratorioPorID(id);

		} catch (PersistenceException ex) {
			throw new ServicesException("Error registrando novedades:" + ex.getLocalizedMessage(), ex);
		}
  }
  
  @Override
	public Integer maxIdLaboratorio() throws ServicesException {
		try {
			return laboratorioDAO.maxIdLaboratorio();
	 } catch (PersistenceException ex) {
		 throw new ServicesException("Error listando elementos:" + ex.getLocalizedMessage(), ex);
	 }
  }
  
  @Override
    public void registrarLaboratorio(Laboratorio laboratorio) throws ServicesException{
    try {
      java.util.Date fechaActual = new java.util.Date();
      laboratorio.setFechaCreacion(fechaActual);
      laboratorioDAO.registrarLaboratorio(laboratorio);
        
      }catch (PersistenceException ex) {
      throw new ServicesException("Error listando elementos:" + ex.getLocalizedMessage(), ex);
      }
  }
  @Override
    public void darBajaLaboratorio(Integer id) throws ServicesException {
    try { 
      List<Equipo> equiposLab = buscarLaboratorioPorID(id).getEquipos();
      for (Equipo e : equiposLab){
        desAsociarEquipoAlab(e.getId());
      }
      laboratorioDAO.darBajaLaboratorio(id);

        } catch (PersistenceException ex) {
                throw new ServicesException("Error registrando novedades:" + ex.getLocalizedMessage(), ex);
        }
  }
  @Override
    public void asociarEquipoAlab(Integer idEquipo, Integer id) throws ServicesException {
    try { 
        Equipo eq = buscarEquipoPorId(idEquipo);
        if (eq.isActivo()){
            if (eq.getlab() != null){
                desAsociarEquipoAlab(idEquipo);
            }
            equipoDAO.asociarEquipoAlab(idEquipo,id);
        }
      

        } catch (PersistenceException ex) {
                throw new ServicesException("Error registrando novedades:" + ex.getLocalizedMessage(), ex);
        }
  }

  @Override
    public void desAsociarEquipoAlab(Integer idEquipo) throws ServicesException {
    try { 
      equipoDAO.desAsociarEquipoAlab(idEquipo);

    } catch (PersistenceException ex) {
            throw new ServicesException("Error registrando novedades:" + ex.getLocalizedMessage(), ex);
    }
  }

  @Override
    public void buscarEquiposDisponibles() throws ServicesException {
    try { 
        equipoDAO.buscarEquiposDisponibles();

    } catch (PersistenceException ex) {
            throw new ServicesException("Error registrando novedades:" + ex.getLocalizedMessage(), ex);
    }
  }

}