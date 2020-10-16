package Models;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import Beans.Doc;

public class ModelDoc extends  AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Doc> listDoc;
	private String[] columnName = {"libelle","Description"};
	
	public ModelDoc() {
		listDoc = new ArrayList<Doc>();
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return listDoc.size();
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		return this.columnName[columnIndex];
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		Doc doc = listDoc.get(arg0);
		switch(arg1) {
		case 0 : return doc.getLiblle();
		case 1 : return doc.getDecription();
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
	
	public ArrayList<Doc> getListDoc() {
		return listDoc;
	}
	public void setListDoc(ArrayList<Doc> listDoc) {
		this.listDoc = listDoc;
	}

	public void addDoc(Doc doc) {
		this.listDoc.add(doc);
		fireTableRowsInserted(listDoc.size() - 1, listDoc.size() - 1);
	}
	
	public void removeDoc(int index) {
	this.listDoc.remove(index);
	fireTableRowsDeleted(index, index);
	}
}
