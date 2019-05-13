package edu.eci.cvds.managedbeans;

import edu.eci.cvds.entities.Elemento;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import javax.faces.bean.SessionScoped;

import edu.eci.cvds.entities.Equipo;
import edu.eci.cvds.services.LaboratorioServices;
import edu.eci.cvds.services.ServicesException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


/**
 * 
 */
@SuppressWarnings("deprecation")
@ManagedBean(name = "equipoBean")
@SessionScoped
public class EquipoBean extends BasePageBean {

    @Inject
    private LaboratorioServices laboratorioServices;

    
    private Equipo equipo;   
 
    private Elemento elemento;
    
    private List<Equipo> equipos;

    private List<Equipo> seleccionados;    
    
    private Integer idElemento;
    
    private boolean asociar;

    public Integer getIdElemento() {
        return idElemento;
    }

    public boolean isAsociar() {
        return asociar;
    }

    public void setAsociar(boolean asociar) {
        this.asociar = asociar;
    }

    public void setIdElemento(Integer idElemento) {
        this.idElemento = idElemento;
    }
      
    public void remplaceElemento(){
    
    }

 


    /**
     * @return the equipo
     * @throws ServicesException
     */
    public Equipo getEquipo() throws ServicesException {      
        return equipo;
    }

    public Elemento getElemento() {
        return elemento;
    }

    public void setElemento(Elemento elemento) {
        this.elemento = elemento;
    }

    /**
     * @param equipo the equipo to set
     */
    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public String seleccionarEquipo(Equipo equipo){
        setEquipo(equipo);
        return "Equipo.xhtml?faces-redirect=true";      
    }
    
    public String seleccionarElemento(Elemento elemento){
        setElemento(elemento);
        return "EditarEquipo.xhtml?faces-redirect=true";   
    }



     public List<Equipo> buscarEquipos() throws Exception{
         if(equipos==null){
             equipos=laboratorioServices.buscarEquipos();
         }
        return equipos;
        
        
    }

    /**
     * @return the seleccionados
     */
    public List<Equipo> getSeleccionados() {
        return seleccionados;
    }

    /**
     * @param seleccionados the seleccionados to set
     */
    public void setSeleccionados(List<Equipo> seleccionados) {
        this.seleccionados = seleccionados;
    }

    
    // ??? Exception ???
    public void darBajaEquiposConElementos()  throws Exception{
        for (Equipo e:seleccionados){
            List<Elemento> elementos = e.getComponets(); 
            for (Elemento el: elementos){
                laboratorioServices.desAsociarElemento(el.getId());
                laboratorioServices.darBajaElemento(el.getId());
            }
            laboratorioServices.darBajaEquipo(e.getId());
        }
    } 

    public void darBajaEquiposSinElementos()  throws Exception{

        for (Equipo e:seleccionados){
            List<Elemento> elementos = e.getComponets();
            for (Elemento el: elementos){
                
                laboratorioServices.desAsociarElemento(el.getId());
            }
            laboratorioServices.darBajaEquipo(e.getId());
        }
    } 




}