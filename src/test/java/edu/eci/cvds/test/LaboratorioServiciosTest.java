/*
  * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.test;

import com.google.inject.Inject;
import edu.eci.cvds.entities.Elemento;
import edu.eci.cvds.entities.Equipo;
import edu.eci.cvds.entities.Tipo;

import edu.eci.cvds.persistence.PersistenceException;
import edu.eci.cvds.services.LaboratorioServices;
import edu.eci.cvds.services.LaboratorioServiciosFactory;
import edu.eci.cvds.services.ServicesException;
import java.io.PrintWriter;
import java.io.StringWriter;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.ibatis.session.SqlSession;

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
    public void agregarEquipoTest() throws PersistenceException{   
        
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
                assert(serviciosLab.buscarElementoPorEquipo(i).size()==4);  
            }
           assert(serviciosLab.buscarEquipos().size()==idEquimax);
           
        } catch (ServicesException ex) {            
            
            fail(getStackTrace(ex));
        }        
    }
 
    
    

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
