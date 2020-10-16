package Vues;

import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Controleurs.ContolleurTraitementEtape;
import Models.ModelEtape;

public class VueAccueilEmploye extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tableEtape;
	private ModelEtape model;
	private JButton logOut,traiter;
	private JPanel centerpan,contentPanel =(JPanel) this.getContentPane();
	private ContolleurTraitementEtape controlleur;
	private String cinEmploye;
	
	
	public VueAccueilEmploye(ModelEtape model) throws HeadlessException {
		super("Accueil Employe");
		this.setSize(800,600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.model = model;
		init();
		draw();
		addListeners();
	}
	
	private void init() {
		tableEtape = new JTable(model);
		logOut = new JButton();
		logOut.setIcon(new ImageIcon("../icons/0.png"));
		traiter = new JButton("Traiter");
		centerpan = new JPanel();
	}
	
	private void draw() {
		
		contentPanel.setLayout(null);
		centerpan.setBounds(20,70,740,400);
		centerpan.setLayout(new GridLayout(1,1));
		centerpan.setBorder(BorderFactory.createTitledBorder("liste des Etapes à traiter"));
		JScrollPane scrollPane = new JScrollPane(tableEtape);
		scrollPane.setBounds(10,10,770,0);
		centerpan.add(scrollPane);
		
		logOut.setBounds(732,20 , 30, 30);
		
		traiter.setBounds(647,500, 110, 25);
		contentPanel.add(logOut);
		
		contentPanel.add(traiter);
		contentPanel.add(centerpan);
		contentPanel.setBackground(SystemColor.inactiveCaptionBorder);
		centerpan.setBackground(SystemColor.inactiveCaptionBorder);
	}
	
	public void addListeners() {
		
		traiter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedIndex;
				selectedIndex = tableEtape.getSelectedRow();
				if(selectedIndex == -1) {
					JOptionPane.showMessageDialog(VueAccueilEmploye.this, "aucune ligne selectionnée");
					return;
				}
				selectedIndex = tableEtape.convertRowIndexToModel(selectedIndex);
				controlleur.genererVuetraitementEtp(selectedIndex, cinEmploye);
			}
		});
		logOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controlleur.disconnect();
				dispose();
			}
		});
	}

	
	
	
	public ContolleurTraitementEtape getControlleur() {
		return controlleur;
	}

	public void setControlleur(ContolleurTraitementEtape controlleur) {
		this.controlleur = controlleur;
	}

	public String getCinEmploye() {
		return cinEmploye;
	}

	public void setCinEmploye(String cinEmploye) {
		this.cinEmploye = cinEmploye;
	}
	
	
	
	
	

}
