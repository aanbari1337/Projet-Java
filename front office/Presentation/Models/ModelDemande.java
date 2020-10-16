package Models;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ModelDemande extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<String[]> listDoc;
	private String columnName = "Nom du document";
	
	public ModelDemande() {
		listDoc = new ArrayList<String[]>();
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return listDoc.size();
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		return this.columnName;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		String[] docs = listDoc.get(arg0);
		switch(arg1) {
		case 0: return docs[0];
		default :return null;
		}
	}
	
	public Class<? extends Object> getColumnClass(int columnIndex) {
			switch(columnIndex) {
			case 0 : return String.class;
			default : return Object.class;
			}
	}
	public ArrayList<String[]> getListDoc() {
		return listDoc;
	}
	public void setListDoc(ArrayList<String[]> listDoc) {
		this.listDoc = listDoc;
	}
	
	public void addDoc(String[] doc) {
		listDoc.add(doc);
		fireTableRowsInserted(listDoc.size(), listDoc.size());
	}
	
	public void removeDoc(int index) {
		listDoc.remove(index);
		fireTableRowsDeleted(index, index);
	}
		
}
