package edu.eci.cvds.services;

import java.util.List;


import edu.eci.cvds.entities.Elemento;
import edu.eci.cvds.entities.Equipo;
import edu.eci.cvds.entities.Laboratorio;
import edu.eci.cvds.entities.NovedadElemento;
import edu.eci.cvds.entities.NovedadEquipo;

public interface LaboratorioServices {

    public List<Elemento> buscarElementoPorEquipo(Integer idEquipo) throws ServicesException;

    public List<Elemento> buscarElementos() throws ServicesException;

    public Elemento buscarElemento(Integer id) throws ServicesException;

    public void registrarElemento(Elemento elemento) throws ServicesException;

    public List<Elemento> elementosDisponiblesPorTipo(String tipo) throws ServicesException;

    public List<Elemento> elementosDisponibles() throws ServicesException;

    public void asociarEquipo(Integer idEquipo,Elemento elemento) throws ServicesException;

    public void desAsociarElemento(Integer id) throws ServicesException;
    
    public List<Equipo> buscarEquipoPorLab(String lab) throws ServicesException;

    public List<Equipo> buscarEquipos() throws ServicesException;

    public Equipo buscarEquipoPorId(Integer idEquipo) throws ServicesException;

    public void registrarEquipo(Equipo equipo) throws ServicesException;

    public Integer maxIdEquipo() throws ServicesException;

    public Integer maxIdElemento() throws ServicesException;
    
    public List<NovedadEquipo> buscarNovedadesPorEquipo(Integer idEquipo) throws ServicesException;
    
    public List<NovedadEquipo> buscarNovedadesDeEquipos() throws ServicesException;
    
    public void registrarNovedadEquipo(NovedadEquipo novedad) throws ServicesException;
    
    public List<NovedadElemento> buscarNovedadesDeElementosPorEquipos(Integer idEquipo) throws ServicesException;

    public List<NovedadElemento> buscarNovedadesDeElementosPorElementos(Integer idElemento) throws ServicesException;

    public List<NovedadElemento> buscarNovedadesDeElementos() throws ServicesException;
    
    public void registrarNovedadElemento(NovedadElemento novedad) throws ServicesException;

    public void darBajaEquipo(Integer id) throws ServicesException;
    
    public void darBajaElemento(Integer id) throws ServicesException;

    public List<Laboratorio> buscarLaboratorios() throws ServicesException ;

    public Laboratorio buscarLaboratorioPorID(Integer id) throws ServicesException;

    public Integer maxIdLaboratorio() throws ServicesException;

    public void registrarLaboratorio(Laboratorio laboratorio) throws ServicesException;

    public void darBajaLaboratorio(Integer id) throws ServicesException;
    
    public void asociarEquipoAlab(Integer idEquipo, Integer id) throws ServicesException;

    public void desAsociarEquipoAlab(Integer idEquipo) throws ServicesException ;

}