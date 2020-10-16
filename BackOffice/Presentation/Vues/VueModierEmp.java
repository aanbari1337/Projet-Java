package Vues;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Beans.employeBean;
import Utils.Utils;


public class VueModierEmp extends VueAddEmp{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton edit;
	private String cin;
	private String grade;
	
	public VueModierEmp() {
		super();
		JPanel panel = (JPanel)this.getContentPane();
		panel.setBackground(SystemColor.inactiveCaptionBorder);
		this.setTitle("modifier Employe");
		panel.remove(this.getAdd());
		edit = new JButton("modifier");
		edit.setBounds(240,382,90,25);
		panel.add(edit);
		ajouterListenerss();
	}
	
	public void ajouterListenerss() {
		
		
		edit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int result = 0;
				employeBean bean;
				int gradechanged = -1;
				
				String msg = "employe modifie avec succes\n";
				
				if (checkFieldIsEmpty() == 0)
					return;
				if (checkRegex() == false)
					return;
				bean = initBean();
				if(!grade.equals(bean.getGrade()) && grade.equals("Chef de division"))
					gradechanged = JOptionPane.showConfirmDialog(VueModierEmp.this, "Le changement de grade peut entraîner des procedures sans responsable,Êtes-vous sur","confirmation",
							JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				if(gradechanged == JOptionPane.NO_OPTION)
					return;
				result = getControleur().modifierEmp(bean,cin,gradechanged);
				if (result != 1) {
					Utils.displayMessage(VueModierEmp.this, result);
					return;
				}
				if (!cin.equals(bean.getCin()))
					msg += "le nouveau mot de passe de l'employe est: " + Utils.cryptWithMD5(bean.getCin());
				JOptionPane.showMessageDialog(VueModierEmp.this, msg);
				dispose();
			}
		});
	}

	public JButton getEdit() {
		return edit;
	}

	public void setEdit(JButton edit) {
		this.edit = edit;
	}
	
	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	
}
