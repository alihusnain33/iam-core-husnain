package fr.epita.iam.service;

import java.util.Scanner;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.exceptions.EntityCreationException;
import fr.epita.iam.exceptions.EntityDeletionException;
import fr.epita.iam.exceptions.EntitySearchException;
import fr.epita.iam.exceptions.EntityUpdateException;
import fr.epita.iam.service.IdentityJDBCDAO;

/**
 * this Console operation class perform the following functionalities 
 * create,update,search and delete Entity
 */
public class ConsoleOperation {
	/**
	 *Insertion is a function use to insert in identity
	 *It takes the input from user; 
	 *Create instance of identityJDBCDAO and 
	 *calls the create function 
	 * @param scanner
	 */
	public static void insertion(Scanner scanner) {
		System.out.println("INSERT DESIRED IDENTITY");
		System.out.println("PLEASE ENTER YOUR DISPLAY NAME:");
		String displayName = scanner.nextLine();
		System.out.println("PLEASE ENTER YOUR EMAIL ID:");
		String email = scanner.nextLine();
		System.out.println("PLEASE ENTER YOUR USER ID:");
		String uid = scanner.nextLine();
		Identity identity = new Identity(displayName, email, uid);

		try {
			IdentityJDBCDAO createDAO = new IdentityJDBCDAO();
			createDAO.create(identity);
			System.out.println("YOUR DATA IS INSERTED SUCCESSFULLY");
		} catch (EntityCreationException e) {
			System.out.println("UNFORTUNATELY, IDENTITY COULD NOT BE INSERTED");
			e.printStackTrace();
		}

	}

	/**
	 * DeleteIdentity function is use to delete identity 
	 * takes the input from users
	 * create instance of identityJDBCDAO 
	 * calls the delete function
	 * @param scanner
	 */

	public static void DeleteIdentity(Scanner scanner) {
		System.out.println("IDENTITY TO BE DELETED");
		System.out.println("PLEASE ENTER THE IDENTITY UID TO DELETE:");
		String idToBeDeleted = scanner.nextLine();
		Identity identity1 = new Identity(null, null, idToBeDeleted);

		try {
			IdentityJDBCDAO delDAO = new IdentityJDBCDAO();
			delDAO.delete(identity1);
			System.out.println("YOUR SELECTED IDENTITY HAS BEEN DELETED");
		} catch (EntityDeletionException e) {
			System.out.println("SOME PROBLEM OCCURED IN DELETION OF IDENTITY, CONTACT ADMIN!");
			e.printStackTrace();
		}
	}

	/**
	 * UpdateIdentity function is use for updation 
	 * it's takes the input from users
	 * create instance of identityJDBCDAO and
	 * calls the update function
	 * @param scanner
	 */
	public static void UpdateIdentity(Scanner scanner) {

		System.out.println("IDENTITY TO BE UPDATED");
		System.out.println("ENTER UID OF IDENTITY WHICH YOU WANT TO MODIFY:");
		String idToBeUpdate = scanner.nextLine();
		System.out.println("ENTER DISPLAYNAME WHICH YOU WANT TO MODIFY:");
		String nameToBeUpdate = scanner.nextLine();
		System.out.println("ENTER EMAIL WHICH YOU WANT TO MODIFY:");
		String emailToBeUpdate = scanner.nextLine();
		Identity identity2 = new Identity(nameToBeUpdate, emailToBeUpdate, idToBeUpdate);
		try {

			IdentityJDBCDAO updateDAO = new IdentityJDBCDAO();
			updateDAO.update(identity2);
			System.out.println("YOUR DESIRED IDENTITY HAS BEEN UPDATED");
		} catch (EntityUpdateException e) {
			System.out.println("A PROBLEM OCCURED IN YOUR DESIRED IDENTITY UPDATION.CONTACT ADMIN!");
			e.printStackTrace();
		}
	}

	/**
	 * SearchIdentity function use to Search identity 
	 * Takes the input from user
	 * Create Instance of identityJDBCDAO and calls the search function
	 * @param scanner
	 */
	public static void searchIdentity(Scanner scanner) {
		System.out.println("IDENTITY TO BE SEARCHED");
		System.out.println("PLEASE ENTER UID OF IDENTITY TO BE SEARCHED:");
		String idToBeSearched = scanner.nextLine();
		Identity identity3 = new Identity(null, null, idToBeSearched);

		try {
			IdentityJDBCDAO searchDAO = new IdentityJDBCDAO();
			System.out.println("Desired Identity" + "=" + searchDAO.search(identity3));

		} catch (EntitySearchException e) {
			System.out.println(" ERROR OCCURED");
			e.printStackTrace();
		}
	
}
}
