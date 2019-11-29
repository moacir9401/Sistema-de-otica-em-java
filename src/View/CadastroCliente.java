package View;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import Controller.BizCliente;
import Controller.BizUF;
import DAO.Conexao;
import Model.Cliente;
import Model.UF;
import utilitarios.Icons;
import utilitarios.LtpLib;

import java.util.ArrayList;
import java.awt.event.*;

public class CadastroCliente extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtNome;
	private JTextField txtCpf;
	private JTextField txtRg;
	private JTextField txtTelefone;
	private JTextField txtDescricao;
	private JTextField txtBairro;
	private JTextField txtEndereco;
	private JTextField txtNumEndereco;
	private JTextField txtCidade;
	private JTextField txtComplemento;
	private JTextField txtCep;
	private JComboBox<String> cmbUF;
	private JFrame tela;
	private BizCliente bizCliente = new BizCliente();
	private String comando;
	private JButton btnSalvar;

	public void mostrar(JFrame tela, String _comando) {
		this.tela = tela;
		setVisible(true);
		comando = _comando;
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					CadastroCliente frame = new CadastroCliente();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CadastroCliente() {
		setTitle("Cadastro Cliente");

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {

			}

			@Override
			public void windowClosing(WindowEvent e) {
				
				if (tela instanceof TelaCliente) {
					((TelaCliente) tela).atualizar();
				}
				else {
					((telaPrinc) tela).Desbloquaer();
				}
			}
		});
		ImageIcon icone = new ImageIcon(Icons.class.getResource("/img/logo.png"));
		Image img = icone.getImage();
		setIconImage(img);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 559, 514);
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
		txtCodigo.setBounds(10, 22, 86, 27);
		panel.add(txtCodigo);
		txtCodigo.setColumns(10);
		txtCodigo.setEnabled(false);
		JLabel lblNewLabel = new JLabel("Código");
		lblNewLabel.setBounds(10, 8, 86, 14);
		panel.add(lblNewLabel);

		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(117, 22, 297, 27);
		panel.add(txtNome);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(117, 8, 282, 14);
		panel.add(lblNome);

		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(10, 58, 86, 14);
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
		txtCpf.setBounds(10, 72, 86, 29);
		panel.add(txtCpf);

		txtRg = new JTextField();
		txtRg.setColumns(10);
		txtRg.setBounds(117, 72, 86, 29);
		panel.add(txtRg);

		JLabel lblRg = new JLabel("RG");
		lblRg.setBounds(117, 58, 86, 14);
		panel.add(lblRg);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(225, 58, 105, 14);
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
		txtTelefone.setBounds(225, 72, 105, 29);
		panel.add(txtTelefone);

		JLabel lblDescrio = new JLabel("Descrição");
		lblDescrio.setBounds(10, 290, 404, 14);
		panel.add(lblDescrio);

		txtDescricao = new JTextField();
		txtDescricao.setColumns(10);
		txtDescricao.setBounds(10, 304, 487, 94);
		panel.add(txtDescricao);

		txtBairro = new JTextField();
		txtBairro.setColumns(10);
		txtBairro.setBounds(10, 198, 143, 29);
		panel.add(txtBairro);

		JLabel lblBiarro = new JLabel("Bairro");
		lblBiarro.setBounds(10, 184, 143, 14);
		panel.add(lblBiarro);

		txtEndereco = new JTextField();
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(10, 145, 249, 27);
		panel.add(txtEndereco);

		JLabel lblEndereo = new JLabel("Endereço");
		lblEndereo.setBounds(10, 131, 249, 14);
		panel.add(lblEndereo);

		txtNumEndereco = new JTextField();
		txtNumEndereco.setColumns(10);
		txtNumEndereco.setBounds(286, 145, 48, 27);
		panel.add(txtNumEndereco);

		JLabel lblN = new JLabel("Nº");
		lblN.setBounds(286, 131, 48, 14);
		panel.add(lblN);

		txtCidade = new JTextField();
		txtCidade.setColumns(10);
		txtCidade.setBounds(191, 198, 143, 29);
		panel.add(txtCidade);

		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(191, 184, 143, 14);
		panel.add(lblCidade);

		txtComplemento = new JTextField();
		txtComplemento.setColumns(10);
		txtComplemento.setBounds(354, 145, 143, 27);
		panel.add(txtComplemento);

		JLabel lblComplemento = new JLabel("Complemento");
		lblComplemento.setBounds(354, 131, 143, 14);
		panel.add(lblComplemento);

		BizUF objuf = new BizUF();

		cmbUF = new JComboBox<String>();
		cmbUF.setBounds(354, 198, 143, 29);
		panel.add(cmbUF);

		ArrayList<UF> list = objuf.Consultar();
		for (UF uf : list) {
			cmbUF.addItem(uf.getDescricao());
		}

		JLabel lblUf = new JLabel("UF");
		lblUf.setBounds(353, 184, 113, 14);
		panel.add(lblUf);

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvar();

			}
		});
		btnSalvar.setBounds(7, 409, 89, 23);
		panel.add(btnSalvar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tela != null) {
					((TelaCliente) tela).atualizar();
				}
				setVisible(false);
			}
		});
		btnCancelar.setBounds(408, 409, 89, 23);
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
		txtCep.setBounds(10, 251, 143, 27);
		panel.add(txtCep);

		JLabel lblCep = new JLabel("CEP");
		lblCep.setBounds(10, 237, 143, 14);
		panel.add(lblCep);

		setVisible(true);
	}

	public void preencherCampo(ArrayList<Cliente> listCli) {

		for (Cliente cli : listCli) {
			txtCodigo.setText(Integer.toString(cli.getIdPessoa()));
			txtNome.setText(cli.getNome());
			txtCpf.setText(LtpLib.formatarCPF(cli.getCpf()));
			txtRg.setText(cli.getRg());
			txtDescricao.setText(cli.getDescricao());
			txtTelefone.setText(LtpLib.formatarTelefone(cli.getTelefone()));
			txtEndereco.setText(cli.getRua());
			txtNumEndereco.setText(Integer.toString(cli.getNumRua()));
			txtBairro.setText(cli.getBairro());
			txtCidade.setText(cli.getCidade());
			txtCep.setText(LtpLib.formatarCEP(cli.getCep()));
			txtComplemento.setText(cli.getComplemento());
			cmbUF.setSelectedItem(cli.getUf());

		}

	}

	public void bloqueioCampo() {
		txtCodigo.setEnabled(false);
		txtNome.setEnabled(false);
		txtCpf.setEnabled(false);
		txtRg.setEnabled(false);
		txtDescricao.setEnabled(false);
		txtTelefone.setEnabled(false);
		txtEndereco.setEnabled(false);
		txtNumEndereco.setEnabled(false);
		txtBairro.setEnabled(false);
		txtCidade.setEnabled(false);
		txtCep.setEnabled(false);
		txtComplemento.setEnabled(false);
		cmbUF.setEnabled(false);
		btnSalvar.setVisible(false);
	}

	private void salvar() {
		try {
			Cliente objCli = new Cliente();
			if (txtCpf.getText().length() <= 0) {
				throw new IllegalArgumentException("Campo cpf é obrigatorio");
			}
			if (!LtpLib.validarCPF(txtCpf.getText())) {
				throw new IllegalArgumentException("CPF Invalido");
			}
			if (txtNome.getText().length() <= 0) {
				throw new IllegalArgumentException("Nome Invalido");
			}
			if (txtTelefone.getText().length() <= 0) {
				throw new IllegalArgumentException("telefone Invalido");
			}

			if (comando == "Editar") {
				objCli = (Cliente) Conexao.Show(objCli, Integer.parseInt(txtCodigo.getText()));
			}

			objCli.setNome(txtNome.getText());
			objCli.setRua(txtEndereco.getText());
			objCli.setNumRua(Integer.parseInt(txtNumEndereco.getText() == "" ? "0" : txtNumEndereco.getText()));
			objCli.setComplemento(txtComplemento.getText());
			objCli.setBairro(txtBairro.getText());
			objCli.setCidade(txtCidade.getText());
			objCli.setUf(cmbUF.getSelectedItem().toString());
			objCli.setCep(LtpLib.RetiraSimbolo(txtCep.getText()));
			objCli.setTelefone(LtpLib.RetiraSimbolo(txtTelefone.getText()));
			objCli.setRg(txtRg.getText());
			objCli.setCpf(LtpLib.RetiraSimbolo(txtCpf.getText()));
			objCli.setDescricao(txtDescricao.getText());

			if (comando.equals("Editar")) {
				Conexao.Update(objCli);
				JOptionPane.showMessageDialog(null, "Alterado com sucesso");
			} else if (comando.equals("Inserir")) {
				bizCliente.Cadastrar(objCli);
				JOptionPane.showMessageDialog(null, "Salvo com sucesso");
			}

			if(tela instanceof TelaCliente) {
				TelaCliente frame = (TelaCliente) tela;
				frame.atualizar();
			}
			else {
				((telaPrinc) tela).Desbloquaer();
			}

			this.setVisible(false);
		} catch (IllegalArgumentException e) {
			if (e.getMessage().length() > 0) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}

	}
}
