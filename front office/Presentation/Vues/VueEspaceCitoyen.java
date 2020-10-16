package Vues;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controleurs.ControleurEspaceCitoyen;

public class VueEspaceCitoyen extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7979715786703561811L;
	private JComboBox<String> listProc;
	private JButton demander,info,quiter,consulter;
	private JLabel label1,label2;
	private JTextField jeton;
	private String cin;
	private ControleurEspaceCitoyen control;
	
	public VueEspaceCitoyen(String cin,ControleurEspaceCitoyen control,String[] list) {
		this.cin =cin;
		this.control = control;
		setTitle("Espace Citoyen");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(800,500);
		setLocationRelativeTo(null);
		setResizable(false);
		init(list);
		draw();
		listeners();
	}
	
	private void init(String[] list) {
		
		demander = new JButton("Demander");
		info = new JButton("Infos");
		quiter = new JButton("Quiter");
		consulter = new JButton("Consulter");
		listProc = new JComboBox<String>(list);
		listProc.setSelectedItem(null);
		label1 = new JLabel("veuillez selectionner une procedure :");
		label1.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		label2 = new JLabel("Veuillez entrer le code de la demande :");
		label2.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		jeton = new JTextField();
	}
	
	private void draw() {
		JPanel content = (JPanel) this.getContentPane();
		
		content.setBackground(SystemColor.inactiveCaptionBorder);
		JPanel pan1 = new JPanel(null);
		JPanel pan2 = new JPanel(null);
		pan1.setBorder(BorderFactory.createTitledBorder("Deposer une demande"));
		pan1.setBackground(SystemColor.inactiveCaptionBorder);
		pan1.setBounds(100,70,600,150);
		pan2.setBounds(100,250,600,150);
		content.setLayout(null);
		JLabel label3 = new JLabel("Vous pouvez consulter les documents necessaires de la procedure selectionner :");
		label3.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		
		demander.setBounds(370,62,90,25);
		info.setBounds(489,108,90,25);
		listProc.setBounds(151,62,200,25);
		label1.setBounds(22,23,244,40);
		label3.setBounds(22,99,472,40);
		pan1.add(demander);
		pan1.add(info);
		pan1.add(listProc);
		pan1.add(label1);
		pan1.add(label3);
		
		pan2.setBackground(SystemColor.inactiveCaptionBorder);
		label2.setBounds(25,26,400,40);
		consulter.setBounds(370,62,90,25);
		jeton.setBounds(151,62,200,25);
		pan2.setBorder(BorderFactory.createTitledBorder("Consulter l'etat d'une procedure"));
		pan2.add(label2);
		pan2.add(consulter);
		pan2.add(jeton);
		
		quiter.setBounds(694,435,90,25);
		content.add(quiter);
		content.add(pan1);
		content.add(pan2);
	}
	
	private void listeners() {
		info.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(listProc.getSelectedItem() != null)
				control.showVueInfoProc(listProc.getSelectedItem().toString());
				else
					JOptionPane.showMessageDialog(null, "Veuillez selectioner une procedure");
			}
		});
		demander.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(listProc.getSelectedItem() != null)
					control.showVueUploadDocument(listProc.getSelectedItem().toString(),cin);
					else
						JOptionPane.showMessageDialog(null, "Veuillez selectioner une procedure");
				}
				
		});
		quiter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LoginPage().setVisible(true);
			}
		});
		
		consulter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(jeton.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Veuillez entrer un jeton","Empty",JOptionPane.INFORMATION_MESSAGE);
				}else
					control.showVueConsulterDemande(jeton.getText().toString(),cin);
				
			}
		});
		
	}
	

}
