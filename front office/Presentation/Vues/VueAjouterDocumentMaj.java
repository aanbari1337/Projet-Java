package Vues;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Controleurs.ContolleurTraitementEtape;

public class VueAjouterDocumentMaj extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField libelleDoc;
    private JTextArea descriptionDoc;
    private JButton add;
    private JButton cancel;
    private ContolleurTraitementEtape control;
    
    public VueAjouterDocumentMaj() {
    	//this.control = control;
        setTitle("ajouter Document");
        this.setSize(500,500);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        init();
        draw();
        listners();
    }

    private void init(){
        libelleDoc = new JTextField();
        descriptionDoc = new JTextArea();
        add = new JButton("ajouter");
        cancel = new JButton("annuler");
    }

    private void draw(){
        JPanel content = (JPanel)getContentPane();
        content.setLayout(null);

        JLabel libelle = new JLabel("libelle du document * :");
        JLabel desc = new JLabel("description :");

        libelle.setBounds(30,30,140,25);
        libelleDoc.setBounds(295,30,160,25);
        desc.setBounds(30,65,80,25);
        descriptionDoc.setBounds(30,100,425,250);

        JScrollPane scrollPane = new JScrollPane(descriptionDoc);
        scrollPane.setBounds(30,100,425,250);

        cancel.setBounds(270,400,80,25);
        add.setBounds(375,400,80,25);

        content.add(libelle);
        content.add(libelleDoc);
        content.add(desc);
        content.add(scrollPane);
        content.add(add);
        content.add(cancel);
        content.setBackground(SystemColor.inactiveCaptionBorder);
    }

   private void listners() {
	   add.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(libelleDoc.getText().equals("") || descriptionDoc.getText().equals(""))
				JOptionPane.showMessageDialog(null, "veuilez remlir tous les champs");
			else {
				control.validerAjoutDocument();
				dispose();
			}
		}
	});
	   
	   cancel.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			
		}
	});
   }

	public ContolleurTraitementEtape getControl() {
		return control;
	}
	
	public void setControl(ContolleurTraitementEtape control) {
		this.control = control;
	}
	
	public JTextField getLibelleDoc() {
		return libelleDoc;
	}
	
	public void setLibelleDoc(JTextField libelleDoc) {
		this.libelleDoc = libelleDoc;
	}
	
	public JTextArea getDescriptionDoc() {
		return descriptionDoc;
	}
	
	public void setDescriptionDoc(JTextArea descriptionDoc) {
		this.descriptionDoc = descriptionDoc;
	}
}
