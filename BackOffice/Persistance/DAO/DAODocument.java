package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Beans.Doc;
import BeansMetier.Document;

public class DAODocument {

	
	public static int addDoc(Document doc) {
		int result;
		
		try {
			Statement st = (Statement) DAOconnexion.getInstance().getConnexion().createStatement();
			String query = "insert into document(libelle,desc_doc,id_proc) values('"+doc.getLibelle_doc() +"','"+
			doc.getDescriptionDoc() + "'," + doc.getIdProc() + ")";
			
			result = st.executeUpdate(query);
			return result;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return -1;
	}
	
public static ArrayList<Doc> getAllDoc(int id_proc){
		
		ArrayList<Doc> documents = new ArrayList<Doc>();
		try {
		Statement st = DAOconnexion.getInstance().getConnexion().createStatement();
		String query = "select *from document where id_proc = '"+id_proc+"'";
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			Doc doc = new Doc();
			doc.setLiblle(rs.getString("libelle"));
			doc.setDecription(rs.getString("desc_doc"));
			documents.add(doc);
		}
		return documents;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

public static ArrayList<Document> allDoc(){
	ArrayList<Document> documents = new ArrayList<Document>();
	try {
		Statement st = DAOconnexion.getInstance().getConnexion().createStatement();
		String query = "select *from document";
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			Document doc = new Document();
			doc.setIdDoc(rs.getInt("id_doc"));
			doc.setLibelle_doc(rs.getString("libelle"));
			doc.setDescriptionDoc(rs.getString("desc_doc"));
			doc.setIdProc(rs.getInt("id_proc"));
			documents.add(doc);
		}
		return documents;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
}

public static Doc getDoc(String libelle) {
	String query = "select *from document where libelle = '"+libelle+"'";
	try {
		Statement st = DAOconnexion.getInstance().getConnexion().createStatement();
		ResultSet rs = st.executeQuery(query);
		rs.next();
		Doc doc = new Doc();
		doc.setLiblle(rs.getString("libelle"));
		doc.setDecription(rs.getString("desc_doc"));
		return doc;
	
	} catch (SQLException | ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
}

public static String updateDoc(Doc doc, String libelle) {
	String query = "update document set libelle ='"+doc.getLiblle()+"', desc_doc='"+doc.getDecription()+
			"' where libelle='"+libelle+"'";
	try {
		Statement st = DAOconnexion.getInstance().getConnexion().createStatement();
		int rs = st.executeUpdate(query);
		if (rs == 0)
			return null;
	}catch (Exception e) {
		System.err.println(e.getMessage());
		return null;
	}
	return doc.getLiblle();
}

public static int getIdDoc(String libelle) {
	String query = "select id_doc from document where libelle = '"+libelle+"'";
	try {
		Statement st = DAOconnexion.getInstance().getConnexion().createStatement();
		ResultSet rs = st.executeQuery(query);
		rs.next();
		return (rs.getInt("id_doc"));
	
	} catch (SQLException | ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return -1;
	}
}

public static boolean RemoveDoc(String libelle) {

	String query = "delete from document where (libelle = '"+libelle+"')";
	try {
		Statement st = (Statement) DAOconnexion.getInstance().getConnexion().createStatement();

		int rs = st.executeUpdate(query);

		if (rs == 0)
			return false;
	}catch (Exception e) {
		System.err.println(e.getMessage());
		return false;
	}
	return true;
}

public static boolean nbrOccurenceLibelle(String libelle, String preLibelle) {
	int id_doc = getIdDoc(preLibelle);
	String query = "select libelle,id_doc from document";

	try {
		Statement st = (Statement) DAOconnexion.getInstance().getConnexion().createStatement();
		ResultSet rs = st.executeQuery(query);
		while(rs.next()) {
			if (rs.getString("libelle").equals(libelle) && rs.getInt("id_doc") != id_doc)
				return false;
		}
		
	} catch (SQLException | ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return true;
}

}
