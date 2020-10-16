package DAO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

import BeansMetier.Procedure;
import Metier.Demande;

public class DAOTraitDemande {

	public static ArrayList<Demande> getAllDemande(String Login, ArrayList<Procedure> proc){
		
		ArrayList<Demande> demandes = new ArrayList<Demande>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		DBCollection collection = ConnexionDAO.getInstance().getMongoDatabase().getCollection("demande");
		DBCursor cursor = collection.find();
		try {
		    while (cursor.hasNext())
		    {
		    	
		        Demande dem = new Demande();
		        dem.setCinCitoyen((String) cursor.next().get("cinCitoyen"));
		        dem.setDateDepo((LocalDateTime.parse((String)cursor.curr().get("dateDepo"),formatter)));
		        dem.setId_proc((int) cursor.curr().get("id_proc"));
		        dem.setIsArchived((Boolean) cursor.curr().get("isArchived"));
		        dem.setEtat((String) cursor.curr().get("etat"));
		        dem.setJeton((String) cursor.curr().get("jeton"));
		        dem.setNomProc((String) cursor.curr().get("nomProc"));
		        if(checkChef(Login, dem.getId_proc(), proc)==true)
		        	demandes.add(dem);
		    }
		} finally {
		    cursor.close();
		}
		return demandes;
	}
	
//	public static void addIdEtapeActuel() {
//		DBCollection collection = ConnexionDAO.getInstance().getMongoDatabase().getCollection("demande");
//		DBCursor cursor = collection.find();
//
//		try {
//		    while (cursor.hasNext()) 
//		    {
//		        Demande dem = new Demande();
//		        dem.setJeton((String) cursor.next().get("jeton"));
//		        System.out.println(dem.getJeton());
//		        BasicDBObject query = new BasicDBObject().append("jeton", dem.getJeton());
//		    	BasicDBObject document = new BasicDBObject();
//				document.append("idEtapeActuel", 0);
//				BasicDBObject setQuery = new BasicDBObject();
//				setQuery.append("$set", document);
//				collection.update(query, setQuery);
//		    }
//		} finally {
//		    cursor.close();
//		}
//	}
	
	private static boolean checkChef(String Login, int id_proc, ArrayList<Procedure> proc) {
		for (int i = 0; i < proc.size(); i++) {
			if(proc.get(i).getNum()==id_proc && proc.get(i).getCinEmploye().equals(Login))
				return true;		
		}
		return false;
	}
	public static Demande getDemande(String jeton) {
		
		DBCollection collection = ConnexionDAO.getInstance().getMongoDatabase().getCollection("demande");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    BasicDBObject whereQuery = new BasicDBObject().append("jeton", jeton);
	    DBCursor cursor = collection.find(whereQuery);
	    try {
		    Demande dem = new Demande();
	        dem.setCinCitoyen((String) cursor.next().get("cinCitoyen"));
	        dem.setDateDepo((LocalDateTime.parse((String)cursor.curr().get("dateDepo"),formatter)));
	        dem.setId_proc((int) cursor.curr().get("id_proc"));
	        dem.setIsArchived((Boolean) cursor.curr().get("isArchived"));
	        dem.setEtat((String) cursor.curr().get("etat"));
	        dem.setJeton((String) cursor.curr().get("jeton"));
	        dem.setNomProc((String) cursor.curr().get("nomProc"));
	        return dem;
	    }finally {
		    cursor.close();
		}
	}
	
	public static boolean updateDemande(String jeton, Boolean isArchived, String etat, int idEtape) {
		System.out.println(idEtape);
		DBCollection collection = ConnexionDAO.getInstance().getMongoDatabase().getCollection("demande");
		BasicDBObject query = new BasicDBObject().append("jeton", jeton);
		
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			BasicDBObject document = new BasicDBObject();
			document.append("isArchived", isArchived);
			document.append("etat", etat);
			document.append("idEtapeActuel", idEtape);
			if (isArchived==true)
				document.append("dateFinTraitement",LocalDateTime.now().format(formatter));
				
			BasicDBObject setQuery = new BasicDBObject();
			setQuery.append("$set", document);
			collection.update(query, setQuery);
		
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<String> getLien(String jeton){
		ArrayList<String> lien = new ArrayList<String>() ;
		DBCollection collection = ConnexionDAO.getInstance().getMongoDatabase().getCollection("demande");
		
		    BasicDBObject whereQuery = new BasicDBObject().append("jeton", jeton);
		    DBCursor cursor = collection.find(whereQuery);
		    lien = (ArrayList<String>) cursor.next().get("lienDoc");
		    
		return lien;
	}
	
	public static boolean setDateDebutTrait(String jeton) {
		DBCollection collection = ConnexionDAO.getInstance().getMongoDatabase().getCollection("demande");
		BasicDBObject query = new BasicDBObject().append("jeton", jeton);
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			BasicDBObject document = new BasicDBObject();
			document.append("dateDebutTraitement", LocalDateTime.now().format(formatter));	
			BasicDBObject setQuery = new BasicDBObject();
			setQuery.append("$set", document);
			collection.update(query, setQuery);
		
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static ArrayList<Demande> consulterAllDemande(String Login, ArrayList<Procedure> proc){
		
		ArrayList<Demande> demandes = new ArrayList<Demande>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		DBCollection collection = ConnexionDAO.getInstance().getMongoDatabase().getCollection("demande");
		DBCursor cursor = collection.find();
		try {
		    while (cursor.hasNext()) 
		    {
		        Demande dem = new Demande();
		        dem.setId_proc((int) cursor.next().get("id_proc"));
		        if(checkChef(Login, dem.getId_proc(), proc)==true) {
			        dem.setCinCitoyen((String) cursor.curr().get("cinCitoyen"));
			        dem.setDateDepo((LocalDateTime.parse((String)cursor.curr().get("dateDepo"),formatter))); 
			        dem.setIsArchived((Boolean) cursor.curr().get("isArchived"));
			        dem.setEtat((String) cursor.curr().get("etat"));
			        dem.setJeton((String) cursor.curr().get("jeton"));
			        dem.setNomProc((String) cursor.curr().get("nomProc"));
			        if(cursor.curr().get("idEtapeActuel")==null) {
			        	BasicDBObject query = new BasicDBObject().append("jeton", dem.getJeton());
			        	BasicDBObject document = new BasicDBObject();
			        	document.append("idEtapeActuel", 0);	
						BasicDBObject setQuery = new BasicDBObject();
						setQuery.append("$set", document);
						collection.update(query, setQuery);
			        }else {
			        	dem.setIdEtapeActuel((int)cursor.curr().get("idEtapeActuel"));
			        }
			        	
			        demandes.add(dem);
		        }
		    }
		} finally {
		    cursor.close();
		}
		return demandes;
	}
}
