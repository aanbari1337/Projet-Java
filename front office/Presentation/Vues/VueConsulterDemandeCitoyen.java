package Vues;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import Controleurs.ControleurConsulterDemande;

public class VueConsulterDemandeCitoyen extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JTextField txtDateDepot,libelleProc,etpactuel,etatFinal;
	private JButton maj,close,info;
	private JSlider etat;
	private ControleurConsulterDemande control;
	
	
	public VueConsulterDemandeCitoyen(ControleurConsulterDemande control) {
		this.control = control;
		setTitle("Consultation de la demande");
		setSize(500,400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		panel = (JPanel) this.getContentPane();
		panel.setBackground(SystemColor.inactiveCaptionBorder);
		panel.setLayout(null);
		init();
		init_2();
		listners();
	}
	
	private void init() {
		JLabel infos = new JLabel("Informations de la demande");
		infos.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		infos.setBounds(140,11,231,25);
		JLabel libelle = new JLabel("Libelle de la procedure demande :");
		libelle.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		libelle.setBounds(40,53,221,25);
		libelleProc = new JTextField("");
		libelleProc.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		libelleProc.setEditable(false);
		libelleProc.setBounds(280,54,167,25);
		JLabel dateDepot = new JLabel("Date depot :");
		dateDepot.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		dateDepot.setBounds(40, 90, 221, 25);
		txtDateDepot = new JTextField("");
		txtDateDepot.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		txtDateDepot.setEditable(false);
		txtDateDepot.setBounds(280, 90, 167, 25);
		JLabel avancement = new JLabel("Etat d'avancement de la demande");
		avancement.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		avancement.setBounds(150, 126, 201, 25);
		
		etat = new JSlider(0,100);
		etat.setSize(380, 26);
		etat.setLocation(60, 154);
		etat.setEnabled(false);
		panel.add(etat);
		panel.add(libelleProc);
		panel.add(libelle);
		panel.add(infos);
		panel.add(dateDepot);
		panel.add(txtDateDepot);
		panel.add(avancement);
	}

	private void init_2() {
		JLabel etpActuel = new JLabel("Etat etape actuel :");
		etpActuel.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		etpActuel.setBounds(40, 203, 178, 25);
		etpactuel = new JTextField("");
		etpactuel.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		etpactuel.setEditable(false);
		etpactuel.setBounds(167, 202, 150, 25);
		maj = new JButton("Mise a jour");
		maj.setBounds(362,203,85,25);
		maj.setEnabled(false);
		JLabel lblEtatFinal = new JLabel("Etat final de la demande :");
		lblEtatFinal.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		lblEtatFinal.setBounds(40, 243, 178, 25);
		etatFinal = new JTextField("");
		etatFinal.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		etatFinal.setEditable(false);
		etatFinal.setBounds(280, 243, 167, 25);
		close = new JButton("Fermer");
		close.setBounds(194,335,90,25);
		info = new JButton();
		info.setIcon(new ImageIcon("../icons/1.png"));
		info.setEnabled(false);
		info.setBounds(327, 203, 25, 25);
		
		panel.add(close);
		panel.add(maj);
		panel.add(etpactuel);
		panel.add(etpActuel);
		panel.add(lblEtatFinal);
		panel.add(etatFinal);
		panel.add(info);
		
	}

	public JTextField getLibelleProc() {
		return libelleProc;
	}


	public JTextField getTxtDateDepot() {
		return txtDateDepot;
	}

	public JTextField getEtpactuel() {
		return etpactuel;
	}

	public JTextField getEtatFinal() {
		return etatFinal;
	}

	public JSlider getEtat() {
		return etat;
	}
	
	private void listners() {
		close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		maj.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				control.showVueMaj();
			}
		});
		info.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				control.showVueInfo();
				
			}
		});
	}

	public JButton getMaj() {
		return maj;
	}

	public JButton getInfo() {
		return info;
	}

	public JPanel getPanel() {
		return panel;
	}
	
}
