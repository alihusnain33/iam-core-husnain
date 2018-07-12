package fr.epita.iam.service;
/**
 * This class is use for  authentication of USER
 *
 */
public class Authenticator {
	
	public static boolean authenticate(String userName, String password) {
		return "admin".equals(userName) && "admin".equals(password);

	}

}
