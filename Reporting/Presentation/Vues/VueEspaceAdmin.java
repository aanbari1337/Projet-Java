package Vues;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.SwingConstants;

import Controleurs.ControleurStatDemande;

public class VueEspaceAdmin extends JFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton tauxAccept,moyTrait,tauxValid,nbDem,logOut;
	private ControleurStatDemande control;
	
	public VueEspaceAdmin() {
		control = new ControleurStatDemande();
		setTitle("Statistiques");
		setSize(500,350);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		draw();
		listners();
	}
	
	private void draw() {
		JPanel panel = (JPanel) getContentPane();
		panel.setLayout(null);
		tauxAccept = new JButton("Taux d'acceptation des demandes");
		tauxAccept.setSize(200, 80);
		tauxAccept.setLocation(15, 90);
		tauxValid = new JButton("Taux de validation des etapes");
		tauxValid.setSize(200, 80);
		tauxValid.setLocation(265, 90);
		nbDem= new JButton("nombre des demandes");
		nbDem.setSize(200, 80);
		nbDem.setLocation(15, 202);
		moyTrait = new JButton("Duree des traitements");
		moyTrait.setSize(200, 80);
		moyTrait.setLocation(265, 202);
		logOut = new JButton();
		logOut.setIcon(new ImageIcon("../icons/0.png"));
		logOut.setSize(35, 35);
		logOut.setLocation(449, 11);
		panel.setBackground(SystemColor.inactiveCaptionBorder);
		panel.add(logOut);
		panel.add(tauxAccept);
		panel.add(tauxValid);
		panel.add(nbDem);
		panel.add(moyTrait);
		
		JLabel stats = new JLabel("Statistiques");
		stats.setHorizontalAlignment(SwingConstants.CENTER);
		stats.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		stats.setBounds(155, 31, 169, 35);
		panel.add(stats);
	}

	
	private void listners() {
		tauxAccept.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new VueStatDemProc().setVisible(true);
				dispose();
			}
		});
		tauxValid.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VueStatEtapeByEmp().setVisible(true);
				dispose();
			}
		});
		nbDem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				control.vueNbrDemandeTraite();
			}
		});
		moyTrait.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				control.vueMoyenTraite();
			}
		});
		logOut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new VueLogin().setVisible(true);;
			}
		});
	}
}
