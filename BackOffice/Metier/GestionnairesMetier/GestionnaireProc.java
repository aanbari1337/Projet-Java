package GestionnairesMetier;

import java.util.ArrayList;

import BeansMetier.Procedure;
import DAO.DAOProcedure;

public class GestionnaireProc {

	
	public static ArrayList<Procedure> getAllProc(){
		return DAOProcedure.listProcedure();
	}
	
	public static int deleteProcedure(int id) {
		return DAOProcedure.deleteProcedure(id);
	}
	
	public static int archiverProcedure(int id) {
		return DAOProcedure.archiveProcedure(id);
	}
	
	public static int addProcedure(Procedure p) {
		return DAOProcedure.addProc(p);
	}
	
	public static boolean updateProc(Procedure proc, int id_proc) {
		return DAOProcedure.update(proc, id_proc);
	}
	public static Procedure searchById(int id_proc) {
		return DAOProcedure.getProcedure(id_proc);
	}
	
	public static boolean checkLibelle(String libelle, int id_proc) {
		return DAOProcedure.nbrOccurenceLibelle(libelle, id_proc);
	}
}
