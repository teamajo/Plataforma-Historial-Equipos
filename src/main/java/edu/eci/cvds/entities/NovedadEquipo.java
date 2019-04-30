package edu.eci.cvds.entities;

import java.util.Date;


public class NovedadEquipo {
    private Integer id;
    private String titulo;
    private Integer idEquipo;
    private Date fecha;
    private String descripcion;
    private String responsable;
    
	public NovedadEquipo(Integer id, String titulo, Integer idEquipo, Date fecha, String descripcion, String responsable) {
		this.id = id;
		this.titulo = titulo;
		this.idEquipo = idEquipo;
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.responsable = responsable;
	}
	public NovedadEquipo() {
		super();
	}
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Integer getIdEquipo() {
		return idEquipo;
	}
	public void setIdEquipo(Integer idEquipo) {
		this.idEquipo = idEquipo;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getResponsable() {
		return responsable;
	}
	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}
	
    @Override
    public String toString() {
        return "NovedadEquipo{id=" + id + ", Titulo="+titulo+ ", idEquipo=" + idEquipo +'}';
    }

}
