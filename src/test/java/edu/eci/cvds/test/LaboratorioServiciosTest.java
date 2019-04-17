/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.test;
import com.google.inject.Inject;
import edu.eci.cvds.services.LaboratorioServices;
import edu.eci.cvds.services.LaboratorioServiciosFactory;
import edu.eci.cvds.services.ServicesException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.ibatis.session.SqlSession;


import org.junit.Test;
import static org.quicktheories.QuickTheory.qt;



/**
 *
 * @author julia
 */
public class LaboratorioServiciosTest {
    @Inject
    private SqlSession sqlSession;
    
    private static int idElemmax=0;
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
                idElemmax++;
                return true;
            } catch (ServicesException ex) {                 
                return false;
            }
           }
                
        );      
               
        try {
            assert(serviciosLab.buscarElementos().size()==idElemmax);
        } catch (ServicesException ex) {
            Logger.getLogger(LaboratorioServiciosTest.class.getName()).log(Level.SEVERE, null, ex);
        }
     
            
    }
    
   
    
    
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
