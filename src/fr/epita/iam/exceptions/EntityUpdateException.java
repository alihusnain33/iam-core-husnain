package fr.epita.iam.exceptions;


public class EntityUpdateException extends Exception {
	
	Object entity;

	/**
	 *
	 */
	public EntityUpdateException(Object entity, Throwable cause) {
		this.entity = entity;
		initCause(cause);
	}

	public String getUserMessage() {
		return "ERROR occured while UPDATING the following entity " + entity.toString();
	}

}
