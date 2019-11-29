package View;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import Controller.BizProduto;
import Model.Produto;
import utilitarios.Icons;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TelaProdutos extends JFrame {

	private JPanel contentPane;
	private static TelaProdutos frame;
	private JFrame tela;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

			}
		});
	}

	

	private static BizProduto bizProduto = new BizProduto();
	private static ArrayList<Produto> listProduto = new ArrayList<>();

	private JButton btnAdicionar = new JButton("Adicionar", Icons.AddProduto(28, 28));
	private JButton btnEditar = new JButton("Editar", Icons.EditarProduto(28, 28));
	private JButton btnVisualizar = new JButton("Visualizar", Icons.VisualizarProduto(28, 28));
	private JButton btnExcluir = new JButton("Excluir", Icons.ExcluirProduto(28, 28));
	private JMenuBar menuBar = new JMenuBar();
	private JTable table = new JTable();
	private JScrollPane scrollPane = new JScrollPane(table);

	private DefaultTableModel model = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(final int row, final int column) {
			return false;
		}

	};
	private final JPanel panel = new JPanel();
	private final JPanel panel_1 = new JPanel();
	private final JLabel lblNewLabel = new JLabel("Produto");
	private final JTextField txtDigiteNome = new JTextField();
	private final JButton btnPesquiCpf = new JButton("Pesquisar", Icons.PesquisarProduto(28, 28));

	public void mostrar(JFrame _tela) {
		frame = this;
		this.tela = _tela;
		setVisible(true);
	}
	
	public TelaProdutos() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				((telaPrinc)tela).Desbloquaer();
			}
		});
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			setResizable(false);
			setSize(new Dimension(1070, 580));// tamanho do Formulario
			setLocationRelativeTo(null);// centralizado
			setTitle("Produto");// titulo
			setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		} catch (Exception e) {
			e.printStackTrace();
		}ImageIcon icone = new ImageIcon(Icons.class.getResource("/img/logo.png"));
		Image img = icone.getImage();
		setIconImage(img);
		setJMenuBar(menuBar);
		btnAdicionar.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		btnAdicionar.setForeground(new Color(0, 0, 0));
		btnAdicionar.setBackground(new Color(204, 204, 204));
		menuBar.add(btnAdicionar);

		btnVisualizar.setForeground(new Color(0, 0, 0));
		btnVisualizar.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		btnVisualizar.setBackground(new Color(204, 204, 204));
		menuBar.add(btnVisualizar);

		btnEditar.setForeground(Color.BLACK);
		btnEditar.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		btnEditar.setBackground(new Color(204, 204, 204));
		menuBar.add(btnEditar);

		btnExcluir.setForeground(Color.BLACK);
		btnExcluir.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		btnExcluir.setBackground(new Color(204, 204, 204));
		menuBar.add(btnExcluir);

		model.addColumn("Código");
		model.addColumn("Descrição");
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 0, 1100, 101);
		panel_1.setLayout(null);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 5, 1110, 50);
		lblNewLabel.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 37));

		panel_1.add(lblNewLabel);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 97, 1100, 352);
		panel.setLayout(null);

		scrollPane.setBounds(10, 5, 1050, 350);
		panel.add(scrollPane);
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == 127) {
					excluir();
				}
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		table.setBounds(0, 0, 1110, 500);

		table.setModel(bizProduto.CarregaTable(model, "1", "1"));
		table.getTableHeader().setReorderingAllowed(false);

		contentPane.setLayout(null);
		contentPane.add(panel_1);
		txtDigiteNome.setBounds(10, 64, 222, 29);

		panel_1.add(txtDigiteNome);
		btnPesquiCpf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizar();
			}
		});
		btnPesquiCpf.setBounds(256, 61, 163, 29);

		panel_1.add(btnPesquiCpf);
		
		JLabel lblDigiteOCdigo = new JLabel("Digite o c\u00F3digo do produto");
		lblDigiteOCdigo.setBounds(10, 41, 216, 14);
		panel_1.add(lblDigiteOCdigo);
		contentPane.add(panel);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {

					VisualizarProduto(table.getValueAt(table.getSelectedRow(), 0).toString(), table);
					setEnabled(false);
				}
			}
		});

		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastroProduto cadProduto = new CadastroProduto();
				cadProduto.mostrar(frame, "Inserir");
				frame.setEnabled(false);
			}
		});

		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluir();

			}
		});

		btnVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (table.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Selecione um registro");
				} else {
					VisualizarProduto(table.getValueAt(table.getSelectedRow(), 0).toString(), table);
					setEnabled(false);
				}
			}

		});
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Selecione um registro");
				} else {
					Editar();
				}
			}
		});
	}

	public void atualizar() {

		String[] cpf = (txtDigiteNome.getText().length() == 0) ? new String[] { "1", "1" }
				: new String[] { "p.idProduto", txtDigiteNome.getText() };

		table.setModel(bizProduto.CarregaTable(model, cpf[0], cpf[1]));
		setEnabled(true);
		this.setAlwaysOnTop(true);
		this.setAlwaysOnTop(false);
	}

	public void excluir() {
		if (table.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null, "Selecione um registro");
		} else {
			Object[] options = { "Não", "Sim" };
			int opcao = JOptionPane.showOptionDialog(null, "Tec certeza que deseja excluir?", "",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);

			if (opcao == 0) {
				bizProduto.Deletar(table.getValueAt(table.getSelectedRow(), 0).toString());
				table.setModel(bizProduto.CarregaTable(model, "1", "1"));
			}
		}
	}

	public void Editar() {
		CadastroProduto cadProduto = new CadastroProduto();
		cadProduto.mostrar(this, "Editar");
		cadProduto.preencherCampo(bizProduto.Consultar(table.getValueAt(table.getSelectedRow(), 0).toString()));
		this.setEnabled(false);

	}

	public void VisualizarProduto(String codigo, JTable table) {
		if (table.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null, "Selecione um registro");
		} else {
			CadastroProduto cadProd = new CadastroProduto();

			cadProd.preencherCampo(bizProduto.Consultar(codigo));
			cadProd.bloqueioCampo();
			cadProd.mostrar(frame, "Visualizar");
			setEnabled(false);
		}
	}
}
