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

    @ManagedProperty(value = "#{param.elemento}")
    private Tipo elementoTipo;





    

    /**
     * @return the elemento
     */
    public Elemento getElemento() {
        return elemento;
    }

    /**
     * @param elemento the elemento to set
     */
    public void setElemento(Elemento elemento) {
        this.elemento = elemento;
    }






    

    





}