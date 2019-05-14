package edu.eci.cvds.managedbeans;


import edu.eci.cvds.entities.Equipo;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import javax.faces.bean.SessionScoped;

import edu.eci.cvds.entities.Laboratorio;
import edu.eci.cvds.entities.NovedadEquipo;
import edu.eci.cvds.services.LaboratorioServices;
import edu.eci.cvds.services.ServicesException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;


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
    
    private StreamedContent chart;

    private StreamedContent chartAct;
    
    private StreamedContent chartInac;
    
    public Laboratorio getLaboratorio() {
        return laboratorio;
    }
    
    public LaboratorioBean(){
        laboratorio=new Laboratorio();
    } 

    public void setLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }


    public List<Laboratorio> buscarLaboratorios(){
        if(laboratorios==null){
            try {
                laboratorios=laboratorioServices.buscarLaboratorios();
            } catch (ServicesException ex) {
                Logger.getLogger(LaboratorioBean.class.getName()).log(Level.SEVERE, null, ex);
            }
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
    
    public void registrarLab(){
        try {
            laboratorioServices.registrarLaboratorio(laboratorio);
            laboratorio=new Laboratorio();
        } catch (ServicesException ex) {
            /// Faces mensajes 
            Logger.getLogger(LaboratorioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void registrarLabEquipos(){
        ////
    }
    
 
         
    public StreamedContent getChart() {
        try {
            JFreeChart jfreechart = ChartFactory.createPieChart("Equipos", createDatasetLab(), true, true, false);
            File chartFile = new File("dynamichart");
            ChartUtilities.saveChartAsPNG(chartFile, jfreechart, 375, 300);
            chart = new DefaultStreamedContent(new FileInputStream(chartFile), "image/png");
            
        } catch (IOException ex) {
            Logger.getLogger(LaboratorioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return chart;
    }
    
    
      public StreamedContent getChartAct() {
        try {
            JFreeChart jfreechart = ChartFactory.createPieChart("Equipos Activos", createDatasetAct(), true, true, false);
            File chartFile = new File("dynamichart1");
            ChartUtilities.saveChartAsPNG(chartFile, jfreechart, 375, 300);
            chartAct = new DefaultStreamedContent(new FileInputStream(chartFile), "image/png");
            
        } catch (IOException ex) {
            Logger.getLogger(LaboratorioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return chartAct;
    }

    public StreamedContent getChartInac() {
        try {
            JFreeChart jfreechart = ChartFactory.createPieChart("Equipos Inactivo", createDatasetInac(), true, true, false);
            File chartFile = new File("dynamichart2");
            ChartUtilities.saveChartAsPNG(chartFile, jfreechart, 375, 300);
            chartInac = new DefaultStreamedContent(new FileInputStream(chartFile), "image/png");            
        } catch (IOException ex) {
            Logger.getLogger(LaboratorioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return chartInac;
    }
    
    
    private PieDataset createDatasetLab() {
         DefaultPieDataset dataset = new DefaultPieDataset();
        try {
           
            int activos=disponibles().size();
            int inactivos=laboratorioServices.maxIdEquipo()-activos;          
            dataset.setValue("Activos: "+activos, activos);               
            dataset.setValue("Inactivos: "+inactivos, inactivos);        
            
           
        } catch (ServicesException ex) {
            Logger.getLogger(LaboratorioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
         return dataset;
    }
    
      private PieDataset createDatasetAct() {
         DefaultPieDataset dataset = new DefaultPieDataset();         

        try {
            for (Laboratorio lab:laboratorioServices.buscarLaboratorios()){
                dataset.setValue(lab.getName()+": "+lab.activos(), lab.activos());           
            }
        } catch (ServicesException ex) {
            Logger.getLogger(LaboratorioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    
         return dataset;
    }
    
    
      
    public List<NovedadEquipo> novedadesLab(){
        // por implemnetar
        /// laboratorioServices.buscarNovedadesPorLaB(Integer.SIZE)
        return new ArrayList<>();
    }

    private PieDataset createDatasetInac() {
      DefaultPieDataset dataset = new DefaultPieDataset();    
      
        try {
            for (Laboratorio lab:laboratorioServices.buscarLaboratorios()){
                dataset.setValue(lab.getName()+": "+(lab.getEquipos().size()-lab.activos()), lab.getEquipos().size()-lab.activos());
            }
        } catch (ServicesException ex) {
            Logger.getLogger(LaboratorioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataset;
    }
    

}