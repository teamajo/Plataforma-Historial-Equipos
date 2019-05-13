package edu.eci.cvds.persistence;

import java.util.List;

import edu.eci.cvds.entities.Elemento;
import edu.eci.cvds.services.ServicesException;


public interface ElementoDAO{

   
    public List<Elemento> buscarElementos() throws ServicesException;
    public void registrarElemento(Elemento elemento) throws ServicesException;
	public List<Elemento> elementosDisponiblesPorTipo(String tipo) throws ServicesException;
	public List<Elemento> elementosDisponibles() throws ServicesException;
	public void asociarEquipo(Integer idEquipo,Integer id)throws ServicesException ;
	public Integer maxIdElemento() throws ServicesException;
	public Elemento buscarElemento(Integer id) throws ServicesException;
	public void desAsociarElemento(Integer id) throws ServicesException;
	public void darBajaElemento(Integer id) throws ServicesException;

	


} 