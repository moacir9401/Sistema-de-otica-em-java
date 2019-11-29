package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.transaction.Transactional.TxType;

import Controller.BizProduto;
import Model.Produto;
import utilitarios.Icons;
import utilitarios.LtpLib;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class TelaBuscarProduto extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtIdProd;
	private JTable table;
	private BizProduto bizProduto = new BizProduto();
	private JFrame tela;
	private static TelaBuscarProduto frame;
	private DefaultTableModel model = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(final int row, final int column) {
			return false;
		}

	};

	public void mostrar(JFrame tela) {
		this.tela = tela;
		setVisible(true);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					frame = new TelaBuscarProduto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaBuscarProduto() {
		ImageIcon icone = new ImageIcon(Icons.class.getResource("/img/logo.png"));
		Image img = icone.getImage();
		setIconImage(img);
		setTitle("Produto");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 700, 482);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JLabel lblBuscarPorNome = new JLabel("Buscar por nome");
		lblBuscarPorNome.setBounds(10, 83, 163, 14);
		contentPane.add(lblBuscarPorNome);

		txtNome = new JTextField();
		txtNome.setBounds(10, 97, 163, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtNome.getText().length() > 0) {

					atualizar("p where p.nome like '%" + txtNome.getText().toUpperCase() + "%' order by p.nome");
				} else {
					atualizar("p order by p.nome");
				}
			}
		});
		button.setBounds(183, 97, 89, 23);
		contentPane.add(button);

		JLabel lblBuscarPorCpf = new JLabel("Buscar por id");
		lblBuscarPorCpf.setBounds(304, 83, 163, 14);
		contentPane.add(lblBuscarPorCpf);

		txtIdProd = new JTextField();
		txtIdProd.setColumns(10);
		txtIdProd.setBounds(304, 97, 163, 20);
		contentPane.add(txtIdProd);

		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtIdProd.getText().length() > 0) {

					atualizar("p where p.idProduto = " + txtIdProd.getText() + "  order by p.nome");
				} else {
					atualizar("p order by p.nome");
				}
			}
		});
		button_1.setBounds(477, 94, 46, 23);
		contentPane.add(button_1);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 128, 644, 290);
		contentPane.add(panel);
		panel.setLayout(null);

		table = new JTable();
		table.getTableHeader().setReorderingAllowed(false);

		JScrollPane scrollPane = new JScrollPane(table);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					preencheCampo();
				}
			}
		});

		scrollPane.setBounds(0, 5, 644, 285);
		panel.add(scrollPane);

		atualizar("p");

		JLabel label = new JLabel("Busca por Produto");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 37));
		label.setBounds(10, 11, 644, 50);
		contentPane.add(label);
	}

	public void atualizar(String where) {

		model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(final int row, final int column) {
				return false;
			}

		};
		model.addColumn("Código");
		model.addColumn("Descrição");
		model.addColumn("Tipo produto");
		model.addColumn("Valor");
		model.addColumn("Estoque");
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		table.setModel(CarregaTable(where));
	}

	public DefaultTableModel CarregaTable(String where) {

		model.setRowCount(0);

		ArrayList<Produto> listProduto = bizProduto.BuscarProduto(where);

		for (Model.Produto Produto : listProduto) {
			model.addRow(new Object[] { Produto.getIdProduto(), Produto.getNome(), Produto.getProdTipo(),
					LtpLib.formatarValor(Produto.getPreco(), "R$ "), Produto.getEstoque() });
		}

		return model;
	}

	public void preencheCampo() {
		TelaVenda telaVenda = (TelaVenda) tela;

		String id = table.getValueAt(table.getSelectedRow(), 0).toString();
		String nomeProd = table.getValueAt(table.getSelectedRow(), 1).toString();
		String tipoProd = table.getValueAt(table.getSelectedRow(), 2).toString();
		String valor = LtpLib.RetirarReal(table.getValueAt(table.getSelectedRow(), 3).toString());
		String estoque = table.getValueAt(table.getSelectedRow(), 4).toString();

		telaVenda.preencherProduto(id, nomeProd, tipoProd, valor, estoque);

		this.setVisible(false);
	}
}
