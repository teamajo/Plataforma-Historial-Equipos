package edu.eci.cvds.entities;



/**
 * Clase que representa un elemento 
 */

 public class Usuario {
    private int id;
    private String name;
    private String rol;

    public Usuario(int id, String name, String rol) {
        this.id = id;
        this.name = name;
        this.setRol(rol);

    }

    public Usuario() {
        super();
    }



    /**
     * @return the rol
     */
    public String getRol() {
        return rol;
    }

    /**
     * @param rol the idEquipo to set
     */
    public void setRol(String rol) {
        this.rol = rol;
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

 }