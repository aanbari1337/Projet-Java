package Vues;

import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class VueAjouterDocumentEtape extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField libelleDoc;
    private JTextArea descriptionDoc;
    private JButton add;
    private JButton cancel;
    
    public VueAjouterDocumentEtape() {
        setTitle("ajouter Document");
        this.setSize(500,500);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        init();
        draw();
    }

    private void init(){
        libelleDoc = new JTextField();
        descriptionDoc = new JTextArea();
        add = new JButton("ajouter");
        cancel = new JButton("annuler");
    }

    private void draw(){
        JPanel content = (JPanel)getContentPane();
        content.setLayout(null);

        JLabel libelle = new JLabel("libelle du document * :");
        JLabel desc = new JLabel("description :");

        libelle.setBounds(30,30,140,25);
        libelleDoc.setBounds(295,30,160,25);
        desc.setBounds(30,65,80,25);
        descriptionDoc.setBounds(30,100,425,250);

        JScrollPane scrollPane = new JScrollPane(descriptionDoc);
        scrollPane.setBounds(30,100,425,250);

        cancel.setBounds(270,400,80,25);
        add.setBounds(375,400,80,25);

        content.add(libelle);
        content.add(libelleDoc);
        content.add(desc);
        content.add(scrollPane);
        content.add(add);
        content.add(cancel);
        content.setBackground(SystemColor.inactiveCaptionBorder);
    }

}
