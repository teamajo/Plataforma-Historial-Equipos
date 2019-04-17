package edu.eci.cvds.services;

import java.util.List;

import edu.eci.cvds.entities.Elemento;

public interface LaboratorioServices {

    public List<Elemento> buscarElementoPorEquipo(int idEquipo) throws ServicesException;

    public List<Elemento> buscarElementos() throws ServicesException;

    public void registrarElemento(Elemento elemento) throws ServicesException;

    public List<Elemento> elementosDisponibles() throws ServicesException;
    
}