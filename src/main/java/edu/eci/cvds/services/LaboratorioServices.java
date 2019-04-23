package edu.eci.cvds.services;

import java.util.List;

import edu.eci.cvds.entities.Elemento;
import edu.eci.cvds.entities.Equipo;

public interface LaboratorioServices {

    public List<Elemento> buscarElementoPorEquipo(int idEquipo) throws ServicesException;

    public List<Elemento> buscarElementos() throws ServicesException;

    public void registrarElemento(Elemento elemento) throws ServicesException;

    public List<Elemento> elementosDisponibles() throws ServicesException;

    public void asociarEquipo(int idEquipo,int id) throws ServicesException;
    
    public List<Equipo> buscarEquipoPorLab(String lab) throws ServicesException;

    public List<Equipo> buscarEquipos() throws ServicesException;

    public void registrarEquipo(Equipo equipo) throws ServicesException;

    public int maxIdEquipo() throws ServicesException;

    public int maxIdElemento() throws ServicesException;
    
}