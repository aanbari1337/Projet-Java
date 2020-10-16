package GestionnairesMetier;

import java.util.ArrayList;

import BeansMetier.Etape;
import DAO.DAOEtape;

public class GestionnaireEtape {

	
	public static int getIdetape(String libelle) {
		int id = DAOEtape.getId(libelle);
		return id;
	}
	public static boolean ajoutEtape(Etape etap) {
		return DAOEtape.add(etap);
	}
	public static void DeleteEtape(int id_etape) {
		DAOEtape.delete(id_etape);
	}
	
	public static Etape SearchEtapeById(int id_etape) {
		return DAOEtape.searchById(id_etape);
	}
	
	public static boolean updateEtape(Etape etap, int id_etape) {
		
		return DAOEtape.update(etap, id_etape);
	}
	public static String[] getCINEmp() {
		return DAOEtape.getCINEmploye();
	}
	
	public static ArrayList<Etape> getAll(int id_proc){
		return DAOEtape.getAll(id_proc);
	}
	
	public static ArrayList<Etape> getAllEtape(){
		return DAOEtape.getAllEtape();
	}
	
	public static boolean checkLibelle(String libelle, int id_etape) {
		return DAOEtape.nbrOccurenceLibelle(libelle, id_etape);
	}
}
