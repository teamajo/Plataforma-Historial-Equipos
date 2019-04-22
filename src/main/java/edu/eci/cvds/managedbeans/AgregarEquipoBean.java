package edu.eci.cvds.managedbeans;

import edu.eci.cvds.entities.Elemento;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import edu.eci.cvds.entities.Equipo;
import edu.eci.cvds.services.LaboratorioServices;
import edu.eci.cvds.services.ServicesException;
import edu.eci.cvds.services.impl.LaboratorioServicesImpl;

/**
 * Bean para la interfaz de usuario de las decanaturas
 */
@SuppressWarnings("agregarEquipo")
@ManagedBean(name = "agregarEquipo")
@RequestScoped
public class AgregarEquipoBean extends BasePageBean {

    @Inject
    private LaboratorioServicesImpl laboratorioServices;
	
    private Equipo nuevoEquipo;  
  
    private int idk;
    
    private boolean buscarK;
 
    public AgregarEquipoBean(){
        nuevoEquipo= new Equipo();
    }

    public Equipo getNuevoEquipo() {
        return nuevoEquipo;
    }

    public void setNuevoEquipo(Equipo nuevoEquipo) {
        this.nuevoEquipo = nuevoEquipo;
    }

    public void registrarEquipo() throws Exception {
        String mensaje;
        try {
            //if (Equipo.getTeclado()!=null && Equipo.getTorre!=null...){
                laboratorioServices.registrarEquipo(nuevoEquipo);
            //   }
           
            mensaje = "success !!";
        } catch (ServicesException ex) {
            mensaje = "Fail";
            throw ex;
        }
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,mensaje,mensaje));
    }

    public void setBuscarK(boolean buscarK) {
        this.buscarK = buscarK;
    }
    
    public boolean isBuscarK() {
        return buscarK;
    }
    
    public int getIdk() {
        return idk;
    }
    
    public void setIdk(int idk) {
        if (buscarK){
          this.idk = idk;
          //teclado=laboratorioServices.buscarElemento(idk);
          //nuevoEquipo.setTeclado(teclado);
        }            
    }

    
    
  

    
   
  
}