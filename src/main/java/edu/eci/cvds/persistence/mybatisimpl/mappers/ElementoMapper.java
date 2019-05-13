package edu.eci.cvds.persistence.mybatisimpl.mappers;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.entities.Elemento;
import edu.eci.cvds.entities.Tipo;
import edu.eci.cvds.persistence.PersistenceException;

public interface ElementoMapper {

    public List<Elemento> buscarElementoPorEquipo(@Param("idEquipo") Integer idEquipo) throws PersistenceException;
    public List<Elemento> buscarElementos() throws PersistenceException;
    
    public void registrarElemento(Elemento elemento) throws PersistenceException;

    public List<Elemento> elementosDisponiblesPorTipo(@Param("tipo")String tipo) throws PersistenceException;

    public List<Elemento> elementosDisponibles() throws PersistenceException;
    
	public void asociarEquipo(@Param("idEquipo") Integer idEquipo,@Param("id") Integer id) throws PersistenceException;
	public int maxIdElemento() throws PersistenceException;
	public Elemento buscarElemento(@Param("id")Integer id) throws PersistenceException;
	public void desAsociarElemento(@Param("id")Integer id) throws PersistenceException;
	public void darBajaElemento(@Param("id")Integer id) throws PersistenceException;
	

}