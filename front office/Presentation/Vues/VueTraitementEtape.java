package Vues;

import java.awt.GridLayout;
import java.awt.HeadlessException;
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
import Models.ModelDocument;

public class VueTraitementEtape extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton back,validate,refuse,reject,addfiles,displayDesc,displayDoc;
	private JPanel docPanle,conteJPanel;
	private JScrollPane scrollPane;
	private JTable	tabDocument;
	private ContolleurTraitementEtape controlleur;
	private ModelDocument model;
	private String jeton;
	private String descEtape;
	private int idProc,idEtape;
	
	
	public VueTraitementEtape(ModelDocument model) throws HeadlessException {
		super("Traitement Etape");
		this.setSize(700,450);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.model = model;
		init();
		draw();
		addListeners();
	}
	
	private void init() {
		
		back = new JButton("retour");
		validate = new JButton("valider");
		refuse = new JButton("refuser");
		reject = new JButton("reject");
		displayDesc = new JButton("afficher la description");
		displayDoc = new JButton("afficher contenue");
		addfiles = new JButton("demender l'ajout d'un document");
		conteJPanel = (JPanel)this.getContentPane();

		
		docPanle = new JPanel();

		tabDocument = new JTable(model);
	}
	
	private void draw() {
		
		conteJPanel.setLayout(null);
		back.setBounds(20, 10, 80, 25);
		validate.setBounds(160, 340, 80, 25);
		refuse.setBounds(250,340, 80, 25);
		reject.setBounds(340, 340, 80, 25);
		addfiles.setBounds(430, 340, 80, 25);
		displayDesc.setBounds(520, 125, 130, 25);
		displayDoc.setBounds(520, 170, 130, 25);
		
		docPanle.setBounds(50,50,450,270);
		docPanle.setBorder(BorderFactory.createTitledBorder("Documents"));
		scrollPane = new JScrollPane(tabDocument);
		docPanle.setLayout(new GridLayout(1,1));
		docPanle.add(scrollPane);
		conteJPanel.add(back);
		conteJPanel.add(validate);
		conteJPanel.add(refuse);
		conteJPanel.add(reject);
		conteJPanel.add(addfiles);
		conteJPanel.add(docPanle);
		conteJPanel.add(displayDesc);
		conteJPanel.add(displayDoc);
		
		conteJPanel.setBackground(SystemColor.inactiveCaptionBorder);
		docPanle.setBackground(SystemColor.inactiveCaptionBorder);
		
	}
	
	
	public void addListeners() {
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				controlleur.backfromVuetraitemen();
			}
		});
		
		displayDoc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedIndex;
				selectedIndex = tabDocument.getSelectedRow();
				if(selectedIndex == -1) {
					JOptionPane.showMessageDialog(VueTraitementEtape.this, "aucun document selectionné");
					return;
				}
				selectedIndex = tabDocument.convertRowIndexToModel(selectedIndex);
				if(controlleur.displayDocs(selectedIndex) == false)
					JOptionPane.showMessageDialog(VueTraitementEtape.this, "une erreur est survenue lors de l'ouverture du document");
			}
		});
		displayDesc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VueAffichageDesc(descEtape).setVisible(true);
			}
		});
		
		reject.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int reponse = JOptionPane.showConfirmDialog(VueTraitementEtape.this, "vous voules vraiment rejeter la demande?","confirmation"
						,JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				if(reponse == JOptionPane.YES_OPTION)
				{
					if(!controlleur.rejeterDemande(jeton,idEtape))
						JOptionPane.showMessageDialog(VueTraitementEtape.this, "une erreur est survenue lors du rejet");
					else {
						controlleur.genererVueRapport(jeton, idEtape,"rejeter");
						dispose();
					}
				}
			}
		});
		
		validate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(!controlleur.validerEtape(idProc, idEtape, jeton)) {
					JOptionPane.showMessageDialog(VueTraitementEtape.this, "une erreur est survenue lors de la validation");
					return;
				}
				else {
					controlleur.genererVueRapport(jeton, idEtape,"valider");
					dispose();
				}
			}
		});
		
		refuse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(!controlleur.refsuerEtape(idProc, idEtape, jeton))
					JOptionPane.showMessageDialog(VueTraitementEtape.this, "une erreur est survenue lors du refus");
				else {
					controlleur.genererVueRapport(jeton, idEtape,"refuser");
					dispose();
				}
			}
		});
		
		addfiles.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controlleur.genererVueDemandeDoc(jeton,idEtape);
			}
		});
		
		
	}
	
	
	public ContolleurTraitementEtape getControlleur() {
		return controlleur;
	}

	public void setControlleur(ContolleurTraitementEtape controlleur) {
		this.controlleur = controlleur;
	}

	
	
	public int getIdProc() {
		return idProc;
	}

	public void setIdProc(int id_proc) {
		this.idProc = id_proc;
	}

	public String getDescEtape() {
		return descEtape;
	}

	public void setDescEtape(String descEtape) {
		this.descEtape = descEtape;
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
