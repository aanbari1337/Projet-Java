package GestionnairesMetier;

import java.util.ArrayList;

import BeansMetier.Procedure;
import DAO.DAOTraitDemande;
import Metier.Demande;

public class GestionnaireTraitDemande {

	public static ArrayList<Demande> getAllDem(String Login){
		ArrayList<Procedure> listProc = GestionnaireRecuperationData.getALLProcedures();
		return DAOTraitDemande.getAllDemande(Login, listProc);
	}
	
	public static ArrayList<String> getLien(String jeton){
		return DAOTraitDemande.getLien(jeton);
	}
	
	public static boolean updateDemande(String jeton ,Boolean isArchived, String etat, int idEtape) {
		return DAOTraitDemande.updateDemande(jeton, isArchived,etat, idEtape);
	}
	
	public static boolean setDateDebutTrait(String jeton) {
		return DAOTraitDemande.setDateDebutTrait(jeton);
	}
	
	public static Demande getDemande(String jeton) {
		return DAOTraitDemande.getDemande(jeton);
	}
	public static ArrayList<Demande> consulteAllDemande(String login){
		ArrayList<Procedure> listProc = GestionnaireRecuperationData.getALLProcedures();
		return DAOTraitDemande.consulterAllDemande(login, listProc);
	}

}
