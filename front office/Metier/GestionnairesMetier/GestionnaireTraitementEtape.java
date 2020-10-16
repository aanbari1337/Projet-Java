package GestionnairesMetier;

import java.util.ArrayList;

import BeansMetier.Document;
import DAO.TraitementEtapeDAO;
import Metier.Demande;

public class GestionnaireTraitementEtape {

	public static ArrayList<Demande> getAllDemandes() {
		
		return TraitementEtapeDAO.getDemandes();
		
	}
	
	/*public static ArrayList<Etape> getAllEtapes() {
		return TraitementEtapeDAO.getALLEtapes();
	}*/
	
	public static Boolean rejectDemande(String jeton,int idEtapeActuelle) {
		
		if (TraitementEtapeDAO.rejeterDemande(jeton))
			if(TraitementEtapeDAO.enregisterDataEtape(jeton, idEtapeActuelle, "rejeter"))
				return true;
		return false;
	}
	
	
	public static Boolean validerEtape(int idEtapeSuivante, String jeton,int idEtapeActuelle) {
		
		if(idEtapeSuivante == 0) {
			if(TraitementEtapeDAO.validerDemande(jeton))
				if(TraitementEtapeDAO.enregisterDataEtape(jeton, idEtapeActuelle, "valider")) {
					return true;
				}
		}
		else if(TraitementEtapeDAO.validerEtape(idEtapeSuivante, jeton)) {
			if(TraitementEtapeDAO.enregisterDataEtape(jeton, idEtapeActuelle, "valider")) {
				return true;
			}
		}
		return false;
	}
	
	public static Boolean refuserEtape(int idEtapePrecedente, String jeton, int idEtapeActuelle) {
		
		if(idEtapePrecedente == -1)
			if(TraitementEtapeDAO.rejeterDemande(jeton))
				if(TraitementEtapeDAO.enregisterDataEtape(jeton, idEtapeActuelle, "refuser"))
					return true;
		else if(TraitementEtapeDAO.refuserEtape(idEtapePrecedente, jeton))
			if(TraitementEtapeDAO.enregisterDataEtape(jeton, idEtapeActuelle, "refuser"))
				return true;
		return false;
	}
	
	public static Boolean miseAJour(ArrayList<Document> documents, String jeton, int idEtape) {
		
		if(TraitementEtapeDAO.miseAJour(documents, jeton, idEtape))
			if(TraitementEtapeDAO.miseAJourDemande(jeton))
				return true;
		return false;
	}
	
	public static Boolean commencerTraitement(String jeton,int idEtape, String cinEmploye) {
		
		return TraitementEtapeDAO.enregistrerDateDebutTrait(jeton, idEtape,cinEmploye);
	}
	
	
	public static Boolean enregistrerRapport(String jeton,int idEtape,String rapport, String decision) {
		
		return TraitementEtapeDAO.enregistrerRapport(jeton, idEtape, rapport, decision);
		
	}
	
	public static ArrayList<String> getDocMaj(String jeton,int idEtape){
		return TraitementEtapeDAO.getDocMaj(idEtape, jeton);
	}
	
}
