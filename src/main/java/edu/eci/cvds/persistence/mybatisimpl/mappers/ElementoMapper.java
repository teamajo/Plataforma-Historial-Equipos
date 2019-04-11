package edu.eci.cvds.persistence.mybatisimpl.mappers;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.entities.Elemento;
import edu.eci.cvds.persistence.PersistenceException;

public interface ElementoMapper {

    public List<Elemento> buscarElementoPorEquipo(@Param("equipoId") int idEquipo) throws PersistenceException;
    public List<Elemento> buscarElementos() throws PersistenceException;


}