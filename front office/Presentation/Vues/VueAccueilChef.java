package Vues;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableRowSorter;

import Controleurs.controleurTraitDemande;
import Models.ModelTraitDemande;

public class VueAccueilChef extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tabDemande;
	private TableRowSorter<ModelTraitDemande> sorter;
	private ModelTraitDemande model;
	private JButton Traiter, deconnexion, consulter;
	private controleurTraitDemande control;
	
	public VueAccueilChef(String Login) {
		setTitle("accueil chef division");
		setSize(700, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		control = new controleurTraitDemande(Login);
		this.model = new ModelTraitDemande(control.getAllDemande(Login));
		control.setModelDem(model);
		init();
		draw();
		Action();
	}
	
	
	public void init() {
	
		Traiter = new JButton("Traiter");
		deconnexion = new JButton();
		deconnexion.setIcon(new ImageIcon("../icons/0.png"));
		consulter = new JButton("Consulter");
		tabDemande = new JTable(model);
		sorter = new TableRowSorter<>(model);
		tabDemande.setRowSorter(sorter);
	}
	
	public void draw() {
		JPanel content = (JPanel)getContentPane();
		content.setLayout(null);
		
		JScrollPane scDemande = new JScrollPane(tabDemande);
		scDemande.setBounds(30,60,630,320);
        scDemande.setBorder(BorderFactory.createTitledBorder("Liste Demande"));
        content.add(scDemande);
        Traiter.setBounds(580,420,100,30);
        consulter.setBounds(480, 420, 100, 30);
        deconnexion.setBounds(640, 20, 30, 30);
        content.add(Traiter);
        content.add(deconnexion);
        content.add(consulter);
      content.setBackground(SystemColor.inactiveCaptionBorder);
        }
	
	public void Action() {
		Traiter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				control.vueTraitDem(getInstance());
			}
		});
		consulter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				control.consulterDemande();
			}
		});
		deconnexion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				control.vueLogin();
				dispose();
			}
		});
	}
	
	
	public VueAccueilChef getInstance() {
		return this;
	}
	public controleurTraitDemande getControl() {
		return control;
	}


	public void setControl(controleurTraitDemande control) {
		this.control = control;
	}


	public JTable getTabDemande() {
		return tabDemande;
	}


	public void setTabDemande(JTable tabDemande) {
		this.tabDemande = tabDemande;
	}


	public TableRowSorter<ModelTraitDemande> getSorter() {
		return sorter;
	}


	public void setSorter(TableRowSorter<ModelTraitDemande> sorter) {
		this.sorter = sorter;
	}
	
	



}
