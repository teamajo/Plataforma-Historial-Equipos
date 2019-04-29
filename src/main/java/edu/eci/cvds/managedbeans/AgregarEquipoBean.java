package edu.eci.cvds.managedbeans;

import edu.eci.cvds.entities.Elemento;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import edu.eci.cvds.entities.Equipo;
import edu.eci.cvds.persistence.PersistenceException;
import edu.eci.cvds.services.LaboratorioServices;
import edu.eci.cvds.services.ServicesException;

import javax.faces.bean.SessionScoped;

/**
 * Bean para la interfaz de usuario de las decanaturas
 */
@SuppressWarnings("agregarEquipo")
@ManagedBean(name = "agregarEquipo")
@SessionScoped
public class AgregarEquipoBean extends BasePageBean {

    @Inject
    private LaboratorioServices laboratorioServices;

    private Equipo nuevoEquipo;
    private Elemento torre;
    private Elemento pantalla;
    private Elemento mouse;
    private Elemento teclado;

    private int idk, idp, idm, idt;

    private boolean buscarK, buscarP, buscarM, buscarT;

    public AgregarEquipoBean() {
        nuevoEquipo = new Equipo();
  
    }

    public void setIdp(int idp) throws ServicesException {
        if (buscarP) {
            this.idp = idp;
            pantalla = laboratorioServices.buscarElemento(idp);
            try {
                nuevoEquipo.setMouse(pantalla);
            } catch (PersistenceException e) {
                
                e.printStackTrace();
            }
        }  
    }

    public void setIdm(int idm) throws ServicesException {
        if (buscarM){
          this.idm = idm;
          mouse=laboratorioServices.buscarElemento(idm);
            try {
                nuevoEquipo.setMouse(mouse);
            } catch (PersistenceException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }  
    }
    
    public void setIdt(int idt) throws ServicesException {
        if (buscarT){
          this.idt = idt;
          torre=laboratorioServices.buscarElemento(idt);
            try {
                nuevoEquipo.setTorre(torre);
            } catch (PersistenceException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }  
    }
    
       
    public void setIdk(int idk) throws ServicesException {
        if (buscarK){
          this.idk = idk;
          teclado=laboratorioServices.buscarElemento(idk);
            try {
                nuevoEquipo.setTeclado(teclado);
            } catch (PersistenceException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }            
    }
    
    public void setBuscarP(boolean buscarP) throws ServicesException {
        this.buscarP = buscarP;
        setIdp(idp);
    }

    public void setBuscarM(boolean buscarM) throws ServicesException {
        this.buscarM = buscarM;
        setIdm(idm);
    }

    public void setBuscarT(boolean buscarT) throws ServicesException {
        this.buscarT = buscarT;
        setIdt(idt);
    }
    
     public void setBuscarK(boolean buscarK) throws ServicesException {
        this.buscarK = buscarK;
        setIdk(idk);
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

    public int getIdk() {
        return idk;
    }

    public int getIdp() {
        return idp;
    }

    public int getIdm() {
        return idm;
    }

    public int getIdt() {
        return idt;
    }

    public boolean isBuscarK() {
        return buscarK;
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
            laboratorioServices.registrarEquipo(nuevoEquipo);
            mensaje = "success !!";
        } catch (ServicesException ex) {
            mensaje = "Fail";
            throw ex;
        }
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,mensaje,mensaje));
    }


     

    /**
     * @return the mouse
     */
    public Elemento getMouse() {
        return mouse;
    }

    /**
     * @param mouse the mouse to set
     */
    public void setMouse(Elemento mouse) {
        this.mouse = mouse;
    }

    /**
     * @return the teclado
     */
    public Elemento getTeclado() {
        return teclado;
    }

    /**
     * @param teclado the teclado to set
     */
    public void setTeclado(Elemento teclado) {
        this.teclado = teclado;
    }

    /**
     * @return the pantalla
     */
    public Elemento getPantalla() {
        return pantalla;
    }

    /**
     * @param pantalla the pantalla to set
     */
    public void setPantalla(Elemento pantalla) {
        this.pantalla = pantalla;
    }

    /**
     * @return the torre
     */
    public Elemento getTorre() {
        return torre;
    }

    /**
     * @param torre the torre to set
     */
    public void setTorre(Elemento torre) {
        this.torre = torre;
    }
	    
    
  
    

    
   
  
}