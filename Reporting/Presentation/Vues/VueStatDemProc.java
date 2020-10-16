package Vues;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Controleurs.ControleurStatDemProc;

public class VueStatDemProc extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton byProc,allProc,close;
	private JPanel panel;
	private ControleurStatDemProc control;
	
	public VueStatDemProc() {
		control = new ControleurStatDemProc(this);
		setTitle("Taux d'acceptation des demandes de procedures");
		setSize(500,400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		init();
		draw();
		listners();
	}

	private void init() {
		byProc = new JButton("Taux par procedure");
		allProc = new JButton("Taux par plusieurs procedure");
		close = new JButton("Fermer");
		panel = new JPanel(new BorderLayout());
	}
	
	private void draw() {
		JPanel content = (JPanel) getContentPane();
		content.setBackground(SystemColor.inactiveCaptionBorder);
		JPanel pan = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pan.setBackground(SystemColor.inactiveCaptionBorder);
		pan.add(close);
		JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p.setBackground(SystemColor.inactiveCaptionBorder);
		p.add(byProc);
		p.add(allProc);
		panel.setBackground(SystemColor.inactiveCaptionBorder);
		content.add(p,BorderLayout.NORTH);
		content.add(panel);
		content.add(pan,BorderLayout.SOUTH);
	}

	private void listners() {
		close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new VueEspaceAdmin().setVisible(true);
			}
		});
		byProc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				control.showStatByProc();
			}
		});
		allProc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				control.showStatAllProc();
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
