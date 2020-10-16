package Controleurs;

import javax.swing.JOptionPane;

import GestionnairesMetier.GestionnaireDemande;
import Metier.Demande;
import Utils.Help;
import Vues.VueEspaceCitoyen;
import Vues.VueInfoProc;
import Vues.VueUploadDocument;

public class ControleurEspaceCitoyen {
	private VueEspaceCitoyen espaceCitoyen;
	
	public ControleurEspaceCitoyen(String cin) {
		espaceCitoyen = new VueEspaceCitoyen(cin,this,Help.ProcNames());
		espaceCitoyen.setVisible(true);
	}
	
	
	
	public void showVueInfoProc(String libelle) {
		new VueInfoProc(Help.ProcInfo(libelle)).setVisible(true);
	}
	
	public void showVueUploadDocument(String libelle,String cin) {
		new VueUploadDocument(libelle,cin,null).setVisible(true);
	}
	
	public void showVueConsulterDemande(String jeton,String cin) {
		
		Demande dem = GestionnaireDemande.getDemandeByCin(jeton,cin);
		
		if(dem==null) {
			JOptionPane.showMessageDialog(null, "Jeton inexistant \n Veuillez entrer une jeton valide","invalid jeton",JOptionPane.ERROR_MESSAGE);
		}else {
			if(dem.getEtat().equals("en attente"))
				JOptionPane.showMessageDialog(null, "Votre demande est en attente de traitement","Demande en attente",JOptionPane.INFORMATION_MESSAGE);
			else if(dem.getEtat().equals("rejetee") && dem.getIdEtapeActuel()==0) {
				JOptionPane.showMessageDialog(null, "Votre demande est rejetee","Demande rejetee",JOptionPane.INFORMATION_MESSAGE);
			}else {
				new ControleurConsulterDemande(dem);
			}
		}
	}
	
	
}
