package edu.eci.cvds.persistence;

import java.util.List;

import edu.eci.cvds.entities.Equipo;
import edu.eci.cvds.entities.NovedadElemento;

public interface NovedadElementoDAO {

    public List<NovedadElemento> buscarNovedadesDeElementosPorEquipos(Integer idEquipo) throws PersistenceException;
    public List<NovedadElemento> buscarNovedadesDeElementosPorElementos(Integer idElemento) throws PersistenceException;
    public List<NovedadElemento> buscarNovedadesDeElementos() throws PersistenceException;  
    public void registrarNovedadElemento(NovedadElemento novedad) throws PersistenceException;

}