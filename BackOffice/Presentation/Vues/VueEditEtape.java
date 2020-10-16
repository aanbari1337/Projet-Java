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
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Beans.BeansAjoutEtape;
import BeansMetier.Etape;
import Contoleurs.controleurGestEtape;

public class VueEditEtape  extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton Modifier;
    private JLabel libelle;
    private JTextField txtlibelle;
    private JLabel desc;
    private JTextArea txtdesc;
    private JLabel emp;
    private JComboBox<String> txtcin;
    
    controleurGestEtape controle;
    BeansAjoutEtape beansajout;
    Etape etape;
    VueEditProc proc;

    public VueEditEtape(int id_etape, Etape etp, String[] cinEmp, JPanel pan1){
        //construct components
    	super("Modifier Etape");
    	setSize(480, 530);
    	setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
    	setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
    	
    	init(id_etape, etp, cinEmp);
    	draw();
    	Action(pan1);
    }
    
    public void init(int id_etape, Etape etp, String[] cinEmp) {
    	
	   	 etape = new Etape(etp.getId_etape(),
	     		etp.getLibelle(),
	     		etp.getDescription(),
	     		etp.getCinEmploye(),
	     		etp.getId_proc()
	     		);
    	 
    	  Modifier = new JButton ("Modifier");
          libelle = new JLabel ("Libelle :");
          txtlibelle = new JTextField (5);
          txtlibelle.setText(etp.getLibelle());
          desc = new JLabel ("Description :");
          txtdesc = new JTextArea (5, 5);
          txtdesc.setText(etp.getDescription());
          emp = new JLabel ("CIN d'employe :");
          
          String[] listCIN = new String[cinEmp.length];
	      txtcin = new JComboBox<String>(cinEmp);
	      txtcin.setSelectedItem(etape.getCinEmploye().toString());

    }
    
    public void draw() {
        setPreferredSize (new Dimension (457, 492));
        setLayout (null);
    	
        add (Modifier);
        add (libelle);
        add (txtlibelle);
        add (desc);
        add (txtdesc);
        add (emp);
        add (txtcin);

        //set component bounds (only needed by Absolute Positioning)
        Modifier.setBounds (310, 460, 140, 25);
        libelle.setBounds (30, 50, 100, 25);
        txtlibelle.setBounds (110, 50, 255, 25);
        desc.setBounds (30, 140, 100, 25);
        txtdesc.setBounds (125, 140, 325, 150);
        emp.setBounds (40, 365, 100, 25);
        txtcin.setBounds (140, 365, 240, 25);
    }
    
    public void Action( JPanel pan1) {
    	Modifier.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setBeansajout(new BeansAjoutEtape(txtlibelle.getText(), txtdesc.getText(), txtcin.getSelectedItem().toString()));
				if(controle.editEtape(etape.getId_etape(), pan1)==false)
				{
					JOptionPane.showMessageDialog(null,"Ce Libelle déjà existe");
					return;
				}
				JOptionPane.showMessageDialog(null,"Information(s) modifiées(s)");
				dispose();
			}
		});
    }

	public controleurGestEtape getControle() {
		return controle;
	}

	public void setControle(controleurGestEtape controle) {
		this.controle = controle;
	}

	public BeansAjoutEtape getBeansajout() {
		return beansajout;
	}

	public void setBeansajout(BeansAjoutEtape beansajout) {
		this.beansajout = beansajout;
	}

	public Etape getEtape() {
		return etape;
	}

	public void setEtape(Etape etape) {
		this.etape = etape;
	}

	public VueEditProc getProc() {
		return proc;
	}

	public void setProc(VueEditProc proc) {
		this.proc = proc;
	}
    
	
}
