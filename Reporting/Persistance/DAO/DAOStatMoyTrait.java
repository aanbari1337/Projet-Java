package DAO;

import java.time.Duration;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import BeansMetier.Etape;
import BeansMetier.StatEtape;
import Metier.Demande;

public class DAOStatMoyTrait {

	public static HashMap<String, Double> getDureeTraitProc(ArrayList<Demande> listDem,String[] nomProc){
		HashMap<String, Double> data = new HashMap<String, Double>();
		
		Duration duree;
		for (int i = 0; i < nomProc.length; i++) {
			int count=0;
			double diff=0;
			for (int j = 0; j < listDem.size(); j++) {
				Temporal tmp1 = listDem.get(j).getDateDebutTraitement();
				Temporal tmp2 = listDem.get(j).getDateFinTraitement();
				if(listDem.get(j).getNomProc().equals(nomProc[i]) && tmp1 != null && tmp2!=null) {
					duree = Duration.between(tmp1, tmp2);
					diff += TimeUnit.SECONDS.toMinutes(duree.getSeconds());
					count++;
				}
			}

			data.put(nomProc[i], diff/count);
		}
		return data;
	}
	
	public static HashMap<String, Double> getDureeTraitEmp(ArrayList<StatEtape> listStatEtape, String[] cinEmp){
		HashMap<String, Double> Data = new HashMap<String, Double>();
		Duration duree;
		
		for (int i = 0; i < cinEmp.length; i++) {
			int count=0;
			double diff=0;;
			for (int j = 0; j < listStatEtape.size(); j++) {
				Temporal tmp1 = listStatEtape.get(j).getDateDebutTraitement();
				Temporal tmp2 = listStatEtape.get(j).getDateFinTraitement();
				if(listStatEtape.get(j).getCinEmploye().equals(cinEmp[i]) && tmp1!=null && tmp2!=null) {
					duree = Duration.between(tmp1,tmp2);
					diff += duree.getSeconds();
					count++;
				}
			}
		Data.put(cinEmp[i], diff/count);	
		}
		
		return Data;
	}
	
	public static HashMap<Integer, Double> getDureeTraitEtape(ArrayList<StatEtape> listStatEtape, ArrayList<Etape> listEtape){
		HashMap<Integer, Double> Data = new HashMap<Integer, Double>();
		Duration duree;
		
		for (int i = 0; i < listEtape.size(); i++) {
			
			int count=0;
			double diff=0;
			for (int j = 0; j < listStatEtape.size(); j++) {
				Temporal tmp1 = listStatEtape.get(j).getDateDebutTraitement();
				Temporal tmp2 = listStatEtape.get(j).getDateFinTraitement();
				if(listStatEtape.get(j).getIdEtape()==listEtape.get(i).getId_etape() && tmp1!=null && tmp2!=null) {
					duree = Duration.between(tmp1, tmp2);
					diff += duree.getSeconds();
					count++;
				}
			}
			Data.put(listEtape.get(i).getId_etape(), diff/count);
		}
		return Data;
	}
	
}
