package Controleurs;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import BeansMetier.Document;
import BeansMetier.Employe;
import BeansMetier.Etape;
import BeansMetier.Procedure;
import GestionnairesMetier.GestionnaireTraitementEtape;
import Metier.Demande;
import Models.ModelDocument;
import Models.ModelDocumentMaj;
import Models.ModelEtape;
import Utils.StoreDataFront;
import Vues.LoginPage;
import Vues.VueAccueilEmploye;
import Vues.VueAjouterDocumentEtape;
import Vues.VueAjouterDocumentMaj;
import Vues.VueAjouterDocumentPrincipal;
import Vues.VueMiseAJour;
import Vues.VueModifierDocumentMaj;
import Vues.VueRapport;
import Vues.VueTraitementEtape;

public class ContolleurTraitementEtape {
	
	private ArrayList<Demande> demandes;
	private VueTraitementEtape vueTraitementEtape;
	private VueAjouterDocumentPrincipal vueAjouterDocumentPrincipal;
	private VueAjouterDocumentEtape vueAjouterDocumentEtape;
	private VueAccueilEmploye vueListeDemande;
	private ModelEtape modelEtape;
	private ModelDocument modelDoc;
	private ArrayList<Etape> etapes,allEtapes;
	private ArrayList<Procedure> procedures;
	private VueMiseAJour vueMiseAJour;
	private VueAjouterDocumentMaj vueAjouterDocumentProc;
	private ModelDocumentMaj modelDocMaj;
	private VueModifierDocumentMaj vueModifierDocumentMaj;
	private VueRapport vueRapport;

	public ContolleurTraitementEtape() {}
	
	///////////////////////////////////////////////////////////
	public void disconnect() {
		new StoreDataFront();
		LoginPage.setInstance(0);
		new LoginPage().setVisible(true);
		
	}
	///////////////////////////////////////////////////////////
	public Boolean genererVuetraitementEtp(int rowIndex,String cinEmploye) {
		
		ArrayList<String> docMaj;
		if(!GestionnaireTraitementEtape.commencerTraitement(demandes.get(rowIndex).getJeton(), demandes.get(rowIndex).getIdEtapeActuel(), cinEmploye))
			return false;
		if((docMaj = GestionnaireTraitementEtape.getDocMaj(demandes.get(rowIndex).getJeton(), demandes.get(rowIndex).getIdEtapeActuel())) == null)
			return false;
		modelDoc = new ModelDocument(demandes.get(rowIndex).getLienDoc());
		modelDoc.addDocs(docMaj);
		this.vueTraitementEtape = new VueTraitementEtape(modelDoc);
		vueTraitementEtape.setDescEtape(findEtapeDesc(rowIndex));
		vueTraitementEtape.setIdProc(demandes.get(rowIndex).getId_proc());
		vueTraitementEtape.setIdEtape(demandes.get(rowIndex).getIdEtapeActuel());
		vueTraitementEtape.setControlleur(this);
		vueTraitementEtape.setJeton(demandes.get(rowIndex).getJeton());
		vueTraitementEtape.setVisible(true);
		return true;
	}
	//////////////////////////////////////////////////////////
	public Boolean genererdemandeEmploye() {
		
		if((demandes = GestionnaireTraitementEtape.getAllDemandes()) == null)
			return false;
		int i = 0;
		while (i < demandes.size()) {
			if(!idEtapeExiste(demandes.get(i).getIdEtapeActuel()) || !demandes.get(i).getEtat().equals("en cours de traitement")) {
				demandes.remove(i);
				i--;
			}
			i++;
		}
		return true;
	}
	///////////////////////////////////////////////////////////
	public Boolean genererVueListeDemande(Employe employe) {
		
		if(!genererdemandeEmploye())
			return false;
		modelEtape = new ModelEtape(demandes);
		this.vueListeDemande = new VueAccueilEmploye(modelEtape);
		vueListeDemande.setControlleur(this);
		vueListeDemande.setCinEmploye(employe.getCin_emp());
		vueListeDemande.setVisible(true);
		return true;
	}
	///////////////////////////////////////////////////////////
	public void genererVueRapport(String jeton,int idEtape,String decision) {
		
		vueRapport = new VueRapport();
		vueRapport.setControlleur(this);
		vueRapport.setJeton(jeton);
		vueRapport.setIdEtape(idEtape);
		vueRapport.setDecision(decision);
		vueRapport.setVisible(true);
		
	}
	////////////////////////////////////////////////////////////
	public Boolean enregistrerRappot(String jeton,int idEtape,String rapport,String decision) {
		
		return GestionnaireTraitementEtape.enregistrerRapport(jeton, idEtape, rapport, decision);
		
	}
	///////////////////////////////////////////////////////////
	public Boolean refsuerEtape(int idProc, int idEtape, String jeton) {
		
		int i = 0;
		ArrayList<Etape> procEtapes = getEtapesOfProc(idProc);
		
		while(i < procEtapes.size()) {
			if(procEtapes.get(i).getId_etape() == idEtape)
				break;
			i++;
		}
		if(i != 0)
			i = procEtapes.get(i - 1).getId_etape();
		
		if(GestionnaireTraitementEtape.refuserEtape(i, jeton,idEtape)) {
				if(genererdemandeEmploye()) {
					modelEtape.setDemandes(demandes);
					modelEtape.fireTableStructureChanged();
					return true;
				}
				else
					return false;
		}
		else
			return false;
		
	}
	///////////////////////////////////////////////////////////
	public Boolean validerEtape(int idProc, int idEtape, String jeton) {
		
		int i = 0;
		ArrayList<Etape> procEtapes = getEtapesOfProc(idProc);		
		while(i < procEtapes.size()) {
			if(procEtapes.get(i).getId_etape() == idEtape)
				break;
			i++;
		}
		if(i == (procEtapes.size() - 1))
			i = 0;
		else
			i = procEtapes.get(i + 1).getId_etape();
		if(GestionnaireTraitementEtape.validerEtape(i, jeton, idEtape)) {
				if(genererdemandeEmploye()) {
					modelEtape.setDemandes(demandes);
					modelEtape.fireTableStructureChanged();
					return true;
				}
				else
					return false;
		}
		else
			return false;
	}
	////////////////////////////////////////////////////////////
	public ArrayList<Etape> getEtapesOfProc(int idProc) {
		
		ArrayList<Etape> procEtapes = new ArrayList<Etape>();
		int i = 0;
		
		while(i < allEtapes.size()) {
			if(allEtapes.get(i).getId_proc() == idProc) {
				procEtapes.add(allEtapes.get(i));
			}
			i++;
		}
		java.util.Collections.sort(procEtapes);
		return procEtapes;
	}
	/////////////////////////////////////////////////////////////
	public Boolean idEtapeExiste(int id) {
		int i = 0;
		
		while(i < etapes.size()) {
			if(id == etapes.get(i).getId_etape())
				return true;
			i++;
		}
		return false;
	}
	////////////////////////////////////////////////////////////
	public Boolean rejeterDemande(String jeton, int idEtape) {
		if(GestionnaireTraitementEtape.rejectDemande(jeton,idEtape)) {
			if(genererdemandeEmploye()) {
				modelEtape.setDemandes(demandes);
				modelEtape.fireTableStructureChanged();
				return true;
			}
			else
				return false;
		}
		else
			return false;
	}
	/////////////////////////////////////////////
	public Boolean displayDocs(int rowIndex) {
		
		try {
			Desktop.getDesktop().open(new File(modelDoc.getDocuments().get(rowIndex)));
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	////////////////////////////////////////////////////////////
	public void backfromVuetraitemen() {
		vueTraitementEtape.dispose();
		vueListeDemande.setVisible(true);
	}
	/////////////////////////////////////////////////////////
	public String findEtapeDesc(int rowindex) {
		
		int i = 0;
		while(i < etapes.size())
		{
			if (demandes.get(rowindex).getIdEtapeActuel() == etapes.get(i).getId_etape())
				return etapes.get(i).getDescription();
			i++;
		}
		return "";
	}
	//////////////////////////////////////////////////////////
	public void ajouterDocumentMaj() {
		
		vueAjouterDocumentProc = new VueAjouterDocumentMaj();
		vueAjouterDocumentProc.setControl(this);
		vueAjouterDocumentProc.setVisible(true);
		
	}
	/////////////////////////////////////////////////////////
	public void validerAjoutDocument() {
		
		Document document = new Document();
		document.setLibelle_doc(vueAjouterDocumentProc.getLibelleDoc().getText());
		document.setDescriptionDoc(vueAjouterDocumentProc.getDescriptionDoc().getText());
		modelDocMaj.getDocuments().add(document);
		vueAjouterDocumentProc.dispose();
		modelDocMaj.fireTableRowsInserted(0, modelDocMaj.getDocuments().size()-1);
	}
	////////////////////////////////////////////////////////////
	public void modifierDocument(int rowIndex) {
		
		Document document = modelDocMaj.getDocuments().get(rowIndex);
		vueModifierDocumentMaj = new VueModifierDocumentMaj();
		vueModifierDocumentMaj.setControl(this);
		vueModifierDocumentMaj.setRowIndex(rowIndex);
		vueModifierDocumentMaj.getLibelleDoc().setText(document.getLibelle_doc());
		vueModifierDocumentMaj.getDescriptionDoc().setText(document.getDescriptionDoc());
		vueModifierDocumentMaj.setVisible(true);
		
	}
	///////////////////////////////////////////////////////////
	public void validerModificationDocument() {
		
		modelDocMaj.getDocuments().get(vueModifierDocumentMaj.getRowIndex()).setLibelle_doc(vueModifierDocumentMaj.getLibelleDoc().getText());
		modelDocMaj.getDocuments().get(vueModifierDocumentMaj.getRowIndex()).setDescriptionDoc(vueModifierDocumentMaj.getDescriptionDoc().getText());
		vueModifierDocumentMaj.dispose();
		modelDocMaj.fireTableDataChanged();
		
	}
	///////////////////////////////////////////////////////////
	public void genererVueDemandeDoc(String jeton, int idEtape) {
		
		modelDocMaj = new ModelDocumentMaj();
		modelDocMaj.setDocuments(new ArrayList<Document>());
		vueAjouterDocumentPrincipal = new VueAjouterDocumentPrincipal(modelDocMaj);
		vueAjouterDocumentPrincipal.setContolleur(this);
		vueAjouterDocumentPrincipal.setIdEtape(idEtape);
		vueAjouterDocumentPrincipal.setJeton(jeton);
		vueAjouterDocumentPrincipal.setVisible(true);
	}
	///////////////////////////////////////////////////////////////
	public Boolean miseAJour(String jeton, int idEtape) {
		return GestionnaireTraitementEtape.miseAJour(modelDocMaj.getDocuments(), jeton, idEtape);
	}
	//////////////////////////////////////////////////////////////
	public void supprimerDocument(int rowIndex) {
		int size = modelDocMaj.getDocuments().size() - 2;
		modelDocMaj.getDocuments().remove(rowIndex);
		if(size < 0)
			size = 0;
		modelDocMaj.fireTableRowsDeleted(0, size);
	}
	////////////////////////////////////////////////////////////////////
	public ArrayList<Demande> getDemandes() {
		return demandes;
	}
	////////////////////////////////////////////////////////////////////
	public void setDemandes(ArrayList<Demande> demandes) {
		this.demandes = demandes;
	}

	public ArrayList<Etape> getEtapes() {
		return etapes;
	}

	public void setEtapes(ArrayList<Etape> etapes) {
		this.etapes = etapes;
	}

	public ArrayList<Procedure> getProcedures() {
		return procedures;
	}

	public void setProcedures(ArrayList<Procedure> procedures) {
		this.procedures = procedures;
	}

	public ArrayList<Etape> getAllEtapes() {
		return allEtapes;
	}

	public void setAllEtapes(ArrayList<Etape> allEtapes) {
		this.allEtapes = allEtapes;
	}
	
	

}
