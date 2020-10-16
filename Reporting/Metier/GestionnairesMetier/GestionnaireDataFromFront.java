package GestionnairesMetier;

import java.util.ArrayList;

import BeansMetier.StatEtape;
import DAO.DAODataFromFront;
import Metier.Demande;

public class GestionnaireDataFromFront {

	public static ArrayList<Demande> getAllDemandes() {
		return DAODataFromFront.getALLDemande();
	}
	
	public static ArrayList<StatEtape> getAllStatEtape(){
		return DAODataFromFront.getAllStatEtape();
	}
}
