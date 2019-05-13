package edu.eci.cvds.persistence;

import java.util.List;

import edu.eci.cvds.entities.NovedadEquipo;
import edu.eci.cvds.services.ServicesException;

public interface NovedadEquipoDAO {

    public List<NovedadEquipo> buscarNovedadesDeEquiposPorEquipos(Integer idEquipo) throws ServicesException;
    public List<NovedadEquipo> buscarNovedadesDeEquipos() throws ServicesException;  
    public void registrarNovedadEquipo(NovedadEquipo novedad) throws ServicesException;

}
