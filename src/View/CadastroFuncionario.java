package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.CloseAction;

import org.hibernate.HibernateError;
import org.hibernate.HibernateException;

import Controller.BizFuncionario;
import Controller.BizFuncao;
import Controller.BizUF;
import DAO.Conexao;
import Model.Funcionario;
import Model.Cliente;
import Model.Funcao;
import Model.UF;
import utilitarios.Icons;
import utilitarios.LtpLib;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Window.Type;
import java.util.ArrayList;
import java.awt.Color;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.Executable;
import java.sql.SQLTransientException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class CadastroFuncionario extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtNome;
	private JTextField txtCpf;
	private JTextField txtTelefone;
	private JTextField txtDescricao;
	private JTextField txtBairro;
	private JTextField txtEndereco;
	private JTextField txtNumEndereco;
	private JTextField txtCidade;
	private JTextField txtComplemento;
	private JTextField txtCep;
	private JComboBox<String> cmbUF;
	private JComboBox<String> cmbFuncao;
	private JButton btnSalvar;
	private BizFuncionario bizFuncionario = new BizFuncionario();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					CadastroFuncionario frame = new CadastroFuncionario();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public void preencherCampo(ArrayList<Funcionario> listFun) {

		for (Funcionario fun : listFun) {
			txtCodigo.setText(Integer.toString(fun.getIdPessoa()));
			txtNome.setText(fun.getNome());
			txtCpf.setText(LtpLib.formatarCPF(fun.getFunCPF()));
			txtDescricao.setText(fun.getDescricao());
			txtTelefone.setText(fun.getTelefone());
			txtEndereco.setText(fun.getRua());
			txtNumEndereco.setText(Integer.toString(fun.getNumRua()));
			txtBairro.setText(fun.getBairro());
			txtCidade.setText(fun.getCidade());
			txtCep.setText(LtpLib.formatarCEP(fun.getCep()));
			txtComplemento.setText(fun.getComplemento());
			cmbFuncao.setSelectedItem(fun.getFunFuncao());
			cmbUF.setSelectedItem(fun.getUf());
			txtLogin.setText(fun.getFunLogin());
			txtSenha.setText(fun.getFunSenha());

		}

	}

	public void bloqueioCampo() {
		txtCodigo.setEnabled(false);
		txtNome.setEnabled(false);
		txtCpf.setEnabled(false);
		txtDescricao.setEnabled(false);
		txtTelefone.setEnabled(false);
		txtEndereco.setEnabled(false);
		txtNumEndereco.setEnabled(false);
		txtBairro.setEnabled(false);
		txtCidade.setEnabled(false);
		txtCep.setEnabled(false);
		txtComplemento.setEnabled(false);
		cmbUF.setEnabled(false);
		txtLogin.setEnabled(false);
		txtSenha.setEnabled(false);
		cmbFuncao.setEnabled(false);
		btnSalvar.setVisible(false);
	}

	private JFrame telaFun;
	private String comando;
	private JTextField txtLogin;
	private JTextField txtSenha;

	public void mostrar(JFrame tela, String _comando) {
		this.telaFun = tela;
		setVisible(true);
		comando = _comando;
	}

	public CadastroFuncionario(java.awt.Frame parent, boolean modal) {
	}

	public CadastroFuncionario() {
		setTitle("Cadastro Funcionario");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				if (telaFun instanceof TelaFuncionario) {
					((TelaFuncionario) telaFun).atualizar();
				} else {
					((telaPrinc) telaFun).Desbloquaer();
				}
			}
		});
		ImageIcon icone = new ImageIcon(Icons.class.getResource("/img/logo.png"));
		Image img = icone.getImage();
		setIconImage(img);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 534, 514);
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
		txtCodigo.setEnabled(false);

		JLabel lblNewLabel = new JLabel("Código");
		lblNewLabel.setBounds(10, 11, 86, 14);
		panel.add(lblNewLabel);

		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(117, 22, 297, 20);
		panel.add(txtNome);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(117, 11, 282, 14);
		panel.add(lblNome);

		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(10, 61, 86, 14);
		panel.add(lblCpf);

		txtCpf = new JTextField();
		txtCpf.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtCpf.setText(LtpLib.RetiraSimbolo(txtCpf.getText()));
			}

			@Override
			public void focusLost(FocusEvent e) {
				txtCpf.setText(LtpLib.formatarCPF(txtCpf.getText()));
			}
		});
		txtCpf.setColumns(10);
		txtCpf.setBounds(10, 72, 86, 20);
		panel.add(txtCpf);

		JLabel lblTelefone = new JLabel("Telefone");

		lblTelefone.setBounds(117, 61, 86, 14);
		panel.add(lblTelefone);

		txtTelefone = new JTextField();
		txtTelefone.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtTelefone.setText(LtpLib.RetiraSimbolo(txtTelefone.getText()));
			}

			@Override
			public void focusLost(FocusEvent e) {
				txtTelefone.setText(LtpLib.formatarTelefone(txtTelefone.getText()));
			}
		});
		txtTelefone.setColumns(10);
		txtTelefone.setBounds(117, 72, 86, 20);
		panel.add(txtTelefone);

		JLabel lblDescrio = new JLabel("Descrição");
		lblDescrio.setBounds(10, 294, 404, 14);
		panel.add(lblDescrio);

		txtDescricao = new JTextField();
		txtDescricao.setColumns(10);
		txtDescricao.setBounds(10, 305, 487, 94);
		panel.add(txtDescricao);

		txtBairro = new JTextField();
		txtBairro.setColumns(10);
		txtBairro.setBounds(10, 187, 143, 20);
		panel.add(txtBairro);

		JLabel lblBiarro = new JLabel("Bairro");
		lblBiarro.setBounds(10, 176, 143, 14);
		panel.add(lblBiarro);

		txtEndereco = new JTextField();
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(10, 145, 249, 20);
		panel.add(txtEndereco);

		JLabel lblEndereo = new JLabel("Endereço");
		lblEndereo.setBounds(10, 134, 249, 14);
		panel.add(lblEndereo);

		txtNumEndereco = new JTextField();
		txtNumEndereco.setColumns(10);
		txtNumEndereco.setBounds(286, 145, 48, 20);
		panel.add(txtNumEndereco);

		JLabel lblN = new JLabel("Nº");
		lblN.setBounds(286, 134, 48, 14);
		panel.add(lblN);

		txtCidade = new JTextField();
		txtCidade.setColumns(10);
		txtCidade.setBounds(191, 187, 143, 20);
		panel.add(txtCidade);

		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(191, 176, 143, 14);
		panel.add(lblCidade);

		txtComplemento = new JTextField();
		txtComplemento.setColumns(10);
		txtComplemento.setBounds(354, 145, 143, 20);
		panel.add(txtComplemento);

		JLabel lblComplemento = new JLabel("Complemento");
		lblComplemento.setBounds(354, 134, 143, 14);
		panel.add(lblComplemento);

		JLabel lblCmbfuncao = new JLabel("Função");
		lblCmbfuncao.setBounds(213, 61, 113, 14);
		panel.add(lblCmbfuncao);

		cmbFuncao = new JComboBox();
		cmbFuncao.setBounds(214, 72, 143, 20);
		panel.add(cmbFuncao);

		BizUF objuf = new BizUF();

		cmbUF = new JComboBox();
		cmbUF.setBounds(354, 187, 143, 20);
		panel.add(cmbUF);

		ArrayList<UF> list = objuf.Consultar();
		for (UF uf : list) {
			cmbUF.addItem(uf.getDescricao());
		}

		BizFuncao objFuncao = new BizFuncao();

		ArrayList<Funcao> listFuncao = objFuncao.Consultar();
		for (Funcao funcao : listFuncao) {
			cmbFuncao.addItem(funcao.getFuncaoDescricao());
		}

		JLabel lblUf = new JLabel("UF");
		lblUf.setBounds(353, 176, 113, 14);
		panel.add(lblUf);

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvar();

			}
		});
		btnSalvar.setBounds(10, 410, 89, 23);
		panel.add(btnSalvar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				((TelaFuncionario) telaFun).atualizar();
				setVisible(false);
			}
		});
		btnCancelar.setBounds(408, 413, 89, 23);
		panel.add(btnCancelar);

		txtCep = new JTextField();
		txtCep.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtCep.setText(LtpLib.RetiraSimbolo(txtCep.getText()));
			}

			@Override
			public void focusLost(FocusEvent e) {
				txtCep.setText(LtpLib.formatarCEP(txtCep.getText()));
			}
		});
		txtCep.setColumns(10);
		txtCep.setBounds(10, 228, 143, 20);
		panel.add(txtCep);

		JLabel lblCep = new JLabel("CEP");
		lblCep.setBounds(10, 217, 143, 14);
		panel.add(lblCep);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(10, 252, 143, 14);
		panel.add(lblLogin);

		txtLogin = new JTextField();
		txtLogin.setColumns(10);
		txtLogin.setBounds(10, 263, 143, 20);
		panel.add(txtLogin);

		txtSenha = new JTextField();
		txtSenha.setColumns(10);
		txtSenha.setBounds(191, 263, 143, 20);
		panel.add(txtSenha);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(191, 252, 143, 14);
		panel.add(lblSenha);

		setVisible(true);
	}

	private void salvar() {
		try {
			Funcionario objFun = new Funcionario();
			if (txtCpf.getText().length() <= 0) {
				throw new IllegalArgumentException("Campo cpf é obrigatorio");
			}
			if (!LtpLib.validarCPF(txtCpf.getText())) {
				throw new IllegalArgumentException("CPF Invalido");
			}
			if (txtNome.getText().length() == 0) {
				throw new IllegalArgumentException("Nome Invalido");
			}
			if (txtTelefone.getText().length() == 0) {
				throw new IllegalArgumentException("telefone Invalido");
			}

			if (comando.equals("Editar")) {
				objFun = (Funcionario) Conexao.Show(objFun, Integer.parseInt(txtCodigo.getText()));
			}

			objFun.setNome(txtNome.getText());
			objFun.setRua(txtEndereco.getText());
			objFun.setNumRua(Integer.parseInt(txtNumEndereco.getText() == "" ? "0" : txtNumEndereco.getText()));
			objFun.setComplemento(txtComplemento.getText());
			objFun.setBairro(txtBairro.getText());
			objFun.setCidade(txtCidade.getText());
			objFun.setUf(cmbUF.getSelectedItem().toString());
			objFun.setCep(LtpLib.RetiraSimbolo(txtCep.getText()));
			objFun.setTelefone(LtpLib.RetiraSimbolo(txtTelefone.getText()));
			objFun.setFunCPF(LtpLib.RetiraSimbolo(txtCpf.getText()));
			objFun.setDescricao(txtDescricao.getText());
			objFun.setFunLogin(txtLogin.getText());
			objFun.setFunSenha(txtSenha.getText());
			objFun.setFunFuncao(cmbFuncao.getSelectedItem().toString());

			if (comando.equals("Editar")) {
				Conexao.Update(objFun);
				JOptionPane.showMessageDialog(null, "Alterado com sucesso");
			} else if (comando.equals("Inserir")) {
				bizFuncionario.Cadastrar(objFun);
				JOptionPane.showMessageDialog(null, "Salvo com sucesso");
			}

			if (telaFun instanceof TelaFuncionario) {
				((TelaFuncionario) telaFun).atualizar();
			} else {
				((telaPrinc) telaFun).Desbloquaer();
			}

			this.setVisible(false);

		} catch (IllegalArgumentException e) {
			if (e.getMessage().length() > 0) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}

	}
}
