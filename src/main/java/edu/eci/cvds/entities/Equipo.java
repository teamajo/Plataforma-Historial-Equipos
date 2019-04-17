package edu.eci.cvds.entities;



/**
 * Clase que representa un elemento 
 */

 public class Equipo {
    private int id;
    private String lab;

    public Equipo(int id, String lab) {
        this.id = id;
        this.lab = lab;
    }


    public Equipo() {
        super();
    }



    /**
     * @return the idEquipo
     */
    public int getId() {
        return id;
    }

    /**
     * @param idEquipo the idEquipo to set
     */
    public void setId(int id) {
        this.id = id;
    }

   

    /**
     * @return the lab
     */
    public String getlab() {
        return lab;
    }

    /**
     * @param set lab
     */
    public void setLab(String lab) {
        this.lab = lab;
    }

    
    
    @Override
    public String toString() {
        return "Item{" + ", id=" + id + "Laboratiorio="+lab+'}';
    }
 }