package Contoleurs;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import Beans.Doc;
import BeansMetier.Document;
import GestionnairesMetier.GestionnaireDoc;
import Models.ModelDoc;
import Vues.VueAjouterDocumentProc;
import Vues.VueEditDocument;
import Vues.VueEditProc;

public class controleurEditDoc {

	VueEditProc editProc;
	VueEditDocument editDoc;
	ControleurAddProc control;
	VueAjouterDocumentProc vueAddDoc;
	ModelDoc modelDoc;
	ArrayList<Doc> docs;
	String libelle;


	public void vueEditDoc(VueEditProc proc, int id_proc) {
		this.setEditProc(proc);
		editProc.setDocControl(this);
		int row = editProc.getTabDoc().getSelectedRow();
		if (row < 0) {
			JOptionPane.showMessageDialog(null,"s'il vous plait selectionner un document");
			return;
		}
		row = editProc.getSorter().convertRowIndexToModel(row);
		libelle = (String) modelDoc.getValueAt(row, 0);
		editDoc = new VueEditDocument(GestionnaireDoc.getDoc(libelle), id_proc);
		this.setEditDoc(editDoc);
		editDoc.setControlDoc(this);
		editDoc.setVisible(true);
	}
	
	public boolean editDocument(Doc document, String preLibelle, int id_proc) {
		
		if(GestionnaireDoc.checkLibelle(document.getLiblle(),preLibelle)==false)
			return false;
		libelle = GestionnaireDoc.updateDoc(document, preLibelle);
		docs = GestionnaireDoc.getAllDoc(id_proc);
		modelDoc.setListDoc(docs);
		modelDoc.fireTableDataChanged();
		return true;
	}
	
	public void RemoveDocFromDb(String libelle) {
		GestionnaireDoc.RemoveDoc(libelle);
	}
	
	public ModelDoc getModel(int id_proc) {		
		modelDoc = new ModelDoc();
		for(int i=0; i < GestionnaireDoc.getAllDoc(id_proc).size();i++)
			modelDoc.addDoc(GestionnaireDoc.getAllDoc(id_proc).get(i));
		return modelDoc;
	}
	
	public boolean addDoc(int id_proc,int start) {
		if(!addDocToDb(modelDoc.getListDoc(), id_proc, start))
			return false;
		return true;
	}
	
	private boolean addDocToDb(ArrayList<Doc> list, int id, int start) {

		int nbDoc = modelDoc.getRowCount();
		for(int i = start; i<nbDoc;i++) {
			Doc d = list.get(i);
			if(GestionnaireDoc.addDoc(new Document(d.getLiblle(),d.getDecription(),id)) == -1)
				return false;
		}
		return true;
	}
	
	public void AjoutDoc() {
		vueAddDoc = new VueAjouterDocumentProc(null, this);
		vueAddDoc.setVisible(true);
	}
	
	public void DeleteDoc(VueEditProc proc) {
		this.setEditProc(proc);
		editProc.setDocControl(this);
		int row = editProc.getTabDoc().getSelectedRow();
		libelle = (String)modelDoc.getValueAt(row, 0);
		System.out.println(libelle);
		if (row < 0) {
			JOptionPane.showMessageDialog(null,"s'il vous plait selectionner un document");
			return;
		}
		row = editProc.getSorter().convertRowIndexToModel(row);
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
	
	public void documentAjouter(String libelle,String descr) {
		modelDoc.addDoc(new Doc(libelle, descr));
		vueAddDoc.dispose();
	}
	
	public ModelDoc getModelDoc() {
		return modelDoc;
	}
	
	public void setModelDoc(ModelDoc modelDoc) {
		this.modelDoc = modelDoc;
	}

	public VueEditProc getEditProc() {
		return editProc;
	}

	public void setEditProc(VueEditProc editProc) {
		this.editProc = editProc;
	}

	public VueEditDocument getEditDoc() {
		return editDoc;
	}

	public void setEditDoc(VueEditDocument editDoc) {
		this.editDoc = editDoc;
	}

	public ArrayList<Doc> getDocs() {
		return docs;
	}

	public void setDocs(ArrayList<Doc> docs) {
		this.docs = docs;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	
	
	
	
	
}
