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
    public List<Elemento> buscarElementos() throws ServicesException{
        return elementoDAO.buscarElementos();	
    }
    @Override
    public Elemento buscarElemento(Integer id) throws ServicesException{
        return elementoDAO.buscarElemento(id);
    }

    @Override
    public void registrarElemento(Elemento elemento) throws ServicesException{
            elementoDAO.registrarElemento(elemento);
            java.util.Date fechaActual = new java.util.Date();
            NovedadElemento novedad = new NovedadElemento(null,"novedad elmento registrar",elemento.getIdEquipo(),maxIdElemento(),fechaActual,"se registro el elemento","admin");
            novedadElementoDAO.registrarNovedadElemento(novedad);
    }
    @Override
    public List<Elemento> elementosDisponiblesPorTipo(String tipo) throws ServicesException{
      return elementoDAO.elementosDisponiblesPorTipo(tipo);
    } 
	
    @Override
    public List<Elemento> elementosDisponibles() throws ServicesException{
        return elementoDAO.elementosDisponibles();   
    }
    @Override
    public List<Equipo> buscarEquipoPorLab(Integer lab) throws ServicesException{
        return equipoDAO.buscarEquipoPorLab(lab);
    }
	
    @Override
    public List<Equipo> buscarEquipos() throws ServicesException{
        return equipoDAO.buscarEquipos();
    }
    @Override
    public Equipo buscarEquipoPorId(Integer idEquipo) throws ServicesException{
        return equipoDAO.buscarEquipoPorId(idEquipo);
    }
	
	@Override
  @Transactional
    public void registrarEquipo(Equipo equipo) throws ServicesException{
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
      // ?? Esto que ??
      NovedadEquipo novedad = new NovedadEquipo(null,"novedad equipo asociacion", idEquipo,fechaActual,"se Asocio el equipo","admin");
      NovedadElemento novedadel = new NovedadElemento(null,"novedad elmento asociar",idEquipo,el.getId(),fechaActual,"sPe asocio el elemento","admin");
      Elemento e=null;
      if(equi.isActivo() && el.isActivo()){        
        switch(el.getTipo()){
            case mouse:
                 e=equi.getMouse();
                 break;                 
            case teclado:
                 e=equi.getTeclado();
                 break;
            case pantalla:
                 e=equi.getPantalla();
                 break;
            case torre:
                 e=equi.getTorre();
                 break;                   
         }
        
        if(e!=null && e.getId()!=null){            
            desAsociarElemento(e.getId());
        }
        elementoDAO.asociarEquipo(idEquipo, el.getId());          
        novedadElementoDAO.registrarNovedadElemento(novedadel); 
        // De esto habra un test.
        novedadEquipoDAO.registrarNovedadEquipo(novedad);
      }
        
    } catch (ServicesException ex) {
        Logger.getLogger(LaboratorioServicesImpl.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
	@Override
    public void desAsociarElemento(Integer id) throws ServicesException {
       java.util.Date fechaActual = new java.util.Date();
       Integer idEquipo = buscarElemento(id).getIdEquipo();
       NovedadEquipo novedad = new NovedadEquipo(null,"novedad equipo desasociacion", idEquipo,fechaActual,"se desasocio el equipo","admin");
       elementoDAO.desAsociarElemento(id);
       novedadEquipoDAO.registrarNovedadEquipo(novedad);
    
    }

    @Override
    public Integer maxIdEquipo() throws ServicesException {
            try {
                    return equipoDAO.maxIdEquipo();
     } catch (ServicesException ex) {
             throw new ServicesException("Error listando elementos:" + ex.getLocalizedMessage(), ex);
     }
    }

    @Override
    public Integer maxIdElemento() throws ServicesException {
            try {
                    return elementoDAO.maxIdElemento();
     } catch (ServicesException ex) {
             throw new ServicesException("Error listando elementos:" + ex.getLocalizedMessage(), ex);
     }
    }

    @Override
    public List<NovedadEquipo> buscarNovedadesPorEquipo(Integer idEquipo) throws ServicesException {
    try {
                    return novedadEquipoDAO.buscarNovedadesDeEquiposPorEquipos(idEquipo);
            } catch (ServicesException ex) {
                    throw new ServicesException("No se encontro el equipo" );
            }
    }

    @Override
    public List<NovedadEquipo> buscarNovedadesDeEquipos() throws ServicesException {
    try {
                    return novedadEquipoDAO.buscarNovedadesDeEquipos();
            } catch (ServicesException ex) {
                    throw new ServicesException("Error listando novedades:" + ex.getLocalizedMessage(), ex);
            }
    }

    @Override
    public void registrarNovedadEquipo(NovedadEquipo novedad) throws ServicesException {
    try {
            novedadEquipoDAO.registrarNovedadEquipo(novedad);
            } catch (ServicesException ex) {
                    throw new ServicesException("Error registrando novedades:" + ex.getLocalizedMessage(), ex);
            }

    }

    @Override
    public List<NovedadElemento> buscarNovedadesDeElementosPorEquipos(Integer idEquipo) throws ServicesException {
        try {
                    return novedadElementoDAO.buscarNovedadesDeElementosPorEquipos(idEquipo);
            } catch (ServicesException ex) {
                    Logger.getLogger(LaboratorioServicesImpl.class.getName()).log(Level.SEVERE, null, ex);throw new ServicesException("Error listando novedades de equipos:" + ex.getLocalizedMessage(), ex);
            }
    }

    @Override
    public List<NovedadElemento> buscarNovedadesDeElementosPorElementos(Integer idElemento) throws ServicesException {
        try {
                    return novedadElementoDAO.buscarNovedadesDeElementosPorElementos(idElemento);
            } catch (ServicesException ex) {
                    throw new ServicesException("Error listando novedades de equipos:" + ex.getLocalizedMessage(), ex);
            }
    }

    @Override
    public List<NovedadElemento> buscarNovedadesDeElementos() throws ServicesException {
        try {
                    return novedadElementoDAO.buscarNovedadesDeElementos();
            } catch (ServicesException ex) {
                    throw new ServicesException("Error listando novedades:" + ex.getLocalizedMessage(), ex);
            }
    }

	@Override
	public void registrarNovedadElemento(NovedadElemento novedad) throws ServicesException {
	    try {
	    	novedadElementoDAO.registrarNovedadElemento(novedad);
		} catch (ServicesException ex) {
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
        } catch (ServicesException ex) {
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

		} catch (ServicesException ex) {
			throw new ServicesException("Error registrando novedades:" + ex.getLocalizedMessage(), ex);
		}
  }
  @Override
	public List<Laboratorio> buscarLaboratorios() throws ServicesException {
		try {
        return laboratorioDAO.buscarLaboratorios();

		} catch (ServicesException ex) {
			throw new ServicesException("Error registrando novedades:" + ex.getLocalizedMessage(), ex);
		}
  }
  
  @Override
	public Laboratorio buscarLaboratorioPorID(Integer id) throws ServicesException {
		try {
        return laboratorioDAO.buscarLaboratorioPorID(id);

		} catch (ServicesException ex) {
			throw new ServicesException("Error registrando novedades:" + ex.getLocalizedMessage(), ex);
		}
  }
  
  @Override
	public Integer maxIdLaboratorio() throws ServicesException {
		try {
			return laboratorioDAO.maxIdLaboratorio();
	 } catch (ServicesException ex) {
		 throw new ServicesException("Error listando elementos:" + ex.getLocalizedMessage(), ex);
	 }
  }
  
  @Override
    public void registrarLaboratorio(Laboratorio laboratorio) throws ServicesException{
    try {
      java.util.Date fechaActual = new java.util.Date();
      laboratorio.setFechaCreacion(fechaActual);
      laboratorioDAO.registrarLaboratorio(laboratorio);
        
      }catch (ServicesException ex) {
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

        } catch (ServicesException ex) {
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
      

        } catch (ServicesException ex) {
                throw new ServicesException("Error registrando novedades:" + ex.getLocalizedMessage(), ex);
        }
  }

  @Override
    public void desAsociarEquipoAlab(Integer idEquipo) throws ServicesException {
    try { 
      equipoDAO.desAsociarEquipoAlab(idEquipo);

    } catch (ServicesException ex) {
            throw new ServicesException("Error registrando novedades:" + ex.getLocalizedMessage(), ex);
    }
  }

  @Override
    public  List<Equipo> buscarEquiposDisponibles() throws ServicesException {
    try { 
        return equipoDAO.buscarEquiposDisponibles();

        } catch (ServicesException ex) {
            throw new ServicesException("Error registrando novedades:" + ex.getLocalizedMessage(), ex);
        }
    }
    @Override
    public void darBajaEquiposConElementos(List<Equipo> seleccionados) throws ServicesException {
        for (Equipo e:seleccionados){
            List<Elemento> elementos = e.getComponets(); 
            for (Elemento el: elementos){
                desAsociarElemento(el.getId());
                darBajaElemento(el.getId());
            }
            darBajaEquipo(e.getId());
        }
    }
    @Override
    public void darBajaEquiposSinElementos(List<Equipo> seleccionados) throws ServicesException {
        for (Equipo e:seleccionados){
            List<Elemento> elementos = e.getComponets();
            for (Elemento el: elementos){
                desAsociarElemento(el.getId());
            }
            darBajaEquipo(e.getId());
        }
    }

}