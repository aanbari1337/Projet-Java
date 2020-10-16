package Contoleurs;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import Beans.BeansAjoutEtape;
import BeansMetier.Etape;
import GestionnairesMetier.GestionnaireEtape;
import Vues.VueAjoutEtape;
import Vues.VueEditEtape;
import Vues.VueEditProc;

public class controleurGestEtape {

	VueEditProc proc;
	VueAjoutEtape vueEtape;
	BeansAjoutEtape beans;
	VueEditEtape editEtape;
	Etape etape;
	private int id_proc;
	
	public controleurGestEtape() {
		super();
	}
	
	public void generateVueAjoutEtape(VueEditProc procedure, int id) {
	
		this.id_proc = id;
		this.setProc(procedure);
		vueEtape = new VueAjoutEtape(GestionnaireEtape.getCINEmp());
		vueEtape.setControle(this);
		this.setVueEtape(vueEtape);
		vueEtape.setVisible(true);
	}
	
	public void generateVueEditEtape(int id_etape, JPanel pan1) {
		editEtape = new VueEditEtape(id_etape, GestionnaireEtape.SearchEtapeById(id_etape), 
				GestionnaireEtape.getCINEmp(), pan1);
		this.setEditEtape(editEtape);
		editEtape.setControle(this);
		editEtape.setVisible(true);
	}
	public boolean AjoutEtape() {
		etape = getBeans(vueEtape.getBeansajout());
		if(GestionnaireEtape.checkLibelle(etape.getLibelle(),-1)==false)
			return false;
		GestionnaireEtape.ajoutEtape(etape);
		etape.setId_etape(getIdEtape(etape.getLibelle()));
		proc.setControle(this);
		proc.etape(etape);//// pour ajouter l'etape dans le panel centrale
		
		return true;
	}
	
	public boolean editEtape(int id_etape, JPanel pan1) {
		etape = getBeans(editEtape.getBeansajout());

		if(GestionnaireEtape.checkLibelle(etape.getLibelle(),id_etape)==false)
			return false;
		GestionnaireEtape.updateEtape(etape, id_etape);
		pan1.setBorder(BorderFactory.createTitledBorder(etape.getLibelle()));
		pan1.repaint();
		return true;
	}
	
	public int getIdEtape(String libelle) {
		int id = GestionnaireEtape.getIdetape(libelle);
		return id;
	}
	
	///// getting etape with the same id_procedure
	public ArrayList<Etape> getAllEtape(int id_proc) {	
		return GestionnaireEtape.getAll(id_proc);
	}
	
	//// beans to metier
	public Etape getBeans(BeansAjoutEtape beans) {
		this.beans = beans;
		etape = new Etape();
		etape.setLibelle(beans.getLibelle());
		etape.setDescription(beans.getDescription());
		etape.setCinEmploye(beans.getEmploye());
		etape.setId_proc(id_proc);

		return (etape);
	}
	
	
	///Gestionnaire:DeleteEtape
	public void gestionnaireDelete(int id_etape) {
		GestionnaireEtape.DeleteEtape(id_etape);
	}
	
	public VueEditProc getProc() {
		return proc;
	}

	public void setProc(VueEditProc proc) {
		this.proc = proc;
	}

	public VueAjoutEtape getVueEtape() {
		return vueEtape;
	}

	public void setVueEtape(VueAjoutEtape vueEtape) {
		this.vueEtape = vueEtape;
	}

	public Etape getEtape() {
		return etape;
	}

	public void setEtape(Etape etape) {
		this.etape = etape;
	}

	public BeansAjoutEtape getBeans() {
		return beans;
	}

	public void setBeans(BeansAjoutEtape beans) {
		this.beans = beans;
	}

	public VueEditEtape getEditEtape() {
		return editEtape;
	}

	public void setEditEtape(VueEditEtape editEtape) {
		this.editEtape = editEtape;
	}


	
	
}
