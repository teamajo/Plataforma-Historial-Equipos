package edu.eci.cvds.persistence.mybatisimpl;

import java.util.List;

import com.google.inject.Inject;

import edu.eci.cvds.entities.Equipo;
import edu.eci.cvds.persistence.EquipoDAO;
import edu.eci.cvds.persistence.mybatisimpl.mappers.EquipoMapper;
import edu.eci.cvds.services.ServicesException;


public class MyBatisEquipoDAO implements EquipoDAO {

	@Inject
	private EquipoMapper equipoMapper;

	@Override
	public List<Equipo> buscarEquipoPorLab(Integer lab) throws ServicesException {
		try {
			return equipoMapper.buscarEquipoPorLab(lab);
		} catch (Exception e) {
			throw new ServicesException(ServicesException.NO_EXISTE_LAB+lab);
		}
	}

	@Override
	public List<Equipo> buscarEquipos() throws ServicesException {
		try {
			return equipoMapper.buscarEquipos();
		} catch (Exception e) {
			throw new ServicesException(ServicesException.ERROR_BASE_DATOS);
		}
	}

	@Override
	public void registrarEquipo(Equipo equipo) throws ServicesException {
		try {
			equipoMapper.registrarEquipo(equipo);
		} catch (Exception e) {
			throw new ServicesException(ServicesException.VALORES_INVALIDOS);
		}
	}

	@Override
	public Integer maxIdEquipo() throws ServicesException {
		try {
			return equipoMapper.maxIdEquipo();
		} catch (Exception e) {
			throw new ServicesException(ServicesException.ERROR_BASE_DATOS);
		}
	}

	@Override
	public Equipo buscarEquipoPorId(Integer id) throws ServicesException {
            Equipo eq;
            try {
                    eq= equipoMapper.buscarEquipoPorId(id);
                    if (eq ==null){
                        throw new ServicesException(ServicesException.NO_EXISTE_ID_EQUIPO);
                    }
			 
		} catch (Exception e) {
                throw new ServicesException(ServicesException.ERROR_BASE_DATOS);
            }        
            return eq;
	}

	@Override
	public void darBajaEquipo(Integer id) throws ServicesException {
            Equipo eq;
            try {
                eq= equipoMapper.buscarEquipoPorId(id);
                if (eq ==null){
                    throw new ServicesException(ServicesException.NO_EXISTE_ID_EQUIPO);
                }
                equipoMapper.darBajaEquipo(id);
            } catch (Exception ex) {
                    throw new ServicesException(ServicesException.ERROR_BASE_DATOS);
            }
	}

	@Override
	public void asociarEquipoAlab(Integer idEquipo, Integer id) throws ServicesException {
            Equipo eq;
            try {
                eq= equipoMapper.buscarEquipoPorId(idEquipo);
                if (eq ==null){
                    throw new ServicesException(ServicesException.NO_EXISTE_ID_EQUIPO);
                }
                equipoMapper.asociarEquipoAlab(idEquipo,id);
	   } catch (Exception ex) {
		   throw new ServicesException(ServicesException.ERROR_BASE_DATOS);
	   }
	}

	@Override
	public void desAsociarEquipoAlab(Integer idEquipo) throws ServicesException {
            Equipo eq;
            try {
                eq= equipoMapper.buscarEquipoPorId(idEquipo);
                if (eq ==null){
                    throw new ServicesException(ServicesException.NO_EXISTE_ID_EQUIPO);
                }
			equipoMapper.desAsociarEquipoAlab(idEquipo);
	   } catch (Exception ex) {
		   throw new ServicesException(ServicesException.ERROR_BASE_DATOS);
	   }
	}

	@Override
	public List<Equipo> buscarEquiposDisponibles() throws ServicesException {
            try{
                return equipoMapper.buscarEquiposDisponibles();
	   } catch (Exception ex) {
		throw new ServicesException(ServicesException.NO_HAY_EQUIPOS_DISPONIBLES);
	   }
	}


}