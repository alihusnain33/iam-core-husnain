package fr.epita.iam.exceptions;

public class EntityCreationException extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Object entity;

	/**
	 *
	 */
	public EntityCreationException(Object entity, Throwable cause) {
		this.entity = entity;
		initCause(cause);
	}

	public String getUserMessage() {
		return "the following entity creation has failed :" + entity.toString();
	}

}
