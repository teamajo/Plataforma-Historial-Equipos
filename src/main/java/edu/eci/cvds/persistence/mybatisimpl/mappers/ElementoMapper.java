package edu.eci.cvds.persistence.mybatisimpl.mappers;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.entities.Elemento;
import edu.eci.cvds.entities.Tipo;
import edu.eci.cvds.services.ServicesException;


public interface ElementoMapper {

    public List<Elemento> buscarElementos() throws ServicesException;
    
    public void registrarElemento(Elemento elemento) throws ServicesException;

    public List<Elemento> elementosDisponiblesPorTipo(@Param("tipo")String tipo) throws ServicesException;

    public List<Elemento> elementosDisponibles() throws ServicesException;
    
    public void asociarEquipo(@Param("idEquipo") Integer idEquipo,@Param("id") Integer id) throws ServicesException;
    public int maxIdElemento() throws ServicesException;
    public Elemento buscarElemento(@Param("id")Integer id) throws ServicesException;
    public void desAsociarElemento(@Param("id")Integer id) throws ServicesException;
    public void darBajaElemento(@Param("id")Integer id) throws ServicesException;
	

}