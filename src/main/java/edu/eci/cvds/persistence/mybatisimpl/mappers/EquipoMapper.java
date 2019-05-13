package edu.eci.cvds.persistence.mybatisimpl.mappers;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.entities.Equipo;
import edu.eci.cvds.services.ServicesException;


public interface EquipoMapper {

    public List<Equipo> buscarEquipoPorLab(@Param("lab") Integer lab) throws ServicesException;
    public List<Equipo> buscarEquipos() throws ServicesException;
    
    public void registrarEquipo(Equipo equipo) throws ServicesException;
	public int maxIdEquipo() throws ServicesException;
	public Equipo buscarEquipoPorId(@Param("id") Integer id) throws ServicesException;
	public void darBajaEquipo(@Param("id") Integer id) throws ServicesException;
    public void asociarEquipoAlab(@Param("idEquipo")Integer idEquipo,@Param("id")Integer id)throws ServicesException ;
    public void desAsociarEquipoAlab(@Param("idEquipo")Integer idEquipo) throws ServicesException;
    public List<Equipo> buscarEquiposDisponibles() throws ServicesException;


}