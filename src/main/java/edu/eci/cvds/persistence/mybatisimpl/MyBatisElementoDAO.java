package edu.eci.cvds.persistence.mybatisimpl;

import java.util.List;

import com.google.inject.Inject;

import edu.eci.cvds.entities.Elemento;
import edu.eci.cvds.entities.Tipo;
import edu.eci.cvds.persistence.ElementoDAO;
import edu.eci.cvds.persistence.PersistenceException;
import edu.eci.cvds.persistence.mybatisimpl.mappers.ElementoMapper;

public class MyBatisElementoDAO implements ElementoDAO {

    @Inject
    private ElementoMapper elementoMapper;
    

	@Override
	public List<Elemento> buscarElementoPorEquipo(int idEquipo) throws PersistenceException {
		try {
			return elementoMapper.buscarElementoPorEquipo(idEquipo);
		} catch (Exception e) {
			throw new PersistenceException("Load all persistence error", e);
		}
	}
	
	@Override
	public List<Elemento> buscarElementos() throws PersistenceException {
		try {
			return elementoMapper.buscarElementos();
		} catch (Exception e) {
			throw new PersistenceException("Load all persistence error", e);
		}
    }
    
    @Override
	public void registrarElemento(Elemento elemento) throws PersistenceException {
		try {
			elementoMapper.registrarElemento(elemento);
		} catch (Exception e) {
			throw new PersistenceException(e.getMessage(), e);
		}
	}


	@Override
	public List<Elemento> elementosDisponiblesPorTipo(String tipo) throws PersistenceException {
		try {
			return elementoMapper.elementosDisponiblesPorTipo(tipo);
		} catch (Exception e) {
			throw new PersistenceException("Load all persistence error", e);
		}
	}

	@Override
	public List<Elemento> elementosDisponibles() throws PersistenceException {
		try {
			return elementoMapper.elementosDisponibles();
		} catch (Exception e) {
			throw new PersistenceException("Load all persistence error", e);
		}
	}
	@Override
	public void asociarEquipo(int idEquipo,int id)throws PersistenceException {
		try {
		 elementoMapper.asociarEquipo(idEquipo,id);
		} catch (Exception e) {
			throw new PersistenceException("Load all persistence error", e);
		}
	}

	@Override
	public int maxIdElemento() throws PersistenceException {
		try {
			return elementoMapper.maxIdElemento();
		   } catch (Exception e) {
			   throw new PersistenceException("Load all persistence error", e);
		   }
	}

	@Override
	public Elemento buscarElemento(Integer id) throws PersistenceException {
            Elemento el;
            try{
                el=elementoMapper.buscarElemento(id);
                if(el==null){
                   throw new PersistenceException("No existe el elemento");
                }     
            } catch (Exception e) {
                throw e;
            }               
                       
            return el;
           
	}

	@Override
	public void desAsociarElemento(int id) throws PersistenceException {
		try {
			 elementoMapper.desAsociarElemento(id);
			} catch (Exception e) {
			throw new PersistenceException("Load all persistence error", e);
		}
	}

	@Override
	public void darBajaElemento(int id) throws PersistenceException {
		try {
			elementoMapper.darBajaElemento(id);

	   } catch (Exception ex) {
		   throw new PersistenceException(ex.getMessage(), ex);
	   }
   
	}

	
	
	

}
