package GestionnairesMetier;

import java.util.ArrayList;
import java.util.HashMap;

import DAO.DAOStatDemande;
import Metier.Demande;

public class GestionnaireStatDemande {

	public static HashMap<String, Integer> getData(ArrayList<Demande> listeDem, String[] nomProc){
		return DAOStatDemande.getData(listeDem, nomProc);
	}
}
