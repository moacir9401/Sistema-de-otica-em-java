package View;

import java.awt.*;
import java.util.*;
import java.util.stream.Collectors;

import javax.swing.*;
import Controller.BizAutorizacaoPerfil;
import Controller.BizFuncao;
import DAO.Conexao;
import Model.AutorizacaoPerfil;
import Model.Funcao;
import antlr.collections.List;
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

public class TelaAutorizacaoPerfil extends JFrame {

	private JPanel contentPane;
	private static TelaAutorizacaoPerfil frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frame = new TelaAutorizacaoPerfil();
				frame.setVisible(true);
			}
		});
	}

	private static BizAutorizacaoPerfil bizAutorizacaoPerfil = new BizAutorizacaoPerfil();
	private static AutorizacaoPerfil objAutorizacaoPerfil;
	private static ArrayList<AutorizacaoPerfil> listobjAutorizacaoPerfil = new ArrayList<>();
	private JTable table = new JTable();
	private JTable table_1 = new JTable();
	private JScrollPane scrollPane = new JScrollPane(table);
	private JScrollPane scrollPane_1 = new JScrollPane(table_1);
	private DefaultTableModel model = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(final int row, final int column) {
			return false;
		}

	};
	private final JPanel panel = new JPanel();
	private final JPanel panel_1 = new JPanel();
	private final JLabel lblNewLabel = new JLabel(" Designar Funções ");
	private final JButton btnPesquiCpf = new JButton("Pesquisar");
	private final JComboBox<String> cmbPerfilUsuario = new JComboBox();
	private final JLabel lblPerfilUsurio = new JLabel("Perfil Usuario");
	private final JLabel lblFunesNoAutorizadas = new JLabel("Funções Não Autorizadas");
	private final JLabel lblFunesAutorizadas = new JLabel("Funções Autorizadas");
	private final JButton button = new JButton(">");
	private final JButton button_1 = new JButton("<");
	private final JButton button_2 = new JButton("<<");
	private JFrame tela;
	private BizFuncao bizFuncao = new BizFuncao();

	public void mostrar(JFrame _tela) {
		frame = this;
		this.tela = _tela;
		setVisible(true);
	}

	public TelaAutorizacaoPerfil() {
		ImageIcon icone = new ImageIcon(Icons.class.getResource("/img/logo.png"));
		Image img = icone.getImage();
		setIconImage(img);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				((telaPrinc) tela).Desbloquaer();
			}
		});
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

			setResizable(false);
			setSize(new Dimension(1070, 580));// tamanho do Formulario
			setLocationRelativeTo(null);// centralizado
			setTitle(" Designar Funções ");// titulo
			setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		} catch (Exception e) {
			e.printStackTrace();
		}
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

		scrollPane.setBounds(577, 45, 459, 296);
		panel.add(scrollPane);

		BizFuncao bizFuncao = new BizFuncao();

		ArrayList<Funcao> listFuncao = bizFuncao.Consultar();

		for (Funcao funcao : listFuncao) {
			cmbPerfilUsuario.addItem(funcao.getFuncaoDescricao());
		}

		atualizar();
		contentPane.setLayout(null);
		contentPane.add(panel_1);
		btnPesquiCpf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizar();
			}
		});

		btnPesquiCpf.setBounds(256, 67, 79, 23);

		panel_1.add(btnPesquiCpf);
		cmbPerfilUsuario.setBounds(27, 68, 200, 20);

		panel_1.add(cmbPerfilUsuario);
		lblPerfilUsurio.setBounds(27, 53, 200, 14);

		panel_1.add(lblPerfilUsurio);
		contentPane.add(panel);
		scrollPane_1.setBounds(44, 45, 459, 296);

		panel.add(scrollPane_1);
		lblFunesNoAutorizadas.setHorizontalAlignment(SwingConstants.CENTER);
		lblFunesNoAutorizadas.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 26));
		lblFunesNoAutorizadas.setBounds(44, 11, 472, 28);

		panel.add(lblFunesNoAutorizadas);
		lblFunesAutorizadas.setHorizontalAlignment(SwingConstants.CENTER);
		lblFunesAutorizadas.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 26));
		lblFunesAutorizadas.setBounds(553, 11, 472, 28);

		panel.add(lblFunesAutorizadas);

		JButton btnNewButton = new JButton(">>");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int total = table_1.getRowCount();
				objAutorizacaoPerfil = new AutorizacaoPerfil();
				for (int i = 0; i < total; i++) {
					ArrayList<AutorizacaoPerfil> listPerfil = bizAutorizacaoPerfil.Consultar(" where p.perfilFuncao = '"
							+ cmbPerfilUsuario.getSelectedItem().toString() + "' and p.permissao = 'Nao'");

					objAutorizacaoPerfil = new AutorizacaoPerfil();

					
						if (listPerfil.size() > 0) {
							objAutorizacaoPerfil = (AutorizacaoPerfil) Conexao.Show(objAutorizacaoPerfil,
									(int) listPerfil.get(0).getCodigo());
							objAutorizacaoPerfil.setPermissao("Sim");
							Conexao.Update(objAutorizacaoPerfil);

						} else {
							AutorizacaoPerfil novoObjAutorizacaoPerfil = new AutorizacaoPerfil();
							novoObjAutorizacaoPerfil.setPerfilFuncao(cmbPerfilUsuario.getSelectedItem().toString());
							novoObjAutorizacaoPerfil.setFuncao(table_1.getValueAt(i, 1).toString());
							novoObjAutorizacaoPerfil.setPermissao("Sim");
							bizAutorizacaoPerfil.Cadastrar(novoObjAutorizacaoPerfil);
						}
					
				}
				atualizar();
			}
		});
		btnNewButton.setBounds(513, 91, 54, 44);
		panel.add(btnNewButton);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int total = table_1.getRowCount();
				objAutorizacaoPerfil = new AutorizacaoPerfil();
					ArrayList<AutorizacaoPerfil> listPerfil = bizAutorizacaoPerfil.Consultar(" where p.perfilFuncao = '"
							+ cmbPerfilUsuario.getSelectedItem().toString() + "' and p.permissao = 'Nao'");

					objAutorizacaoPerfil = new AutorizacaoPerfil();

					
						if (listPerfil.size() > 0) {
							objAutorizacaoPerfil = (AutorizacaoPerfil) Conexao.Show(objAutorizacaoPerfil,
									(int) listPerfil.get(0).getCodigo());
							objAutorizacaoPerfil.setPermissao("Sim");
							Conexao.Update(objAutorizacaoPerfil);

						} else {
							AutorizacaoPerfil novoObjAutorizacaoPerfil = new AutorizacaoPerfil();
							novoObjAutorizacaoPerfil.setPerfilFuncao(cmbPerfilUsuario.getSelectedItem().toString());
							novoObjAutorizacaoPerfil.setFuncao(table_1.getValueAt(table_1.getSelectedRow(), 1).toString());
							novoObjAutorizacaoPerfil.setPermissao("Sim");
							bizAutorizacaoPerfil.Cadastrar(novoObjAutorizacaoPerfil);
						}
					
				atualizar();
			}
		});
		button.setBounds(513, 146, 54, 44);

		panel.add(button);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Selecione um registro");
				} else {
					objAutorizacaoPerfil = new AutorizacaoPerfil();
					objAutorizacaoPerfil = (AutorizacaoPerfil) Conexao.Show(objAutorizacaoPerfil,
							(int) table.getValueAt(table.getSelectedRow(), 0));
					objAutorizacaoPerfil.setPermissao("Nao");
					Conexao.Update(objAutorizacaoPerfil);
					atualizar();
				}
			}
		});
		button_1.setBounds(513, 201, 54, 44);

		panel.add(button_1);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int total = table.getRowCount();
				objAutorizacaoPerfil = new AutorizacaoPerfil();
				for (int i = 0; i < total; i++) {
					objAutorizacaoPerfil = (AutorizacaoPerfil) Conexao.Show(objAutorizacaoPerfil,
							Integer.parseInt(table.getValueAt(0, 0).toString()));
					objAutorizacaoPerfil.setPermissao("Nao");
					Conexao.Update(objAutorizacaoPerfil);
					atualizar();
				}
			}

		});
		button_2.setBounds(513, 256, 54, 44);

		panel.add(button_2);

	}

	public void atualizar() {

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Código");
		model.addColumn("Descrição");

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		table.setBounds(0, 0, 1110, 500);
		table_1.setBounds(0, 0, 1110, 500);
		table.getTableHeader().setReorderingAllowed(false);
		table_1.getTableHeader().setReorderingAllowed(false);

		DefaultTableModel model2 = new DefaultTableModel();
		model2.addColumn("Código");
		model2.addColumn("Descrição");

		table.setModel(CarregaTable(model2, "Sim", " where p.perfilFuncao = '"
				+ cmbPerfilUsuario.getSelectedItem().toString() + "' and p.permissao = 'Sim'"));

		String where = " where 1=1 ";
		for (int i = 0; i < table.getRowCount(); i++) {

			where += " and p.funcao <> '" + table.getValueAt(i, 1) + "'";
		}
		table_1.setModel(CarregaTable(model, "Nao", where));

	}

	int indice = 0;

	public Boolean verificarDuplicado(DefaultTableModel objModel, String campo) {
		for (int j = 0; j < objModel.getRowCount() - 1; j++) {
			if (objModel.getValueAt(j, 1).equals(campo)) {
				indice = j;
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public DefaultTableModel CarregaTable(DefaultTableModel model, String permissao, String where) {

		model.setRowCount(0);
		ArrayList<AutorizacaoPerfil> list = new ArrayList<AutorizacaoPerfil>();
		list = bizAutorizacaoPerfil.Consultar(where);

		for (AutorizacaoPerfil objFuncao : list) {
			model.addRow(new Object[] { objFuncao.getCodigo(), objFuncao.getFuncao() });

			if (verificarDuplicado(model, objFuncao.getFuncao())) {
				model.removeRow(indice);
			}
		}

		return model;
	}

}
