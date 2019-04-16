package edu.eci.cvds.managedbeans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import edu.eci.cvds.entities.Elemento;
import edu.eci.cvds.services.LaboratorioServices;
import edu.eci.cvds.services.LaboratorioServices;
import edu.eci.cvds.services.ServicesException;

/**
 * Bean para la interfaz de usuario de las decanaturas
 */
@SuppressWarnings("agregarElemento")
@ManagedBean(name = "agregarElemento")
@RequestScoped
public class AgregarElementoBean extends BasePageBean {

	@Inject
	private LaboratorioServices laboratorioServices;
	
    private Elemento nuevoElemento;


    public AgregarElementoBean(){
        nuevoElemento= new Elemento();
    }

    public Elemento getNuevoElemento() {
        return nuevoElemento;
    }

    public void setNuevoElemento(Elemento nuevoElemento) {
        this.nuevoElemento = nuevoElemento;
    }

	public void registrarElemento() throws Exception {
		try {
		     laboratorioServices.registrarElemento(nuevoElemento);
		} catch (ServicesException ex) {

			throw ex;
		}
	}
	
	

}