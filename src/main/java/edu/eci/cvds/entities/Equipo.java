package edu.eci.cvds.entities;

import edu.eci.cvds.services.ServicesException;
import java.util.ArrayList;
import java.util.List;




/**
 * Clase que representa un elemento
 */

 public class Equipo {
    private Integer id;
    private Integer lab;
    private boolean activo;
    private Elemento torre;
    private Elemento pantalla;
    private Elemento mouse;
    private Elemento teclado;
    private String name;

    public Equipo(Integer id, Integer lab, Elemento torre, Elemento pantalla, Elemento mouse, Elemento teclado,String name)throws ServicesException {
        this.id = id;
        this.lab = lab;
        setTorre(torre);
        setPantalla(pantalla);
        setMouse(mouse);
        setTeclado(teclado);
        activo = true;
        setName(name);
    }

    

    public Equipo(Integer id, Integer lab) {
        this.id = id;
        this.lab = lab;
        activo=true;
    }

    public Equipo() throws ServicesException {       
        setTorre(new Elemento(Tipo.torre));      
        setPantalla(new Elemento(Tipo.pantalla));
        setMouse(new Elemento(Tipo.mouse));
        setTeclado(new Elemento(Tipo.teclado));
    }
  


    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

   

    /**
     * @return the lab
     */
    public Integer getlab() {
        return lab;
    }

    /**
     * 
     * @param lab
     */
    public final void setLab(Integer lab) {
        this.lab = lab;
    }

    
    /**
     * @return the teclado
     */
    public Elemento getTeclado() {
        return teclado;
    }

    /**
     * @param teclado the teclado to set
     */
    public final void setTeclado(Elemento teclado) throws ServicesException{
       
        if (teclado.getTipo().equals(Tipo.teclado)){
            this.teclado = teclado;
        }else{
            throw new ServicesException("no se puede asignar el elemento");
        }
    }

    /**
     * @return the mouse
     */
    public Elemento getMouse() {
        return mouse;
    }

   
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean isActivo() {
        return activo;
    }

    /**
     * @param mouse the mouse to set
     */
    public final void setMouse(Elemento mouse) throws ServicesException {
        
        if (mouse.getTipo().equals(Tipo.mouse)){
            this.mouse = mouse;
        }else{
            throw new ServicesException("no se puede asignar el elemento");
        }
        
    }

    /**
     * @return the pantalla
     */
    public Elemento getPantalla() {
        return pantalla;
    }

    /**
     * @param pantalla the pantalla to set
     */
    public final void setPantalla(Elemento pantalla) throws ServicesException {
        
        if (pantalla.getTipo().equals(Tipo.pantalla)){
            this.pantalla = pantalla;
        }else{
            throw new ServicesException("no se puede asignar el elemento");
        }
    }

    /**
     * @return the torre
     */
    public Elemento getTorre() {
        return torre;
    }

    /**
     * @param torre the torre to set
     */
    public final void setTorre(Elemento torre) throws ServicesException{
        
        if (torre.getTipo().equals(Tipo.torre)){
            this.torre = torre;
        }else{
            throw new ServicesException("no se puede asignar el elemento");
        }
        
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }




    public List<Elemento> getComponets(){
        List<Elemento> componentes =new ArrayList<>();
        componentes.add(torre);
        componentes.add(teclado);
        componentes.add(mouse);
        componentes.add(pantalla);
        return componentes;
    }

    
    
    @Override
    public String toString() {
        return "Equipo{id=" + id + ", Laboratiorio="+lab+'}';
    }
 }