package edu.eci.cvds.services;

public class ServicesException extends Exception {

    /**
	 * Default generated servial version id
	 */private static final long serialVersionUID = 2872498150117457223L;
        
    public static final String ELMENTO_NO_ENCONTRADO = "Elemento no encontrado :";
    public static final String NO_HAY_ELEMENTOS = "No existen elementos" ;
    public static final String NO_HAY_EQUIPOS = "No existen equipos" ;
    public static final String VALORES_INVALIDOS = "Los valores ingresados son invalidos" ;
    public static final String NO_HAY_ELEMENTOS_POR_TIPO = "No existen elementos disponibles por tipo" ;
    public static final String NO_HAY_ELEMENTOS_DISPONIBLES = "No existen elementos disponibles" ;
    public static final String NO_HAY_EQUIPOS_DISPONIBLES = "No existen Equipos disponibles" ;
    public static final String NO_EXISTE_ID_EQUIPO = "No existe el equipo" ;
    public static final String NO_EXISTE_ID_EQUIPO_ELEMENTO = "No existe el equipo o elemento" ;
    public static final String NO_EXISTE_ID_Elemento = "No existe el elemento" ;
    public static final String NO_EXISTE_LAB = "No existe el laboratorio: " ;
    public static final String NO_EXISTE_LAB_EQ = "No existe el laboratorio o el equipo " ;
    public static final String NO_HAY_LABORATORIOS = "No existen Laboratorios" ;
    public static final String ERROR_BASE_DATOS = "Exite error en la base de datos" ;
    

    public ServicesException() {
    }

    public ServicesException(String message) {
        super(message);
    }

    public ServicesException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServicesException(Throwable cause) {
        super(cause);
    }
    
}