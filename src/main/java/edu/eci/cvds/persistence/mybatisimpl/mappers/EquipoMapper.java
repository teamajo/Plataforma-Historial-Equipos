package edu.eci.cvds.persistence.mybatisimpl.mappers;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.entities.Equipo;
import edu.eci.cvds.persistence.PersistenceException;

public interface EquipoMapper {

    public List<Equipo> buscarEquipoPorLab(@Param("lab") String lab) throws PersistenceException;
    public List<Equipo> buscarEquipos() throws PersistenceException;
    
    public void registrarEquipo(Equipo equipo) throws PersistenceException;



}