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

import edu.eci.cvds.entities.Elemento;
import edu.eci.cvds.entities.Equipo;
import edu.eci.cvds.entities.Tipo;
import edu.eci.cvds.services.LaboratorioServices;
import edu.eci.cvds.services.ServicesException;
import edu.eci.cvds.services.impl.LaboratorioServicesImpl;
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
  
    private int idk,idp,idm,idt;
    
    private boolean buscarK,buscarP,buscarM,buscarT;
 
    public AgregarEquipoBean(){
        nuevoEquipo= new Equipo();
        torre = new Elemento();
        torre.setTipo(Tipo.torre);
        pantalla = new Elemento();
        pantalla.setTipo(Tipo.pantalla);
        mouse = new Elemento();
        mouse.setTipo(Tipo.mouse);
        teclado = new Elemento();
        teclado.setTipo(Tipo.teclado);
    }

    public void setIdp(int idp) {
        if (buscarP){
          this.idp = idp;
          //pantalla=laboratorioServices.buscarElemento(idp);
          //nuevoEquipo.setMouse(pantalla);
        }  
    }

    public void setIdm(int idm) {
        if (buscarM){
          this.idm = idm;
          //mouse=laboratorioServices.buscarElemento(idm);
          //nuevoEquipo.setMouse(mouse);
        }  
    }
    
    public void setIdt(int idt) {
        if (buscarT){
          this.idt = idt;
          //torre=laboratorioServices.buscarElemento(idt);
          //nuevoEquipo.setTorre(torre);
        }  
    }
    
       
    public void setIdk(int idk) {
        if (buscarK){
          this.idk = idk;
          //teclado=laboratorioServices.buscarElemento(idk);
          //nuevoEquipo.setTeclado(teclado);
        }            
    }
    
    public void setBuscarP(boolean buscarP) {
        this.buscarP = buscarP;
        setIdp(idp);
    }

    public void setBuscarM(boolean buscarM) {
        this.buscarM = buscarM;
        setIdm(idm);
    }

    public void setBuscarT(boolean buscarT) {
        this.buscarT = buscarT;
        setIdt(idt);
    }
    
     public void setBuscarK(boolean buscarK) {
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
            nuevoEquipo.setMouse(mouse);
            nuevoEquipo.setPantalla(pantalla);
            nuevoEquipo.setTeclado(teclado);
            nuevoEquipo.setTorre(torre);
            laboratorioServices.registrarEquipo(nuevoEquipo);
            /*int idEquipo = laboratorioServices.maxIdEquipo();            
            List<Elemento> elementos = null;
            elementos = crearList();
            for (int i = 0; i< elementos.size();i++){
                laboratorioServices.registrarElemento(elementos.get(i));
                int idElemento= laboratorioServices.maxIdElemento();
                System.out.println( idEquipo+"  "+idElemento);
                laboratorioServices.asociarEquipo( idEquipo,idElemento);
            }
            //if (Equipo.getTeclado()!=null && Equipo.getTorre!=null...){
                
            //   }*/
           
            mensaje = "success !!";
        } catch (ServicesException ex) {
            mensaje = "Fail";
            throw ex;
        }
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,mensaje,mensaje));
    }
    /*public List<Elemento> crearList(){
        ArrayList<Elemento> elementos = new ArrayList<Elemento>();
        elementos.add(torre);
        elementos.add(pantalla);
        elementos.add(mouse);
        elementos.add(teclado);
        return elementos;
    }*/


     

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