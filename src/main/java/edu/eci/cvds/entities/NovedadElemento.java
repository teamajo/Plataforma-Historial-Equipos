package edu.eci.cvds.entities;

import java.util.Date;


public class NovedadElemento {
    private Integer id;
    private String titulo;
    private Integer idEquipo;
    private Integer idElemento;
    private Date fecha;
    private String descripcion;
    private String responsable;
    
	public NovedadElemento(Integer id, String titulo, Integer idEquipo, Integer idElemento, Date fecha, String descripcion, String responsable) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.idEquipo = idEquipo;
		this.idElemento = idElemento;
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.responsable = responsable;
	}public NovedadElemento() {
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
	public Integer getIdElemento() {
		return idElemento;
	}
	public void setIdElemento(Integer idElemento) {
		this.idElemento = idElemento;
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
        return "NovedadEquipo{id=" + id + ", Titulo="+titulo+ ", idElemento=" + idElemento +'}';
    }
}
