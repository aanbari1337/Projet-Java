package Models;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import Metier.Demande;

public class ModelEtape extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Demande> demandes;
	
	
	
	public ModelEtape(ArrayList<Demande> demandes) {
		super();
		this.demandes = demandes;
	}
	
	public ModelEtape() {
	}
	

	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public int getRowCount() {
		return demandes.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		
			switch (arg1) {
			case 0: return demandes.get(arg0).getJeton();
			case 1: return demandes.get(arg0).getCinCitoyen();
			case 2: return demandes.get(arg0).getId_proc();
			case 3: return demandes.get(arg0).getNomProc();
			case 4: return demandes.get(arg0).getDateDepo();
			default:return null;
		}
	}
	
	public Class<?> getColumnClass(int columnIndex){
		switch (columnIndex) {
			case 0: return String.class;
			case 1: return String.class;
			case 2: return Integer.class;
			case 3: return String.class;
			case 4: return LocalDateTime.class;
			default : return null;
		}
	}
	
	
	public String getColumnName(int column) {
		switch (column) {
			case 0: return "Jeton";
			case 1: return "Cin citoyen";
			case 2: return "Id procedure";
			case 3: return "libelle procedure";
			case 4: return "Date depo";
			default : return null;
		}
	}

	public ArrayList<Demande> getDemandes() {
		return demandes;
	}

	public void setDemandes(ArrayList<Demande> demandes) {
		this.demandes = demandes;
	}
	
	
}