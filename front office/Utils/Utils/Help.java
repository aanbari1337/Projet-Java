package Utils;

import java.io.File;
import java.util.ArrayList;

import BeansMetier.Document;
import BeansMetier.Etape;
import BeansMetier.Procedure;
import DAO.DAODataFromBack;


public class Help {
	
	private static ArrayList<Document> allDoc;
	private static ArrayList<Procedure> listProc;
	static {
		allDoc = DAODataFromBack.getALLDocuments();
		listProc= DAODataFromBack.getALLProcedures();
	}
	
	public static ArrayList<Document> getDocByID(int id){
		
		ArrayList<Document> listDoc = new ArrayList<Document>();
	
		int i = 0;
		while(i<allDoc.size()) {
			if(allDoc.get(i).getIdProc() == id)
				listDoc.add(allDoc.get(i));
			i++;
		}
		return listDoc;
	}
	
	public static String[] ProcNames() {

		String names[] = new String[listProc.size()];
		
		for (int i=0;i< listProc.size();i++) {
			names[i] = listProc.get(i).getLibelle_proc();
		}
		return names;
	}
	
	public static ArrayList<String[]> ProcInfo(String libelle){
		
		ArrayList<String[]>  infos= new ArrayList<String[]>();
		int id=0;
		
		String[] proc = new String[2];
		proc[0] = libelle;
		for(int i =0 ; i < listProc.size();i++) {
			if(listProc.get(i).getLibelle_proc().equals(libelle)) {
				proc[1] = listProc.get(i).getDescription();
				id = listProc.get(i).getNum();
				break;
			}
		}
		infos.add(proc);
		
		ArrayList<Document> listDoc =getDocByID(id);
		
		for (int i = 0;i<listDoc.size();i++) {
			
			String[] doc = new String[2];
			doc[0] = listDoc.get(i).getLibelle_doc();
			doc[1] = listDoc.get(i).getDescriptionDoc();
			infos.add(doc);
		}

		return infos;
	}
	
	public static Integer[] getNbDoc(String libelle) {
		Integer count[] = new Integer[2];
		for (int i=0;i<listProc.size();i++) {
			if(listProc.get(i).getLibelle_proc().equals(libelle)) {
				count[0] = listProc.get(i).getNbr_document();
				count[1] = listProc.get(i).getNum();
				break;
			}
		}
		return count;
	}
	
	
	public static void deleteRep(File dir) {
		File[] files = dir.listFiles();
		for(File file :files) {
			file.delete();
		}
		dir.delete();
	}
	
	public static float percent(int ordreEtp, int nbEtape) {
		return ((float)ordreEtp/nbEtape*100);
	}
	
	public static ArrayList<Integer> getIdEtapes(int id){
		ArrayList<Etape> listEtape = DAODataFromBack.getALLEtapes();
		ArrayList<Integer> ids = new ArrayList<Integer>();
		for(Etape e :listEtape) {
			if(e.getId_proc() == id) {
				ids.add(e.getId_etape());
			}
		}
		java.util.Collections.sort(ids);
		return ids;
	}

}
