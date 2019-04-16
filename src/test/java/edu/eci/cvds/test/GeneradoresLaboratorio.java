/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.test;

import edu.eci.cvds.entities.Elemento;
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
     
       return cadenas(4,6).zip(cadenas(6,10), integers().between(1,100000),(name,des,iditem)-> new Elemento(iditem, Tipo.teclado, name, 0, des));
    }
    
    public static Gen<String> cadenas(int min,int max){
        return  strings().betweenCodePoints(97, 122).ofLengthBetween(min,max);
    }
    
     public static Gen<Elemento> pantallas(){
      
       return cadenas(4,6).zip(cadenas(6,10), integers().between(1,100000),(name,des,iditem)-> new Elemento(iditem, Tipo.pantalla, name, 0, des));
    }
     
    public static Gen<Elemento> mouses(){
       
       return cadenas(4,6).zip(cadenas(6,10),integers().between(1,100000),(name,des,iditem)-> new Elemento(iditem, Tipo.mouse, name, 0, des));
    }
    
     public static Gen<Elemento> torres(){
       
       return cadenas(4,6).zip(cadenas(6,10),integers().between(1,100000),(name,des,iditem)-> new Elemento(iditem, Tipo.torre, name, 0, des));
    }
     
     public static Gen<Elemento> elemAleatorio(){
       
       return cadenas(4,6).zip(cadenas(6,10),Generate.enumValues(Tipo.class),integers().between(1,100000),(name,des,tipo,iditem)-> new Elemento(iditem, tipo, name, 0, des));
     }
}
