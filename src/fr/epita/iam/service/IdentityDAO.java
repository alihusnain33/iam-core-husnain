package fr.epita.iam.service;

import java.util.List;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.exceptions.EntityCreationException;
import fr.epita.iam.exceptions.EntityDeletionException;
import fr.epita.iam.exceptions.EntitySearchException;
import fr.epita.iam.exceptions.EntityUpdateException;

public interface IdentityDAO {

	public void create(Identity identity) throws EntityCreationException;

	public void update(Identity identity) throws EntityUpdateException;

	public void delete(Identity identity) throws EntityDeletionException;

	public List<Identity> search(Identity criteria) throws EntitySearchException;
	
	
	/**
	 * This is an interface class for Create,Update,Delete and Search;
	 * only the methods are declared here with exception handling
	 * Identity object parameter is pass ineach method
	 */

}
