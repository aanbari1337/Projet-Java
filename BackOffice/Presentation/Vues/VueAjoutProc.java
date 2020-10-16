package Vues;

import java.awt.Dimension;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Contoleurs.ControleurAddProc;
import Models.ModelDoc;
import Models.ModelEtape;

public class VueAjoutProc extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton addDoc,add,cancel,addStep,deleteEtape,deleteDoc;
    private JTextField libelleProc;
    private JComboBox<String> jComboBox;
    private JTextArea descProc;
    private ControleurAddProc control;
    private JTable tableDoc;
    private JTable tabletape;
 

    public VueAjoutProc(ControleurAddProc control,String[] chefs) {
    	this.control = control;
        setTitle("ajouter procedure");
        this.setSize(new Dimension(1000,600));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        init(chefs);
        draw();
        listners();
    }

    private void init(String[] chefs){
        addDoc = new JButton("ajouter document");
        add = new JButton("ajouter");
        cancel = new JButton("annuler");
        addStep = new JButton("ajouter etape");
        deleteDoc = new JButton("Supprimer document");
        deleteEtape = new JButton("Supprimer etape");
        jComboBox = new JComboBox<String>(chefs);
        jComboBox.setSelectedItem(null);
        libelleProc = new JTextField();
        descProc = new JTextArea();
        tableDoc = new JTable(new ModelDoc());
        tabletape = new JTable(new ModelEtape());
    }

    private void draw(){
        JPanel content = (JPanel)getContentPane();
        content.setLayout(null);
        content.setBackground(SystemColor.inactiveCaptionBorder);
        addDoc.setBounds(50,350,150,25);
        addStep.setBounds(230,350,150,25);

        JLabel libelle = new JLabel("libelle *:");
        libelle.setBounds(40,100,80,25);
        libelleProc.setBounds(250,100,150,25);
        JLabel listeChef = new JLabel("Chef responsable :");
        listeChef.setBounds(40,180,120,25);
        jComboBox.setBounds(250,180,150,25);
        descProc.setBounds(40,240,340,100);
        
        JPanel descPanel = new JPanel();
        descPanel.setLayout(null);
        descPanel.setBackground(SystemColor.inactiveCaptionBorder);
        descPanel.setBounds(30,20,450,420);
        descPanel.setLayout(null);
        descPanel.setBorder(BorderFactory.createTitledBorder("informations de la procedure"));
        descPanel.add(addStep);
        descPanel.add(addDoc);
        descPanel.add(libelle);
        descPanel.add(libelleProc);
        descPanel.add(listeChef);
        descPanel.add(jComboBox);
        descPanel.add(descProc);
        content.add(descPanel);


        JScrollPane docPanel = new JScrollPane(tableDoc);
        docPanel.setBounds(500,20,340,200);
        docPanel.setBorder(BorderFactory.createTitledBorder("Docs"));

        JScrollPane etapePanel = new JScrollPane(tabletape);
        etapePanel.setBounds(500,240,340,200);
        etapePanel.setBorder(BorderFactory.createTitledBorder("Etapes"));

        content.add(docPanel);
        content.add(etapePanel);


        add.setBounds(760,500,80,25);
        cancel.setBounds(870,500,80,25);
        deleteDoc.setBounds(870,100,90,25);
        deleteEtape.setBounds(870,332,90,25);
        content.add(deleteEtape);
        content.add(deleteDoc);
        content.add(add);
        content.add(cancel);
    }
    
    private void listners() {
    	addDoc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				control.showVueAddDoc();
			}
		});
    
    	addStep.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				control.showVueAddEtape();
				
			}
		});
    	cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
    	
    	add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(libelleProc.getText().equals("") || descProc.getText().equals(""))
					JOptionPane.showMessageDialog(null, "champ libelle ou description vide");
				else {
					if(jComboBox.getSelectedItem() !=null)
						control.addProc(libelleProc.getText(),descProc.getText(),jComboBox.getSelectedItem().toString());
					else
						control.addProc(libelleProc.getText(),descProc.getText(),"");
			}
				}
		});
    	
    	deleteEtape.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				control.removeEtape();
			}
		});
    	deleteDoc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				control.removeDoc();
			}
		});
    }

	public JTable getTableDoc() {
		return tableDoc;
	}

	public void setTableDoc(JTable tableDoc) {
		this.tableDoc = tableDoc;
	}

	public JTable getTabletape() {
		return tabletape;
	}

	public void setTabletape(JTable tabletape) {
		this.tabletape = tabletape;
	}
    
    
}
