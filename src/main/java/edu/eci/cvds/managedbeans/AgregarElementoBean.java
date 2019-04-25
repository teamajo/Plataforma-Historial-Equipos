package edu.eci.cvds.managedbeans;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import edu.eci.cvds.entities.Elemento;
import edu.eci.cvds.services.LaboratorioServices;
import edu.eci.cvds.services.ServicesException;
import edu.eci.cvds.services.impl.LaboratorioServicesImpl;
import javax.faces.bean.SessionScoped;

/**
 * Bean para la interfaz de usuario de las decanaturas
 */
@SuppressWarnings("agregarElemento")
@ManagedBean(name = "agregarElemento")
@SessionScoped 
public class AgregarElementoBean extends BasePageBean {

    @Inject
    private LaboratorioServicesImpl laboratorioServices;
	
    private Elemento nuevoElemento;


    public AgregarElementoBean(){
        nuevoElemento= new Elemento();
    }

    public Elemento getNuevoElemento() {
        return nuevoElemento;
    }

    public void setNuevoElemento(Elemento nuevoElemento) {
        this.nuevoElemento = nuevoElemento;
    }

    public void registrarElemento() throws Exception {
        String mensaje;
        try {
            //nuevoElemento.setId(laboratorioServices.maxId()+1);
            laboratorioServices.registrarElemento(nuevoElemento);
            mensaje = "success !!";
        } catch (ServicesException ex) {
            mensaje = "Fail";
            throw ex;
        }
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,mensaje,mensaje));
    }
    
    public List<Elemento> elementosDisponibles() throws Exception{
        String mensaje;
        List<Elemento> disponibles= null;
        try {
            disponibles= laboratorioServices.elementosDisponibles();
            mensaje = "success !!";
		} catch (ServicesException ex) {
            mensaje = "Fail";
			throw ex;
        }

        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,mensaje,mensaje));
        return disponibles;

    } 
    public void asociarEquipo(int idEquipo,int id) throws Exception{
        String mensaje;
        try {
            laboratorioServices.asociarEquipo(idEquipo,id);
            mensaje = "success !!";
        } catch (ServicesException ex) {
            mensaje = "Fail";
            throw ex;
        }
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,mensaje,mensaje));
    }
	

    
}