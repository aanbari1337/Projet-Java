package Vues;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Contoleurs.ControleurAddProc;

public class VueAjouterEtapeProc extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField libelleEtape;
    private JTextArea descriptionEtape;
    private JButton add;
    private JButton cancel;
	private JComboBox<String> respos;
    private ControleurAddProc control;
    
    public VueAjouterEtapeProc(ControleurAddProc control,String[] employes) {
    	this.control = control;
        setTitle("Ajouter etape");
        this.setSize(500,500);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        init(employes);
        draw();
        listners();
    }

	private void init(String[] employes){
        libelleEtape = new JTextField();
        descriptionEtape = new JTextArea();
        add = new JButton("ajouter");
        cancel = new JButton("annuler");
        respos = new JComboBox<String>(employes);
        respos.setSelectedItem(null);
    }

    private void draw(){
        JPanel content = (JPanel)getContentPane();
        content.setLayout(null);
        content.setBackground(SystemColor.inactiveCaptionBorder);
        JLabel libelle = new JLabel("libelle de l'étape  :");
        JLabel desc = new JLabel("description :");
        JLabel responsable  = new JLabel("employe responsable :");

        libelle.setBounds(30,30,140,25);
        libelleEtape.setBounds(295,30,160,25);


        responsable.setBounds(30,65,140,25);
        respos.setBounds(295,65,160,25);

        desc.setBounds(30,100,80,25);

        JScrollPane scrollPane = new JScrollPane(descriptionEtape);
        scrollPane.setBounds(30,130,425,250);

        cancel.setBounds(270,400,80,25);
        add.setBounds(375,400,80,25);

        content.add(libelle);
        content.add(libelleEtape);
        content.add(desc);
        content.add(scrollPane);
        content.add(add);
        content.add(cancel);
        content.add(responsable);
        content.add(respos);
    }
    
    private void listners() {
    	add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int res;
				if(libelleEtape.getText().equals("") || descriptionEtape.getText().equals(""))
					JOptionPane.showMessageDialog(null, "champ libelle ou description vide");
				else {
					if(respos.getSelectedItem() != null)
					res = control.etapeAdd(libelleEtape.getText(), respos.getSelectedItem().toString()
							, descriptionEtape.getText());
					else 
						res = control.etapeAdd(libelleEtape.getText(),""
								, descriptionEtape.getText());
					if(res == 0) {
						JOptionPane.showMessageDialog(null, "etape ajouter");
						dispose();
						}else
							JOptionPane.showMessageDialog(null, "etape existe");
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
}
