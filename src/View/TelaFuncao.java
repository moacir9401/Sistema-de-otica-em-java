package View;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import Model.Funcao;
import utilitarios.Icons;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.table.DefaultTableModel;

import Controller.BizFuncao;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TelaFuncao extends JFrame {

	private JPanel contentPane;
	private static TelaFuncao frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					frame = new TelaFuncao();
					frame.setVisible(true);
					frame.setResizable(false);
					frame.setSize(new Dimension(1070, 580));// tamanho do Formulario
					frame.setLocationRelativeTo(null);// centralizado
					frame.setTitle("Funcao");// titulo
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	private static BizFuncao objFuncao = new BizFuncao();
	private static ArrayList<Funcao> listFuncao = new ArrayList<>();

	private JButton btnAdicionar = new JButton("Adicionar", Icons.Add(28, 28));
	private JButton btnEditar = new JButton("Editar", Icons.Add(28, 28));
	private JButton btnVisualizar = new JButton("Visualizar", Icons.Add(28, 28));
	private JButton btnExcluir = new JButton("Excluir", Icons.Add(28, 28));
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
	private final JLabel lblNewLabel = new JLabel("Funcao");
	private final JTextField txtDigiteOCpf = new JTextField();
	private final JButton btnPesquiCpf = new JButton("Pesquisar");
	private final JLabel lblDigiteONome = new JLabel("Digite o nome da fun\u00E7\u00E3o");

	public TelaFuncao() {
		ImageIcon icone = new ImageIcon(Icons.class.getResource("/img/logo.png"));
		Image img = icone.getImage();
		setIconImage(img);
		setTitle("Função");

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
		scrollPane.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 127) {
					excluir();
				}
			}
		});

		scrollPane.setBounds(10, 5, 1050, 350);
		panel.add(scrollPane);

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		table.setBounds(0, 0, 1110, 500);

		table.setModel(objFuncao.CarregaTable(model, "1", "1"));
		table.getTableHeader().setReorderingAllowed(false);

		contentPane.setLayout(null);
		contentPane.add(panel_1);
		txtDigiteOCpf.setBounds(10, 64, 222, 29);

		panel_1.add(txtDigiteOCpf);
		btnPesquiCpf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizar();
			}
		});
		btnPesquiCpf.setBounds(256, 67, 79, 23);

		panel_1.add(btnPesquiCpf);
		lblDigiteONome.setBounds(10, 44, 208, 14);
		
		panel_1.add(lblDigiteONome);
		contentPane.add(panel);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {

					objFuncao.VisualizarFuncao(table.getValueAt(table.getSelectedRow(), 0).toString(), table, frame);
					frame.setEnabled(false);
				}
			}
		});

		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastroFuncao cadFuncao = new CadastroFuncao();
				cadFuncao.mostrar(frame, "Editar");
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
					objFuncao.VisualizarFuncao(table.getValueAt(table.getSelectedRow(), 0).toString(), table, frame);
					frame.setEnabled(false);
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

		String[] cpf = (txtDigiteOCpf.getText().length() == 0) ? new String[] { "1", "1" }
				: new String[] { " p.funcaoDescricao ", txtDigiteOCpf.getText() };

		table.setModel(objFuncao.CarregaTable(model, cpf[0], cpf[1]));
		frame.setEnabled(true);
	}

	public void excluir() {
		if (table.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null, "Selecione um registro");
		} else {
			Object[] options = { "Não", "Sim" };
			int opcao = JOptionPane.showOptionDialog(null, "Tec certeza que deseja excluir?", "",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

			if (opcao == 1) {
				objFuncao.Deletar(table.getValueAt(table.getSelectedRow(), 0).toString());
				table.setModel(objFuncao.CarregaTable(model, "1", "1"));
			}
		}
	}

	public void Editar() {
		CadastroFuncao cadFuncao = new CadastroFuncao();
		cadFuncao.mostrar(this, "Editar");
		cadFuncao.preencherCampo(objFuncao.Consultar(table.getValueAt(table.getSelectedRow(), 0).toString()));
		this.setEnabled(false);

	}
}
