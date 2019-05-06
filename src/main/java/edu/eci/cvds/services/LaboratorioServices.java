package edu.eci.cvds.services;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.entities.Elemento;
import edu.eci.cvds.entities.Equipo;
import edu.eci.cvds.entities.Tipo;
import edu.eci.cvds.entities.NovedadElemento;
import edu.eci.cvds.entities.NovedadEquipo;

public interface LaboratorioServices {

    public List<Elemento> buscarElementoPorEquipo(int idEquipo) throws ServicesException;

    public List<Elemento> buscarElementos() throws ServicesException;

    public Elemento buscarElemento(Integer id) throws ServicesException;

    public void registrarElemento(Elemento elemento) throws ServicesException;

    public List<Elemento> elementosDisponiblesPorTipo(String tipo) throws ServicesException;

    public List<Elemento> elementosDisponibles() throws ServicesException;

    public void asociarEquipo(int idEquipo,int id,Tipo tipo) throws ServicesException;

    public void desAsociarElemento(int id) throws ServicesException;
    
    public List<Equipo> buscarEquipoPorLab(String lab) throws ServicesException;

    public List<Equipo> buscarEquipos() throws ServicesException;

    public Equipo buscarEquipoPorId(Integer idEquipo) throws ServicesException;

    public void registrarEquipo(Equipo equipo) throws ServicesException;

    public int maxIdEquipo() throws ServicesException;

    public int maxIdElemento() throws ServicesException;
    
    public List<NovedadEquipo> buscarNovedadesPorEquipo(int idEquipo) throws ServicesException;
    
    public List<NovedadEquipo> buscarNovedadesDeEquipos() throws ServicesException;
    
    public void registrarNovedadEquipo(NovedadEquipo novedad) throws ServicesException;
    
    public List<NovedadElemento> buscarNovedadesDeElementosPorEquipos(int idEquipo) throws ServicesException;

    public List<NovedadElemento> buscarNovedadesDeElementosPorElementos(int idElemento) throws ServicesException;

    public List<NovedadElemento> buscarNovedadesDeElementos() throws ServicesException;
    
    public void registrarNovedadElemento(NovedadElemento novedad) throws ServicesException;

    public void darBajaEquipo(int id) throws ServicesException;
    
    public void darBajaElemento(int id) throws ServicesException;
    
}