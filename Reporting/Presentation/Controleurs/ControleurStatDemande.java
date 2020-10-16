package Controleurs;

import java.util.ArrayList;
import java.util.HashMap;

import BeansMetier.Procedure;
import GestionnairesMetier.GestionnaireDataFromFront;
import GestionnairesMetier.GestionnaireRecuperationData;
import GestionnairesMetier.GestionnaireStatDemande;
import Metier.Demande;
import Vues.VueMoyenTraitement;
import Vues.VueStatDemandeTraite;

public class ControleurStatDemande {

	private VueStatDemandeTraite statDem;
	private VueMoyenTraitement moyTrait;
	private HashMap<String,Integer> Data;
	
	public void vueNbrDemandeTraite() {
		ArrayList<Demande> listeDem = GestionnaireDataFromFront.getAllDemandes();
		String[] nomProc = getNomProc(GestionnaireRecuperationData.getALLProcedures());
		
		
		Data = GestionnaireStatDemande.getData(listeDem, nomProc);
		statDem = new VueStatDemandeTraite(Data);
		this.setStatDem(statDem);
		statDem.setControl(this);
		statDem.setVisible(true);
	}

	public void vueMoyenTraite() {
		moyTrait = new VueMoyenTraitement();
		this.setMoyTrait(moyTrait);
		moyTrait.setControl(this);
		moyTrait.setVisible(true);
	}
	
	
	public String[] getNomProc(ArrayList<Procedure> listProc) {
		
		String[] nomProc = new String[listProc.size()];
		for (int i = 0; i < listProc.size(); i++) {
			nomProc[i] = listProc.get(i).getLibelle_proc();
		}
		return nomProc;
	}
	
	public VueStatDemandeTraite getStatDem() {
		return statDem;
	}

	public void setStatDem(VueStatDemandeTraite statDem) {
		this.statDem = statDem;
	}

	public VueMoyenTraitement getMoyTrait() {
		return moyTrait;
	}

	public void setMoyTrait(VueMoyenTraitement moyTrait) {
		this.moyTrait = moyTrait;
	}
	
	
}
