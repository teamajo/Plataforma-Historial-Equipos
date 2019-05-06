package edu.eci.cvds.managedbeans;


import java.util.List;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;


import edu.eci.cvds.entities.Elemento;
import edu.eci.cvds.services.ServicesException;
import edu.eci.cvds.services.impl.LaboratorioServicesImpl;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 * Bean para la interfaz de usuario de las decanaturas
 */
@SuppressWarnings("agregarElemento")
@ManagedBean(name = "agregarElemento")
@ViewScoped
public class AgregarElementoBean extends BasePageBean {

    @Inject
    private LaboratorioServicesImpl laboratorioServices;
	
    private Elemento nuevoElemento;

    private List<Elemento> todos;

    private List<Elemento> seleccionados;

    public AgregarElementoBean() {
        nuevoElemento = new Elemento();
    }

    /**
     * @return the seleccionados
     */
    public List<Elemento> getSeleccionados() {
        return seleccionados;
    }

    /**
     * @param seleccionados the seleccionados to set
     */
    public void setSeleccionados(List<Elemento> seleccionados) {
        this.seleccionados = seleccionados;
    }

    public Elemento getNuevoElemento() {
        return nuevoElemento;
    }

    public void setNuevoElemento(Elemento nuevoElemento) {
        this.nuevoElemento = nuevoElemento;
    }

    public void registrarElemento() throws Exception {
        String mensaje;
        try {
            laboratorioServices.registrarElemento(nuevoElemento);
            mensaje = "success !!";
        } catch (ServicesException ex) {
            mensaje = "Fail";
            throw ex;
        }
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, mensaje));
    }

    public List<Elemento> elementosDisponibles() throws Exception {
        try {
            return laboratorioServices.elementosDisponibles();
        } catch (ServicesException ex) {

            throw ex;
        }

    }

    public List<Elemento> elementosDisponiblesPorTipo(String tipo) throws Exception {

        try {
            return laboratorioServices.elementosDisponiblesPorTipo(tipo);
        } catch (ServicesException ex) {

            throw ex;
        }

    }

    public List<Elemento> consultarElementos() throws Exception {
        try {
            if (todos == null) {
                todos = laboratorioServices.buscarElementos();
            }
            return todos;
        } catch (ServicesException ex) {

            throw ex;
        }

    }  

    public void darBajaElementos()  throws Exception{
        String mensaje;
        for (Elemento e:seleccionados){
            FacesMessage.Severity fs=FacesMessage.SEVERITY_ERROR;
            FacesMessage fm=new FacesMessage(fs,"",""); 
            if (laboratorioServices.buscarElemento(e.getId()).getIdEquipo()==null){
                //System.out.println(e.getId());
                laboratorioServices.darBajaElemento(e.getId());
                mensaje = "success !!";
                fs=FacesMessage.SEVERITY_INFO;
            }
            mensaje = "No se puede dar de baja al elemento con id"+e.getId()+" !!";
            fm.setSeverity(fs);
            fm.setSummary(mensaje);      
            FacesContext.getCurrentInstance().addMessage(null,fm);


        }
    }
  

  
	

    
}