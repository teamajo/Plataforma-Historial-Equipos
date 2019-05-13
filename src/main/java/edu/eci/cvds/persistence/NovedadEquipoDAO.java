package edu.eci.cvds.persistence;

import java.util.List;

import edu.eci.cvds.entities.NovedadEquipo;

public interface NovedadEquipoDAO {

    public List<NovedadEquipo> buscarNovedadesDeEquiposPorEquipos(Integer idEquipo) throws PersistenceException;
    public List<NovedadEquipo> buscarNovedadesDeEquipos() throws PersistenceException;  
    public void registrarNovedadEquipo(NovedadEquipo novedad) throws PersistenceException;

}
