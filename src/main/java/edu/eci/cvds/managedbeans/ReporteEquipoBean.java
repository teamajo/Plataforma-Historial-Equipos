package edu.eci.cvds.managedbeans;

import edu.eci.cvds.entities.Elemento;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import edu.eci.cvds.entities.Equipo;
import edu.eci.cvds.persistence.PersistenceException;
import edu.eci.cvds.services.LaboratorioServices;
import edu.eci.cvds.services.ServicesException;

import javax.faces.bean.SessionScoped;

/**
 * 
 */
@SuppressWarnings("reporteEquipo")
@ManagedBean(name = "reporteEquipo")
@SessionScoped
public class ReporteEquipoBean extends BasePageBean {

    @Inject
    private LaboratorioServices laboratorioServices;

    private Equipo equipo;


    public List<Equipo> buscarEquipos() throws ServicesException{
        try {
            return laboratorioServices.buscarEquipos();
        } catch (ServicesException ex) {
            throw ex;
        }
         
    }

    public void onEquipoSelect(SelectEvent event){
        try {
            equipo = (Equipo) event.getObject();
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.getExternalContext().redirect("ModificarEquipo.xhtml");
        } catch (IOException e) {
            Logger.getLogger(ReporteEquipoBean.class.getName()).log(null, e);
        }
    }

    public void onRowUnSelect(UnselectEvent event){
        equipo=null;
    }





}