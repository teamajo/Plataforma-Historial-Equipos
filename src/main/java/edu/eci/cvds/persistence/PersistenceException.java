package edu.eci.cvds.persistence;

/**
 * Excepción de persistencia
 */
public class PersistenceException extends Exception {

    /**
	 * Default generated servial version id
	 */
	private static final long serialVersionUID = 7489763091920168317L;

	public PersistenceException(String message) {
        super(message);
    }

    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersistenceException(Throwable cause) {
        super(cause);
    }

    public PersistenceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}