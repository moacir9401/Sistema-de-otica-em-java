package View;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.UIManager;

import Controller.BizAutorizacaoPerfil;
import Controller.BizFuncionario;
import DAO.Conexao;
import Model.AutorizacaoPerfil;
import Model.Funcionario;
import utilitarios.Icons;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Panel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.event.InputEvent;
import javax.swing.JSeparator;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;

public class telaPrinc extends JFrame {
	private static telaPrinc frame;
	private static BizAutorizacaoPerfil bizAutorizacaoPerfil = new BizAutorizacaoPerfil();
	private static BizFuncionario bizFuncionario = new BizFuncionario();
	private static JButton btnProduto;
	private static JButton btnCliente;
	private static JButton btnFuncionario;
	private static JButton btnVenda;
	private static JMenuItem mntmVendas;
	private static JMenuItem mntmCliente;
	private static JMenuItem mntmProduto;
	private static JMenuItem mntmFuncao;
	private static JMenu mnRelatorio;
	private static JMenuItem mntmDesignarFuno;
	private static JMenuItem mntmFuncionario;
	/**
	 * Launch the application.
	 */

	public String vendedor = "";
	public String idVendedor;

	public void vendedor(String _Vendedor, String _idVendedor) {
		vendedor = _Vendedor;
		idVendedor = _idVendedor;

		ArrayList<Funcionario> objFuncionario = bizFuncionario.Consultar(idVendedor);

		ArrayList<AutorizacaoPerfil> objauList = bizAutorizacaoPerfil
				.Consultar(" where p.perfilFuncao = '" + objFuncionario.get(0).getFunFuncao() + "'");

		for (AutorizacaoPerfil s : objauList) {

			if (s.getPermissao().equals("Sim")) {
				Permitir(s.getFuncao());
			}

		}

	}

	public telaPrinc() {
		ImageIcon icone = new ImageIcon(Icons.class.getResource("/img/logo.png"));
		Image img = icone.getImage();
		setIconImage(img);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		} catch (Exception e) {
			// TODO: handle exception
		}
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1276, 771);
		setLocationRelativeTo(null);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnArquvi = new JMenu("Arquivo");
		menuBar.add(mnArquvi);

		JMenuItem mntmTrocarUsuario = new JMenuItem("Trocar Usuario");
		mntmTrocarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login login = new Login();
				setVisible(false);
			}
		});
		mnArquvi.add(mntmTrocarUsuario);

		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		mnArquvi.add(mntmSair);

		JMenu mnCadastro = new JMenu("Cadastro");

		menuBar.add(mnCadastro);

		mnCadastro.addSeparator();

		mntmCliente = new JMenuItem("Cliente");
		mntmCliente.setVisible(false);
		mntmCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastroCliente cadCliente = new CadastroCliente();
				cadCliente.mostrar(telaPrinc.this, "Inserir");
				setEnabled(false);
			}
		});
		mntmCliente.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
		mnCadastro.add(mntmCliente);
		mntmCliente.setIcon(Icons.AddCliente(15, 15));

		mntmFuncionario = new JMenuItem("Funcionario");
		mntmFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroFuncionario cadFuncionario = new CadastroFuncionario();
				cadFuncionario.mostrar(telaPrinc.this, "Inserir");
				setEnabled(false);
			}
		});
		mntmFuncionario.setVisible(false);
		mntmFuncionario.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, InputEvent.ALT_MASK));
		mnCadastro.add(mntmFuncionario);
		mntmFuncionario.setIcon(Icons.AddFuncionario(15, 15));

		JSeparator separator = new JSeparator();
		mnCadastro.add(separator);

		mntmProduto = new JMenuItem("Produto");
		mntmProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastroProduto cadProd = new CadastroProduto();
				cadProd.mostrar(telaPrinc.this, "Inserir");
				setEnabled(false);
			}
		});
		mntmProduto.setVisible(false);
		mntmProduto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, InputEvent.ALT_MASK));
		mnCadastro.add(mntmProduto);
		mntmProduto.setIcon(Icons.AddProduto(15, 15));

		JMenu mnFuno = new JMenu("Função");
		mnCadastro.add(mnFuno);

		mntmFuncao = new JMenuItem("Função");
		mntmFuncao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroFuncao cadFuncao = new CadastroFuncao();
				cadFuncao.mostrar(telaPrinc.this, "Inserir");
				setEnabled(false);
			}
		});
		mntmFuncao.setVisible(false);
		mnFuno.add(mntmFuncao);
		mntmFuncao.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, InputEvent.ALT_MASK));
		mntmFuncao.setIcon(Icons.AddPerfil(15, 15));

		mntmDesignarFuno = new JMenuItem("Designar Função");
		mntmDesignarFuno.setVisible(false);
		mntmDesignarFuno.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5, InputEvent.ALT_MASK));
		mnFuno.add(mntmDesignarFuno);
		mntmDesignarFuno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaAutorizacaoPerfil t = new TelaAutorizacaoPerfil();
				t.mostrar(telaPrinc.this);
				setEnabled(false);

			}
		});
		mntmDesignarFuno.setIcon(Icons.DesignarFuncao(15, 15));

		JSeparator separator_1 = new JSeparator();
		mnCadastro.add(separator_1);

		mnRelatorio = new JMenu("Relatorio");
		mnRelatorio.setVisible(false);
		menuBar.add(mnRelatorio);

		mntmVendas = new JMenuItem("Vendas");
		mntmVendas.setVisible(false);
		mntmVendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RelatorioVendas relVendas = new RelatorioVendas();
				relVendas.mostrar(telaPrinc.this);
			}
		});
		mnRelatorio.add(mntmVendas);
		mntmVendas.setIcon(Icons.Relatorio(15, 15));
		getContentPane().setLayout(null);

		Panel panel = new Panel();
		panel.setBackground(new Color(163, 184, 204));
		panel.setBounds(0, 0, 420, 34);
		getContentPane().add(panel);
		panel.setLayout(null);

		JMenuBar menuBar_1 = new JMenuBar();
		menuBar_1.setBounds(0, 0, 420, 34);
		panel.add(menuBar_1);

		btnCliente = new JButton(Icons.Cliente(30, 30));
		btnCliente.setVisible(false);
		btnCliente.setText("Cliente");
		btnCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCliente telaCliente = new TelaCliente();
				telaCliente.mostrar(telaPrinc.this);
				setEnabled(false);
			}
		});
		menuBar_1.add(btnCliente);

		btnFuncionario = new JButton(Icons.Funcionario(30, 30));
		btnFuncionario.setVisible(false);
		btnFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaFuncionario telaFunc = new TelaFuncionario();
				telaFunc.mostrar(telaPrinc.this);
				setEnabled(false);
			}
		});
		btnFuncionario.setText("Funcionario");
		menuBar_1.add(btnFuncionario);

		btnProduto = new JButton(Icons.Produto(30, 30));
		btnProduto.setVisible(false);
		btnProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaProdutos telaProd = new TelaProdutos();
				telaProd.mostrar(telaPrinc.this);
				setEnabled(false);
			}
		});
		btnProduto.setText("Produto");
		menuBar_1.add(btnProduto);

		btnVenda = new JButton(Icons.Compra(30, 30));
		btnVenda.setVisible(false);
		btnVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaVenda telaVenda = new TelaVenda(vendedor);
				telaVenda.mostrar(telaPrinc.this);
			}
		});
		btnVenda.setText("Venda");
		menuBar_1.add(btnVenda);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 1276, 717);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(Icons.Fundo(1276, 717));
		lblNewLabel.setBounds(0, -42, 1276, 792);
		panel_1.add(lblNewLabel);
		// setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	}

	public static void Permitir(String funcao) {

		switch (funcao) {
		case "Cliente":
			btnCliente.setVisible(true);
			mntmCliente.setVisible(true);
			break;
		case "Funcionario":
			btnFuncionario.setVisible(true);
			mntmFuncionario.setVisible(true);
			break;
		case "Produto":
			btnProduto.setVisible(true);
			mntmProduto.setVisible(true);
			break;
		case "Venda":
			btnVenda.setVisible(true);
			mntmVendas.setVisible(true);
			break;
		case "Relatorio":
			mnRelatorio.setVisible(true);
			break;
		case "Cadastrar Função":
			mntmFuncao.setVisible(true);
			break;
		case "Designar Função":
			mntmDesignarFuno.setVisible(true);
			break;
		}
	}

	public void Desbloquaer() {
		setEnabled(true);
	}
}
