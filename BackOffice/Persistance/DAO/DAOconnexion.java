package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOconnexion {
	
	private static DAOconnexion daoconnexion = null;
	private Connection connexion;
	
	private DAOconnexion(){

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connexion = DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/office","root","");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static DAOconnexion getInstance() throws ClassNotFoundException, SQLException {
		
		if(daoconnexion == null)
			return new DAOconnexion();
		return daoconnexion;
	}

	public Connection getConnexion() {
		return connexion;
	}
	
	
}
