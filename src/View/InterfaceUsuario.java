package View;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Moacir Afonso Alves
 */

public class InterfaceUsuario {

	public static void main(String args[]) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Splash s = new Splash();
		
		Login objIterface = new Login();
		
	}

};
