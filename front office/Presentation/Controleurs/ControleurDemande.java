package Controleurs;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import Beans.DemandeDeposer;
import GestionnairesMetier.GestionnaireDemande;
import Models.ModelDemande;
import Utils.Help;
import Utils.Utils;
import Vues.VueUploadDocument;

public class ControleurDemande {
	
	private VueUploadDocument vue;
	
	public ControleurDemande(VueUploadDocument vue) {
		this.vue = vue;
		
	}
	
	public void addDoc() {
		ModelDemande model = (ModelDemande) vue.getFiles().getModel();
		JFileChooser chooser  = new JFileChooser();
		chooser.showOpenDialog(null);
		File f = chooser.getSelectedFile();
		if(f!= null) {
		String[] file = new String[2];
		file[1] = f.getAbsolutePath();
		file[0] = f.getName();
		model.addDoc(file);}
	}
	
	public void deleteDoc(int row) {
		ModelDemande model = (ModelDemande) vue.getFiles().getModel();
		model.removeDoc(row);
	}
	
	public void deposeDemande(String cin,String libelle) {
		ModelDemande model = (ModelDemande) vue.getFiles().getModel();
		int count = model.getRowCount();
		
		if(count<Help.getNbDoc(libelle)[0]) {
			JOptionPane.showMessageDialog(null,"Il vous manque certains documents :\n veuillez reessayer:","Warning",JOptionPane.WARNING_MESSAGE);
		}else if(count>Help.getNbDoc(libelle)[0]) {
			JOptionPane.showMessageDialog(null,"vous avez ajouter plusieurs documents :\n veuillez reessayer:","Warning",JOptionPane.WARNING_MESSAGE);
		}else {
			
			String jeton = Utils.generateJeton();
			ArrayList<String> paths =createRepDocument(jeton);
			if(paths ==null)
				JOptionPane.showMessageDialog(null, "Erreur lors de l'importation des documents \n veuillez ressayer","Erreur",JOptionPane.ERROR_MESSAGE);
			else {
				DemandeDeposer dem = new DemandeDeposer();
				dem.setCinCitoyen(cin);		dem.setEtat("en attente");		dem.setId_proc(Help.getNbDoc(libelle)[1]);
				dem.setJeton(jeton);	dem.setNomProc(libelle);	dem.setLienDoc(paths);
				if(GestionnaireDemande.addDemande(dem)) {
					JOptionPane.showMessageDialog(null, "Demande deposer avec succes \njeton de consultation de la demande :\n"
							+ jeton,"demande deposer",JOptionPane.INFORMATION_MESSAGE);
					vue.dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Erreur veuillez ressayer","Erreur",JOptionPane.ERROR_MESSAGE);
					Help.deleteRep(new File("../Demandes/" + jeton));
				}
			}
		}
	}
	
	private ArrayList<String> createRepDocument(String jeton) {
		ArrayList<String> paths = new ArrayList<String>();
		File parentDir = new File("../Demandes");
		parentDir.mkdir();
		
		ModelDemande model = (ModelDemande) vue.getFiles().getModel();
		ArrayList<String[]> listModel = model.getListDoc();
		
		String path = "../Demandes/" +jeton + "/";
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
