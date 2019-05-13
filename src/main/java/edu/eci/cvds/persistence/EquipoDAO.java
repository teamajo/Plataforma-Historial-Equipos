package edu.eci.cvds.persistence;

import java.util.List;


import edu.eci.cvds.entities.Equipo;
import edu.eci.cvds.services.ServicesException;

public interface EquipoDAO{

    public List<Equipo> buscarEquipoPorLab(Integer lab) throws ServicesException;
    public List<Equipo> buscarEquipos() throws ServicesException;
    public void registrarEquipo(Equipo equipo) throws ServicesException;
    public Integer maxIdEquipo() throws ServicesException;
    public Equipo buscarEquipoPorId(Integer idEquipo) throws ServicesException;
    public void darBajaEquipo(Integer id) throws ServicesException;
    public void asociarEquipoAlab(Integer idEquipo,Integer id)throws ServicesException ;
    public void desAsociarEquipoAlab(Integer idEquipo) throws ServicesException;
    public List<Equipo> buscarEquiposDisponibles() throws ServicesException;

    
    


} 