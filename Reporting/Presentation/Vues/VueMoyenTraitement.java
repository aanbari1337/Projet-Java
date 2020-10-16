package Vues;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Controleurs.ControleurStatDemande;
import Controleurs.ControleurStatMoyenTraitement;

public class VueMoyenTraitement extends JFrame{

	private JButton moyProc,moyEtape, moyEmploye,retour;
	private ControleurStatDemande control;
	private ControleurStatMoyenTraitement controleur;
	public VueMoyenTraitement() {
		
		setTitle("Moyenne duree des traitements");
		setSize(500,400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		control = new ControleurStatDemande();
		controleur = new ControleurStatMoyenTraitement();
		draw();
		Action();
	}
	
	private void draw() {
		JPanel panel = (JPanel) getContentPane();
		panel.setLayout(null);
		moyProc = new JButton("Procedures");
		moyProc.setSize(200, 80);
		moyProc.setLocation(15, 100);
		moyEtape = new JButton("Etapes");
		moyEtape .setSize(200, 80);
		moyEtape .setLocation(265, 100);
		moyEmploye= new JButton("Employes");
		moyEmploye.setSize(200, 80);
		moyEmploye.setLocation(150, 230);

		retour = new JButton("Retour");
		retour.setSize(100, 25);
		retour.setLocation(15, 30);
		
		panel.add(moyProc);
		panel.add(moyEtape);
		panel.add(moyEmploye);
		panel.add(retour);
	}
	
	public void Action() {
		moyProc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				controleur.moyenTraitementProc();
			}
		});
		moyEmploye.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				controleur.moyenTraitementEmp();
			}
		});
		moyEtape.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				controleur.moyenTraitementEtape();
			}
		});
		retour.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
	}

	public ControleurStatDemande getControl() {
		return control;
	}

	public void setControl(ControleurStatDemande control) {
		this.control = control;
	}

	public ControleurStatMoyenTraitement getControleur() {
		return controleur;
	}

	public void setControleur(ControleurStatMoyenTraitement controleur) {
		this.controleur = controleur;
	}
	
	
	
}
