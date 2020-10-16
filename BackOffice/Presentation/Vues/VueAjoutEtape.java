package Vues;

import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Beans.BeansAjoutEtape;
import Contoleurs.ControleurGestionProc;
import Contoleurs.controleurGestEtape;

public class VueAjoutEtape extends JFrame{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton jcomp1;
	    private JLabel Libelle;
	    private JTextField jcomp3;
	    private JLabel desc;
	    private JTextArea txtdesc;
	    private JLabel jcomp6;
	    private JComboBox<String> emp;
	    
	    controleurGestEtape controle;
	    ControleurGestionProc controlleur;
	    BeansAjoutEtape beansajout;
	    VueEditProc proc;
	    
	    public VueAjoutEtape(String[] cinEmp) {
	    	
	    	super("Ajouter Etape");
	    	setSize(500,500);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setLocationRelativeTo(null);
			setResizable(false);
			controlleur = new ControleurGestionProc();
			getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		
			init(cinEmp);
			draw();
			Action();
	    }
	    
	    public void init(String[] cinEmp) {
	    	
	        
	        jcomp1 = new JButton ("Ajouter");
	        Libelle = new JLabel ("Libelle");
	        jcomp3 = new JTextField (11);
	        desc = new JLabel ("description");
	        txtdesc = new JTextArea (5, 5);
	        jcomp6 = new JLabel ("CIN de l'employe");
	        
	        String[] listCIN = new String[cinEmp.length];
	        emp = new JComboBox<String>(cinEmp);
	        emp.setSelectedItem(null);
	    }
	    
	    public void draw() {
	        setPreferredSize (new Dimension (477, 461));
	        setLayout (null);

	        //add components
	        add (jcomp1);
	        add (Libelle);
	        add (jcomp3);
	        add (desc);
	        add (txtdesc);
	        add (jcomp6);
	        add (emp);

	        //set component bounds (only needed by Absolute Positioning)
	        jcomp1.setBounds (320, 420, 140, 25);
	        Libelle.setBounds (45, 75, 100, 25);
	        jcomp3.setBounds (140, 75, 151, 25);
	        desc.setBounds (45, 125, 100, 25);
	        txtdesc.setBounds (140, 125, 270, 185);
	        jcomp6.setBounds (50, 335, 100, 25);
	        emp.setBounds (170, 335, 100, 25);
	    }
	    
	    public void Action() {
	    	jcomp1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					if (FieldisEmpty()==0)
						return;
					setBeansajout(new BeansAjoutEtape(jcomp3.getText(), txtdesc.getText(), (String) emp.getSelectedItem()));
					if(controle.AjoutEtape()==false)
					{
						JOptionPane.showMessageDialog(null,"Ce Libelle déjà existe");
						return;
					}
					dispose();
				}
			});
	    }
	    
	    
	    public BeansAjoutEtape getBeansajout() {
			return beansajout;
		}

		public void setBeansajout(BeansAjoutEtape beansajout) {
			this.beansajout = beansajout;
		}

		public int FieldisEmpty() {
	    	if (jcomp3.getText().length() == 0 || txtdesc.getText().length() == 0)
	    	{
				JOptionPane.showMessageDialog(VueAjoutEtape.this, "information(s) manquante(s)");
				return 0;
	    	}
	    	return 1;
	    }
	    public controleurGestEtape getControle() {
			return controle;
		}

		public void setControle(controleurGestEtape controle) {
			this.controle = controle;
		}

		public ControleurGestionProc getControlleur() {
			return controlleur;
		}

		public void setControlleur(ControleurGestionProc controlleur) {
			this.controlleur = controlleur;
		}

	
}
