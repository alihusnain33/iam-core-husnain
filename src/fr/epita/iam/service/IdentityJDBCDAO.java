/**
 * IdentityJDBCDAO perform all the functionality related to database
 */
package fr.epita.iam.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.exceptions.EntityCreationException;
import fr.epita.iam.exceptions.EntityDeletionException;
import fr.epita.iam.exceptions.EntitySearchException;
import fr.epita.iam.exceptions.EntityUpdateException;

public class IdentityJDBCDAO implements IdentityDAO {
	/**
	 * Declaration for database Connection Object 
	 */
	Connection connection;
	public Connection existingConnection;

	/**
	 * Implementation of create function to create identity in database
	 */
	@Override
	public void create(Identity identity) throws EntityCreationException{

		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement pstmt = connection
					.prepareStatement("INSERT INTO IDENTITIES(IDENTITY_UID, IDENTITY_DISPLAYNAME, IDENTITY_EMAIL) values (?,?,?) ");
			pstmt.setString(1, identity.getUid());
			pstmt.setString(2, identity.getDisplayName());
			pstmt.setString(3, identity.getEmail());
			pstmt.execute();

		} catch (ClassNotFoundException | SQLException e) {
			
			EntityCreationException businessException = new EntityCreationException(identity, e);

			throw businessException;
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (final SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}

	}

	/**
	 * Implementation of Update Function to update identity in database
	 */
	@Override
	public void update(Identity identity) throws EntityUpdateException {
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement pstmt = connection
					.prepareStatement("UPDATE IDENTITIES SET IDENTITY_DISPLAYNAME = ?, IDENTITY_EMAIL = ? WHERE IDENTITY_UID = ?");

			pstmt.setString(1, identity.getDisplayName());
			pstmt.setString(2, identity.getEmail());
			pstmt.setString(3, identity.getUid());
			pstmt.executeUpdate();

		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (final SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}

	/**
	 *  delete function to delete identity from database
	 */
	@Override
	public void delete(Identity identity) throws EntityDeletionException {
		Connection connection = null;

		try {
			connection = getConnection();
			PreparedStatement pstmt = connection.prepareStatement("DELETE FROM IDENTITIES WHERE IDENTITY_UID = ?");
			pstmt.setString(1, identity.getUid());
			pstmt.executeUpdate();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (final SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}

	}

	/**
	 * Implementation of SEARCH Function for searching identity in Database
	 */
	@Override
	public List<Identity> search(Identity criteria) throws EntitySearchException {

		List<Identity> identities = new ArrayList<>();
		Connection connection = null;
		try {
			connection = getConnection();
			final PreparedStatement pstmt = connection.prepareStatement(
					"SELECT IDENTITY_UID, IDENTITY_DISPLAYNAME, IDENTITY_EMAIL FROM IDENTITIES WHERE IDENTITY_DISPLAYNAME = ? OR IDENTITY_EMAIL = ? OR IDENTITY_UID = ? ");
			pstmt.setString(3, criteria.getUid());
			pstmt.setString(1, criteria.getDisplayName());
			pstmt.setString(2, criteria.getEmail());

			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				Identity identity = new Identity();
				identity.setDisplayName(resultSet.getString(2));
				identity.setEmail(resultSet.getString(3));
				identity.setUid(resultSet.getString(1));
				identities.add(identity);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// LOGGER.error("error while searching", e);
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (final SQLException e) {
				// LOGGER.error("unresolved error", e);
			}
		}

		return identities;
	}

	/**
	 * 
	 * @return existing connection
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private Connection getConnection() throws ClassNotFoundException, SQLException {

		try {
			String userName = "test";
			String password = "test";
			String connectionString = "jdbc:derby://localhost:1527/testInstance;create=true";
			existingConnection = DriverManager.getConnection(connectionString, userName, password);
		} catch (Exception e) {
			System.out.println(e);

		}

		return existingConnection;
	}

}

