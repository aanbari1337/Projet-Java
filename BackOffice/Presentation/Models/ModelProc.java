package Models;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import BeansMetier.Procedure;

public class ModelProc extends  AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 41984092984553659L;
	
	private ArrayList<Procedure> listePrc;
	private String[] column = {"Num","Libelle","Cin Chef",
			"Date de Creation","Nbr Document","Nbr Etape","Etat"};
	
	public ModelProc(ArrayList<Procedure> listePrc) {
		super();
		this.listePrc = listePrc;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 7;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return listePrc.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		Procedure proc = listePrc.get(arg0);
		switch(arg1) {
		case 0 : return proc.getNum();
		case 1 : return proc.getLibelle_proc();
		case 2 : return proc.getCinEmploye();
		case 3 : return proc.getDate_cration_proc();
		case 4 : return proc.getNbr_document();
		case 5 : return proc.getNbrEtape();
		case 6 : return (proc.getIsArchived() ? "Archivé" : "Actif");
		default : return null;
		}
		
	}
	
	@Override
	public Class<? extends Object> getColumnClass(int columnIndex) {
		switch(columnIndex) {
		case 0 : return Integer.class;
		case 1 : return String.class;
		case 2 : return String.class;
		case 3 : return java.sql.Date.class;
		case 4 : return Integer.class;
		case 5 : return Integer.class;
		case 6 : return	String.class;
		default : return Object.class;
		}
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		return this.column[columnIndex];
	}
	
	public void archiveProc(int rowIndex) {
		Procedure proc = listePrc.get(rowIndex);
		proc.setIsArchived(true);
		fireTableRowsUpdated(rowIndex, rowIndex);
	}

	public ArrayList<Procedure> getListePrc() {
		return listePrc;
	}

	public void setListePrc(ArrayList<Procedure> listePrc) {
		this.listePrc = listePrc;
	}
	
	public void removeProc(int rowIndex) {
		listePrc.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}
	
	public void updateModel() {
		fireTableDataChanged();
	}
}
