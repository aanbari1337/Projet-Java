package Vues;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import Beans.employeBean;
import Contoleurs.ContoleurGestionEmp;
import Utils.DateLabelFormatter;
import Utils.Utils;


public class VueAddEmp extends JFrame {

	private static final long serialVersionUID = 3572829133147902327L;
	private JButton add,cancel;
	private JLabel nom,prenom,cin,genre,dateNaiss,grade,dateRecrut;
	private JTextField txtNom,txtPrenom,txtCin;
	private JComboBox<String> cGrade;
	private JRadioButton m,f;
	private JDatePickerImpl naissance,recrutement;
	private ContoleurGestionEmp controleur;
	private UtilDateModel model;
	private UtilDateModel model1;
	
	
	
	public VueAddEmp() {
		setTitle("Ajouter un employe");
		setSize(450,450);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		init();
		draw();
		ajouterListeners();
	}
	
	private void init() {
		add = new JButton("Ajouter");
		cancel = new JButton("Annuler");
		nom = new JLabel("Nom :");
		prenom = new JLabel("Prenom :");
		cin = new JLabel("Cin :");
		genre = new JLabel("Genre :");
		grade = new JLabel("Grade :");
		dateNaiss = new JLabel("Date de naissance :");
		dateRecrut = new JLabel("Date de Recrutement :");
		txtNom = new JTextField();
		txtPrenom = new JTextField();
		txtCin = new JTextField();
		cGrade = new JComboBox<String>();
		cGrade.addItem("Chef de division");
		cGrade.addItem("Responsable Etape");
		m = new JRadioButton("M");
		m.setSelected(true);
		f = new JRadioButton("F");
		ButtonGroup group = new ButtonGroup();
		group.add(m);
		group.add(f);
		model = new UtilDateModel();
		model1 = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		naissance = new JDatePickerImpl(new JDatePanelImpl(model, p), new DateLabelFormatter());
		recrutement = new JDatePickerImpl(new JDatePanelImpl(model1, p), new DateLabelFormatter());
		
		
		txtCin.setToolTipText("un ou deux lettres et 6 chiffres");
		txtNom.setToolTipText("lettres et espaces seulement");
		txtPrenom.setToolTipText("lettres et espaces seulement");
		
	}
	
	private void draw() {
		JPanel panel = (JPanel)this.getContentPane();
		panel.setLayout(null);
		nom.setBounds(75,30,114,25);
		panel.add(nom);
		txtNom.setBounds(235,30,146,25);
		panel.add(txtNom);
		prenom.setBounds(75,75,114,25);
		panel.add(prenom);
		txtPrenom.setBounds(235,75,146,25);
		panel.add(txtPrenom);
		cin.setBounds(75,120,114,25);
		panel.add(cin);
		txtCin.setBounds(235,120,146,25);
		panel.add(txtCin);
		grade.setBounds(75,165,114,25);
		panel.add(grade);
		cGrade.setBounds(235,165,146,25);
		panel.add(cGrade);
		genre.setBounds(75,210,114,25);
		panel.add(genre);
		m.setBounds(265,210,40,25);
		panel.add(m);
		f.setBounds(340,210,40,25);
		panel.add(f);
		dateNaiss.setBounds(75,255,140,25);
		panel.add(dateNaiss);
		naissance.setBounds(240,255,140,25);
		panel.add(naissance);
		dateRecrut.setBounds(75,307,163,25);
		panel.add(dateRecrut);
		recrutement.setBounds(240,307,140,25);
		panel.add(recrutement);
		add.setBounds(240,382,90,25);
		cancel.setBounds(338,382,90,25);
		panel.add(add);
		panel.add(cancel);
		panel.setBackground(SystemColor.inactiveCaptionBorder);
	}

	public void ajouterListeners(){

		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int result;
				employeBean empBean;
				if (checkFieldIsEmpty() == 0)
					return;
				if (checkRegex() == false)
					return;
				empBean = initBean();
				result = controleur.ajouterEmp(empBean);
				if (result != 1) {
					Utils.displayMessage(VueAddEmp.this, result);
					return;
				}
				JOptionPane.showMessageDialog(VueAddEmp.this, "employe ajoute avec succes\n-le mot de passe de l'employe est: "+Utils.cryptWithMD5(txtCin.getText()));
				dispose();
			}
		});


		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					dispose();
			}
		});
	}

	public void setControleur(ContoleurGestionEmp controleur) {
		this.controleur = controleur;
	}

	public int checkFieldIsEmpty(){

		if (txtNom.getText().length() == 0 || txtPrenom.getText().length() == 0 || txtCin.getText().length() == 0
				|| naissance.getJFormattedTextField().getText().length() == 0 || recrutement.getJFormattedTextField().getText().length() == 0) {
			JOptionPane.showMessageDialog(VueAddEmp.this, "information(s) manquante(s)");
			return 0;
		}
		return 1;
	}
	
	
	public employeBean initBean() {
		
		employeBean bean = new employeBean();
		
		Boolean sexe = true;
		if(f.isSelected())
			sexe = false;
		bean.setSexe(sexe);
		bean.setCin(txtCin.getText());
		bean.setDate_naissance(java.sql.Date.valueOf(naissance.getJFormattedTextField().getText()));
		bean.setDate_rec(java.sql.Date.valueOf(recrutement.getJFormattedTextField().getText()));
		bean.setGrade(cGrade.getItemAt(cGrade.getSelectedIndex()));
		bean.setNom(txtNom.getText());
		bean.setPrenom(txtPrenom.getText());
		
		return bean;
	}
	
	public Boolean checkRegex() {
		
		String alert = "";
		if (Utils.regexVerifier("[A-Za-z, ]*",txtNom.getText()) == false)
			alert += "*nom doesn't match regex";
		if (Utils.regexVerifier("[A-Za-z, ]*",txtPrenom.getText()) == false)
			alert += "\n*prenom doesn't match regex";
		if (Utils.regexVerifier("[A-Za-z]{1,2}[0-9]{6}",txtCin.getText()) == false)
			alert += "\n*Cin doesn't match regex";
		if (alert.equals(""))
			return true;
		JOptionPane.showMessageDialog(VueAddEmp.this, alert);
		return false;
	}

	public JTextField getTxtNom() {
		return txtNom;
	}

	public void setTxtNom(JTextField txtNom) {
		this.txtNom = txtNom;
	}

	public JTextField getTxtPrenom() {
		return txtPrenom;
	}

	public void setTxtPrenom(JTextField txtPrenom) {
		this.txtPrenom = txtPrenom;
	}

	public JTextField getTxtCin() {
		return txtCin;
	}

	public void setTxtCin(JTextField txtCin) {
		this.txtCin = txtCin;
	}

	public JRadioButton getM() {
		return m;
	}

	public void setM(JRadioButton m) {
		this.m = m;
	}

	public JRadioButton getF() {
		return f;
	}

	public void setF(JRadioButton f) {
		this.f = f;
	}

	public JDatePickerImpl getNaissance() {
		return naissance;
	}

	public void setNaissance(JDatePickerImpl naissance) {
		this.naissance = naissance;
	}

	public JDatePickerImpl getRecrutement() {
		return recrutement;
	}

	public void setRecrutement(JDatePickerImpl recrutement) {
		this.recrutement = recrutement;
	}

	public UtilDateModel getModel() {
		return model;
	}

	public void setModel(UtilDateModel model) {
		this.model = model;
	}

	public UtilDateModel getModel1() {
		return model1;
	}

	public void setModel1(UtilDateModel model1) {
		this.model1 = model1;
	}

	public JButton getAdd() {
		return add;
	}

	public void setAdd(JButton add) {
		this.add = add;
	}

	public ContoleurGestionEmp getControleur() {
		return controleur;
	}

	public JComboBox<String> getcGrade() {
		return cGrade;
	}

	public void setcGrade(JComboBox<String> cGrade) {
		this.cGrade = cGrade;
	}

	
	
	

}
