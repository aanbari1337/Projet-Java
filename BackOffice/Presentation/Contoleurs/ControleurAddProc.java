package Contoleurs;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Beans.Doc;
import Beans.EtapeForm;
import BeansMetier.Document;
import BeansMetier.Etape;
import BeansMetier.Procedure;
import GestionnairesMetier.GestionnaireDoc;
import GestionnairesMetier.GestionnaireEmp;
import GestionnairesMetier.GestionnaireEtape;
import GestionnairesMetier.GestionnaireProc;
import Models.ModelDoc;
import Models.ModelEtape;
import Vues.VueAjoutProc;
import Vues.VueAjouterDocumentProc;
import Vues.VueAjouterEtapeProc;

public class ControleurAddProc {
	
	private VueAjoutProc vueAjoutProc;
	private Integer id;
	private VueAjouterDocumentProc vueDoc;
	private ModelDoc modelDoc;
	private ModelEtape modelEtape;
	private VueAjouterEtapeProc vueAddEtape;
	private ControleurGestionProc ctl;
	
	public ControleurAddProc(ControleurGestionProc ctl) {
		super();
		this.ctl = ctl;
	}
	
	
	public void showVue(int id) {
		vueAjoutProc = new VueAjoutProc(this, GestionnaireEmp.getAllChef());
		vueAjoutProc.setVisible(true);
		this.id = id;
		this.modelDoc = (ModelDoc) vueAjoutProc.getTableDoc().getModel();
		this.modelEtape = (ModelEtape) vueAjoutProc.getTabletape().getModel();
	}
	
	public void showVueAddDoc() {
		vueDoc = new VueAjouterDocumentProc(this, null);
		vueDoc.setVisible(true);
	}
	
	public void  showVueAddEtape() {
		vueAddEtape = new VueAjouterEtapeProc(this, GestionnaireEmp.getAllEmploye());
		vueAddEtape.setVisible(true);
	}
	
	public void documentajouter(String libelle,String descr) {
		modelDoc.addDoc(new Doc(libelle, descr));
		vueDoc.dispose();
	}
	
	public int etapeAdd(String libelle,String respo,String descri)
	{
		int r = -1;
			if(!isExiste(libelle)) {
				modelEtape.addEtape(new EtapeForm(libelle,descri,respo));
				r  = 0;
			}
			return r;
	}
	
	private boolean isExiste(String libelle) {
		int c = modelEtape.getRowCount();
		ArrayList<EtapeForm> lis = modelEtape.getListEtape();
		for(int i = 0; i<c;i++) {
			if(lis.get(i).getLibelle().equals(libelle))
				return true;
		}
		return false;
	}
	
	public void addProc(String libelle,String descr,String chef) {
		Procedure p = new Procedure();
		p.setNum(id + 1);
		p.setCinEmploye(chef);
		p.setLibelle_proc(libelle);
		p.setDescription(descr);
		p.setNbr_document(modelDoc.getRowCount());
		p.setNbrEtape(modelEtape.getRowCount());
		
		LocalDate current = LocalDate.now();
		p.setDate_cration_proc(java.sql.Date.valueOf(current));
		p.setModification_proc(java.sql.Date.valueOf(current));
		if(GestionnaireProc.addProcedure(p)==-1 || !addEtapeToDb(modelEtape.getListEtape()) || !addDocToDb(modelDoc.getListDoc()))
			JOptionPane.showMessageDialog(null, "information invalide");
		else {
			JOptionPane.showMessageDialog(null, "Procedure ajouter");
			vueAjoutProc.dispose();
			updateTable();
			}
	}
	
	private boolean addEtapeToDb(ArrayList<EtapeForm> list) {
		int nbEtape = modelEtape.getRowCount();
		for(int i=0; i<nbEtape;i++) {
			EtapeForm e = list.get(i);
			Etape etap = new Etape();
			etap.setLibelle(e.getLibelle());
			etap.setCinEmploye(e.getResp());
			etap.setDescription(e.getDescription());
			etap.setId_proc(id + 1);
			if(!GestionnaireEtape.ajoutEtape(etap))
				return false;
		}
		return true;
	}
	
	private boolean addDocToDb(ArrayList<Doc> list) {
		int nbDoc = modelDoc.getRowCount();
		for(int i=0; i<nbDoc;i++) {
			Doc d = list.get(i);
			if(GestionnaireDoc.addDoc(new Document(d.getLiblle(),d.getDecription(),id+1)) == -1)
				return false;
		}
		return true;
	}
	
	public void removeEtape() {
		int row =-1;
		row = vueAjoutProc.getTabletape().getSelectedRow();
		try {
			if(row == -1)
				JOptionPane.showMessageDialog(null, "aucune etape selectionnée");
			else
				modelEtape.removeEtape(row);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void removeDoc() {
		int row =-1;
		row = vueAjoutProc.getTableDoc().getSelectedRow();
		try {
			if(row == -1)
				JOptionPane.showMessageDialog(null, "aucun document selectionné");
			else
				modelDoc.removeDoc(row);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void updateTable() {
		ctl.getModelProc().setListePrc(GestionnaireProc.getAllProc());
		ctl.getModelProc().updateModel();
	}
}
