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
    private Elemento torre;
    private Elemento pantalla;
    private Elemento mouse;
    private Elemento teclado;
  
    private int idk;
    
    private boolean buscarK;
 
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
            int idEquipo = laboratorioServices.maxIdEquipo();            
            List<Elemento> elementos = null;
            elementos = crearList();
            for (int i = 0; i< elementos.size();i++){
                laboratorioServices.registrarElemento(elementos.get(i));
                int idElemento= laboratorioServices.maxIdElemento();
                System.out.println( idEquipo+"  "+idElemento);
                laboratorioServices.asociarEquipo( idEquipo,idElemento);
            }
            //if (Equipo.getTeclado()!=null && Equipo.getTorre!=null...){
                
            //   }
           
            mensaje = "success !!";
        } catch (ServicesException ex) {
            mensaje = "Fail";
            throw ex;
        }
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,mensaje,mensaje));
    }
    public List<Elemento> crearList(){
        ArrayList<Elemento> elementos = new ArrayList<Elemento>();
        elementos.add(torre);
        elementos.add(pantalla);
        elementos.add(mouse);
        elementos.add(teclado);
        return elementos;
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