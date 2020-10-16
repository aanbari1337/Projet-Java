package Vues;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import Controleurs.controleurTraitDemande;

public class VueDesProc extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea txtProc;
    private JLabel descProc;
    private JButton fermer;
    private controleurTraitDemande control;

    public VueDesProc(String descProc) {
    	super("Description procedure");
		this.setSize(411,400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
        //construct components
        txtProc = new JTextArea (5, 5);
        fermer = new JButton("fermer");
        txtProc.setText(descProc);
        txtProc.setEditable(false);
        this.descProc = new JLabel ("Description de la procedure :");
        this.descProc.setFont(new Font("Verdana", Font.PLAIN, 13));

        //adjust size and set layout
        setPreferredSize (new Dimension (411, 378));
        setLayout (null);
        
        txtProc.setBounds (35, 65, 325, 245);
        this.descProc.setBounds (35, 30,200, 25);
        fermer.setBounds(150, 330, 100, 25);
        fermer.addActionListener(e->Action());

        //add components
        add (txtProc);
        add (this.descProc);
        add(fermer);
        getContentPane().setBackground(SystemColor.inactiveCaptionBorder);

    }
     public void Action() {
    	 dispose();
     }
	public controleurTraitDemande getControl() {
		return control;
	}

	public void setControl(controleurTraitDemande control) {
		this.control = control;
	}
 
	
 
}
