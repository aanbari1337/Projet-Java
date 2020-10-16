package Contoleurs;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

import Beans.EditProc;
import BeansMetier.Procedure;
import GestionnairesMetier.GestionnaireDoc;
import GestionnairesMetier.GestionnaireEmp;
import GestionnairesMetier.GestionnaireEtape;
import GestionnairesMetier.GestionnaireProc;
import Models.ModelProc;
import Vues.VueEditProc;
import Vues.VueGestionProc;

public class ControleurGestionProc {

	private VueGestionProc vueGestioProc;
	private ModelProc modelProc;
	private VueEditProc editProc;
	private Procedure proc;
	private EditProc beansProc;
	private int id_proc;
	private controleurEditDoc controle;
	
	public ControleurGestionProc() {
		initModel();
	}
	
	public void vueEditProc() {
		int row = vueGestioProc.getTable().getSelectedRow();
		if (row < 0) {
			JOptionPane.showMessageDialog(null,"s'il vous plait selectionner une procedure");
			return;
		}
		row = vueGestioProc.getSorter().convertRowIndexToModel(row);
		id_proc = (int) modelProc.getValueAt(row, 0);
		editProc = new VueEditProc(id_proc, GestionnaireProc.searchById(id_proc), GestionnaireEmp.getAllChef());
		editProc.setControl(this);
		this.setEditProc(editProc);
		editProc.setVisible(true);
	}
	public void initModel() {
		this.modelProc = new ModelProc(GestionnaireProc.getAllProc());
	}
	
	
	public void  showVueGestionProc() {
		vueGestioProc = new VueGestionProc(this);
		vueGestioProc.setVisible(true);
	}

	public ModelProc getModelProc() {
		return modelProc;
	}

	public void setModelProc(ModelProc modelProc) {
		this.modelProc = modelProc;
	}
	
	public void deleteProc() {
		
		int row = vueGestioProc.getTable().getSelectedRow();
		row = vueGestioProc.getSorter().convertRowIndexToModel(row);
		int id = (int) modelProc.getValueAt(row, 0);
		try {
			int input = JOptionPane.showConfirmDialog(null, "etes vous sur de supprimer la procedure : "+ id +" ?","Suppression de procédure",
					JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
			if(input == 0) {
			modelProc.removeProc(row);
			GestionnaireProc.deleteProcedure(id);
			JOptionPane.showMessageDialog(null, "procedure supprimé");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void archivePoc() {
		int row = vueGestioProc.getTable().getSelectedRow();
		row = vueGestioProc.getSorter().convertRowIndexToModel(row);
		int id = (int) modelProc.getValueAt(row, 0);
		try {
			int input = JOptionPane.showConfirmDialog(null, "etes vous sur d'archiver la procedure : "+ id +" ?","Suppression de procédure",
					JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
			if(input == 0) {
			GestionnaireProc.archiverProcedure(id);
			modelProc.archiveProc(row);
			JOptionPane.showMessageDialog(null, "procedure archivé");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public int getIdProc() {
		
		ArrayList<Integer> ids = new ArrayList<Integer>();
		
		int size = vueGestioProc.getTable().getRowCount();
		if(size == 0)return 0;
		for (int i = 0; i<size;i++) {
			ids.add((int) modelProc.getValueAt(i, 0));
		}
		return Collections.max(ids);
	}
	
	
	public boolean editProcedure(int id_proc, String libelle) {
		proc = getBeans(editProc.getEditProc());
		if(GestionnaireProc.checkLibelle(proc.getLibelle_proc(),id_proc)==false)
			return false;
		controle = new controleurEditDoc();
		controle.RemoveDocFromDb(libelle);
		proc.setNbr_document(GestionnaireDoc.getAllDoc(id_proc).size());
		GestionnaireProc.updateProc(proc, id_proc);
		modelProc.setListePrc(GestionnaireProc.getAllProc());
		modelProc.fireTableDataChanged();
		return true;
	}
	
	public Procedure getBeans(EditProc beansProc) {
		this.beansProc = beansProc;
		proc = new Procedure();
		proc.setLibelle_proc(beansProc.getLibelle());
		proc.setCinEmploye(beansProc.getChef());
		proc.setNbr_document(GestionnaireDoc.getAllDoc(id_proc).size());
		proc.setNbrEtape(GestionnaireEtape.getAll(id_proc).size());
		return proc;
	}
	
	public VueEditProc getEditProc() {
		return editProc;
	}

	public void setEditProc(VueEditProc editProc) {
		this.editProc = editProc;
	}

	public Procedure getProc() {
		return proc;
	}

	public void setProc(Procedure proc) {
		this.proc = proc;
	}

	public EditProc getBeansProc() {
		return beansProc;
	}

	public void setBeansProc(EditProc beansProc) {
		this.beansProc = beansProc;
	}
	
}
