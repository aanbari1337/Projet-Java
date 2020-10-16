package Vues;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class VueConnexion extends JFrame{

	private static final long serialVersionUID = 2387140950143971980L;
	private JTextField txtLogin ;
	private JPasswordField txtPass;
	private JButton connect,cancel;
	private JLabel login,pass;
	
	public VueConnexion() {
		setTitle("Identification");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(400,300);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		getContentPane().setLayout(null);
		init();
		draw();
		setListeners();
	}
	
	private void init() {
		
		login = new JLabel("Login :");
		pass = new JLabel("Password :");
		txtLogin = new JTextField();
		txtPass = new JPasswordField();
		connect = new JButton("Sign in");
		cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
	}
	
	private void draw() {
		JPanel pan = (JPanel) this.getContentPane();
		login.setBounds(50,70,80,25);
		
		pass.setBounds(50,100,80,25);
		txtLogin.setBounds(150,70,160,25);
		txtPass.setBounds(150,100,160,25);
		connect.setBounds(200,230,90,25);
		cancel.setBounds(300,230,80,25);
		pan.add(login);
		pan.setBackground(SystemColor.inactiveCaptionBorder);
		pan.add(txtLogin);
		pan.add(txtPass);
		pan.add(connect);
		pan.add(pass);
		pan.add(cancel);
	}

	private void setListeners(){
		connect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtLogin.getText().length() == 0 || String.valueOf(txtPass.getPassword()).length() == 0)
					JOptionPane.showMessageDialog(VueConnexion.this,"informations insuffisantes");
				else if(txtLogin.getText().equals("admin") && String.valueOf(txtPass.getPassword()).equals("admin"))
				{
					new VueControlPanel().setVisible(true);
					dispose();
				}
				else
					JOptionPane.showMessageDialog(VueConnexion.this,"informations invalides");
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
