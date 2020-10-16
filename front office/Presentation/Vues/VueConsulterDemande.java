package Vues;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import BeansMetier.Etape;
import Controleurs.controleurTraitDemande;
import Metier.Demande;

public class VueConsulterDemande extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTabbedPane tabs;
	private JPanel pan;
	private JTextField txtDateDepot,libelleProc,etatFinal;
	private JButton close;
	private JSlider etat;
	private ArrayList<Etape> listeEtape;
	private controleurTraitDemande control;
	public VueConsulterDemande(ArrayList<Demande> demandes, String Login) {
		setTitle("Infos Procedure");
		setSize(500,500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		control = new controleurTraitDemande(Login);
		tabs = new JTabbedPane();
		draw();
		createTabs(demandes);
	}
	
	private void draw() {
		JPanel content = (JPanel)this.getContentPane();
		JPanel pan = new JPanel(new FlowLayout());
		JLabel lbl = new JLabel("Information des demandes");
		lbl.setFont(new Font("Verdana", Font.PLAIN, 13));
		pan.add(lbl);
		content.add(pan,BorderLayout.NORTH);
		content.add(tabs);
		pan.setBackground(SystemColor.inactiveCaptionBorder);
		content.setBackground(SystemColor.inactiveCaptionBorder);
	}
	
	public void createTabs(ArrayList<Demande> demandes) {
		for (int i = 0; i < demandes.size(); i++) {
			init(demandes.get(i));
		}
	}
	
	public void init(Demande demandes) {
		this.setListeEtape(control.getIdEtapeActuel(demandes.getJeton(),demandes.getId_proc()));
		pan = new JPanel();
		pan.setBackground(SystemColor.inactiveCaptionBorder);
		pan .setLayout(null);
		JLabel libelle = new JLabel("Libelle de la procedure demande :");
		libelle.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		libelle.setBounds(60,53,201,25);
		libelleProc = new JTextField();
		libelleProc.setText(demandes.getNomProc());
		libelleProc.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		libelleProc.setEditable(false);
		libelleProc.setBounds(280,54,150,25);
		JLabel dateDepot = new JLabel("Date depot :");
		dateDepot.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		dateDepot.setBounds(60, 90, 201, 25);
		txtDateDepot = new JTextField();
		txtDateDepot.setText(demandes.getDateDepo().toString());
		txtDateDepot.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		txtDateDepot.setEditable(false);
		txtDateDepot.setBounds(280, 90, 150, 25);
		JLabel avancement = new JLabel("Etat d'avancement de la demande");
		avancement.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		avancement.setBounds(150, 126, 201, 25);
		
		etat = new JSlider(0,listeEtape.size());
		etat.setPaintTrack(true); 
        etat.setPaintTicks(true); 
        etat.setPaintLabels(true);
        etat.setMajorTickSpacing(1); 
        //b.setMinorTickSpacing(5); 
		etat.setValue(setSlideValue(demandes.getIdEtapeActuel()));
		etat.setSize(380, 35);
		etat.setLocation(60, 154);
		etat.setEnabled(false);
		
		JLabel lblEtatFinal = new JLabel("Etat final de la demande :");
		lblEtatFinal.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		lblEtatFinal.setBounds(60, 243, 158, 25);
		etatFinal = new JTextField();
		etatFinal.setText(demandes.getEtat());
		etatFinal.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		etatFinal.setEditable(false);
		etatFinal.setBounds(280, 243, 150, 25);
		close = new JButton("Fermer");
		close.setBounds(194,335,90,25);
		close.addActionListener(e->Action());
		
		pan.add(libelle);
		pan.add(etat);
		pan.add(libelleProc);
		pan.add(dateDepot);
		pan.add(txtDateDepot);
		pan.add(avancement);
		pan.add(lblEtatFinal);
		pan.add(etatFinal);
		pan.add(close);
		
		tabs.add(demandes.getNomProc(), pan);
	}
	
	public void Action() {
				dispose();
	}
	public int setSlideValue(int idEtapeActuel) {
		for (int i = 0; i < listeEtape.size(); i++) {
			if(listeEtape.get(i).getId_etape()==idEtapeActuel)
				return i+1;
		}
		return 0;
	}
	
	public controleurTraitDemande getControl() {
		return control;
	}

	public void setControl(controleurTraitDemande control) {
		this.control = control;
	}

	public ArrayList<Etape> getListeEtape() {
		return listeEtape;
	}

	public void setListeEtape(ArrayList<Etape> listeEtape) {
		this.listeEtape = listeEtape;
	}
	
	
	
} 
