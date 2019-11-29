package View;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import Controller.BizCliente;
import Model.Cliente;
import utilitarios.Icons;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TelaCliente extends JFrame {

	private JPanel contentPane;
	private static BizCliente bizCliente = new BizCliente();
	private static ArrayList<Cliente> listCliente = new ArrayList<>();

	private JButton btnAdicionar = new JButton("Adicionar", Icons.AddCliente(28, 28));
	private JButton btnEditar = new JButton("Editar", Icons.EditarCliente(28, 28));
	private JButton btnVisualizar = new JButton("Visualizar", Icons.VisualizarCliente(28, 28));
	private JButton btnExcluir = new JButton("Excluir", Icons.ExcluirCliente(28, 28));
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
	private final JLabel lblNewLabel = new JLabel("Cliente");
	private final JTextField txtDigiteOCpf = new JTextField();
	private final JButton btnPesquiCpf = new JButton("Pesquisar",Icons.PesquisarCliente(28, 28));
	
	private static TelaCliente frame;
	private JFrame tela;
	private final JLabel lblDigiteOCpf = new JLabel("Digite o cpf do cliente");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

			}
		});
	}

	public void mostrar(JFrame _tela) {
		frame = this;
		this.tela = _tela;
		setVisible(true);
	}

	public TelaCliente() {
		ImageIcon icone = new ImageIcon(Icons.class.getResource("/img/logo.png"));
		Image img = icone.getImage();
		setIconImage(img);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				((telaPrinc)tela).Desbloquaer();
			}
		});

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			setResizable(false);
			setSize(new Dimension(1070, 580));// tamanho do Formulario
			setLocationRelativeTo(null);// centralizado
			setTitle("Cliente");// titulo
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		} catch (Exception e) {
			e.printStackTrace();
		}
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

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 0, 1100, 101);
		panel_1.setLayout(null);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 5, 1057, 50);
		lblNewLabel.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 37));

		panel_1.add(lblNewLabel);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 97, 1100, 352);
		panel.setLayout(null);
		scrollPane.setBounds(10, 5, 1050, 350);
		panel.add(scrollPane);

		model.addColumn("Código");
		model.addColumn("Nome");
		model.addColumn("Telefone");
		model.addColumn("CPF");
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		table.setBounds(0, 0, 1110, 500);

		table.setModel(bizCliente.CarregaTable(model, "1", "1"));
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
		btnPesquiCpf.setBounds(256, 61, 164, 32);

		panel_1.add(btnPesquiCpf);
		lblDigiteOCpf.setBounds(10, 49, 222, 14);
		
		panel_1.add(lblDigiteOCpf);
		contentPane.add(panel);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {

					VisualizarCliente(table.getValueAt(table.getSelectedRow(), 0).toString(), table, frame);
					frame.setEnabled(false);
				}
			}
		});

		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastroCliente cadCliente = new CadastroCliente();
				cadCliente.mostrar(frame, "Inserir");

				setEnabled(false);
			}
		});

		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Selecione um registro");
				} else {
					Object[] options = { "Sim", "Não" };
					int opcao = JOptionPane.showOptionDialog(null, "Tec certeza que deseja excluir?", "",
							JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);

					if (opcao == 0) {
						bizCliente.Deletar(table.getValueAt(table.getSelectedRow(), 0).toString());
						table.setModel(bizCliente.CarregaTable(model, "1", "1"));
					}
				}
			}
		});

		btnVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (table.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Selecione um registro");
				} else {
					VisualizarCliente(table.getValueAt(table.getSelectedRow(), 0).toString(), table, frame);
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

	public JComponent coresAlternativa(DefaultTableModel defaultTableModel) {
		JTable table = new JTable(defaultTableModel) {
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component component = super.prepareRenderer(renderer, row, column);
				component.setBackground(row % 2 == 0 ? Color.ORANGE : Color.BLUE);
				return component;
			}
		};
		return new JScrollPane(table);
	}

	public void atualizar() {

		String[] cpf = (txtDigiteOCpf.getText().length() == 0) ? new String[] { "1", "1" }
				: new String[] { "p.cpf", txtDigiteOCpf.getText() };

		table.setModel(bizCliente.CarregaTable(model, cpf[0], cpf[1]));
		this.setEnabled(true);
	}

	public void Editar() {
		CadastroCliente cadCliente = new CadastroCliente();
		cadCliente.mostrar(frame, "Editar");
		cadCliente.preencherCampo(bizCliente.Consultar(table.getValueAt(table.getSelectedRow(), 0).toString()));

		setEnabled(false);

	}

	public void VisualizarCliente(String codigo, JTable table, JFrame frame) {
		if (table.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null, "Selecione um registro");
		} else {
			CadastroCliente cadCliente = new CadastroCliente();

			cadCliente.preencherCampo(bizCliente.Consultar(codigo));
			cadCliente.bloqueioCampo();
			cadCliente.mostrar(frame, "Visualizar");

			setEnabled(false);
		}
	}
}
