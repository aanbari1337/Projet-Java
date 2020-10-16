package DAO;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class ConnexionDAO {

	private static ConnexionDAO connexionDAO = null;
	private MongoClient mongoClient;
	private DB mongoDatabase;

	@SuppressWarnings("deprecation")
	private ConnexionDAO() {
		
		try {
		mongoClient = new MongoClient("localhost",27017);
		mongoDatabase = mongoClient.getDB("front");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ConnexionDAO getInstance(){
		
		if(connexionDAO == null)
			return new ConnexionDAO();
		return connexionDAO;
	}

	public DB getMongoDatabase() {
		return mongoDatabase;
	}

}
