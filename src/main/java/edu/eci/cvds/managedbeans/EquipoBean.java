package edu.eci.cvds.managedbeans;

import edu.eci.cvds.entities.Elemento;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import javax.faces.bean.SessionScoped;

import edu.eci.cvds.entities.Equipo;
import edu.eci.cvds.entities.NovedadElemento;
import edu.eci.cvds.entities.NovedadEquipo;
import edu.eci.cvds.services.LaboratorioServices;
import edu.eci.cvds.services.ServicesException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private Elemento nuevoElemento;

    private List<Equipo> equipos;

    private List<Equipo> seleccionados;

    private Integer idElemento;

    private Integer idEquipo;

    private boolean asociar;

    public EquipoBean() {
        nuevoElemento = new Elemento();
    }

    

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

    public String seleccionarEquipo(){
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            Map<String,String> params =
                    fc.getExternalContext().getRequestParameterMap();
            String productIdString =  params.get("equipoID");
            int id = Integer.parseInt(productIdString);
            setEquipo(laboratorioServices.buscarEquipoPorId(id));      
          
        } catch (ServicesException ex) {
            Logger.getLogger(EquipoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Equipo.xhtml?faces-redirect=true";
    }
    
    public String editarElemento(Elemento elemento){
        setElemento(elemento);
        return "EditarEquipo.xhtml?faces-redirect=true";   
    }
    

	
    
     public String seleccionarElemento(){
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            Map<String,String> params =
                    fc.getExternalContext().getRequestParameterMap();
            String productIdString =  params.get("elementoID");
            int id = Integer.parseInt(productIdString);
            setElemento(laboratorioServices.buscarElemento(id));   
          
        } catch (ServicesException ex) {
            Logger.getLogger(EquipoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Elemento.xhtml?faces-redirect=true";
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
        laboratorioServices.darBajaEquiposConElementos(seleccionados);
        refresh();
    } 

    public void darBajaEquiposSinElementos()  throws Exception{
        laboratorioServices.darBajaEquiposSinElementos(seleccionados);
        refresh();
    } 

    public List<Equipo> buscarEquipos(){
       if (equipos==null){
           try {    
               equipos=laboratorioServices.buscarEquipos();
           } catch (ServicesException ex) {
               Logger.getLogger(EquipoBean.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
           
        return equipos;       
        
    }
    
    public void refresh(){
        try {
            equipos=laboratorioServices.buscarEquipos();
        } catch (ServicesException ex) {
            Logger.getLogger(AgregarEquipoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void remplaceElemento(){
        if(asociar){
            System.out.println(idElemento);
            try {
                laboratorioServices.asociarEquipo(elemento.getIdEquipo(),laboratorioServices.buscarElemento(idElemento));
            } catch (ServicesException e) {       
                e.printStackTrace();
            }
        }else{
            nuevoElemento.setTipo(elemento.getTipo());
            try {
                laboratorioServices.registrarElemento(nuevoElemento);
                laboratorioServices.asociarEquipo(elemento.getIdEquipo(), laboratorioServices.buscarElemento(laboratorioServices.maxIdElemento()));
            } catch (ServicesException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void asocieEquipoaElemento(){
        try {
            laboratorioServices.asociarEquipo(idEquipo, elemento);
        } catch (ServicesException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * @return the nuevoElemento
     */
    public Elemento getNuevoElemento() {
        return nuevoElemento;
    }

    /**
     * @param nuevoElemento the nuevoElemento to set
     */
    public void setNuevoElemento(Elemento nuevoElemento) {
        this.nuevoElemento = nuevoElemento;
    }

    /**
     * @return the idEquipo
     */
    public Integer getIdEquipo() {
        return idEquipo;
    }

    /**
     * @param idEquipo the idEquipo to set
     */
    public void setIdEquipo(Integer idEquipo) {
        this.idEquipo = idEquipo;
    }
    
    public List<NovedadEquipo> novedadEquipo(){
        List<NovedadEquipo> ans=new ArrayList<>();                
                
        try {
            ans= laboratorioServices.buscarNovedadesPorEquipo(equipo.getId());
        } catch (ServicesException ex) {
            Logger.getLogger(EquipoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ans;
    }


}