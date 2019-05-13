package edu.eci.cvds.persistence.mybatisimpl.mappers;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.entities.Equipo;
import edu.eci.cvds.persistence.PersistenceException;

public interface EquipoMapper {

    public List<Equipo> buscarEquipoPorLab(@Param("lab") String lab) throws PersistenceException;
    public List<Equipo> buscarEquipos() throws PersistenceException;
    
    public void registrarEquipo(Equipo equipo) throws PersistenceException;
	public int maxIdEquipo() throws PersistenceException;
	public Equipo buscarEquipoPorId(@Param("id") Integer id) throws PersistenceException;
	public void darBajaEquipo(@Param("id") Integer id) throws PersistenceException;
    public void asociarEquipoAlab(@Param("id")int idEquipo,@Param("id")int id)throws PersistenceException ;
    public void desAsociarEquipoAlab(@Param("id")int idEquipo) throws PersistenceException;


}