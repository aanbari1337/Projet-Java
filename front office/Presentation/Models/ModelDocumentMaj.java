package Models;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import BeansMetier.Document;

public class ModelDocumentMaj extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Document> documents;
	
	
	
	
	public ModelDocumentMaj(ArrayList<Document> documents) {
		super();
		this.documents = documents;
	}
	
	public ModelDocumentMaj() {
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
	public String getValueAt(int arg0, int arg1) {
		return documents.get(arg0).getLibelle_doc();
	}
	
	public Class<?> getColumnClass(int columnIndex){
		return String.class;
	}
	
	public String getColumnName(int column) {
		return "libelle du document";
	}

	public ArrayList<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(ArrayList<Document> documents) {
		this.documents = documents;
	}
	
	
	
	
	
	

}
