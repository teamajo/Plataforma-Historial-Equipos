package edu.eci.cvds.persistence.mybatisimpl;

import java.util.List;

import com.google.inject.Inject;

import edu.eci.cvds.entities.Equipo;
import edu.eci.cvds.persistence.EquipoDAO;
import edu.eci.cvds.persistence.PersistenceException;
import edu.eci.cvds.persistence.mybatisimpl.mappers.EquipoMapper;

public class MyBatisEquipoDAO implements EquipoDAO {

    @Inject
    private EquipoMapper equipoMapper;
    

	@Override
	public List<Equipo> buscarEquipoPorLab(String lab) throws PersistenceException {
		try {
			return equipoMapper.buscarEquipoPorLab(lab);
		} catch (Exception e) {
			throw new PersistenceException("Load all persistence error", e);
		}
	}
	
	@Override
	public List<Equipo> buscarEquipos() throws PersistenceException {
		try {
			return equipoMapper.buscarEquipos();
		} catch (Exception e) {
			throw new PersistenceException("Load all persistence error", e);
		}
    }
    
    @Override
	public void registrarEquipo(Equipo equipo) throws PersistenceException {
		try {
			equipoMapper.registrarEquipo(equipo);
		} catch (Exception e) {
			throw new PersistenceException("Load all persistence error", e);
		}
	}


}