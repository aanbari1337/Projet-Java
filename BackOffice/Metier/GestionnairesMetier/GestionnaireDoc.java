package GestionnairesMetier;

import java.util.ArrayList;

import Beans.Doc;
import BeansMetier.Document;
import DAO.DAODocument;

public class GestionnaireDoc {
	
	
	
	public static int addDoc(Document doc) {
		return DAODocument.addDoc(doc);
	}
	
	public static ArrayList<Doc> getAllDoc(int id_proc){
		return DAODocument.getAllDoc(id_proc);
	}
	
	public static ArrayList<Document> allDoc(){
		return DAODocument.allDoc();
	}
	
	public static Doc getDoc(String libelle) {
		return DAODocument.getDoc(libelle);
	}
	public static String updateDoc(Doc doc, String libelle) {
		return DAODocument.updateDoc(doc, libelle);
	}
	
	public static boolean checkLibelle(String libelle, String preLibelle) {
		return DAODocument.nbrOccurenceLibelle(libelle, preLibelle);
	}
	
	public static boolean RemoveDoc(String libelle) {
		return DAODocument.RemoveDoc(libelle);
	}
}
