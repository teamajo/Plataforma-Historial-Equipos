package edu.eci.cvds.managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import javax.faces.bean.SessionScoped;

import edu.eci.cvds.entities.Equipo;
import edu.eci.cvds.services.LaboratorioServices;
import edu.eci.cvds.services.ServicesException;
import java.util.List;
import javax.faces.bean.ViewScoped;


/**
 * 
 */
@SuppressWarnings("deprecation")
@ManagedBean(name = "equipoBean")
@RequestScoped
public class EquipoBean extends BasePageBean {

    @Inject
    private LaboratorioServices laboratorioServices;

    
    private Equipo equipo;
    
    @ManagedProperty(value = "#{param.equipoSeleccionado}")
    private Integer equipoId;
    
    private List<Equipo> equipos;

    private List<Equipo> seleccionados;





    /**
     * @return the equipo
     * @throws ServicesException
     */
    public Equipo getEquipo() throws ServicesException {
       if (equipo == null && equipoId != null) {
			equipo = laboratorioServices.buscarEquipoPorId(equipoId);
		}
		return equipo;
    }

    /**
     * @param equipo the equipo to set
     */
    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    

    /**
     * @param equipoId the equipoId to set
     */
    public void setEquipoId(Integer equipoId) {
        this.equipoId = equipoId;
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

    public void darBajaEquipos()  throws Exception{
        for (Equipo e:seleccionados){
            //System.out.println(e.getId());
            laboratorioServices.darBajaEquipo(e.getId());
        }
    }
    





}