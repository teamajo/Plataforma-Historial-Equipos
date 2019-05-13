package edu.eci.cvds.managedbeans;


import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import javax.faces.bean.SessionScoped;

import edu.eci.cvds.entities.Laboratorio;
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
@ManagedBean(name = "laboratorioBean")
@SessionScoped
public class LaboratorioBean extends BasePageBean {

    @Inject
    private LaboratorioServices laboratorioServices;

    private List<Laboratorio> laboratorios ;
    private List<Laboratorio> seleccionados;   

    public List<Laboratorio> buscarLaboratorios() throws Exception{
        if(laboratorios==null){
            laboratorios=laboratorioServices.buscarLaboratorios();
        }
       return laboratorios;
    }


    /**
     * @return the seleccionados
     */
    public List<Laboratorio> getSeleccionados() {
        return seleccionados;
    }

    /**
     * @param seleccionados the seleccionados to set
     */
    public void setSeleccionados(List<Laboratorio> seleccionados) {
        this.seleccionados = seleccionados;
    }


}