package GestionnairesMetier;

import java.util.ArrayList;
import java.util.HashMap;

import BeansMetier.Employe;
import BeansMetier.Etape;
import BeansMetier.StatEtape;
import DAO.DAOStatMoyTrait;
import Metier.Demande;

public class GestionnaireMoyDureeTrait {

	public static HashMap<String, Double> getData(ArrayList<Demande> listeDem, String[] nomProc){
		return DAOStatMoyTrait.getDureeTraitProc(listeDem, nomProc);
	}
	
	public static HashMap<String, Double> getDataEmp(ArrayList<StatEtape> listStatEtape, String[] cinEmp){
		return DAOStatMoyTrait.getDureeTraitEmp(listStatEtape, cinEmp);
	}
	
	public static HashMap<Integer, Double> getDataEtape(ArrayList<StatEtape> listStatEtape, ArrayList<Etape> listEtape){
		return DAOStatMoyTrait.getDureeTraitEtape(listStatEtape, listEtape);
	}
}
