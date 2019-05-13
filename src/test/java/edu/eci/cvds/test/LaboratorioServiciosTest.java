/*
  * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.test;

import com.google.inject.Inject;
import edu.eci.cvds.entities.Elemento;
import edu.eci.cvds.entities.Equipo;
import edu.eci.cvds.entities.Laboratorio;
import edu.eci.cvds.entities.NovedadEquipo;
import edu.eci.cvds.entities.Tipo;
import edu.eci.cvds.persistence.ElementoDAO;

import edu.eci.cvds.persistence.mybatisimpl.MyBatisElementoDAO;
import edu.eci.cvds.services.LaboratorioServices;
import edu.eci.cvds.services.LaboratorioServiciosFactory;
import edu.eci.cvds.services.ServicesException;
import edu.eci.cvds.services.impl.LaboratorioServicesImpl;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.ibatis.session.SqlSession;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


import org.junit.Test;
import static org.quicktheories.QuickTheory.qt;



/**
 *
 * @author julia
 */
public class LaboratorioServiciosTest {
    @Inject
    private SqlSession sqlSession;    
    
    private static int idEquimax=0;  
    private LaboratorioServices serviciosLab;
    private static boolean st=true;
    
    
    
    
    public LaboratorioServiciosTest(){
        serviciosLab=LaboratorioServiciosFactory.getInstance().getServiciosAlquilerTesting();
    }
    
    
    
    @Test
    public void agregarItemTest(){
        qt().forAll(GeneradoresLaboratorio.elemAleatorio()).check(
           (elem)->{              
            try {                      
                serviciosLab.registrarElemento(elem);                
                return true;
            } catch (ServicesException ex) { 
                
                return false;
            }
           }
                
        );        
            
    }
    /**
     * No se deberian poder hacer sets incorrectos
     */
    
    @Test
    public void setElemTest(){
        qt().forAll(GeneradoresLaboratorio.equipos(), GeneradoresLaboratorio.elemAleatorio()).check(
            (eq,el)->{
              try{
                switch(el.getTipo()){
                    case torre:
                        eq.setTeclado(el);
                    break;
                     case teclado:
                        eq.setTorre(el);
                    break;
                     case mouse:
                        eq.setPantalla(el);
                    break;
                    case pantalla:
                        eq.setMouse(el);
                    break;
                }
                return false;
              }catch(Exception e){
                return true;
              }              
                
            }
        );  
       
        
    }
   
    
    @Test
    public void agregarEquipoTest() {   
        
        qt().forAll(GeneradoresLaboratorio.completoEquipos()).check(
            (eq)->{                
                try {
                    serviciosLab.registrarEquipo(eq);
                    idEquimax++;
             
                    return true;
                } catch (ServicesException ex) {
                    Logger.getLogger(LaboratorioServiciosTest.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
               
            }
           
        );     
        try {
            for(int i=1; i<=idEquimax;i++){
                assert(serviciosLab.buscarEquipoPorId(i).getComponets().size()==4);  
            }
           assert(serviciosLab.buscarEquipos().size()==idEquimax);
           
           
        } catch (ServicesException ex) {            
            
            fail(getStackTrace(ex));
        }        
    }
 

    @Test
    public void etc(){     
    
        try {
            serviciosLab.registrarElemento(new Elemento(null, Tipo.mouse, "prueba", null, "desc"));
            int id =serviciosLab.maxIdElemento();
            Elemento e= serviciosLab.buscarElemento(id+1);       
            fail("");
        } catch (ServicesException ex) {     
           // Logger.getLogger(LaboratorioServiciosTest.class.getName()).log(Level.SEVERE, null, ex);
            assert(true);
        }
    }
    
    @Test
    public void novedadEquipoTest(){
        
        qt().forAll(GeneradoresLaboratorio.completoEquipos()).check(
            (eq)->{                
                try {
                    serviciosLab.registrarEquipo(eq);
                    idEquimax++;                         
                    return serviciosLab.buscarNovedadesDeElementosPorEquipos(idEquimax).size()>3;
                } catch (ServicesException ex) {
                    Logger.getLogger(LaboratorioServiciosTest.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
               
            }
           
        );
        //fail("");
        
    } 
    
    @Test
    public void darDeBajaTest(){
        qt().forAll(GeneradoresLaboratorio.completoEquipos()).check(
            (eq)->{                
                try {
                    serviciosLab.registrarEquipo(eq);
                    idEquimax++;   
                    boolean ans=serviciosLab.buscarEquipoPorId(idEquimax).isActivo()==true;
                    serviciosLab.darBajaEquipo(idEquimax);
                    return serviciosLab.buscarEquipoPorId(idEquimax).isActivo()==false && ans;
                    
                } catch (ServicesException ex) {
                    Logger.getLogger(LaboratorioServiciosTest.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
               
            }
           
        ); 
    }
    
    
    
    /*
    @Test
    public void noPoderAsociarTest(){
        
        qt().forAll(GeneradoresLaboratorio.completoEquipos()).check(
            (eq)->{                
                try {
                    serviciosLab.registrarEquipo(eq);
                    idEquimax++; 
                    Equipo eqb=serviciosLab.buscarEquipoPorId(idEquimax);
                    Elemento torre=eqb.getTorre();
           
                                     
                    serviciosLab.darBajaEquipo(eqb.getId());
                    
                    serviciosLab.asociarEquipo(eqb.getId(), torre);                   
                    
                    return true;
                    
                } catch (ServicesException ex) {
                    Logger.getLogger(LaboratorioServiciosTest.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }               
            }
           
        );
    }*/
    @Test
    public void pruebaLab(){
        try {
            java.util.Date fechaActual = new java.util.Date();
            Elemento torre=new Elemento(null, Tipo.torre, "abs", null, "asda");
            Elemento pantalla=new Elemento(null, Tipo.pantalla, "abs", null, "asda");
            Elemento mouse=new Elemento(null, Tipo.mouse, "abs", null, "asda");
            Elemento teclado=new Elemento(null, Tipo.teclado, "abs", null, "asda");
            Elemento torre2=new Elemento(null, Tipo.torre, "abs2", null, "asda");
            Elemento pantalla2=new Elemento(null, Tipo.pantalla, "abs2", null, "asda");
            Elemento mouse2=new Elemento(null, Tipo.mouse, "abs2", null, "asda");
            Elemento teclado2=new Elemento(null, Tipo.teclado, "abs2", null, "asda");
            
            Equipo eq=new Equipo(null, 2, torre, pantalla, mouse, teclado, "Preuba");
            serviciosLab.registrarEquipo(eq);
            idEquimax++; 
            
            Equipo eq2=new Equipo(null, 2, torre2, pantalla2, mouse2, teclado2, "Preuba2");
            serviciosLab.registrarEquipo(eq2);
            idEquimax++; 
            Laboratorio lab = new Laboratorio(null, "Prueba",fechaActual , null);
            serviciosLab.registrarLaboratorio(lab);

            Laboratorio lab2 = serviciosLab.buscarLaboratorioPorID(1);

            System.out.println(serviciosLab.buscarEquipoPorId(idEquimax).getlab()+" " + lab2.getId());
            
            serviciosLab.asociarEquipoAlab(1, lab2.getId());
            serviciosLab.asociarEquipoAlab(2, lab2.getId());

            Laboratorio lab3 = serviciosLab.buscarLaboratorioPorID(1);
            System.out.println(lab3.getEquipos().size());
            Equipo e2 =  lab3.getEquipos().get(0);
            Equipo e3 =  lab3.getEquipos().get(1);
            System.out.println(e2.toString()+" "+serviciosLab.buscarEquipoPorId(1).toString());
            System.out.println(e3.toString()+" "+serviciosLab.buscarEquipoPorId(2).toString());
            System.out.println(e2.getlab()+" "+serviciosLab.buscarEquipoPorId(idEquimax).getlab()+" " + lab3.getId());
            
            serviciosLab.darBajaLaboratorio(lab2.getId());
            System.out.println(e2.getlab()+" "+serviciosLab.buscarEquipoPorId(idEquimax).getlab()+" " + lab3.getId());
        } catch (ServicesException ex) {
            Logger.getLogger(LaboratorioServiciosTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
  
    
    
    
/*
    @Test
    public void agregarNovedadEquipoTest() throws PersistenceException{  
        Elemento torre = new Elemento(null,Tipo.torre,"Torre 1",123,"torre de 123");
        Elemento teclado = new Elemento(null,Tipo.teclado,"teclado 1",123,"teclado de 123");
        Elemento pantalla = new Elemento(null,Tipo.pantalla,"pantalla 1",123,"pantalla de 123");
        Elemento mouse = new Elemento(null,Tipo.mouse,"mouse 1",123,"mouse de 123");

        Equipo equipo = new Equipo(null,1,torre,pantalla,mouse,teclado,"name");
       
        try {
            serviciosLab.registrarEquipo(equipo);
        } catch (ServicesException ex) {            
            
            fail(getStackTrace(ex));
        }   
    }*/
    
    


    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    public static String getStackTrace(final Throwable throwable) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }
}
