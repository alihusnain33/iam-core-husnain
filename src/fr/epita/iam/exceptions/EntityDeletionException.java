package fr.epita.iam.exceptions;

public class EntityDeletionException extends Exception {
	
	Object entity;

	/**
	 *
	 */
	public EntityDeletionException(Object entity, Throwable cause) {
		this.entity = entity;
		initCause(cause);
	}

	public String getUserMessage() {
		return "Deletion of following Entity failed Problem occured while deleting :" + entity.toString();
	}
}
