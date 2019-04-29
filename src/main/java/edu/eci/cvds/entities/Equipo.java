package edu.eci.cvds.entities;

import java.util.ArrayList;
import java.util.List;

import edu.eci.cvds.persistence.PersistenceException;


/**
 * Clase que representa un elemento
 */

 public class Equipo {
    private Integer id;
    private String lab;
    private boolean activo;
    private Elemento torre;
    private Elemento pantalla;
    private Elemento mouse;
    private Elemento teclado;

    public Equipo(Integer id, String lab, Elemento torre, Elemento pantalla, Elemento mouse, Elemento teclado) throws PersistenceException {
        this.id = id;
        this.lab = lab;
        setTorre(torre);
        setPantalla(pantalla);
        setMouse(mouse);
        setTeclado(teclado);
        activo=true;
    }


    public Equipo(Integer id, String lab) {
        this.id = id;
        this.lab = lab;
        activo=true;
    }

    public Equipo() {
        super();
        activo=true;
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
    public String getlab() {
        return lab;
    }

    /**
     * 
     * @param lab
     */
    public final void setLab(String lab) {
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
    public final void setTeclado(Elemento teclado) throws PersistenceException{
       
        if (teclado.getTipo().equals(Tipo.teclado)){
            this.teclado = teclado;
        }else{
            throw new PersistenceException("no se puede asignar el elemento");
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
    public final void setMouse(Elemento mouse) throws PersistenceException {
        
        if (mouse.getTipo().equals(Tipo.mouse)){
            this.mouse = mouse;
        }else{
            throw new PersistenceException("no se puede asignar el elemento");
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
    public final void setPantalla(Elemento pantalla) throws PersistenceException {
        
        if (pantalla.getTipo().equals(Tipo.pantalla)){
            this.pantalla = pantalla;
        }else{
            throw new PersistenceException("no se puede asignar el elemento");
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
    public final void setTorre(Elemento torre) throws PersistenceException{
        
        if (torre.getTipo().equals(Tipo.torre)){
            this.torre = torre;
        }else{
            throw new PersistenceException("no se puede asignar el elemento");
        }
        
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