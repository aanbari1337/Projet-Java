package Vues;

import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Controleurs.controleurTraitDemande;
import Models.ModelDoc;

public class vueTraiteDem extends JFrame{

	private static final long serialVersionUID = 1L;
	private JButton back,validate,refuse,displayDesc,displayDoc;
	private JPanel docPanle,conteJPanel;
	private JScrollPane scrollPane;
	private JTable	tabDocument;
	private controleurTraitDemande controlleur;
	private ModelDoc model;
	public vueTraiteDem(ArrayList<String> lien, String jeton){
		super("Traitement Demande");
		this.setSize(700,500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.model = new ModelDoc(lien);
		init();
		draw();
		Action(jeton);
	}
	
	private void init() {
		
		back = new JButton("retour");
		validate = new JButton("valider");
		refuse = new JButton("refuser");
		displayDesc = new JButton("afficher la description");
		displayDoc = new JButton("afficher contenue");
		conteJPanel = (JPanel)this.getContentPane();
		conteJPanel.setBackground(SystemColor.inactiveCaptionBorder);
		docPanle = new JPanel();
		docPanle.setBackground(SystemColor.inactiveCaptionBorder);
		tabDocument = new JTable(model);
	}
	
	private void draw() {
		
		conteJPanel.setLayout(null);
		back.setBounds(20, 10, 80, 25);
		validate.setBounds(240, 420, 100, 30);
		refuse.setBounds(350,420, 100, 30);
		displayDesc.setBounds(500, 160, 130, 30);
		displayDoc.setBounds(500, 200, 130, 30);
		
		docPanle.setBounds(50,80,400,270);
		docPanle.setBorder(BorderFactory.createTitledBorder("Documents"));
		scrollPane = new JScrollPane(tabDocument);
		docPanle.setLayout(new GridLayout(1,1));
		docPanle.add(scrollPane);
		
		conteJPanel.add(back);
		conteJPanel.add(validate);
		conteJPanel.add(refuse);
		conteJPanel.add(docPanle);
		conteJPanel.add(displayDesc);
		conteJPanel.add(displayDoc);
		
	}
	
	
	public void Action(String jeton) {
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		displayDoc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedIndex;
				selectedIndex = tabDocument.getSelectedRow();
				if(selectedIndex == -1) {
					JOptionPane.showMessageDialog(vueTraiteDem.this, "aucun document selectionné");
					return;
				}
				selectedIndex = tabDocument.convertRowIndexToModel(selectedIndex);
				if(controlleur.displayDocs(selectedIndex, jeton) == false)
					JOptionPane.showMessageDialog(vueTraiteDem.this, "une erreur est survenue lors de l'ouverture du document");
			}
		});
		
		displayDesc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				controlleur.vueDesProc();
			}
		});
		
		validate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				controlleur.validateDemande(jeton, false,"en cours de traitement");
				dispose();
			}
		});
		
		refuse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				controlleur.archiverDemande(jeton, true, "rejetee");
				dispose();
			}
		});
	}
	
	

	public JTable getTabDocument() {
		return tabDocument;
	}

	public void setTabDocument(JTable tabDocument) {
		this.tabDocument = tabDocument;
	}

	public ModelDoc getModel() {
		return model;
	}

	public void setModel(ModelDoc model) {
		this.model = model;
	}

	public controleurTraitDemande getControl() {
		return controlleur;
	}

	public void setControl(controleurTraitDemande control) {
		this.controlleur = control;
	}
	
	
	
}
