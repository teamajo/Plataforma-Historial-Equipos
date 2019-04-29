package edu.eci.cvds.managedbeans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import edu.eci.cvds.entities.NovedadEquipo;
import edu.eci.cvds.services.ServicesException;
import edu.eci.cvds.services.impl.LaboratorioServicesImpl;


@SuppressWarnings("agregarNovedadEquipo")
@ManagedBean(name = "agregarNovedadEquipo")
@SessionScoped 
public class AgregarNovedadEquipoBean extends BasePageBean  {

	@Inject
    private LaboratorioServicesImpl laboratorioServices;
	
    private NovedadEquipo nuevaNovedadEquipo;


    public AgregarNovedadEquipoBean(){
    	nuevaNovedadEquipo= new NovedadEquipo();
    }

    public NovedadEquipo getNuevaNovedadEquipo() {
        return nuevaNovedadEquipo;
    }

    public void setNuevaNovedadEquipo(NovedadEquipo nuevaNovedadEquipo) {
        this.nuevaNovedadEquipo = nuevaNovedadEquipo;
    }

    public void registrarNovedadEquipo() throws Exception {
        String mensaje;
        try {
            laboratorioServices.registrarNovedadEquipo(nuevaNovedadEquipo);
            mensaje = "success !!";
        } catch (ServicesException ex) {
            mensaje = "Fail";
            throw ex;
        }
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,mensaje,mensaje));
    }
    
    public List<NovedadEquipo> buscarNovedadesDeEquipos() throws Exception{
        String mensaje;
        List<NovedadEquipo> disponibles= null;
        try {
            disponibles= laboratorioServices.buscarNovedadesDeEquipos();
            mensaje = "success !!";
		} catch (ServicesException ex) {
            mensaje = "Fail";
			throw ex;
        }

        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,mensaje,mensaje));
        return disponibles;

    } 

    public List<NovedadEquipo> buscarNovedadesDeEquiposPorEquipos(int idEquipo) throws Exception{
        String mensaje;
        List<NovedadEquipo> disponibles= null;
        try {
            disponibles= laboratorioServices.buscarNovedadesDeEquiposPorEquipos(idEquipo);
            mensaje = "success !!";
		} catch (ServicesException ex) {
            mensaje = "Fail";
			throw ex;
        }

        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,mensaje,mensaje));
        return disponibles;

    }     
    
    
    
}
