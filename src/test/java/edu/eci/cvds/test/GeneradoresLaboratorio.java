/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.test;

import edu.eci.cvds.entities.Elemento;
import edu.eci.cvds.entities.Equipo;
import edu.eci.cvds.entities.Tipo;
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
     
       return cadenas(4,6).zip(cadenas(6,10), integers().between(1,100000),(name,des,iditem)-> new Elemento(iditem, Tipo.teclado, name, null, des));
    }
    
    public static Gen<String> cadenas(int min,int max){
        return  strings().betweenCodePoints(97, 122).ofLengthBetween(min,max);
    }
    
     public static Gen<Elemento> pantallas(){
      
       return cadenas(4,6).zip(cadenas(6,10), integers().between(1,100000),(name,des,iditem)-> new Elemento(iditem, Tipo.pantalla, name, null, des));
    }
     
    public static Gen<Elemento> mouses(){
       
       return cadenas(4,6).zip(cadenas(6,10),integers().between(1,100000),(name,des,iditem)-> new Elemento(iditem, Tipo.mouse, name, null, des));
    }
    
     public static Gen<Elemento> torres(){
       
       return cadenas(4,6).zip(cadenas(6,10),integers().between(1,100000),(name,des,iditem)-> new Elemento(iditem, Tipo.torre, name, null, des));
    }
     
     public static Gen<Elemento> elemAleatorio(){
       
       return cadenas(4,6).zip(cadenas(6,10),Generate.enumValues(Tipo.class),integers().between(1,1000000),(name,des,tipo,iditem)-> new Elemento(iditem, tipo, name, null, des));
     }
     
     public static Gen<Equipo> equipos(){
        return cadenas(6,10).zip(integers().between(1,100000),(lab,id)-> new Equipo(id, lab));                 
     }
     
     public static Gen<Equipo> completoEquipos(){
       return equipos().zip(pantallas(), mouses(), torres(), teclados(), (eq,p,m,to,te)->{
           eq.setMouse(m);
           eq.setPantalla(p);
           eq.setTeclado(te);
           eq.setTorre(to);
           return eq;
       });
     }
}
