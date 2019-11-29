package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import Controller.BizFuncao;
import DAO.Conexao;
import Model.Funcao;
import utilitarios.Icons;

import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.*;

public class CadastroFuncao extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtDescricao;
	private BizFuncao objFuncao = new BizFuncao();
	private String comando;
	private JFrame tela;

	public void mostrar(JFrame tela, String _comando) {
		this.tela = tela;
		comando = _comando;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					CadastroFuncao frame = new CadastroFuncao();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public void preencherCampo(ArrayList<Funcao> listFuncao) {

		for (Funcao funcao : listFuncao) {
			txtCodigo.setText(Integer.toString(funcao.getFuncaoCodigo()));
			txtDescricao.setText(funcao.getFuncaoDescricao());
		}

	}

	public void bloqueioCampo() {
		txtCodigo.setEnabled(false);
		txtDescricao.setEnabled(false);
	}

	public CadastroFuncao(java.awt.Frame parent, boolean modal) {
	}

	public CadastroFuncao() {
		setTitle("Cadastro Função");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (tela instanceof TelaFuncao) {
					((TelaFuncao) tela).atualizar();
				} else {
					((telaPrinc) tela).Desbloquaer();
				}
			}
		});
		ImageIcon icone = new ImageIcon(Icons.class.getResource("/img/logo.png"));
		Image img = icone.getImage();
		setIconImage(img);
		setBackground(Color.WHITE);
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 566, 172);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(10, 22, 86, 20);
		panel.add(txtCodigo);
		txtCodigo.setColumns(10);
		txtCodigo.setEditable(false);
		JLabel lblNewLabel = new JLabel("Código");
		lblNewLabel.setBounds(10, 11, 86, 14);
		panel.add(lblNewLabel);

		JLabel lblDescrio = new JLabel("Descrição");
		lblDescrio.setBounds(121, 11, 404, 14);
		panel.add(lblDescrio);

		txtDescricao = new JTextField();
		txtDescricao.setColumns(10);
		txtDescricao.setBounds(121, 22, 386, 20);
		panel.add(txtDescricao);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvar();

			}
		});
		btnSalvar.setBounds(64, 69, 89, 23);
		panel.add(btnSalvar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnCancelar.setBounds(361, 69, 89, 23);
		panel.add(btnCancelar);

		setVisible(true);
	}

	private void salvar() {
		try {
			BizFuncao bizFuncap = new BizFuncao();
			Funcao objfuncao = new Funcao();
			if (txtDescricao.getText().length() <= 0) {
				throw new IllegalArgumentException("descrição é obrigatorio");
			}
			if (comando == "Editar") {
				objfuncao = (Funcao) Conexao.Show(objfuncao, Integer.parseInt(txtCodigo.getText()));
			}

			objfuncao.setFuncaoDescricao(txtDescricao.getText());

			if (comando == "Editar") {
				Conexao.Update(objfuncao);
				throw new IllegalArgumentException("Alterado com sucesso");
			} else if (bizFuncap.Cadastrar(objfuncao)) {

				if(tela instanceof TelaFuncao) {
					TelaFuncao frame = (TelaFuncao) tela;
					frame.atualizar();
				}
				else {
					((telaPrinc) tela).Desbloquaer();
				}
			
				this.setVisible(false);
				throw new IllegalArgumentException("Salvo com sucesso");

			} else {
				throw new IllegalArgumentException("Erro ao salvar");
			}

		} catch (IllegalArgumentException e) {
			if (e.getMessage().length() > 0) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}

	}
}
