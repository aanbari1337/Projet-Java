package Vues;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Controleurs.ContolleurTraitementEtape;

public class VueRapport extends JFrame{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton valider;
	private JTextArea rapport;
	private ContolleurTraitementEtape controlleur;
	private int idEtape;
	private String jeton,decision;
	
	
	private JPanel contentpanel = (JPanel)this.getContentPane();
	
	public VueRapport() throws HeadlessException {
		super("Rapport");
		this.setSize(600,670);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		
		init();
		draw();
		addListeners();
		
	}
	
	private void init() {
		valider = new JButton("Valider");
		rapport = new JTextArea();
	}
	
	private void draw() {
		contentpanel.setLayout(null);
		rapport.setBounds(20,20, 550, 550);
		valider.setBounds(490, 590, 80, 25);
		
		contentpanel.add(rapport);
		contentpanel.add(valider);
	}
	
	private void addListeners() {
		valider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(!controlleur.enregistrerRappot(jeton, idEtape, rapport.getText(), decision))
					JOptionPane.showMessageDialog(VueRapport.this, "une erreur est survenue lors de l'nregistrement du rapport");
				dispose();
			}
		});
	}

	public JTextArea getRapport() {
		return rapport;
	}

	public void setRapport(JTextArea rapport) {
		this.rapport = rapport;
	}

	public ContolleurTraitementEtape getControlleur() {
		return controlleur;
	}

	public void setControlleur(ContolleurTraitementEtape controlleur) {
		this.controlleur = controlleur;
	}

	public int getIdEtape() {
		return idEtape;
	}

	public void setIdEtape(int idEtape) {
		this.idEtape = idEtape;
	}

	public String getJeton() {
		return jeton;
	}

	public void setJeton(String jeton) {
		this.jeton = jeton;
	}

	public String getDecision() {
		return decision;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}
	
	
	

}
