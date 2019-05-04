package edu.eci.cvds.managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import javax.faces.bean.SessionScoped;

import edu.eci.cvds.entities.Elemento;
import edu.eci.cvds.entities.Tipo;
import edu.eci.cvds.services.LaboratorioServices;
import edu.eci.cvds.services.ServicesException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;

import javax.faces.context.FacesContext;


/**
 * 
 */
@SuppressWarnings("deprecation")
@ManagedBean(name = "editarEquipo")
@RequestScoped
public class EditarEquipoBean extends BasePageBean {

    @Inject
    private LaboratorioServices laboratorioServices;

    
    private Elemento elemento;

    @ManagedProperty(value = "#{param.idElemento}")
    private Integer idElemento;
    
    public EditarEquipoBean(){
        System.out.println("Creado de nuevo");
    }

//    @PostConstruct
//    public void init() {
//        idElemento = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("editarEquipo"));       
//   }

    /**
     * @return the elemento
     */
    public Elemento getElemento() {
        if (elemento == null && idElemento != null) {
            try {
                System.out.print(idElemento);
                elemento = laboratorioServices.buscarElemento(idElemento);
            } catch (ServicesException ex) {
                Logger.getLogger(EditarEquipoBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        return elemento;
    }

    /**
     * @param elemento the elemento to set
     */
    public void setElemento(Elemento elemento) {
        if(elemento!=null){
              System.out.print("No set null");
            this.elemento = elemento;       
        }else{
             System.out.print("Set null");
        }
    }
    public Integer getIdElemento() {
        return idElemento;
    }

    public void setIdElemento(Integer idElemento) {
        this.idElemento = idElemento;
    }
    
    public void dosomething(){

    }


}