package Models;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ModelDocument extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<String> documents;
	
	
	
	
	public ModelDocument(ArrayList<String> documents) {
		super();
		this.documents = documents;
	}
	
	public ModelDocument() {
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return documents.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		String[] str = documents.get(arg0).split("\\\\");
		return str[str.length - 1];
	}
	
	public Class<?> getColumnClass(int columnIndex){
		return String.class;
	}
	
	public String getColumnName(int column) {
		return "libelle du document";
	}

	public ArrayList<String> getDocuments() {
		return documents;
	}

	public void setDocuments(ArrayList<String> documents) {
		this.documents = documents;
	}
	
	public void addDocs(ArrayList<String> a) {
		int i = 0;
		while(i < a.size()) {
			documents.add(a.get(i));
			i++;
		}
	}
	
	
	
	

}
