package Vues;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

public class VueInfoProc extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6318532303271732683L;
	private JTabbedPane tabs;
	private JLabel lbl;
	
	public VueInfoProc(ArrayList<String[]> info) {
		setTitle("Information");
		setSize(400,300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		tabs = new JTabbedPane();
		tabs.setBackground(SystemColor.inactiveCaptionBorder);
		draw();
		createTabs(info);
	}
	
	
	private void draw() {
		JPanel content = (JPanel)this.getContentPane();
		JPanel pan = new JPanel(new FlowLayout());
		 lbl = new JLabel("Information de la procedure");
		lbl.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		pan.add(lbl);
		content.add(pan,BorderLayout.NORTH);
		content.add(tabs);
		pan.setBackground(SystemColor.inactiveCaptionBorder);
		content.setBackground(SystemColor.inactiveCaptionBorder);
	}
	
	private void createTabs(ArrayList<String[]> info) {
		for(int i = 0;i<info.size();i++) {
		JPanel pan = new JPanel(new BorderLayout());
		pan.setBackground(SystemColor.inactiveCaptionBorder);
		JPanel descri = new JPanel(new FlowLayout());
		descri.setBackground(SystemColor.inactiveCaptionBorder);
		JLabel des = new JLabel("Description :");
		des.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		descri.add(des);
		pan.add(descri,BorderLayout.NORTH);
		JTextArea text = new JTextArea();
		text.setText(info.get(i)[1]);
		text.setEditable(false);
		text.setBackground(SystemColor.inactiveCaptionBorder);
		pan.add(text);
		tabs.addTab(info.get(i)[0], pan);
		}
	}


	public JLabel getLbl() {
		return lbl;
	}
	
}
