package DAO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

import BeansMetier.Document;
import Metier.Demande;

public class TraitementEtapeDAO extends TratementEtapeAbstract{
	
	public static ArrayList<Demande> getDemandes(){
		
		ArrayList<Demande> demandes = new ArrayList<Demande>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		try {
		BasicDBObject query = new BasicDBObject();
		query.append("etat", "en cours de traitement");
		DBCollection collection = ConnexionDAO.getInstance().getMongoDatabase().getCollection("demande");
		DBCursor c = (DBCursor) collection.find(query).iterator();
		
		while(c.hasNext()) {
			
			Demande d = new Demande();
		
			
			d.setCinCitoyen((String)c.next().get("cinCitoyen"));
			d.setDateDepo(LocalDateTime.parse(c.curr().get("dateDepo").toString(),formatter));
			
			d.setEtat(String.valueOf((c.curr().get("etat"))));
			d.setId_proc((int)c.curr().get("id_proc"));
			d.setIdEtapeActuel((int)c.curr().get("idEtapeActuel"));
			d.setIsArchived((Boolean)c.curr().get("isArchived"));
			d.setJeton((String)c.curr().get("jeton"));
			@SuppressWarnings("unchecked")
			ArrayList<String> list = (ArrayList<String>) c.curr().get("lienDoc");
			d.setLienDoc(list);
			d.setNomProc((String)c.curr().get("nomProc"));
			demandes.add(d);
		}
		return demandes;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static Boolean validerEtape(int idEtapesuivante, String jeton) {
		
		BasicDBObject query = new BasicDBObject();
		BasicDBObject update = new BasicDBObject();
		try {
		DBCollection collection = ConnexionDAO.getInstance().getMongoDatabase().getCollection("demande");
		query.append("jeton", jeton);
		update.append("$set", new BasicDBObject().append("idEtapeActuel", idEtapesuivante));
		collection.update(query,update);
		return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static Boolean validerDemande(String jeton) {
		
		BasicDBObject query = new BasicDBObject();
		BasicDBObject update = new BasicDBObject();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		try {
		DBCollection collection = ConnexionDAO.getInstance().getMongoDatabase().getCollection("demande");
		query.append("jeton", jeton);
		update.append("$set", new BasicDBObject().append("dateFinTraitement", LocalDateTime.now().format(dateTimeFormatter)).append("etat", "validee"));
		collection.update(query,update);
		return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static Boolean refuserEtape(int idEtapePrecedanet, String jeton) {
		
		BasicDBObject query = new BasicDBObject();
		BasicDBObject update = new BasicDBObject();
		
		try {
		DBCollection collection = ConnexionDAO.getInstance().getMongoDatabase().getCollection("demande");
		query.append("jeton", jeton);
		update.append("$set",new BasicDBObject().append("idEtapeActuel", idEtapePrecedanet));
		collection.update(query, update);
		return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/*public static ArrayList<Etape> getALLEtapes() {
		Gson gson = new Gson();
		ArrayList<Etape> listEtp;
		try {
			BufferedReader br = new BufferedReader(new FileReader("../Data/etapes.json"));
			listEtp = gson.fromJson(br,new TypeToken<ArrayList<Etape>>(){}.getType());
			return listEtp;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}*/
	
	public static Boolean rejeterDemande(String jeton) {
		
		BasicDBObject query = new BasicDBObject();
		query.append("jeton", jeton);
		BasicDBObject update = new BasicDBObject();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		update.append("$set",new BasicDBObject().append("etat", "rejetee").append("dateFinTraitement", LocalDateTime.now().format(dateTimeFormatter)));
		try {
		DBCollection collection = ConnexionDAO.getInstance().getMongoDatabase().getCollection("demande");
		collection.update(query,update);
		return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static Boolean miseAJour(ArrayList<Document> documents, String jeton, int idEtape) {
		int i = 0;
		try {
		DBCollection collection = ConnexionDAO.getInstance().getMongoDatabase().getCollection("documentMaj");
		BasicDBObject document = new BasicDBObject();
		while(i < documents.size()) {
			
			document.put("libelle document", documents.get(i).getLibelle_doc());
			document.put("description document", documents.get(i).getDescriptionDoc());
			document.put("jeton",jeton);
			document.put("id Etape", idEtape);
			
			collection.insert(document);
			i++;
		}
		return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static Boolean miseAJourDemande(String jeton) {
		try {
			DBCollection collection = ConnexionDAO.getInstance().getMongoDatabase().getCollection("demande");
			BasicDBObject query = new BasicDBObject();
			query.append("jeton", jeton);
			BasicDBObject update = new BasicDBObject();
			update.append("$set",new BasicDBObject().append("etat", "mise a jour"));
			collection.update(query, update);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static Boolean enregisterDataEtape(String jeton, int idEtape, String decision) {
		
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		try {
			
			
			DBCollection collection = ConnexionDAO.getInstance().getMongoDatabase().getCollection("StatEtape");
			BasicDBObject query = new BasicDBObject();
			BasicDBObject update = new BasicDBObject();
			query.append("jeton", jeton).append("idEtape", idEtape);
			update.append("$set", new BasicDBObject().append("decision", decision).
					append("date de fin du traitement", LocalDateTime.now().format(dateTimeFormatter)));
			collection.update(query, update);
			return true;
			}catch (Exception e) {
				e.printStackTrace();
				return false;
			}
	}
	
	public static Boolean enregistrerDateDebutTrait(String jeton, int idEtape, String cinEmploye) {
	
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		try {
			
			DBCollection collection = ConnexionDAO.getInstance().getMongoDatabase().getCollection("StatEtape");
			BasicDBObject document = new BasicDBObject();
			document.put("jeton",jeton);
			document.put("idEtape",idEtape);
			document.put("date de debut du traitement",LocalDateTime.now().format(dateTimeFormatter));
			document.put("cinEmploye",cinEmploye);
			
			collection.insert(document);
			return true;
			}catch (Exception e) {
				e.printStackTrace();
				return false;
			}
	}
	
	public static Boolean enregistrerRapport(String jeton,int idEtape,String rapport, String decision) {
		try {
		DBCollection collection = ConnexionDAO.getInstance().getMongoDatabase().getCollection("Rapports");
		BasicDBObject document = new BasicDBObject();
		document.put("jeton",jeton);
		document.put("idEtapeActuel", idEtape);
		document.put("contenue du rapport", rapport);
		document.put("decision",decision);
		
		collection.insert(document);
		return true;
		
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
			
	}
	
	public static ArrayList<String> getDocMaj(int idEtape, String jeton){
		
		ArrayList<String> lienDoc = new ArrayList<String>();
		try {
		DBCollection collection = ConnexionDAO.getInstance().getMongoDatabase().getCollection("documentMaj");
		DBCursor c = (DBCursor) collection.find().iterator();
		BasicDBObject query = new BasicDBObject();
		query.append("jeton", jeton);
		query.append("id Etape", idEtape);
		DBCursor cursor = (DBCursor) collection.find(query).iterator();
		while(cursor.hasNext())
			lienDoc.add((String) cursor.next().get("lien"));
		return lienDoc;
		
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	


}
