package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.BizFuncionario;
import utilitarios.Icons;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import java.awt.Color;

public class Splash extends JWindow {

	private JPanel contentPane;
	private static JProgressBar progressBar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			Splash frame = new Splash();
			frame.setVisible(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public Splash() {
		ImageIcon icone = new ImageIcon(Icons.class.getResource("/img/logo.png"));
		Image img = icone.getImage();
		setIconImage(img);
		setBounds(100, 100, 397, 239);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 102, 0));
		panel.setBounds(0, 0, 450, 270);
		contentPane.add(panel);
		panel.setLayout(null);

		progressBar = new JProgressBar();
		progressBar.setBounds(-13, 206, 425, 36);
		progressBar.setValue(0);

		progressBar.setStringPainted(true);
		panel.add(progressBar);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Splash.class.getResource("/img/splash.gif")));
		lblNewLabel.setBounds(0, 0, 399, 242);
		panel.add(lblNewLabel);

		setVisible(true);
		BizFuncionario bizFun = new BizFuncionario();
		bizFun.Consultar();
		fill();
		setVisible(false);
	}

	public static void fill() {
		int i = 0;
		try {
			while (i <= 100) {
				// fill the menu bar
				progressBar.setValue(i + 10);

				// delay the thread
				Thread.sleep(400);
				i += 10;
			}
		} catch (Exception e) {
		}
	}

}
