package edu.eci.cvds.managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import javax.faces.bean.SessionScoped;

import edu.eci.cvds.entities.Equipo;
import edu.eci.cvds.services.LaboratorioServices;
import edu.eci.cvds.services.ServicesException;


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

    

    





}