package Vues;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Contoleurs.ContoleurGestionEmp;
import Contoleurs.ControleurGestionProc;
import Utils.StoreData;

public class VueControlPanel extends JFrame{


	private static final long serialVersionUID = 1138234565370752078L;
	private JButton manageEmp,manageProc,deconnect;
	
	public VueControlPanel() {
		setTitle("Control Panel");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(500,400);
		this.setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);
		init();
		draw();
		listeners();
	}
	
	private void init() {
		manageProc = new JButton("Gestion des procédures");
		manageEmp = new JButton("Gestion des employés");
		deconnect = new JButton();
	}
	
	private void draw() {
		JPanel pan = (JPanel)this.getContentPane();
		manageEmp.setBounds(95, 105, 300, 100);
		manageProc.setBounds(95, 228, 300, 100);
		deconnect.setBounds(434, 11, 50, 50);
		deconnect.setIcon(new ImageIcon("../icons/0.png"));
		pan.add(manageProc);
		pan.add(manageEmp);
		pan.setBackground(SystemColor.inactiveCaptionBorder);
		pan.add(deconnect);
	}
	
	private void listeners() {
		manageProc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new ControleurGestionProc().showVueGestionProc();
				dispose();
				
			}
		});
		manageEmp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ContoleurGestionEmp contoleurGestionEmp = new ContoleurGestionEmp();
				contoleurGestionEmp.getVueGestionEmp().setContoleur(contoleurGestionEmp);
				contoleurGestionEmp.getVueGestionEmp().setVisible(true);
				
			}
		});
		
		deconnect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VueConnexion().setVisible(true);
				StoreData.store();
				dispose();
			}
		});
		
	}
}

