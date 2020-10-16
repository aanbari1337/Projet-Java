package Vues;



import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.PatternSyntaxException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

import Contoleurs.ContoleurGestionEmp;
import Models.ModelEmploye;

public class VueGestionEmp extends JFrame{

	private static final long serialVersionUID = -5899686628736837228L;
	private JButton back,add,edit,del,arch;
	private JTextField search;
	private ContoleurGestionEmp contoleur;
	private ModelEmploye model;
	private JTable tableEmploye;
	TableRowSorter<ModelEmploye> sorter;
	
	public VueGestionEmp(ModelEmploye model) {
		
		setTitle("Gestion des employes");
		setSize(700,500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		this.model = model;
		
		init();
		draw();
		ajouterListeners();
	}
	
	private void init() {
		back = new JButton("Retour");
		add = new JButton("Ajouter");
		search = new JTextField();
		arch = new JButton("Archiver");
		tableEmploye = new JTable(model);
		sorter = new TableRowSorter<>(model);
        tableEmploye.setRowSorter(sorter);
		edit = new JButton("Modifier");
		del = new JButton("Supprimer");
	}
	
	private void draw() {
		JScrollPane scrollPane;
		JLabel searchlabel = new JLabel("recherche: ");
		
		JPanel pan = (JPanel) getContentPane();
		pan.setLayout(null);
		back.setBounds(600,430,80,25);
		JPanel panel = new JPanel(null);
		panel.setBounds(30,80,635,330);
		panel.setBorder(BorderFactory.createTitledBorder("Employes"));
		add.setBounds(547,295,80,25);
		edit.setBounds(470,295,80,25);
		del.setBounds(392, 295,80, 25);
		arch.setBounds(315,295,80,25);
		searchlabel.setBounds(10, 295, 80, 25);
		search.setBounds(70,295,220,25);
		panel.add(search);
		panel.add(searchlabel);
		panel.add(add);
		panel.add(del);
		panel.add(edit);
		panel.add(arch);
		tableEmploye.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane = new JScrollPane(tableEmploye);
		scrollPane.setBounds(7,25,620,270);
		panel.add(scrollPane);
		panel.setBackground(SystemColor.inactiveCaptionBorder);
		pan.setBackground(SystemColor.inactiveCaptionBorder);
		pan.add(panel);
		pan.add(back);

	}

	private void ajouterListeners(){
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contoleur.genererVueAjout();
			}
		});
		
		
		search.addKeyListener(new KeyAdapter() {
			
			@Override
            public void keyTyped(KeyEvent e) {
                String text = search.getText();
                try{
                sorter.setRowFilter(RowFilter.regexFilter(text));}
                catch (PatternSyntaxException pse){
                    pse.getMessage();
                }
            }
		});
		
		
		del.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String cin;
				int selectedRowIndex;
				selectedRowIndex = tableEmploye.getSelectedRow();
				if (selectedRowIndex == -1) {
					JOptionPane.showMessageDialog(VueGestionEmp.this, "aucune ligne selectionée");
					return;
				}
				selectedRowIndex = tableEmploye.convertRowIndexToModel(selectedRowIndex);
				int reponse = JOptionPane.showConfirmDialog(VueGestionEmp.this, "vous voules vraiment supprimer cet employe?","confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				if(reponse == JOptionPane.YES_OPTION)
				{
					cin = (String) model.getValueAt(selectedRowIndex, 0);
					contoleur.supprimerEmp(cin);
				}
			}
		});
		
		edit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int selectedRowIndex;
				selectedRowIndex = tableEmploye.getSelectedRow();
				if (selectedRowIndex == -1) {
					JOptionPane.showMessageDialog(VueGestionEmp.this, "aucune ligne selectionée");
					return;
				}
				tableEmploye.convertRowIndexToModel(selectedRowIndex);
				contoleur.genereVueModification(tableEmploye.convertRowIndexToModel(selectedRowIndex));
			}
		});
		
		arch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String cin;
				int result;
				int selectedRowIndex;
				
				selectedRowIndex = tableEmploye.getSelectedRow();
				if (selectedRowIndex == -1) {
					JOptionPane.showMessageDialog(VueGestionEmp.this, "aucune ligne selectionée");
					return;
				}
				selectedRowIndex = tableEmploye.convertRowIndexToModel(selectedRowIndex);
				int reponse = JOptionPane.showConfirmDialog(VueGestionEmp.this, "vous voules vraiment archiver cet employe?","confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				if(reponse == JOptionPane.YES_OPTION)
				{
					cin = (String) model.getValueAt(selectedRowIndex, 0);
					result = contoleur.archiverEmp(cin);
					if (result == -1) {
						JOptionPane.showMessageDialog(VueGestionEmp.this, "un probleme s'est produit lors de la'archivage de l'employe");
						return;
					}
					JOptionPane.showMessageDialog(VueGestionEmp.this, "employe archive avec succes");
				}
			}
		});
		
		
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new VueControlPanel().setVisible(true);
				dispose();
			}
		});
	}


	public void setContoleur(ContoleurGestionEmp contoleur) {
		this.contoleur = contoleur;
	}

	
	
	
}
