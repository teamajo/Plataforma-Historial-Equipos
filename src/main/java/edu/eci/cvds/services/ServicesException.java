package edu.eci.cvds.services;

public class ServicesException extends Exception {

    /**
	 * Default generated servial version id
	 */
	private static final long serialVersionUID = 2872498150117457223L;

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