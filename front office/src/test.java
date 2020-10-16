import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatIntelliJLaf;

import DAO.DAODataFromBack;
import Vues.LoginPage;

public class test {

	public static void main(String[] args) throws UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(new FlatIntelliJLaf());
		new LoginPage().setVisible(true);
	
	}
	
}
