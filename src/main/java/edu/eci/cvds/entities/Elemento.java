package edu.eci.cvds.entities;



/**
 * Clase que representa un elemento 
 */

 public class Elemento {
    private Integer id;
    private String name;
    private Integer idEquipo;
    private Tipo tipo;
    private String descripcion;

    public Elemento(Integer id, Tipo tipo, String name, Integer idEquipo, String descripcion) {
        this.id = id;
        this.name = name;
        this.idEquipo=idEquipo;
        this.descripcion=descripcion;
        this.tipo=tipo;

    }
    
    public Elemento(Tipo t){
        id=null;
        this.tipo=t;
    }



    public Elemento() {
        super();
        id=null;
    }



    /**
     * @return the idEquipo
     */
    public Integer getIdEquipo() {
        return idEquipo;
    }

    /**
     * @param idEquipo the idEquipo to set
     */
    public void setIdEquipo(Integer idEquipo) {
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
     /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @Override
    public String toString() {
        return "Item{" + "tipo=" + tipo + ", id=" + id + ", nombre=" + name +", idEquipo="+idEquipo+", descripcion="+descripcion+'}';
    }
 }