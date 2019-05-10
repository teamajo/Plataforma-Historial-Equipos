/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.test;

import edu.eci.cvds.entities.Elemento;
import edu.eci.cvds.entities.Equipo;
import edu.eci.cvds.entities.Tipo;
import edu.eci.cvds.persistence.PersistenceException;
import java.util.logging.Level;
import java.util.logging.Logger;


import org.quicktheories.core.Gen;
import org.quicktheories.generators.Generate;
import static org.quicktheories.generators.SourceDSL.integers;
import static org.quicktheories.generators.SourceDSL.strings;

/**
 *
 * @author julia
 */
public class GeneradoresLaboratorio {
   
    
    public static Gen<Elemento> teclados(){
     
       return cadenas(4,6).zip(cadenas(6,10),(name,des)-> new Elemento(null, Tipo.teclado, name, null, des));
    }
    
    public static Gen<String> cadenas(int min,int max){
        return  strings().betweenCodePoints(97, 122).ofLengthBetween(min,max);
    }
    
     public static Gen<Elemento> pantallas(){
      
       return cadenas(4,6).zip(cadenas(6,10), (name,des)-> new Elemento(null, Tipo.pantalla, name, null, des));
    }
     
    public static Gen<Elemento> mouses(){
       
       return cadenas(4,6).zip(cadenas(6,10),(name,des)-> new Elemento(null, Tipo.mouse, name, null, des));
    }
    
     public static Gen<Elemento> torres(){
       
       return cadenas(4,6).zip(cadenas(6,10),(name,des)-> new Elemento(null, Tipo.torre, name, null, des));
    }
     
     public static Gen<Elemento> elemAleatorio(){
       
       return cadenas(4,6).zip(cadenas(6,10),Generate.enumValues(Tipo.class),(name,des,tipo)-> new Elemento(null, tipo, name, null, des));
     }
     
     public static Gen<Equipo> equipos(){
        return cadenas(6,10).map((lab)-> new Equipo(null, lab));                 
     }
     
     public static Gen<Equipo> completoEquipos() {
       return equipos().zip(pantallas(), mouses(), torres(), teclados(), (eq,p,m,to,te)->{
           try {
            eq.setMouse(m);
            eq.setPantalla(p);
            eq.setTeclado(te);
            eq.setTorre(to);
         } catch (PersistenceException e) {
            // TODO Auto-generated catch block
            Logger.getLogger(LaboratorioServiciosTest.class.getName()).log(Level.SEVERE, null, e);
         }
         return eq;
           
       });
     }
}
