package Vues;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Controleurs.ControleurStatEtapeByEmp;

public class VueStatEtapeByEmp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JButton byEmp,allEmp,close;
	private JPanel panel;
	private ControleurStatEtapeByEmp control;
	
	public VueStatEtapeByEmp() {
		control = new ControleurStatEtapeByEmp(this);
		setTitle("Taux de validation des etapes");
		setSize(500,400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		init();
		draw();
		listners();
	}
	
	private void init() {
		byEmp = new JButton("Par employe");
		allEmp = new JButton("par plusieurs employes");
		close = new JButton("Fermer");
		panel = new JPanel(new BorderLayout());
		panel.setBackground(SystemColor.inactiveCaptionBorder);
	}
	
	private void draw() {
		JPanel content = (JPanel) getContentPane();
		content.setBackground(SystemColor.inactiveCaptionBorder);
		JPanel pan = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pan.setBackground(SystemColor.inactiveCaptionBorder);
		pan.add(close);
		JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p.setBackground(SystemColor.inactiveCaptionBorder);
		p.add(byEmp);
		p.add(allEmp);

		content.add(p,BorderLayout.NORTH);
		content.add(panel);
		content.add(pan,BorderLayout.SOUTH);
	}
	
	private void listners() {
		close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new VueEspaceAdmin().setVisible(true);
			}
		});
		byEmp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				control.showStatEtapebyEmp();
			}
		});
		allEmp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				control.showStatEtapeAllEmp();
			}
		});
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}
	

}
