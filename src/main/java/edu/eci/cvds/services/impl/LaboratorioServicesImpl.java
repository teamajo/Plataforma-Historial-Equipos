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
      return equipoDAO.maxIdEquipo();
    }

    @Override
    public Integer maxIdElemento() throws ServicesException {
      return elementoDAO.maxIdElemento();
    }

    @Override
    public List<NovedadEquipo> buscarNovedadesPorEquipo(Integer idEquipo) throws ServicesException {
      return novedadEquipoDAO.buscarNovedadesDeEquiposPorEquipos(idEquipo);
    }

    @Override
    public List<NovedadEquipo> buscarNovedadesDeEquipos() throws ServicesException {
      return novedadEquipoDAO.buscarNovedadesDeEquipos();
    }

    @Override
    public void registrarNovedadEquipo(NovedadEquipo novedad) throws ServicesException {
      novedadEquipoDAO.registrarNovedadEquipo(novedad);
    }

    @Override
    public List<NovedadElemento> buscarNovedadesDeElementosPorEquipos(Integer idEquipo) throws ServicesException {
      return novedadElementoDAO.buscarNovedadesDeElementosPorEquipos(idEquipo);
    }

    @Override
    public List<NovedadElemento> buscarNovedadesDeElementosPorElementos(Integer idElemento) throws ServicesException {
      return novedadElementoDAO.buscarNovedadesDeElementosPorElementos(idElemento);
    }

    @Override
    public List<NovedadElemento> buscarNovedadesDeElementos() throws ServicesException {
      return novedadElementoDAO.buscarNovedadesDeElementos();
    }

	@Override
	public void registrarNovedadElemento(NovedadElemento novedad) throws ServicesException {
	    	novedadElementoDAO.registrarNovedadElemento(novedad);
	}

	@Override
	public void darBajaEquipo(Integer id) throws ServicesException {
	try {
        java.util.Date fechaActual = new java.util.Date();
        NovedadEquipo novedad = new NovedadEquipo(null,"novedad equipo darBajaEquipo", id,fechaActual,"se dio de baja el equipo","admin");
            equipoDAO.darBajaEquipo(id);
            novedadEquipoDAO.registrarNovedadEquipo(novedad);
        } catch (ServicesException ex) {
                throw new ServicesException("Error registrando novedades:" + ex.getLocalizedMessage(), ex);
        }
	}

	@Override
	public void darBajaElemento(Integer id) throws ServicesException {
	try {
            java.util.Date fechaActual = new java.util.Date();
            NovedadElemento novedadel = new NovedadElemento(null,"novedad elmento darBajaElemento",buscarElemento(id).getIdEquipo(),id,fechaActual,"se dio de baja el elemento","admin");
            if (buscarElemento(id).getIdEquipo()==null){
              elementoDAO.darBajaElemento(id);
              novedadElementoDAO.registrarNovedadElemento(novedadel);
            }
	} catch (ServicesException ex) {
            throw new ServicesException("Error registrando novedades:" + ex.getLocalizedMessage(), ex);
	}
  }
  @Override
	public List<Laboratorio> buscarLaboratorios() throws ServicesException {
        return laboratorioDAO.buscarLaboratorios();
  }
  
  @Override
	public Laboratorio buscarLaboratorioPorID(Integer id) throws ServicesException {
      return laboratorioDAO.buscarLaboratorioPorID(id);
  }
  
  @Override
	public Integer maxIdLaboratorio() throws ServicesException {
			return laboratorioDAO.maxIdLaboratorio();
  }
  
  @Override
    public void registrarLaboratorio(Laboratorio laboratorio) throws ServicesException{
      java.util.Date fechaActual = new java.util.Date();
      laboratorio.setFechaCreacion(fechaActual);
      laboratorioDAO.registrarLaboratorio(laboratorio);
  }
  @Override
    public void darBajaLaboratorio(Integer id) throws ServicesException {
      List<Equipo> equiposLab = buscarLaboratorioPorID(id).getEquipos();
      for (Equipo e : equiposLab){
        desAsociarEquipoAlab(e.getId());
      }
      laboratorioDAO.darBajaLaboratorio(id);
  }
  @Override
    public void asociarEquipoAlab(Integer idEquipo, Integer id) throws ServicesException {
    try { 
        java.util.Date fechaActual = new java.util.Date();
        Equipo eq = buscarEquipoPorId(idEquipo);
        NovedadEquipo novedadeq = new NovedadEquipo(null,"novedad equipo asociar a Lab",idEquipo,fechaActual,"se asocio el equipo al lab ".concat(Integer.toString(id)),"admin");
        if (eq.isActivo()){
            if (eq.getlab() != null){
                desAsociarEquipoAlab(idEquipo);
            }
            equipoDAO.asociarEquipoAlab(idEquipo,id);
            novedadEquipoDAO.registrarNovedadEquipo(novedadeq);
        }
      

        } catch (ServicesException ex) {
                throw new ServicesException("Error registrando novedades:" + ex.getLocalizedMessage(), ex);
        }
  }

  @Override
    public void desAsociarEquipoAlab(Integer idEquipo) throws ServicesException {
    try { 
        java.util.Date fechaActual = new java.util.Date();
        NovedadEquipo novedadeq = new NovedadEquipo(null,"novedad equipo des asociar Lab",idEquipo,fechaActual,"se des asocio el equipo del lab al que pertenecia","admin");
        equipoDAO.desAsociarEquipoAlab(idEquipo);
        novedadEquipoDAO.registrarNovedadEquipo(novedadeq);
    } catch (ServicesException ex) {
            throw new ServicesException("Error registrando novedades:" + ex.getLocalizedMessage(), ex);
    }
  }

  @Override
    public  List<Equipo> buscarEquiposDisponibles() throws ServicesException {
        return equipoDAO.buscarEquiposDisponibles();
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