package Vues;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Beans.EditProc;
import BeansMetier.Etape;
import BeansMetier.Procedure;
import Contoleurs.ControleurGestionProc;
import Contoleurs.controleurEditDoc;
import Contoleurs.controleurGestEtape;

public class VueEditProc extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel proc, Liste_proc, Liste_doc, libelle, chef;
	private JTextField txtlib;
	private JComboBox<String> boxchef;
	private JButton register, annuler, ajouter, modifier, ajout_doc, Supp_doc;
	private Etape etp;
	private JTable tabDoc;
	private TableRowSorter<TableModel> sorter;
	
	controleurGestEtape controle;
	ControleurGestionProc control;
	controleurEditDoc docControl;
	EditProc editProc;
	
	///panel 
	JPanel panel1 = (JPanel)getContentPane();
	JPanel pan = new JPanel(new FlowLayout(FlowLayout.LEFT));
	
	public VueEditProc(int id, Procedure proc, String[] cin_chef) {
		setTitle("Editer Procedure");
		setSize(700,540);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		this.controle = new controleurGestEtape();
		this.docControl = new controleurEditDoc();
		
		init(id, proc, cin_chef);
		draw(id);
		Action(id, proc);
		
	}
	
	public void init(int id, Procedure proced, String[] cin_Chef) {
		proc = new JLabel(proced.getLibelle_proc());
		register = new JButton("Enregistrer");
		annuler = new JButton("Annuler");
		modifier = new JButton("Modifer");
		ajouter = new JButton("Ajouter Etape");
		libelle = new JLabel("libelle");
		chef = new JLabel("Chef");
		ajout_doc = new JButton("Ajouter");
		Supp_doc = new JButton("Supprimer");
		boxchef = new JComboBox<String>(cin_Chef);
		boxchef.setSelectedItem(cinChef(proced));
		txtlib = new JTextField();
		txtlib.setText(proced.getLibelle_proc());
		Liste_doc = new JLabel("Liste des documents");
		
	}
	
	public void draw(int id) {
		panel1.setLayout(null);
		proc.setBounds(290,10,100,25);
		
		/////liste des etape
		JScrollPane sc = new JScrollPane(pan);
		
		sc.setBounds(30,45,635,200);
		sc.setBorder(BorderFactory.createTitledBorder("Liste des etapes"));
		
		listEtape(id);
		
		////Liste des document
		tabDoc = new JTable(docControl.getModel(id));
		JScrollPane docPanel = new JScrollPane(tabDoc);
		docPanel.setBounds(30,290,400,150);
        docPanel.setBorder(BorderFactory.createTitledBorder("Documents"));
		
		///Button, JLabel, JTextField
		register.setBounds(470,445,100,25);
		annuler.setBounds(570,445,100,25);
		modifier.setBounds(30,445,100,25);
		Supp_doc.setBounds(130,445 , 100, 25);
		ajout_doc.setBounds(230,445 , 100, 25);
		libelle.setBounds(470, 290, 70, 25);
		txtlib.setBounds(520,290,150,25);
		chef.setBounds(470, 320, 70, 25);
	    boxchef.setBounds(520, 320, 150, 25);
	    ajouter.setBounds(30, 250, 150, 25);
	    
		panel1.add(proc);
		panel1.add(register);
		panel1.add(annuler);
		panel1.add(modifier);
		panel1.add(ajout_doc);
		panel1.add(Supp_doc);
		panel1.add(libelle);panel1.add(txtlib);
		panel1.add(chef);panel1.add(boxchef);
		panel1.add(ajouter);
		panel1.add(sc);
		panel1.add(docPanel);
		panel1.setBackground(SystemColor.inactiveCaptionBorder);
		pan.setBackground(SystemColor.inactiveCaptionBorder);
		sorter = new TableRowSorter<TableModel>(docControl.getModelDoc());
		tabDoc.setRowSorter(sorter);
		
	}
	
	public void Action(int id, Procedure proc){
		ajouter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				controle.generateVueAjoutEtape(getInstance(), id);
			}
		});
		
		register.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setEditProc(new EditProc(txtlib.getText(), selectedCinChef()));
				if(docControl.addDoc(id, proc.getNbr_document())==false || control.editProcedure(id,docControl.getLibelle())==false) 
				{
					JOptionPane.showMessageDialog(null,"Ce Libelle déjà existe");
					return;
				}
				JOptionPane.showMessageDialog(null,"Information(s) enregistree(s)");
				dispose();
			}
		});
		modifier.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				docControl.vueEditDoc(getInstance(),id);
			}
		});
		ajout_doc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				docControl.AjoutDoc();
			}
		});
		Supp_doc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				docControl.DeleteDoc(getInstance());
			}
		});
		annuler.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
	}
	
	public String selectedCinChef() {
		if (boxchef.getSelectedItem()==null)
			return "";
		else
			return boxchef.getSelectedItem().toString();
	}
	
	public String cinChef(Procedure proced) {
		if(proced.getCinEmploye()==null)
			return null;
		else
			return(proced.getCinEmploye().toString());
	}

	public void listEtape(int id_proc) {
		int i = 0;
		while(i < controle.getAllEtape(id_proc).size())
			etape(controle.getAllEtape(id_proc).get(i++));
	}
	
	public VueEditProc getInstance() {
		return this;
	}
	
	public void etape(Etape etape) {

		JButton edit,supprimer;
		JPanel pan1 = new JPanel(new FlowLayout());
		pan1.setPreferredSize(new Dimension(150, 90));
		edit = new JButton();
		edit.setIcon(new ImageIcon("../icons/96.png"));
		supprimer = new JButton();
		supprimer.setIcon(new ImageIcon("../icons/33.png"));
		pan1.setBorder(BorderFactory.createTitledBorder(etape.getLibelle()));
		pan1.add(edit);
		pan1.add(supprimer);
		pan.add(pan1);
		pan1.setBackground(SystemColor.inactiveCaptionBorder);
		///Action
		edit.addActionListener(e->editEtape(etape.getId_etape(), pan1));
		SwingUtilities.updateComponentTreeUI(pan1);
		supprimer.addActionListener(e->DeleteEtape(etape.getId_etape(), pan1));
		SwingUtilities.updateComponentTreeUI(this);

	}

	
	public void editEtape(int id_etape,JPanel pan1) {
		controle.generateVueEditEtape(id_etape, pan1);
		
	}
	
	public void DeleteEtape(int id_etape,JPanel panel) {

		controle.gestionnaireDelete(id_etape);
		pan.remove(panel);
	    pan.repaint();///tell the panel to repaint itself
		JOptionPane.showMessageDialog(null,"Etape a été supprimee avec succees");
	}
	

	public controleurGestEtape getControle() {
		return controle;
	}

	public void setControle(controleurGestEtape controle) {
		this.controle = controle;
	}

	public ControleurGestionProc getControl() {
		return control;
	}

	public void setControl(ControleurGestionProc control) {
		this.control = control;
	}

	public EditProc getEditProc() {
		return editProc;
	}

	public void setEditProc(EditProc editProc) {
		this.editProc = editProc;
	}

	public controleurEditDoc getDocControl() {
		return docControl;
	}

	public void setDocControl(controleurEditDoc docControl) {
		this.docControl = docControl;
	}

	public JTable getTabDoc() {
		return tabDoc;
	}

	public void setTabDoc(JTable tabDoc) {
		this.tabDoc = tabDoc;
	}

	public TableRowSorter<TableModel> getSorter() {
		return sorter;
	}

	public void setSorter(TableRowSorter<TableModel> sorter) {
		this.sorter = sorter;
	}

	public Etape getEtp() {
		return etp;
	}

	public void setEtp(Etape etp) {
		this.etp = etp;
	}

	
	

	
}
