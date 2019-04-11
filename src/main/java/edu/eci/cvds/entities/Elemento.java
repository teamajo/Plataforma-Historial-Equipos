package edu.eci.cvds.entities;



/**
 * Clase que representa un elemento 
 */

 public class Elemento {
    private int id;
    private String name;
    private int idEquipo;
    private Tipo tipo;

    public Elemento(int id, String name, int idEquipo) {
        this.id = id;
        this.name = name;
        this.setIdEquipo(idEquipo);

    }

    

    public Elemento() {
        super();
    }



    /**
     * @return the idEquipo
     */
    public int getIdEquipo() {
        return idEquipo;
    }

    /**
     * @param idEquipo the idEquipo to set
     */
    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
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

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * @return the tipo
     */
    public Tipo getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

 }