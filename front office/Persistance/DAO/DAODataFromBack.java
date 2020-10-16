package DAO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import BeansMetier.Document;
import BeansMetier.Employe;
import BeansMetier.Etape;
import BeansMetier.Procedure;

public class DAODataFromBack {

	public static ArrayList<Procedure> list;
	
	
	public static ArrayList<Procedure> getALLProcedures() {
		Gson gson = new Gson();
		ArrayList<Procedure> listProc;
		try {
			BufferedReader br = new BufferedReader(new FileReader("../Data/procedures.json"));
			listProc = gson.fromJson(br,new TypeToken<ArrayList<Procedure>>(){}.getType());
			if(listProc == null)
				return new ArrayList<Procedure>();
			return listProc;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static ArrayList<Employe> getALLEmployes() {
		Gson gson = new Gson();
		ArrayList<Employe> listEmp;
		try {
			BufferedReader br = new BufferedReader(new FileReader("../Data/employes.json"));
			listEmp = gson.fromJson(br,new TypeToken<ArrayList<Employe>>(){}.getType());
			if(listEmp == null)
				return new ArrayList<Employe>();
			return listEmp;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
 
	public static ArrayList<Etape> getALLEtapes() {
		Gson gson = new Gson();
		ArrayList<Etape> listEtp;
		try {
			BufferedReader br = new BufferedReader(new FileReader("../Data/etapes.json"));
			listEtp = gson.fromJson(br,new TypeToken<ArrayList<Etape>>(){}.getType());
			if(listEtp == null)
				return new ArrayList<Etape>();
			return listEtp;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static ArrayList<Document> getALLDocuments() {
		Gson gson = new Gson();
		ArrayList<Document> listDoc;
		try {
			BufferedReader br = new BufferedReader(new FileReader("../Data/docs.json"));
			listDoc = gson.fromJson(br,new TypeToken<ArrayList<Document>>(){}.getType());
			if(listDoc == null)
				return new ArrayList<Document>();
			return listDoc;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
