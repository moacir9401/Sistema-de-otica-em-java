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

import Controller.BizCliente;
import Model.Cliente;
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

public class TelaBuscarCliente extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtCpf;
	private JTable table;
	private BizCliente bizCliente = new BizCliente();
	private JFrame tela;
	private static TelaBuscarCliente frame;
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
					frame = new TelaBuscarCliente();
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
	public TelaBuscarCliente() {
		ImageIcon icone = new ImageIcon(Icons.class.getResource("/img/logo.png"));
		Image img = icone.getImage();
		setIconImage(img);
		setTitle("Buscar Cliente");
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

		JLabel lblBuscarPorCpf = new JLabel("Buscar por cpf");
		lblBuscarPorCpf.setBounds(304, 83, 163, 14);
		contentPane.add(lblBuscarPorCpf);

		txtCpf = new JTextField();
		txtCpf.setColumns(10);
		txtCpf.setBounds(304, 97, 163, 20);
		contentPane.add(txtCpf);

		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtCpf.getText().length() > 0) {

					atualizar("p where p.cpf = '" + LtpLib.RetiraSimbolo(txtCpf.getText()) +"'  order by p.nome");
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
				if(e.getClickCount() == 2) {
					preencheCampo();
				}
			}
		});
		
		scrollPane.setBounds(0, 5, 644, 285);
		panel.add(scrollPane);

		atualizar("p");
		
		JLabel label = new JLabel("Busca por cliente");
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
		model.addColumn("Nome");
		model.addColumn("Telefone");
		model.addColumn("CPF");
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		table.setModel(CarregaTable(where));
	}

	public DefaultTableModel CarregaTable(String where) {

		model.setRowCount(0);

		ArrayList<Cliente> listCliente = bizCliente.buscarCliente(where);

		for (Model.Cliente cliente : listCliente) {
			model.addRow(new Object[] { cliente.getIdPessoa(), cliente.getNome(), cliente.getTelefone(),
					LtpLib.formatarCPF(cliente.getCpf()) });
		}

		return model;
	}
	
	public void preencheCampo() {
		TelaVenda telaVenda =  (TelaVenda)tela;
		telaVenda.preencherCliente(table.getValueAt(table.getSelectedRow(), 1).toString());
		this.setVisible(false);
	}
}
