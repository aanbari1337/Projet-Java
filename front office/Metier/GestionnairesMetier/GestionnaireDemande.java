package GestionnairesMetier;

import java.util.ArrayList;

import Beans.DemandeDeposer;
import DAO.DAODemande;
import Metier.Demande;

public class GestionnaireDemande {
	
	public static boolean addDemande(DemandeDeposer d) {
		
		return (DAODemande.addDemande(new Demande(d)));
	}
	
	public static Demande getDemandeByCin(String jeton,String cin){
		return DAODemande.getDemandeByCin(jeton,cin);
	}
	
	
	public static ArrayList<String[]> infoMaj(String jeton,int id){
		return DAODemande.getInfoMaj(jeton, id);
	}
	
	public static boolean majDemande(ArrayList<String> link,String jeton,int id) {
		return DAODemande.updateDemandeMAj(link, jeton, id);
	}
}
