package Vues;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class VueAffichageDesc extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea desctxt;
	private JPanel panel = (JPanel)this.getContentPane();
			
			
	public VueAffichageDesc(String desc) throws HeadlessException {
		super("Traitement Etape");
		this.setSize(700,500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		init(desc);
		draw();
	}
	
	private void init(String desc) {
		
		desctxt = new JTextArea();
		desctxt.setText(desc);
		desctxt.setEditable(false);
		Font font = new Font("Bahnschrift", 0, 15);
		desctxt.setFont(font);
	}
	
	private void draw() {
		panel.setLayout(new GridLayout(1,1));
		desctxt.setBounds(0,0,700,50);
		JScrollPane scrollPane = new JScrollPane(desctxt);
		scrollPane.setBounds(0,0,700,500);
		panel.add(scrollPane);
	}

}
