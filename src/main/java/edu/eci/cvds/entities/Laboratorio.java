package edu.eci.cvds.entities;

import java.util.Date;
import java.util.List;

public class Laboratorio{

    private Integer id;
    private String name;
    private Boolean activo;
    private Date fechaCreacion;
    private Date fechaCierre;
    private List<Equipo> equipos;

    public Laboratorio() {

    }

    public Laboratorio(Integer id, String name, Boolean activo, Date fechaCreacion, Date fechaCierre) {
        setActivo(activo);
        setFechaCierre(fechaCierre);
        setId(id);
        setName(name);
        setFechaCreacion(fechaCreacion);
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return the fechaCierre
     */
    public Date getFechaCierre() {
        return fechaCierre;
    }

    /**
     * @param fechaCierre the fechaCierre to set
     */
    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    /**
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * @param fechaCreacion the fechaCreacion to set
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * @return the activo
     */
    public Boolean getActivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(Boolean activo) {
        this.activo = activo;
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
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the equipos
     */
    public List<Equipo> getEquipos() {
        return equipos;
    }

    /**
     * @param equipos the equipos to set
     */
    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }



}