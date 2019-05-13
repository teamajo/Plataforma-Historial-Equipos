package edu.eci.cvds.persistence;

import java.util.List;

import edu.eci.cvds.entities.Equipo;
import edu.eci.cvds.entities.NovedadElemento;
import edu.eci.cvds.services.ServicesException;

public interface NovedadElementoDAO {

    public List<NovedadElemento> buscarNovedadesDeElementosPorEquipos(Integer idEquipo) throws ServicesException;
    public List<NovedadElemento> buscarNovedadesDeElementosPorElementos(Integer idElemento) throws ServicesException;
    public List<NovedadElemento> buscarNovedadesDeElementos() throws ServicesException;  
    public void registrarNovedadElemento(NovedadElemento novedad) throws ServicesException;

}