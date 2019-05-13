package edu.eci.cvds.managedbeans;


import edu.eci.cvds.entities.Equipo;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import javax.faces.bean.SessionScoped;

import edu.eci.cvds.entities.Laboratorio;
import edu.eci.cvds.services.LaboratorioServices;
import edu.eci.cvds.services.ServicesException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
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
    
    private List<Equipo> disponibles;
    
    private Laboratorio laboratorio;

    public Laboratorio getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }


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
    
    public String seleccionarlab(){
             
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            Map<String,String> params =
                    fc.getExternalContext().getRequestParameterMap();
            String productIdString =  params.get("laboratorioID");
            int id = Integer.parseInt(productIdString);
            setLaboratorio(laboratorioServices.buscarLaboratorioPorID(id));   
          
        } catch (ServicesException ex) {
            Logger.getLogger(EquipoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Laboratorio.xhtml?faces-redirect=true";
    }
     
    public List<Equipo> disponibles(){
        if(disponibles==null){
            try {
                disponibles=laboratorioServices.buscarEquiposDisponibles();
            } catch (ServicesException ex) {
                Logger.getLogger(LaboratorioBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return disponibles;
    }
    
    public void asociar(){
        //// implementar
    }
    

}