
package fr.epita.iam.exceptions;
public class EntitySearchException extends Exception {
	Object entity;

	/**
	 *
	 */
	public EntitySearchException(Object entity, Throwable cause) {
		this.entity = entity;
		initCause(cause);
	}

	public String getUserMessage() {
		return "the following entity search has failed :" + entity.toString();
	}

}
