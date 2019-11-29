package View;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JWindow;

import Controller.BizFuncionario;
import Model.Funcionario;
import utilitarios.Icons;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.Button;

public class Login extends JFrame {
	private JTextField txtLogin;
	private JPasswordField txtSenha;

	public Login() {
		ImageIcon icone = new ImageIcon(Icons.class.getResource("/img/logo.png"));
		Image img = icone.getImage();
		setIconImage(img);
		setType(Type.UTILITY);
		setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(390, 334);
		// setExtendedState(JFrame.MAXIMIZED_BOTH); colocar tamanho tela toda
		setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		txtLogin = new JTextField();
		txtLogin.setBounds(66, 136, 259, 35);
		panel.add(txtLogin);
		txtLogin.setColumns(12);
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 102, 0));
		panel_1.setBounds(0, 0, 400, 98);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblLoginDeAcesso = new JLabel(Icons.Logo(150, 90));
		lblLoginDeAcesso.setForeground(Color.WHITE);
		lblLoginDeAcesso.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginDeAcesso.setFont(new Font("Century Gothic", Font.PLAIN, 28));
		lblLoginDeAcesso.setBounds(-124, 0, 366, 76);
		panel_1.add(lblLoginDeAcesso);
		
				JLabel lblEntreComSeus = new JLabel("Entre com seus dados");
				lblEntreComSeus.setBounds(86, 23, 271, 44);
				panel_1.add(lblEntreComSeus);
				lblEntreComSeus.setHorizontalAlignment(SwingConstants.CENTER);
				lblEntreComSeus.setForeground(Color.WHITE);
				lblEntreComSeus.setFont(new Font("Century Gothic", Font.PLAIN, 17));
				lblEntreComSeus.setBackground(Color.WHITE);

		
		JLabel lblNewLabel = new JLabel(Icons.User(28,23));
		lblNewLabel.setBounds(26, 136, 39, 35);
		panel.add(lblNewLabel);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(66, 204, 259, 35);
		panel.add(txtSenha);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBackground(new Color(255, 255, 255));
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setForeground(new Color(102, 102, 102));
		lblLogin.setFont(new Font("Century Gothic", Font.PLAIN, 17));
		lblLogin.setBounds(66, 109, 259, 22);
		panel.add(lblLogin);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setHorizontalAlignment(SwingConstants.CENTER);
		lblSenha.setForeground(new Color(102, 102, 102));
		lblSenha.setFont(new Font("Century Gothic", Font.PLAIN, 17));
		lblSenha.setBackground(Color.WHITE);
		lblSenha.setBounds(50, 182, 259, 22);
		panel.add(lblSenha);

		JLabel label = new JLabel(Icons.Password(28,23));
		label.setBounds(26, 204, 39, 35);
		panel.add(label);
		
		Button button = new Button("Entrar");
		button.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		button.setForeground(Color.WHITE);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BizFuncionario objBizFunc = new BizFuncionario();

				if (txtLogin.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "O campo login é obrigatorio");
				} else if (txtSenha.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "O campo senha é obrigatorio");
				} else {
					ArrayList<Funcionario> objFunc = objBizFunc.ConsultarLogin(txtLogin.getText(), txtSenha.getText());

					if (objFunc.size() <= 0) {
						JOptionPane.showMessageDialog(null, "Login ou senha invalido!");
					} else {
						setVisible(false);
						;
						telaPrinc tPric = new telaPrinc();
						tPric.vendedor(objFunc.get(0).getNome(), Integer.toString(objFunc.get(0).getIdPessoa()));
					}

				}
			}
		});
		button.setBackground(new Color(255, 102, 0));
		button.setBounds(-12, 260, 412, 41);
		panel.add(button);

		setVisible(true);
		JFrame frame = new JFrame();
		frame.setUndecorated(true);

	}
}
