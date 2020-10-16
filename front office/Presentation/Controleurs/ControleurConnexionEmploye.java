package Controleurs;

import java.util.ArrayList;

import BeansMetier.Employe;
import BeansMetier.Etape;
import BeansMetier.Procedure;
import GestionnairesMetier.GestionnaireRecuperationData;
import Vues.LoginPage;
import Vues.VueAccueilChef;


public class ControleurConnexionEmploye {
	
	private ArrayList<Employe> employes;
	private ArrayList<Etape> etapes; 
	private ArrayList<Procedure> procedures;
	private LoginPage loginPage;
	
	public ControleurConnexionEmploye() {}
	
	
	public Boolean getBackData() {
		
		employes = GestionnaireRecuperationData.getALLEmployes();
		etapes = GestionnaireRecuperationData.getALLEtapes();
		procedures = GestionnaireRecuperationData.getALLProcedures();
		if(employes == null || procedures == null || etapes == null)
			return false;
		return true;
	}
	
	
	public Boolean connectEmploye(String login, String pass) {
		
		
		Employe employe = checkEmploye(login, pass);
		if(employe == null) {
			loginPage.dispose();
			return false;
		}
		if(employe.getGrade().equals("Responsable Etape")) {
			
			
			ContolleurTraitementEtape contolleurTraitementEtape = new ContolleurTraitementEtape();
			contolleurTraitementEtape.setEtapes(getEtapesEmploye(employe.getCin_emp()));
			contolleurTraitementEtape.setAllEtapes(etapes);
			contolleurTraitementEtape.genererVueListeDemande(employe);
		}
		else {
			new VueAccueilChef(login).setVisible(true);
		}
		return true;
	}
	
	public Employe checkEmploye(String login, String pass) {
		
		int i = 0;
		while(i < employes.size()) {
			if(login.equals(employes.get(i).getCin_emp()))
				if(pass.equals(Utils.Utils.cryptWithMD5(login)))
					return employes.get(i);
			i++;
		}
		return null;
	}


	public LoginPage getLoginPage() {
		return loginPage;
	}


	public void setLoginPage(LoginPage loginPage) {
		this.loginPage = loginPage;
	}
	
	
	public ArrayList<Etape> getEtapesEmploye(String cin){
		
		ArrayList<Etape> etapesEmploye = new ArrayList<Etape>();
		int i = 0;
		while(i < etapes.size()) {
			if(etapes.get(i).getCinEmploye().equals(cin))
				etapesEmploye.add(etapes.get(i));
			i++;
		}
		return etapesEmploye;
	}
	
	
	public Boolean procExisteInEtapes(int idProc) {
		
		int i = 0;
		while(i < etapes.size()) {
			if(idProc == etapes.get(i).getId_proc())
				return true;
			i++;
		}
		return false;
	}
	
	public void EmployeProc(){
		
		int i = 0;
		while (i < procedures.size()) {
			if(!procExisteInEtapes(procedures.get(i).getNum()))
				procedures.remove(i);
			i++;
		}
	}
	
	
	
	
	
	

}
