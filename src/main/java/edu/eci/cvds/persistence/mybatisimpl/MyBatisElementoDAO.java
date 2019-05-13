package edu.eci.cvds.persistence.mybatisimpl;

import java.util.List;

import com.google.inject.Inject;

import edu.eci.cvds.entities.Elemento;
import edu.eci.cvds.persistence.ElementoDAO;
import edu.eci.cvds.persistence.mybatisimpl.mappers.ElementoMapper;
import edu.eci.cvds.services.ServicesException;

public class MyBatisElementoDAO implements ElementoDAO {

    @Inject
    private ElementoMapper elementoMapper;
    

	
	@Override
	public List<Elemento> buscarElementos() throws ServicesException {
		try {
                    return elementoMapper.buscarElementos();
                    
		} catch (Exception e) {
                    throw  new ServicesException(ServicesException.ERROR_BASE_DATOS);
                }   
         
    }
    
    @Override
	public void registrarElemento(Elemento elemento) throws ServicesException {
		try {
			elementoMapper.registrarElemento(elemento);
		} catch (Exception e) {
			throw new ServicesException(ServicesException.VALORES_INVALIDOS);
		}
	}


	@Override
	public List<Elemento> elementosDisponiblesPorTipo(String tipo) throws ServicesException {
		try {
			return elementoMapper.elementosDisponiblesPorTipo(tipo);
		} catch (Exception e) {
			throw new ServicesException(ServicesException.ERROR_BASE_DATOS);
		}
	}

	@Override
	public List<Elemento> elementosDisponibles() throws ServicesException {
		try {
			return elementoMapper.elementosDisponibles();
		} catch (Exception e) {
			throw new ServicesException(ServicesException.ERROR_BASE_DATOS);
		}
	}
	@Override
	public void asociarEquipo(Integer idEquipo,Integer id)throws ServicesException {
		try {
                    if (buscarElemento(id) == null){
                         throw new ServicesException(ServicesException.NO_EXISTE_ID_EQUIPO_ELEMENTO);
                    }
                    elementoMapper.asociarEquipo(idEquipo,id);
		} catch (Exception e) {
			throw new ServicesException(ServicesException.NO_EXISTE_ID_EQUIPO);
		}
	}

	@Override
	public Integer maxIdElemento() throws ServicesException {
		try {
			return elementoMapper.maxIdElemento();
		   } catch (Exception e) {
			   throw new ServicesException(ServicesException.ERROR_BASE_DATOS);
		   }
	}

	@Override
	public Elemento buscarElemento(Integer id) throws ServicesException {
            Elemento el;
            try{
                el=elementoMapper.buscarElemento(id);
                if(el==null){
                   throw new ServicesException("No existe el elemento");
                }     
            } catch (Exception e) {
                throw new ServicesException(ServicesException.ERROR_BASE_DATOS);
            }               
                       
            return el;
           
	}

	@Override
	public void desAsociarElemento(Integer id) throws ServicesException {
            Elemento el;
            try {
                el=elementoMapper.buscarElemento(id);
                if(el==null){
                    throw new ServicesException(ServicesException.NO_EXISTE_ID_Elemento);
                }
                elementoMapper.desAsociarElemento(id);
                }catch (Exception e) {
                throw new ServicesException(ServicesException.ERROR_BASE_DATOS);
		}
	}

	@Override
	public void darBajaElemento(Integer id) throws ServicesException {
            Elemento el;
            try {
                el=elementoMapper.buscarElemento(id);
                if(el==null){
                    throw new ServicesException(ServicesException.NO_EXISTE_ID_Elemento);
                }
		
                elementoMapper.darBajaElemento(id);

	   } catch (Exception ex) {
		   throw new ServicesException(ServicesException.ERROR_BASE_DATOS);
	   }
   
	}

	
	
	

}
