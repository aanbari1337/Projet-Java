package Controleurs;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

import BeansMetier.Etape;
import GestionnairesMetier.GestionnaireRecuperationData;
import GestionnairesMetier.GestionnaireTraitDemande;
import Metier.Demande;
import Models.ModelDoc;
import Models.ModelTraitDemande;
import Utils.StoreDataFront;
import Vues.LoginPage;
import Vues.VueAccueilChef;
import Vues.VueConsulterDemande;
import Vues.VueDesProc;
import Vues.vueTraiteDem;

public class controleurTraitDemande {

	private VueAccueilChef vueChef;
	private VueDesProc vueDescProc;
	private LoginPage loginPage;
	private vueTraiteDem traitDem;
	private VueConsulterDemande consulteDemnde;
	private ModelTraitDemande modelDem;
	private ModelDoc modelDoc;
	private Demande demande;
	private String Login;
	private ArrayList<Etape> listeEtae;
	
	
	public controleurTraitDemande(String login) {
		super();
		this.Login = login;
	}

	public void vueTraitDem(VueAccueilChef vue) {
		this.setVueChef(vue);
		vueChef.setControl(this);
		int row = vueChef.getTabDemande().getSelectedRow();
		if (row < 0) {
			JOptionPane.showMessageDialog(null,"s'il vous plait selectionner une demande");
			return;
		}
		row = vueChef.getSorter().convertRowIndexToModel(row);
		String jeton = (String) modelDem.getValueAt(row, 0);
		traitDem = new vueTraiteDem(GestionnaireTraitDemande.getLien(jeton), jeton);
		this.setTraitDem(traitDem);
		traitDem.setControl(this);
		traitDem.setVisible(true);
		setDateDebutTraitement(jeton);
		demande = GestionnaireTraitDemande.getDemande(jeton);
		
	}
	
	public void consulterDemande() {
		consulteDemnde = new VueConsulterDemande(consulterAllDemande(Login), Login);
		this.setConsulteDemnde(consulteDemnde);
		consulteDemnde.setControl(this);
		consulteDemnde.setVisible(true);
	}
	
	public void vueDesProc() {
		int i=0;
		while(i < GestionnaireRecuperationData.getALLProcedures().size()) {
			if(GestionnaireRecuperationData.getALLProcedures().get(i).getNum()==demande.getId_proc())
				 break;
			i++;
		}
		String descProc = GestionnaireRecuperationData.getALLProcedures().get(i).getDescription();
		vueDescProc = new VueDesProc(descProc);
		this.setVueDescProc(vueDescProc);
		vueDescProc.setControl(this);
		vueDescProc.setVisible(true);
	}
	
	public void vueLogin() {
		new StoreDataFront();
		loginPage = new LoginPage();
		LoginPage.setInstance(0);
		loginPage.setVisible(true);
	}
	
	public void setDateDebutTraitement(String jeton) {
		GestionnaireTraitDemande.setDateDebutTrait(jeton);
	}
	
	public void validateDemande(String jeton, Boolean isArchived, String etat) {
		int idEtape;
		if(getIdEtapeActuel(jeton, demande.getId_proc()).size()==0)
		{
			idEtape=0;
			etat="validee";
			isArchived=true;
		}
		else
			idEtape = getIdEtapeActuel(jeton, demande.getId_proc()).get(0).getId_etape();
		System.out.println(idEtape);
		GestionnaireTraitDemande.updateDemande(jeton, isArchived, etat, idEtape);
		modelDem.setListDemande(GestionnaireTraitDemande.getAllDem(Login));
		modelDem.fireTableDataChanged();
		
	}
	
	public void archiverDemande(String jeton, Boolean isArchived, String etat) {

		GestionnaireTraitDemande.updateDemande(jeton, isArchived, etat,0);
		modelDem.setListDemande(GestionnaireTraitDemande.getAllDem(Login));
		modelDem.fireTableDataChanged();
	}
	
	public boolean displayDocs(int rowIndex, String jeton) {
		this.setModelDoc(traitDem.getModel());
		 try {
		        Desktop.getDesktop().open(new File(modelDoc.getListDoc().get(rowIndex)));
		    } catch (IOException ex) {
		        ex.printStackTrace();
		        return false;
		    }
		 return true;
	}
	
	public ArrayList<Etape> getIdEtapeActuel(String jeton, int id_proc) {
		listeEtae = new ArrayList<Etape>();
		int i = 0;
		while(i < GestionnaireRecuperationData.getALLEtapes().size()) {
			if(GestionnaireRecuperationData.getALLEtapes().get(i).getId_proc()==id_proc)
				 listeEtae.add(GestionnaireRecuperationData.getALLEtapes().get(i));
			i++;
		}
		Collections.sort(listeEtae);
		return listeEtae;
	}
	
	public ArrayList<Demande> getAllDemande(String login) {
		return GestionnaireTraitDemande.getAllDem(login);
	}

	public ArrayList<Demande> consulterAllDemande(String login){
		return GestionnaireTraitDemande.consulteAllDemande(login);
	}
	
	public VueAccueilChef getVueChef() {
		return vueChef;
	}
	public void setVueChef(VueAccueilChef vueChef) {
		this.vueChef = vueChef;
	}
	public ModelTraitDemande getModelDem() {
		return modelDem;
	}
	public void setModelDem(ModelTraitDemande modelDem) {
		this.modelDem = modelDem;
	}



	public vueTraiteDem getTraitDem() {
		return traitDem;
	}



	public void setTraitDem(vueTraiteDem traitDem) {
		this.traitDem = traitDem;
	}

	public ModelDoc getModelDoc() {
		return modelDoc;
	}

	public void setModelDoc(ModelDoc modelDoc) {
		this.modelDoc = modelDoc;
	}


	public VueDesProc getVueDescProc() {
		return vueDescProc;
	}

	public void setVueDescProc(VueDesProc vueDescProc) {
		this.vueDescProc = vueDescProc;
	}

	public ArrayList<Etape> getListeEtae() {
		return listeEtae;
	}

	public void setListeEtae(ArrayList<Etape> listeEtae) {
		this.listeEtae = listeEtae;
	}

	public VueConsulterDemande getConsulteDemnde() {
		return consulteDemnde;
	}

	public void setConsulteDemnde(VueConsulterDemande consulteDemnde) {
		this.consulteDemnde = consulteDemnde;
	}
	
	
}
