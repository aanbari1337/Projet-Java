package Contoleurs;

import java.util.ArrayList;

import Beans.employeBean;
import BeansMetier.Employe;
import GestionnairesMetier.GestionnaireEmp;
import Models.ModelEmploye;
import Vues.VueAddEmp;
import Vues.VueGestionEmp;
import Vues.VueModierEmp;

public class ContoleurGestionEmp {
    
	private VueGestionEmp vueGestionEmp;
    private VueAddEmp vueAddEmp;
    private VueModierEmp vueModierEmp;
    private ArrayList<Employe> employes;
    private ModelEmploye model;

    public ContoleurGestionEmp() {
    	
    	employes = GestionnaireEmp.getAllEmployes();
    	model = new ModelEmploye(employes);
    	vueGestionEmp = new VueGestionEmp(model);
    }

    public void genererVueAjout(){
    	
        vueAddEmp = new VueAddEmp();
        vueAddEmp.setControleur(this);
        vueAddEmp.setVisible(true);
    }
    
    public void genereVueModification(int rowIndex) {
    	
    	this.vueModierEmp = new VueModierEmp();
        vueModierEmp.setControleur(this);
        displayEmpInfos(employes.get(rowIndex));
        vueModierEmp.setVisible(true);
    }

    public int ajouterEmp(employeBean bean)
    {
    	int result = GestionnaireEmp.ajouterEmp(beanToEmploye(bean));
    	if(result == 1) {
    		employes = GestionnaireEmp.getAllEmployes();
        	model.setEmployes(employes);
        	model.fireTableRowsInserted(0,employes.size()-1);
    	}
    	return result;
    }
    
    public void supprimerEmp(String cin) {
    	
    	GestionnaireEmp.supprimerEmp(cin);
    	employes = GestionnaireEmp.getAllEmployes();
    	model.setEmployes(employes);
    	model.fireTableRowsDeleted(0,employes.size()-1);
    }
    
    public int modifierEmp(employeBean bean,String cin, int gradechanged)
    {
    	int result = GestionnaireEmp.modifierEmp(beanToEmploye(bean), cin,gradechanged);
    	if (result == 1) {
    	employes = GestionnaireEmp.getAllEmployes();
    	model.setEmployes(employes);
   		model.fireTableDataChanged();
    	}
   		return result;
    }
    
    public int archiverEmp(String cin) {
    	int result;
    	result = GestionnaireEmp.archiverEmp(cin);
    	if (result == 1)
    	{
    		employes = GestionnaireEmp.getAllEmployes();
        	model.setEmployes(employes);
       		model.fireTableDataChanged();
    	}
    	return result;
    }
   
	public void displayEmpInfos(Employe employe) {
    	
    	vueModierEmp.getTxtCin().setText(employe.getCin_emp());
    	vueModierEmp.getTxtNom().setText(employe.getNom());
    	vueModierEmp.getTxtPrenom().setText(employe.getPrenom());
    	vueModierEmp.getModel().setValue(employe.getDate_naissance());
    	vueModierEmp.getModel1().setValue(employe.getDate_rec());
    	if(employe.getGrade().equals("Responsable Etape"))
    		vueModierEmp.getcGrade().setSelectedIndex(1);
    	else
    		vueModierEmp.getcGrade().setSelectedIndex(0);
    	vueModierEmp.setCin(employe.getCin_emp());
    	vueModierEmp.setGrade(employe.getGrade());
    }
    
    public Employe beanToEmploye(employeBean bean) {
    	
    	Employe employe = new Employe();
    	
    	employe.setArchived(false);
    	employe.setDate_naissance(bean.getDate_naissance());
    	employe.setDate_rec(bean.getDate_rec());
    	employe.setGrade(bean.getGrade());
    	employe.setNom(bean.getNom());
    	employe.setPrenom(bean.getPrenom());
    	employe.setCin_emp(bean.getCin());
    	employe.setSexe(bean.getSexe());
    	
    	return employe;
    }
    
    public VueGestionEmp getVueGestionEmp() {
        return vueGestionEmp;
    }

    public void setVueGestionEmp(VueGestionEmp vueGestionEmp) {
        this.vueGestionEmp = vueGestionEmp;
    }

    public VueAddEmp getVueAddEmp() {
        return vueAddEmp;
    }

    public void setVueAddEmp(VueAddEmp vueAddEmp) {
        this.vueAddEmp = vueAddEmp;
    }
    
    
}
