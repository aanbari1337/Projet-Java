package Vues;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Controleurs.ControleurConsulterDemande;
import Controleurs.ControleurDemande;
import Models.ModelDemande;

public class VueUploadDocument extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel text;
	private JButton ajouter,deposer,annuler,supprimer;
	private JTable files;
	private String cin;
	private ControleurDemande control;
	private ControleurConsulterDemande control2;
	
	public VueUploadDocument(String libelle,String cin,ControleurConsulterDemande control2) {
		this.cin = cin;
		control = new ControleurDemande(this);
		this.control2 = control2;
		setTitle("Importation des documents");
		setSize(400,300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		init(libelle);
		draw();
		listners(libelle);
	}
	
	private void init(String libelle) {
		text = new JLabel("importer les documents de la Procedure : "+ libelle);
		ajouter = new JButton("Ajouter");
		supprimer = new JButton("Supprimer");
		annuler = new JButton("Annuler");
		deposer = new JButton("Deposer");
		
	}
	
	private void draw() {
		JPanel content = (JPanel) this.getContentPane();
		content.setLayout(null);
		text.setBounds(12,12,358,25);
		files = new JTable(new ModelDemande());
		
		JScrollPane pan = new JScrollPane(files);
		pan.setBounds(12,77,241,106);
		
		ajouter.setBounds(265,98,113,25);
		annuler.setBounds(189,230,90,25);
		deposer.setBounds(288,230,90,25);
		supprimer.setBounds(265,135,113,25);
		
		content.add(text);
		content.add(pan);
		content.add(ajouter);
		content.add(annuler);
		content.add(deposer);
		content.add(supprimer);
		content.setBackground(SystemColor.inactiveCaptionBorder);
	}
	
	private void listners(String libelle) {
		annuler.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		ajouter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(control2 == null)
					control.addDoc();
				else
					control2.addDoc();
				
			}
		});
		
		supprimer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int row = files.getSelectedRow();
				if(row == -1)
					JOptionPane.showMessageDialog(null, "Veuillez selectionner un document");
				else {
					int rep = JOptionPane.showConfirmDialog(null, "voulez vous supprimer ce document ?","Suppression",JOptionPane.YES_NO_OPTION);
					if(rep == 0) {
						if(control2 == null)
							control.deleteDoc(row);
						else
							control2.deleteDoc(row);
					}
				}
			}
		});
		deposer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(control2 == null)
					control.deposeDemande(cin,libelle);
				else
					control2.miseAJour();
			}
		});
	}

	public JTable getFiles() {
		return files;
	}

	public void setFiles(JTable files) {
		this.files = files;
	}

	public JLabel getText() {
		return text;
	}
	
}
