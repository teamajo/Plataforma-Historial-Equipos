package edu.eci.cvds.managedbeans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.context.FacesContext;
import javax.inject.Inject;

import edu.eci.cvds.entities.NovedadElemento;
import edu.eci.cvds.services.ServicesException;
import edu.eci.cvds.services.impl.LaboratorioServicesImpl;
import javax.faces.bean.RequestScoped;


@SuppressWarnings("agregarNovedadElemento")
@ManagedBean(name = "agregarNovedadElemento")
@RequestScoped 
public class AgregarNovedadElementoBean extends BasePageBean  {

	@Inject
    private LaboratorioServicesImpl laboratorioServices;
	
    private NovedadElemento nuevaNovedadElemento;


    public AgregarNovedadElementoBean(){
    	nuevaNovedadElemento= new NovedadElemento();
    }

    public NovedadElemento getNuevaNovedadEquipo() {
        return nuevaNovedadElemento;
    }

    public void setNuevaNovedadEquipo(NovedadElemento nuevaNovedadElemento) {
        this.nuevaNovedadElemento = nuevaNovedadElemento;
    }

    public void registrarNovedadElemento() throws Exception {
        String mensaje;
        try {
            laboratorioServices.registrarNovedadElemento(nuevaNovedadElemento);
            mensaje = "success !!";
        } catch (ServicesException ex) {
            mensaje = "Fail";
            throw ex;
        }
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,mensaje,mensaje));
    }
    
    public List<NovedadElemento> buscarNovedadesElementos() throws Exception{
        String mensaje;
        List<NovedadElemento> disponibles= null;
        try {
            disponibles= laboratorioServices.buscarNovedadesDeElementos();
            mensaje = "success !!";
		} catch (ServicesException ex) {
            mensaje = "Fail";
			throw ex;
        }

        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,mensaje,mensaje));
        return disponibles;

    } 

    public List<NovedadElemento> buscarNovedadesElementosPorEquipos(int idEquipo) throws Exception{
        String mensaje;
        List<NovedadElemento> disponibles= null;
        try {
            disponibles= laboratorioServices.buscarNovedadesDeElementosPorEquipos(idEquipo);
            mensaje = "success !!";
		} catch (ServicesException ex) {
            mensaje = "Fail";
			throw ex;
        }

        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,mensaje,mensaje));
        return disponibles;

    }     
    
    public List<NovedadElemento> buscarNovedadesElementosPorElementos(int idElemento) throws Exception{
        String mensaje;
        List<NovedadElemento> disponibles= null;
        try {
            disponibles= laboratorioServices.buscarNovedadesDeElementosPorElementos(idElemento);
            mensaje = "success !!";
		} catch (ServicesException ex) {
            mensaje = "Fail";
			throw ex;
        }

        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,mensaje,mensaje));
        return disponibles;

    }
    
}
