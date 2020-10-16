package Vues;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controleurs.ControleurConnexionEmploye;
import Controleurs.ControleurEspaceCitoyen;
import Utils.Utils;

public class LoginPage extends JFrame {

	private static final long serialVersionUID = 1821390658938043372L;
	
	private JLabel cin,login,pass;
	private JTextField cinInput, loginInput;
	private JPasswordField passInput;
	private JButton connectCitoyen,ConnectEmploye;
	private ControleurConnexionEmploye controleur;
	private static int instance;
	
	
	public static void setInstance(int instance) {
		LoginPage.instance = instance;
	}

	public LoginPage() {
		setTitle("Identification");
		setSize(800,500);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		controleur = new ControleurConnexionEmploye();
		setControleur(controleur);
		controleur.setLoginPage(LoginPage.this);
		
		getContentPane().setLayout(null);
		init();
		draw();
		listners();
	}
	
	private void init() {
		cin = new JLabel("Cin :");
		login = new JLabel("Login :");
		login.setBounds(50, 71, 40, 25);
		pass = new JLabel("Password :");
		pass.setBounds(50, 128,90,25);
		cinInput = new JTextField();

		cin.setBounds(47,99,150,25);
		cinInput.setBounds(47,120,172,25);
		loginInput = new JTextField();
		loginInput.setBounds(50,90,170, 25);
		passInput = new JPasswordField();
		passInput.setBounds(50,146,170, 25);
		connectCitoyen = new JButton("Connexion");
		connectCitoyen.setBounds(87,247,93,25);
		ConnectEmploye = new JButton("Connexion");
		ConnectEmploye.setBounds(87,247,93,25);
	}
	
	private void draw() {
		JPanel content = (JPanel) getContentPane();
		JPanel pan1 = new JPanel(null);
		JPanel pan2 = new JPanel(null);
		pan1.setBorder(BorderFactory.createTitledBorder("Citoyen"));
		pan2.setBorder(BorderFactory.createTitledBorder("Employe"));
		pan1.setBounds(100,70,270,300);
		pan2.setBounds(430,70,270,300);
		pan1.add(cinInput);
		pan1.add(cin);
		pan1.add(connectCitoyen);
		pan2.add(login);
		pan2.add(loginInput);
		pan2.add(pass);
		pan2.add(passInput);
		pan2.add(ConnectEmploye);
		content.add(pan1);
		content.add(pan2);
		content.setBackground(SystemColor.inactiveCaptionBorder);
		pan1.setBackground(SystemColor.inactiveCaptionBorder);
		pan2.setBackground(SystemColor.inactiveCaptionBorder);
	}
	
	private void listners() {
		
		connectCitoyen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!checkRegex(cinInput))
					return;
				new ControleurEspaceCitoyen(cinInput.getText());
				dispose();
			}
		});
		
		
		ConnectEmploye.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println(Utils.cryptWithMD5("wb183467"));
				System.out.println(instance);
				if(instance == 0) {
					if(!controleur.getBackData()) {
						JOptionPane.showMessageDialog(LoginPage.this,"une erreur s’est sourvenue lors de l’importation de données\nredemarer l'application s'il vous plait");
						return;
					}
				else
					instance++;
				}
				
				if (loginInput.getText().length() == 0 || String.valueOf(passInput.getPassword()).length() == 0)
					JOptionPane.showMessageDialog(LoginPage.this,"informations insuffisantes");
				else if(!checkRegex(loginInput)) {
					return;
				}
				else if (!controleur.connectEmploye(loginInput.getText(), passInput.getText()))
					JOptionPane.showMessageDialog(LoginPage.this,"Employe inexistant");
				else {
					dispose();
				}
			}
		});
		
		
	}
	
	private Boolean checkRegex(JTextField txt) {
		
		String alert = "";
		if (Utils.regexVerifier("[A-Za-z]{1,2}[0-9]{6}",txt.getText()) == false && txt == cinInput)
			alert += "\n*Cin doesn't match regex";
		else if(Utils.regexVerifier("[A-Za-z]{1,2}[0-9]{6}",txt.getText()) == false)
			alert += "Employe inexistant";
		if (alert.equals(""))
			return true;
		JOptionPane.showMessageDialog(null, alert,"Cin invalid",JOptionPane.OK_OPTION);
		return false;
	}

	public ControleurConnexionEmploye getControleur() {
		return controleur;
	}

	public void setControleur(ControleurConnexionEmploye controleur) {
		this.controleur = controleur;
	}
}
