package DAO;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import BeansMetier.StatEtape;
import Metier.Demande;


public class DAODataFromFront {

	public static ArrayList<Demande> getALLDemande() {

		ArrayList<Demande> listDem = new ArrayList<Demande>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		JsonParser jsonParser = new JsonParser();
		try {

			Object obj = jsonParser.parse(new FileReader("../Data/demande.json"));
			JsonArray arr = (JsonArray) obj;
			
			for (int i = 0; i < arr.size(); i++) {
				JsonObject jsonObject = (JsonObject) arr.get(i);
				Demande dem = new Demande();
				dem.setJeton(jsonObject.get("jeton").getAsString());
				dem.setCinCitoyen(jsonObject.get("cinCitoyen").getAsString());
				dem.setNomProc(jsonObject.get("nomProc").getAsString());
				dem.setId_proc(jsonObject.get("id_proc").getAsInt());
				if(jsonObject.get("idEtapeActuel")!=null)
					dem.setIdEtapeActuel(jsonObject.get("idEtapeActuel").getAsInt());
				dem.setEtat(jsonObject.get("etat").getAsString());
				dem.setIsArchived(jsonObject.get("isArchived").getAsBoolean());
				dem.setDateDepo(LocalDateTime.parse(jsonObject.get("dateDepo").getAsString(), formatter));
				if(jsonObject.get("dateDebutTraitement")!=null)
					dem.setDateDebutTraitement(LocalDateTime.parse(jsonObject.get("dateDebutTraitement").getAsString(), formatter));
				else {
					dem.setDateDebutTraitement(null);
				}
				if(jsonObject.get("dateFinTraitement")!=null)
					dem.setDateFinTraitement(LocalDateTime.parse(jsonObject.get("dateFinTraitement").getAsString(), formatter));
				else {
					dem.setDateFinTraitement(null);
				}
				dem.setLienDoc(getLien(jsonObject.get("lienDoc").getAsJsonArray()));
				listDem.add(dem);
			}			
			Collections.sort(listDem);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return listDem;
	}
	
	private static ArrayList<String> getLien(JsonArray arr){
		ArrayList<String> LienDoc = new ArrayList<>();
		for (int i = 0; i < arr.size(); i++) {
			LienDoc.add(arr.get(i).getAsString());
		}
		return LienDoc;
	}
	
	public static ArrayList<StatEtape> getAllStatEtape(){
		ArrayList<StatEtape> listDem = new ArrayList<StatEtape>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		JsonParser jsonParser = new JsonParser();
		try {

			Object obj = jsonParser.parse(new FileReader("../Data/StatEtape.json"));
			JsonArray arr = (JsonArray) obj;
			for (int i = 0; i < arr.size(); i++) {
				JsonObject jsonObject = (JsonObject) arr.get(i);
				StatEtape etp = new StatEtape();
				etp.setJeton(jsonObject.get("jeton").getAsString());
				etp.setIdEtape(jsonObject.get("idEtape").getAsInt());
				etp.setCinEmploye(jsonObject.get("cinEmploye").getAsString());
				if(jsonObject.get("decision")!=null)
					etp.setEtat(jsonObject.get("decision").getAsString());
				if(jsonObject.get("date de debut du traitement") != null)
					etp.setDateDebutTraitement(LocalDateTime.parse(jsonObject.get("date de debut du traitement").getAsString(), formatter));
				if (jsonObject.get("date de fin du traitement") != null)
					etp.setDateFinTraitement(LocalDateTime.parse(jsonObject.get("date de fin du traitement").getAsString(), formatter));
				listDem.add(etp);
			}			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		Collections.sort(listDem);
		return listDem;
	}

}
