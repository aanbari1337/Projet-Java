package Vues;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Beans.Doc;
import Contoleurs.controleurEditDoc;

public class VueEditDocument extends JFrame{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton valider;
	    private JLabel libelle;
	    private JTextField txtlibelle;
	    private JLabel desc;
	    private JTextArea txtDesc;
	    
	    private controleurEditDoc controlDoc;

	    public VueEditDocument(Doc document, int id_proc) {
	        //construct components
	    	setTitle("modifier Document");
	        setSize(480, 480);
	    	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setLocationRelativeTo(null);
	        setResizable(false);
	        setLayout (null);
	        controlDoc = new controleurEditDoc();
	        setBackground(SystemColor.inactiveCaptionBorder);
	        
	        init(document);
	        Draw();
	        Action(document.getLiblle(), id_proc);
	    }

	    public void init(Doc document) {
	    	 valider = new JButton ("Valider");
		        libelle = new JLabel ("Libelle :");
		        txtlibelle = new JTextField (8);
		        txtlibelle.setText(document.getLiblle());
		        desc = new JLabel ("Description :");
		        txtDesc = new JTextArea (5, 5);
		        txtDesc.setText(document.getDecription());
	    }
	    
	    public void Draw() {
	        add (valider);
	        add (libelle);
	        add (txtlibelle);
	        add (desc);
	        add (txtDesc);

	        //set component bounds (only needed by Absolute Positioning)
	        valider.setBounds (340, 385, 100, 25);
	        libelle.setBounds (50, 60, 100, 25);
	        txtlibelle.setBounds (135, 65, 250, 25);
	        desc.setBounds (50, 130, 100, 25);
	        txtDesc.setBounds (135, 135, 255, 195);
	    }

		public controleurEditDoc getControlDoc() {
			return controlDoc;
		}

		public void setControlDoc(controleurEditDoc controlDoc) {
			this.controlDoc = controlDoc;
		}
	    
		public void Action(String libelle, int id_proc) {
			valider.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					Doc document = new Doc(txtlibelle.getText(), txtDesc.getText());
					if(controlDoc.editDocument(document,libelle, id_proc)==false) 
					{
						JOptionPane.showMessageDialog(null,"Ce Libelle déjà existe");
						return;
					}
					dispose();
				}
			});
		}
	    
}
