package Vues;

import java.awt.HeadlessException;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Controleurs.ContolleurTraitementEtape;
import Models.ModelDocumentMaj;

public class VueMiseAJour extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton valider,supprimer,annuler,ajouter,modifier;
	private JPanel docPanel, contentPanel=(JPanel)this.getContentPane();
	private JTable tableDoc;
	private ContolleurTraitementEtape contolleur;
	private ModelDocumentMaj model;
	
	public VueMiseAJour(ModelDocumentMaj model) throws HeadlessException {
		super("mise à jour");
		this.setSize(500, 500);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.model = model;
		
		init();
		draw();
		addlisteners();
	}
	
	private void init() {
		valider = new JButton("valider");
		annuler = new JButton("annuler");
		modifier = new JButton("modifier");
		ajouter = new JButton("ajouter");
		supprimer = new JButton("supprimer");
		docPanel = new JPanel();
		tableDoc = new JTable(model);
	}
	
	private void draw() {
		contentPanel.setLayout(null);
		docPanel.setBounds(20, 20, 400, 350);
		annuler.setBounds(300, 400, 80, 25);
		valider.setBounds(390, 400, 80, 25);
		ajouter.setBounds(435, 30, 40, 25);
		modifier.setBounds(435, 60, 40, 25);
		supprimer.setBounds(435, 90, 40, 25);
		
		JScrollPane scrollPane = new JScrollPane(tableDoc);
		docPanel.add(scrollPane);
		docPanel.setBorder(BorderFactory.createTitledBorder("Documents demandés"));
		
		contentPanel.add(docPanel);
		contentPanel.add(valider);
		contentPanel.add(supprimer);
		contentPanel.add(annuler);
		contentPanel.add(modifier);
		contentPanel.add(ajouter);
		docPanel.setBackground(SystemColor.inactiveCaptionBorder);
		contentPanel.setBackground(SystemColor.inactiveCaptionBorder);
	}
	
	
	private void addlisteners() {
		
		ajouter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				contolleur.ajouterDocumentMaj();
				
			}
		});
		
		
	}
	

	public ContolleurTraitementEtape getContolleur() {
		return contolleur;
	}

	public void setContolleur(ContolleurTraitementEtape contolleur) {
		this.contolleur = contolleur;
	}

	public ModelDocumentMaj getModel() {
		return model;
	}

	public void setModel(ModelDocumentMaj model) {
		this.model = model;
	}
	
	
	
	

	
}
