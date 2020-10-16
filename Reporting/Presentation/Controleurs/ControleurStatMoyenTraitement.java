package Controleurs;

import java.util.ArrayList;
import java.util.HashMap;

import BeansMetier.Etape;
import BeansMetier.StatEtape;
import GestionnairesMetier.GestionnaireDataFromFront;
import GestionnairesMetier.GestionnaireMoyDureeTrait;
import GestionnairesMetier.GestionnaireRecuperationData;
import Metier.Demande;
import Vues.VueStatMoyenTraitEmp;
import Vues.VueStatMoyenTraitEtape;
import Vues.VueStatMoyenTraitProc;

public class ControleurStatMoyenTraitement {

	private VueStatMoyenTraitProc moyProc;
	private VueStatMoyenTraitEmp moyEmp;
	private VueStatMoyenTraitEtape moyEtape;
	private HashMap<String,Double> DataProc;
	private HashMap<String,Double> DataEmp;
	private HashMap<String,Double> DataEtape;
	
	public void moyenTraitementProc() {
		ArrayList<Demande> listeDem = GestionnaireDataFromFront.getAllDemandes();
		String[] nomProc = getNomProc(GestionnaireDataFromFront.getAllDemandes());
		
		DataProc = GestionnaireMoyDureeTrait.getData(listeDem,nomProc);
		
		moyProc = new VueStatMoyenTraitProc(DataProc);
		this.setMoyProc(moyProc);
		moyProc.setControl(this);
		moyProc.setVisible(true);
	}
	
	public String[] getNomProc(ArrayList<Demande> listDem) {
		
		String[] nomProc = new String[listDem.size()];
		for (int i = 0; i < listDem.size(); i++) {
			nomProc[i] = listDem.get(i).getNomProc();
		}
		return nomProc;
	}
	
	public void moyenTraitementEmp() {
		ArrayList<StatEtape> listStatEtape = GestionnaireDataFromFront.getAllStatEtape();
		String[] cinEmploye = getCinEmp(GestionnaireDataFromFront.getAllStatEtape());
		
		DataEmp = GestionnaireMoyDureeTrait.getDataEmp(listStatEtape,cinEmploye);
		
		moyEmp = new VueStatMoyenTraitEmp(DataEmp);
		this.setMoyEmp(moyEmp);
		moyEmp.setControl(this);
		moyEmp.setVisible(true);
	}
	

	public String[] getCinEmp(ArrayList<StatEtape> listStatEtape) {
		
		String[] cinEmp = new String[listStatEtape.size()];
		for (int i = 0; i < listStatEtape.size(); i++) {
			cinEmp[i] = listStatEtape.get(i).getCinEmploye();
		}
		return cinEmp;
	}
	
	
	public void moyenTraitementEtape() {
		ArrayList<Etape> listEtape = GestionnaireRecuperationData.getALLEtapes();
		ArrayList<StatEtape> listStatEtape = GestionnaireDataFromFront.getAllStatEtape();
		
		HashMap<Integer, Double> statEtape = GestionnaireMoyDureeTrait.getDataEtape(listStatEtape, listEtape);
		DataEtape = getLibelleEtape(statEtape, listEtape);
		moyEtape = new VueStatMoyenTraitEtape(DataEtape);
		this.setMoyEtape(moyEtape);
		moyEtape.setControl(this);
		moyEtape.setVisible(true);
	}
	
	public HashMap<String, Double> getLibelleEtape(HashMap<Integer, Double> statEtape, ArrayList<Etape> listEtape){
		HashMap<String, Double> Data = new HashMap<String, Double>();
		int j=0;
		for(int i : statEtape.keySet()) {
			Data.put(listEtape.get(j).getLibelle()+"("+listEtape.get(j).getId_proc()+")", statEtape.get(i));
			j++;
		}
		return Data;
	}
	public VueStatMoyenTraitProc getMoyProc() {
		return moyProc;
	}
	public void setMoyProc(VueStatMoyenTraitProc moyProc) {
		this.moyProc = moyProc;
	}

	public VueStatMoyenTraitEmp getMoyEmp() {
		return moyEmp;
	}

	public void setMoyEmp(VueStatMoyenTraitEmp moyEmp) {
		this.moyEmp = moyEmp;
	}

	public VueStatMoyenTraitEtape getMoyEtape() {
		return moyEtape;
	}

	public void setMoyEtape(VueStatMoyenTraitEtape moyEtape) {
		this.moyEtape = moyEtape;
	}
	
	
}
