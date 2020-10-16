package GestionnairesMetier;



import java.util.ArrayList;

import BeansMetier.Employe;
import DAO.EmployeDAO;
import Utils.Utils;
public class GestionnaireEmp {

    public static int ajouterEmp(Employe emp){
    	
    	int exist;
    	int period1 = Utils.periodCalcul(emp.getDate_naissance());
    	int period2 = Utils.periodCalcul(emp.getDate_rec());
    	
    	exist = GestionnaireEmp.existEmp(emp.getCin_emp());
    	if (exist == 1)
    		return 0;
    	else if(exist == -1)
    		return -1;
    	
    	if (period1 < 18 && period2 < 0)
    		return -2;
    	else if (period1 < 18)
    		return -3;
    	else if (period2 < 0)
    		return -4;
    	else
    		return EmployeDAO.addEmp(emp);
    }
    
    public static int archiverEmp(String cin) {
    	
    	return EmployeDAO.archiverEmp(cin);
    }
    
    public static ArrayList<Employe> getAllEmployes(){
    	
    	return EmployeDAO.getAllEmployes();
    	
    }
    public static int supprimerEmp(String cin) {
    	
    	return EmployeDAO.delEmp(cin);
    }
    
    public static Employe getEmp(String cin) {
    	
    	return EmployeDAO.getEmploye(cin);
    }
    
    public static int existEmp(String cin) {
    	
    	return EmployeDAO.existEmp(cin);
    }
    
    public static int modifierEmp(Employe employe,String cin,int gradechanged) {
    	
    	int exist;
    	int period1 = Utils.periodCalcul(employe.getDate_naissance());
    	int period2 = Utils.periodCalcul(employe.getDate_rec());
    	if (!cin.equals(employe.getCin_emp())) {
    			exist = EmployeDAO.existEmp(employe.getCin_emp());
    			if (exist == 1)
    				return 0;
    			else if(exist == -1)
    				return -1;
    	}
    	System.out.println(period1);
    	System.out.println(period2);
    	if (period1 < 18 && period2 < 0)
    		return -2;
    	else if (period1 < 18)
    		return -3;
    	else if (period2 < 0)
    		return -4;
    	else
    		return EmployeDAO.updateEmp(employe,cin,gradechanged);
    }
    
    public static String[] getAllChef(){
    	
    	return EmployeDAO.getChef();
    }

    public static String[] getAllEmploye() {
    	return EmployeDAO.getEmployes();
    }

}
