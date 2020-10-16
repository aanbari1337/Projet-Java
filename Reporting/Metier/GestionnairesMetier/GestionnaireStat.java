package GestionnairesMetier;

import java.util.ArrayList;

import BeansMetier.StatEtape;
import DAO.DAODataFromFront;
import Metier.Demande;
import UtilClass.Statistiques;

public class GestionnaireStat {


	public static ArrayList<Statistiques> statProc(){
		ArrayList<Demande> allDem;
		try {
		allDem = DAODataFromFront.getALLDemande();
		if(allDem ==null)
			return null;
		}catch(Exception e) {
			return null;
		}
		ArrayList<Statistiques> stat = new ArrayList<Statistiques>();
		String libl = allDem.get(0).getNomProc();
		Statistiques p = null;
		int i=0,c=0;
		while(i<allDem.size()) {
			if(c==0)
				p = new Statistiques();
			if(allDem.get(i).getNomProc().equals(libl)) {
				c=1;
				if(allDem.get(i).getEtat().equals("validee") || allDem.get(i).getEtat().equals("rejetee")) {
					p.setLibelle(allDem.get(i).getNomProc());
					if(allDem.get(i).getEtat().equals("validee"))
						p.setNbV(p.getNbV()+1);
					else
						p.setNbR(p.getNbR()+1);
					p.setNbT(p.getNbR() + p.getNbV());
				}
				i++;
				if(i == allDem.size())
					stat.add(p);
			}else {
				libl = allDem.get(i).getNomProc();
				c = 0;
				if(p.getLibelle()!=null)
					stat.add(p);
			}
			
		}
		return stat;
	}
	
	public static ArrayList<Statistiques> statByEmp(){
		ArrayList<Statistiques> stat = new ArrayList<Statistiques>();
		ArrayList<StatEtape> list=null;
		try {
			list= DAODataFromFront.getAllStatEtape();
			if(list ==null)
				return null;
		}catch(Exception e) {
			return null;
		}
		
		String libl = list.get(0).getCinEmploye();
		Statistiques emp = null;
		int i=0,c=0;
		while(i<list.size()) {
			if(c == 0)
				emp = new Statistiques();
			if(list.get(i).getCinEmploye().equals(libl)) {
				c=1;
				if(list.get(i).getEtat() != null && (list.get(i).getEtat().equals("valider")|| list.get(i).getEtat().equals("rejeter")||list.get(i).getEtat().equals("refuser"))) {
					emp.setLibelle(list.get(i).getCinEmploye());
					if(list.get(i).getEtat().equals("valider"))
						emp.setNbV(emp.getNbV()+1);
					else
						emp.setNbR(emp.getNbR()+1);
					emp.setNbT(emp.getNbV()+emp.getNbR());	
				}
				i++;
				if(i == list.size())
					stat.add(emp);
			}else {
				libl = list.get(i).getCinEmploye();
				c=0;
				if(emp.getLibelle()!=null)
					stat.add(emp);
			}
		}
		return stat;
	}
	
}
