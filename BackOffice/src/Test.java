import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatIntelliJLaf;

import Vues.VueConnexion;

public class Test {

	public static void main(String[] args) throws UnsupportedLookAndFeelException {
		
		UIManager.setLookAndFeel(new FlatIntelliJLaf());
		new VueConnexion().setVisible(true);

	}

}
