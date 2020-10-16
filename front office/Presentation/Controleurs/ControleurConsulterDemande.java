package Controleurs;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import GestionnairesMetier.GestionnaireDemande;
import Metier.Demande;
import Models.ModelDemande;
import Utils.Help;
import Vues.VueConsulterDemandeCitoyen;
import Vues.VueInfoProc;
import Vues.VueUploadDocument;

public class ControleurConsulterDemande {

	
	private VueConsulterDemandeCitoyen vueConsulter;
	private Demande dem;
	private VueUploadDocument vueMAj;
	
	public ControleurConsulterDemande(Demande dem) {
		this.dem = dem;
		vueConsulter = new VueConsulterDemandeCitoyen(this);
		setVue();
		vueConsulter.setVisible(true);
	}
	
	
	private void setVue() {
		
		vueConsulter.getLibelleProc().setText(dem.getNomProc());
		vueConsulter.getTxtDateDepot().setText(dem.getDateDepo().toString());
		vueConsulter.getEtat().setValue(valueSlider(dem.getId_proc(), dem.getIdEtapeActuel()));
		if(dem.getEtat().equals("validee")) {
			vueConsulter.getEtat().setValue(100);
			vueConsulter.getEtatFinal().setText("Demande accepter");
		}else if(dem.getEtat().equals("rejetee")){
			vueConsulter.getEtatFinal().setText("Demande rejetee");
			vueConsulter.getEtpactuel().setText("Rejette");
		}else if(dem.getEtat().equals("en cours de traitement")) {
			vueConsulter.getEtpactuel().setText("En cours de traitement");
			vueConsulter.getEtatFinal().setText("En cours de traitement");
		}else if(dem.getEtat().equals("mise a jour")) {
			vueConsulter.getEtpactuel().setText("Mise a jour");
			vueConsulter.getEtatFinal().setText("En cours de traitement");
			vueConsulter.getInfo().setEnabled(true);
			vueConsulter.getMaj().setEnabled(true);
		}
	}
	
	
	public void showVueInfo() {
		VueInfoProc vue = new VueInfoProc(GestionnaireDemande.infoMaj(dem.getJeton(), dem.getIdEtapeActuel()));
		vue.getLbl().setText("information des documents necessaires");
		vue.setVisible(true);
		
	}
	
	public void showVueMaj() {
		vueMAj = new VueUploadDocument("", "", this);
		vueMAj.getText().setText("Importer les documents necessaires pour la mise a jour");
		vueMAj.setVisible(true);
	}
	
	public void deleteDoc(int row) {
		ModelDemande model = (ModelDemande) vueMAj.getFiles().getModel();
		model.removeDoc(row);
	}
	
	public void addDoc() {
		ModelDemande model = (ModelDemande) vueMAj.getFiles().getModel();
		JFileChooser chooser  = new JFileChooser();
		chooser.showOpenDialog(null);
		File f = chooser.getSelectedFile();
		if(f!= null) {
		String[] file = new String[2];
		file[1] = f.getAbsolutePath();
		file[0] = f.getName();
		model.addDoc(file);
		}
	}
	
	public void miseAJour() {
		ModelDemande model = (ModelDemande) vueMAj.getFiles().getModel();
		int count = model.getRowCount();
		int nbDoc = GestionnaireDemande.infoMaj(dem.getJeton(), dem.getIdEtapeActuel()).size();
		if(count<nbDoc) {
			JOptionPane.showMessageDialog(null,"Il vous manque certains documents :\n veuillez reessayer:","Warning",JOptionPane.WARNING_MESSAGE);
		}else if(count>nbDoc) {
			JOptionPane.showMessageDialog(null,"vous avez ajouter plusieurs documents :\n veuillez reessayer:","Warning",JOptionPane.WARNING_MESSAGE);
		}else {
			ArrayList<String> paths =createRepDocument(dem.getJeton(),String.valueOf(dem.getIdEtapeActuel()));
			if(paths ==null)
				JOptionPane.showMessageDialog(null, "Erreur lors de l'importation des documents \n veuillez ressayer","Erreur",JOptionPane.ERROR_MESSAGE);
			else {
				if(GestionnaireDemande.majDemande(paths, dem.getJeton(), dem.getIdEtapeActuel())) {
					JOptionPane.showMessageDialog(null, "Mise a jour effectuer avec succes","succes",JOptionPane.INFORMATION_MESSAGE);
					vueConsulter.getEtpactuel().setText("en cours de traitement");
					vueConsulter.getInfo().setEnabled(false);
					vueConsulter.getMaj().setEnabled(false);
					vueMAj.dispose();
					vueConsulter.getPanel().repaint();
				}else {
					JOptionPane.showMessageDialog(null, "Erreur veuillez ressayer","Erreur",JOptionPane.ERROR_MESSAGE);
					Help.deleteRep(new File("../Demandes/" + dem.getJeton() +"/" + String.valueOf(dem.getIdEtapeActuel())));
				}
			}
		}
	}
	
	private  int valueSlider(int idProc,int idEtape) {
		ArrayList<Integer> list = Help.getIdEtapes(idProc);
		for(int i=0; i<list.size();i++) {
			if(list.get(i) == idEtape){
				return (int)Help.percent(i, list.size());
			}
		}
	return -1;	
	}
	
	private ArrayList<String> createRepDocument(String jeton,String id) {
		ArrayList<String> paths = new ArrayList<String>();
	
		ModelDemande model = (ModelDemande) vueMAj.getFiles().getModel();
		ArrayList<String[]> listModel = model.getListDoc();
		
		String path = "../Demandes/" +jeton + "/" + id + "/";
		File dir = new File(path);
		dir.mkdir();
		
		for(int i = 0; i< listModel.size(); i++) {
			try {
			Files.copy(Paths.get(listModel.get(i)[1]),
					new File(path+listModel.get(i)[0]).toPath(),
					StandardCopyOption.REPLACE_EXISTING);
			paths.add(path+listModel.get(i)[0]);
			}catch(Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return paths;
	}
}
