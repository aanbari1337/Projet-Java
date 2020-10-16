package DAO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

import Metier.Demande;

public class DAODemande {

	
	public static boolean addDemande(Demande d) {
		
		try {
			DBCollection collection = ConnexionDAO.getInstance().getMongoDatabase().getCollection("demande");
			BasicDBObject document = new BasicDBObject();
			
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			
			document.put("jeton", d.getJeton());
			document.put("cinCitoyen", d.getCinCitoyen());
			document.put("id_proc",d.getId_proc());
			document.put("nomProc",d.getNomProc());
			document.put("dateDepo",d.getDateDepo().format(dateTimeFormatter));
			document.put("etat",d.getEtat());
			document.put("lienDoc",d.getLienDoc());
			document.put("isArchived",d.getIsArchived());
			collection.insert(document);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static Demande getDemandeByCin(String jeton,String cin) {
		try {
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			DBCollection collection = ConnexionDAO.getInstance().getMongoDatabase().getCollection("demande");
			BasicDBObject query = new BasicDBObject();
			query.put("cinCitoyen", cin);
			query.put("jeton", jeton);
			DBCursor cursor = (DBCursor) collection.find(query).iterator();
			if(cursor.hasNext()) {
			        Demande dem = new Demande();
			        dem.setJeton((String) cursor.next().get("jeton"));
			        dem.setDateDepo((LocalDateTime.parse((String)cursor.curr().get("dateDepo"),formatter)));
			        dem.setId_proc((int) cursor.curr().get("id_proc"));
			        dem.setCinCitoyen((String) cursor.curr().get("cinCitoyen"));
			        dem.setIsArchived((Boolean) cursor.curr().get("isArchived"));
			        dem.setEtat((String) cursor.curr().get("etat"));
			       
			        dem.setNomProc((String) cursor.curr().get("nomProc"));
			        if(cursor.curr().get("idEtapeActuel") != null)
			        	dem.setIdEtapeActuel((int)cursor.curr().get("idEtapeActuel"));
			        if(cursor.curr().get("dateDebutTraitement") != null) {
			        	dem.setDateDebutTraitement((LocalDateTime.parse((String)cursor.curr().get("dateDebutTraitement"),formatter)));
			        }
			        
			 return dem;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<String[]> getInfoMaj(String jeton, int id){
		try {
			DBCollection collection = ConnexionDAO.getInstance().getMongoDatabase().getCollection("documentMaj");
			BasicDBObject query = new BasicDBObject();
			query.put("id Etape", id);
			query.put("jeton", jeton);
			DBCursor cursor = (DBCursor) collection.find(query).iterator();
			ArrayList<String[]> list = new ArrayList<String[]>();
			
			while(cursor.hasNext())
			{
				String[] doc = new String[2];
				doc[0] = (String) cursor.next().get("libelle document");
				doc[1] = (String) cursor.curr().get("description document");
				list.add(doc);
			}
			return list;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean updateDemandeMAj(ArrayList<String> lien,String jeton,int id) {
		try {
			DBCollection collection = ConnexionDAO.getInstance().getMongoDatabase().getCollection("documentMaj");
			DBCollection collection1 = ConnexionDAO.getInstance().getMongoDatabase().getCollection("demande");
			BasicDBObject query = new BasicDBObject();
			query.append("jeton", jeton);
			query.append("id Etape", id);
			BasicDBObject inserted = new BasicDBObject();
			inserted.append("$set", new BasicDBObject().append("lien", lien));
			collection.update(query, inserted);
			collection1.update(new BasicDBObject().append("jeton",jeton),
					new BasicDBObject().append("$set", new BasicDBObject().append("etat", "en cours de traitement")));
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
