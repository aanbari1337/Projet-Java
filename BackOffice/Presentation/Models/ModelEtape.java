package Models;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import Beans.EtapeForm;

public class ModelEtape  extends  AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<EtapeForm> listEtape;
	private String[] column = {"Libelle","responsable"};
	
	
	 public ModelEtape() {
		listEtape = new ArrayList<EtapeForm>();
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return listEtape.size();
	}

	@Override
	public String getColumnName(int columnIndex) {
		return this.column[columnIndex];
	}
	
	
	@Override
	public Object getValueAt(int arg0, int arg1) {
		EtapeForm etape = listEtape.get(arg0);
		switch(arg1) {
		case 0 : return etape.getLibelle();
		case 1 : return etape.getResp();
		default: return null;
		}
	}
	
	public Class<? extends Object> getColumnClass(int columnIndex) {
		switch(columnIndex) {
		case 0 : return String.class;
		case 1 : return String.class;
		default : return Object.class;
		}
	}
	public ArrayList<EtapeForm> getListEtape() {
		return listEtape;
	}
	public void setListEtape(ArrayList<EtapeForm> listEtape) {
		this.listEtape = listEtape;
	}
	
	public void addEtape(EtapeForm etape) {
		this.listEtape.add(etape);
		fireTableRowsInserted(listEtape.size() - 1, listEtape.size() - 1);
	}
	
	public void removeEtape(int index) {
		this.listEtape.remove(index);
		fireTableRowsDeleted(index, index);
	}
}
