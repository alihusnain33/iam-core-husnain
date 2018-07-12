package fr.epita.iam.launcher;

import java.util.Scanner;
import fr.epita.iam.service.Authenticator;
import fr.epita.iam.service.ConsoleOperation;

public class Launcher {

	public static void main(String[] args) {
		System.out.println("WELCOME IDENTITY MANAGEMENT SYSTEM");
		Scanner scanner = new Scanner(System.in);

		System.out.println("USER NAME :");
		String userName = scanner.nextLine();
		System.out.println("PASSWORD :");
		String password = scanner.nextLine();
		/**
		 * check for authentication ; username and password must be "admin"
		 */
		if (!Authenticator.authenticate(userName, password) == true) {

			System.out.println("USERNAME OR PASSWORD IS INCORRECT.TRY AGAIN!");

		} else {
			System.out.println("AUTHENTICATION SUCCESSFUL");
			String selection = "";
			
			while (!"5".equals(selection))
			{
				
				System.out.println("1. CREATE IDENTITY");
				System.out.println("2. UPDATE IDENTITY");
				System.out.println("3. DELETE IDENTITY");
				System.out.println("4. SEARCH IDENTITY");
				System.out.println("5. QUIT");
				System.out.println("INSERT YOUR SELECTION.... ");
				
                selection = scanner.nextLine();
				//selection = scanner.nextInt();
				switch (selection) {
				/**
				 *  Insertion Function call
				 */
				case "1":

					ConsoleOperation.insertion(scanner);
					break;
				/**
				 *  Update function call
				 				 */
				case "2":

					ConsoleOperation.UpdateIdentity(scanner);
					break;
				/**
				 *  DeleteIdentity function call 
				 */
				case "3":

					ConsoleOperation.DeleteIdentity(scanner);
					break;

				/**
				 *  Search Function Call
				 */
				case "4":

					ConsoleOperation.searchIdentity(scanner);
					break;

				case "5":
					System.out.println("GOOD BYE , SEE YOU AGAIN");
					break;

				default:

					System.out.println("YOUR SELECTED OPTION IS NOT ACCORDING TO GIVEN PARAMETERS");
					System.out.println("ENTER THE NUMEBER BETWEEN 1 to 4 FOR OPERATIONS");
					System.out.println("ENTER 5 TO QUIT");
					break;

				}
			}

		}

	}
}

