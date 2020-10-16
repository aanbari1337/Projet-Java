package Models;

import java.io.File;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ModelDoc extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<String> listDoc;
	String[] column = {"Nom du document"};
	
	
	public ModelDoc(ArrayList<String> listDoc) {
		super();
		this.listDoc = listDoc;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return listDoc.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		File f=null;
		f = new File(listDoc.get(rowIndex));
		String doc = f.getName();
		switch(columnIndex) {
		case 0 : return doc;
		}
		return null;
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		return this.column[columnIndex];
	}

	public ArrayList<String> getListDoc() {
		return listDoc;
	}

	public void setListDoc(ArrayList<String> listDoc) {
		this.listDoc = listDoc;
	}
	
	

}
