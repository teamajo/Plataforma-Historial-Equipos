package edu.eci.cvds.managedbeans;

import edu.eci.cvds.entities.Elemento;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import edu.eci.cvds.entities.Equipo;
import edu.eci.cvds.persistence.PersistenceException;
import edu.eci.cvds.services.LaboratorioServices;
import edu.eci.cvds.services.ServicesException;

import javax.faces.bean.SessionScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.context.RequestContext;

/**
 * Bean para la interfaz de usuario de las decanaturas
 */
@SuppressWarnings("agregarEquipo")
@ManagedBean(name = "agregarEquipo")
@RequestScoped 
public class AgregarEquipoBean extends BasePageBean {

    @Inject
    private LaboratorioServices laboratorioServices;

    private Equipo nuevoEquipo;
 

    private int idK, idP, idM, idT;

    private static boolean buscarK ;
    private static boolean buscarP ;
    private static boolean buscarM ;
    private static boolean buscarT ;

    public AgregarEquipoBean() throws PersistenceException {
        nuevoEquipo = new Equipo();
  
    }

        
    public void setBuscarP(boolean buscarP) throws ServicesException {
        this.buscarP = buscarP;
        //setidp(idp);
    }

    public void setBuscarM(boolean buscarM) throws ServicesException {
        this.buscarM = buscarM;
        //setidm(idm);
    }

    public void setBuscarT(boolean buscarT) throws ServicesException {
        this.buscarT = buscarT;
        //setidt(idt);
    }
    
     public void setBuscarK(boolean buscarK) throws ServicesException {
        this.buscarK = buscarK;
        //setidk(idk);
    }
    

    public boolean isBuscarP() {
        return buscarP;
    }

    public boolean isBuscarM() {
        return buscarM;
    }

    public boolean isBuscarT() {
        return buscarT;
    }

   
    public boolean isBuscarK() {
        return buscarK;
    }

    public int getIdK() {
        return idK;
    }

    public int getIdP() {
        return idP;
    }

    public int getIdM() {
        return idM;
    }

    public int getIdT() {
        return idT;
    }

    public void setIdK(int idK) {
        this.idK = idK;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    public void setIdM(int idM) {
        this.idM = idM;
    }

    public void setIdT(int idT) {
        this.idT = idT;
    }

  

 

    public Equipo getNuevoEquipo() {
        return nuevoEquipo;
    }

    public void setNuevoEquipo(Equipo nuevoEquipo) {
        this.nuevoEquipo = nuevoEquipo;
    }

    public void registrarEquipo() throws Exception {
        String mensaje;
        FacesMessage.Severity fm=FacesMessage.SEVERITY_ERROR;
        try {
         
            
            if(buscarK){                
                nuevoEquipo.setTeclado(laboratorioServices.buscarElemento(idK));
            }
             if(buscarM){
               nuevoEquipo.setMouse(laboratorioServices.buscarElemento(idM));
            }
            if(buscarP){
                nuevoEquipo.setPantalla(laboratorioServices.buscarElemento(idP));               
            }
             if(buscarT){
                nuevoEquipo.setTorre(laboratorioServices.buscarElemento(idT));
            }         

            laboratorioServices.registrarEquipo(nuevoEquipo);
            mensaje = "success !!";
            fm=FacesMessage.SEVERITY_INFO;
            nuevoEquipo=new Equipo();
            RequestContext.getCurrentInstance().update(":torreformulario");
            RequestContext.getCurrentInstance().update(":pantallaformulario");
            RequestContext.getCurrentInstance().update(":tecladoformulario");
            RequestContext.getCurrentInstance().update(":mouseformulario");
          
        } catch (ServicesException ex) {
            mensaje = "Algun item no encontrado";           
        } catch (PersistenceException ex){
            mensaje = "Algun item no correspoonde con su tipo"; 
        }
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(fm,mensaje,""));
  
    }


     
   

    
   
  
}