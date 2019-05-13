package edu.eci.cvds.persistence;

import java.util.List;

import edu.eci.cvds.entities.Laboratorio;
import edu.eci.cvds.services.ServicesException;

public interface LaboratorioDAO {

    public List<Laboratorio> buscarLaboratorios() throws ServicesException;
    public Laboratorio buscarLaboratorioPorID(Integer id) throws ServicesException;
    public void registrarLaboratorio(Laboratorio laboratorio) throws ServicesException;
    public int maxIdLaboratorio() throws ServicesException;
    public void darBajaLaboratorio(Integer id) throws ServicesException;

}