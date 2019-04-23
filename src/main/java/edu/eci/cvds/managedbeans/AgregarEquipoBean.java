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
    private Elemento torre;
    private Elemento pantalla;
    private Elemento mouse;
    private Elemento teclado;

    public AgregarEquipoBean() {
        nuevoEquipo = new Equipo();
    }

 

    public Equipo getNuevoEquipo() {
        return nuevoEquipo;
    }

    public void setNuevoEquipo(Equipo nuevoEquipo) {
        this.nuevoEquipo = nuevoEquipo;
    }

    public void registrarEquipo() throws Exception {
        String mensaje;
        List<Elemento> disponibles = null;
        disponibles = laboratorioServices.elementosDisponibles();
        List<Elemento> elementos = null;
        elementos = crearList();
        for (int i = 0; i< elementos.size();i++){
            int prueba = estaDisponible(elementos.get(i), disponibles);
            if(prueba == 2){
                laboratorioServices.asociarEquipo(elementos.get(i).getId(), nuevoEquipo.getId());
            }else if (prueba == 1 ){
                laboratorioServices.registrarElemento(elementos.get(i));
                laboratorioServices.asociarEquipo(elementos.get(i).getId(), nuevoEquipo.getId());
            }else{
                mensaje="No se pudo registar el elemento " + elementos.get(i)+"ya que se encuentra en otro elemento";
                FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,mensaje,mensaje));
            }
        }
        try {
            laboratorioServices.registrarEquipo(nuevoEquipo);
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

    public int estaDisponible(Elemento elemento, List<Elemento> disponibles) throws Exception {
        int flag = 0;
        try {
            if (disponibles.contains(elemento) ){
                flag = 2;
            }else{
                if (!laboratorioServices.buscarElementos().contains(elemento)){
                       flag=1; 
                }else{
                    flag=0;
                }
            }
        } catch (ServicesException ex) {
            throw ex;
        }
        return flag;
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