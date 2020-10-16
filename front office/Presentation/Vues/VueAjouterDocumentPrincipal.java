package Vues;

import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Controleurs.ContolleurTraitementEtape;
import Models.ModelDocumentMaj;

public class VueAjouterDocumentPrincipal extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton edit,add,del,cancel,valid;
	private JTable tabledoc;
	private ModelDocumentMaj model;
	private ContolleurTraitementEtape contolleur;
	private String jeton;
	private int idEtape;
	private JPanel centerPanel,pan = (JPanel) this.getContentPane();
	
	public VueAjouterDocumentPrincipal(ModelDocumentMaj model) {
        setTitle("ajouter Document");
        this.setSize(500,500);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.model = model;

        init();
        draw();
        addListeners();
    }
	
	
	private void init() {
		
		edit = new JButton("Modifier");
		del = new JButton("Supprimer");
		add = new JButton("Ajouter");
		cancel = new JButton("Annuler");
		valid = new JButton("Valider");
		tabledoc = new JTable(model);
		centerPanel = new JPanel();
		pan.setLayout(null);
	}
	
	private void draw() {
		
		edit.setBounds(400, 80, 80, 25);
		del.setBounds(400, 115, 80, 25);
		add.setBounds(400, 150, 80, 25);
		cancel.setBounds(160, 400, 80, 25);
		valid.setBounds(250,400,80,25);
		centerPanel.setBounds(20, 20,350, 350);
		centerPanel.setBorder(BorderFactory.createTitledBorder("Liste des fichier a ajouter"));
		JScrollPane scrollPane = new JScrollPane(tabledoc);
		centerPanel.setLayout(new GridLayout(1,1));
		scrollPane.setBounds(20, 20,350, 350);
		centerPanel.add(scrollPane);
		
		pan.add(add);
		pan.add(edit);
		pan.add(del);
		pan.add(cancel);
		pan.add(valid);
		pan.add(centerPanel);
		
		centerPanel.setBackground(SystemColor.inactiveCaptionBorder);
		pan.setBackground(SystemColor.inactiveCaptionBorder);
	}
	
	private void addListeners(){
		
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.print("ok");
				contolleur.ajouterDocumentMaj();
			}
		});
		
		del.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int rowIndex = tabledoc.getSelectedRow();
				if(rowIndex == -1) {
					JOptionPane.showMessageDialog(VueAjouterDocumentPrincipal.this, "aucun document selectionée");
				}
				else
					contolleur.supprimerDocument(rowIndex);
			}
		});
		edit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int rowIndex = tabledoc.getSelectedRow();
				if(rowIndex == -1) {
					JOptionPane.showMessageDialog(VueAjouterDocumentPrincipal.this, "aucun document selectionée");
				}
				else
					contolleur.modifierDocument(rowIndex);
			}
		
		});
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		valid.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!contolleur.miseAJour(jeton, idEtape))
					JOptionPane.showMessageDialog(VueAjouterDocumentPrincipal.this, "une erreur est survenue lors de l'enregistrement des donnees");
				else {
					contolleur.genererVueRapport(jeton, idEtape,"mise a jour");
					dispose();
				}
			}
		});
	}


	public ContolleurTraitementEtape getContolleur() {
		return contolleur;
	}


	public void setContolleur(ContolleurTraitementEtape contolleur) {
		this.contolleur = contolleur;
	}


	public String getJeton() {
		return jeton;
	}


	public void setJeton(String jeton) {
		this.jeton = jeton;
	}


	public int getIdEtape() {
		return idEtape;
	}


	public void setIdEtape(int idEtape) {
		this.idEtape = idEtape;
	}

}
