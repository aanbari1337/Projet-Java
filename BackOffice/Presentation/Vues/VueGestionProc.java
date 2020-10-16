package Vues;

import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Contoleurs.ControleurAddProc;
import Contoleurs.ControleurGestionProc;

public class VueGestionProc extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton back,add,delete,archiver,edit;
  
    private ControleurGestionProc control;
    private JTable table;
    private TableRowSorter<TableModel> sorter;
    public VueGestionProc(ControleurGestionProc control) {
    	this.control = control;
        setTitle("Gestion des procédures administratives");
        setSize(700,500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        init();
        draw();
        listners();
    }

    private void init() {
        back = new JButton("Retour");
        add = new JButton();
        add.setIcon(new ImageIcon("../icons/13.png"));
        delete = new JButton();
        delete.setIcon(new ImageIcon("../icons/33.png"));
        edit = new JButton();
        edit.setIcon(new ImageIcon("../icons/96.png"));
        archiver = new JButton();
        archiver.setIcon(new ImageIcon("../icons/22.png"));
    }

    private void draw() {
        JPanel pan = (JPanel) getContentPane();
        pan.setLayout(null);
        back.setBounds(600,430,80,25);
     //   disconnect.setBounds(585,10,92,25);
        table  = new JTable(control.getModelProc());
        JScrollPane panel = new JScrollPane(table);
        panel.setBounds(30,80,535,300);
        panel.setBorder(BorderFactory.createTitledBorder("Procedures"));
        panel.setBackground(SystemColor.inactiveCaptionBorder);
        pan.add(panel);
      //  pan.add(disconnect);
        pan.add(back);

        JPanel btnPanel = new JPanel(new GridLayout(4,1,0,20));
        btnPanel.setBackground(SystemColor.inactiveCaptionBorder);
        btnPanel.add(add);
        btnPanel.add(edit);
        btnPanel.add(delete);
        btnPanel.add(archiver);
        btnPanel.setBounds(580,150,80,200);
        pan.add(btnPanel);
        pan.setBackground(SystemColor.inactiveCaptionBorder);
        sorter = new TableRowSorter<TableModel>(control.getModelProc());
		table.setRowSorter(sorter);
    }

	public ControleurGestionProc getControl() {
		return control;
	}

	public void setControl(ControleurGestionProc control) {
		this.control = control;
	}
	
	private void listners() {
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ControleurAddProc(control).showVue(control.getIdProc());
			}
		});
		
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (VueGestionProc.this.getTable().getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(VueGestionProc.this, "aucune ligne selectionée");
					return;
				}
				control.deleteProc();
			}
		});
		
		archiver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (VueGestionProc.this.getTable().getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(VueGestionProc.this, "aucune ligne selectionée");
					return;
				}
				control.archivePoc();
			}
		});
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new VueControlPanel().setVisible(true);
				dispose();
				
			}
		});
		
		edit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				control.vueEditProc();
			}
		});
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public TableRowSorter<TableModel> getSorter() {
		return sorter;
	}

	public void setSorter(TableRowSorter<TableModel> sorter) {
		this.sorter = sorter;
	}
}
