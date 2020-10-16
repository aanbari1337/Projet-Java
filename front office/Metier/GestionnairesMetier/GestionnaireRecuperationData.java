package GestionnairesMetier;

import java.util.ArrayList;

import BeansMetier.Document;
import BeansMetier.Employe;
import BeansMetier.Etape;
import BeansMetier.Procedure;
import DAO.DAODataFromBack;

public class GestionnaireRecuperationData{
	
	public static ArrayList<Procedure> getALLProcedures(){
		
		return DAODataFromBack.getALLProcedures();
		
	}
	
	public static ArrayList<Employe> getALLEmployes(){
		
		return DAODataFromBack.getALLEmployes();
	}
	
	public static ArrayList<Etape> getALLEtapes(){
		
		return DAODataFromBack.getALLEtapes();
	}
	
	public static ArrayList<Document> getALLDocuments(){
		
		return DAODataFromBack.getALLDocuments();
		
	}
	
	

}
