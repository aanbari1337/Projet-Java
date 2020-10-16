import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatIntelliJLaf;

import Vues.VueLogin;
import Vues.VueMoyenTraitement;

public class Test {

	public static void main(String args[]) throws UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(new FlatIntelliJLaf());
		new VueLogin().setVisible(true);
	}
}

