package Models;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import BeansMetier.Employe;

public class ModelEmploye extends AbstractTableModel{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Employe> employes;
	
	
	
	public ModelEmploye(ArrayList<Employe> employes) {
		this.employes = employes;
	}

	@Override
	public int getColumnCount() {
		return 8;
	}

	@Override
	public int getRowCount() {
		return employes.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		switch(columnIndex) {
		
		case 0: return employes.get(rowIndex).getCin_emp();
		case 1: return employes.get(rowIndex).getNom();
		case 2: return employes.get(rowIndex).getPrenom();
		case 3: return employes.get(rowIndex).getDate_rec();
		case 4: return employes.get(rowIndex).getGrade();
		case 5: return employes.get(rowIndex).getDate_naissance();
		case 6: return employes.get(rowIndex).getSexe();
		case 7: return employes.get(rowIndex).getArchived();
		default:return null;
		}
	}
	
	public Class<?> getColumnClass(int columnIndex){
		
		switch(columnIndex) {
		
		case 0: return String.class;
		case 1: return String.class;
		case 2: return String.class;
		case 3: return java.sql.Date.class;
		case 4:	return String.class;
		case 5: return java.sql.Date.class;
		case 6: return Boolean.class;
		case 7: return Boolean.class;
		default: return null;
		}
	}
	
	public String getColumnName(int column) {
		
		switch(column) {
		
		case 0: return "Cin";
		case 1: return "Nom";
		case 2: return "Prenom";
		case 3: return "Date de recrutement";
		case 4:	return "Grade";
		case 5: return "Date de naissance";
		case 6: return "Sexe";
		case 7: return "Archive";
		default: return null;
		}
		
	}

	public ArrayList<Employe> getEmployes() {
		return employes;
	}

	public void setEmployes(ArrayList<Employe> employes) {
		this.employes = employes;
	}
	
	
}
