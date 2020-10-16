package Models;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import Metier.Demande;

public class ModelTraitDemande extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Demande> listDemande;
	private String[] column = {"Jeton","Cin Citoyen","Date de Creation","Archivé","Etat"};
	
	public ModelTraitDemande(ArrayList<Demande> demandes) {
		super();
		this.listDemande = demandes;
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return listDemande.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Demande dem = listDemande.get(rowIndex);
		switch(columnIndex) {
		case 0 : return dem.getJeton();
		case 1 : return dem.getCinCitoyen();
		case 2 : return dem.getDateDepo();
		case 3 : return dem.getIsArchived();
		case 4 : return dem.getEtat();
		default : return null;
		}
	}
	
	@Override
	public Class<? extends Object> getColumnClass(int columnIndex) {
		switch(columnIndex) {
		case 0 : return String.class;
		case 1 : return String.class;
		case 2 : return LocalDateTime.class;
		case 3 : return Boolean.class;
		case 4 : return	String.class;
		default : return null;
		}
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		return this.column[columnIndex];
	}
	
	public ArrayList<Demande> getListDemande() {
		return listDemande;
	}

	public void setListDemande(ArrayList<Demande> listDemande) {
		this.listDemande = listDemande;
	}

	
}
