package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.BizVenda;
import Model.Venda;
import utilitarios.Icons;
import utilitarios.LtpLib;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RelatorioVendas extends JFrame {

	private JPanel contentPane;
	private JTextField txtDataInicio;
	private JTextField txtDataTermino;
	private JLabel lblDataTermino;
	private BizVenda bizVenda = new BizVenda();
	private DefaultTableModel model = new DefaultTableModel();
	private JScrollPane scrollPane;
	private JTable table;
	private JFrame tela;
	private static RelatorioVendas frame;

	public void mostrar(JFrame tela) {
		this.tela = tela;
		setVisible(true);
	}

	public RelatorioVendas() {
		ImageIcon icone = new ImageIcon(Icons.class.getResource("/img/logo.png"));
		Image img = icone.getImage();
		setIconImage(img);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				((telaPrinc) tela).Desbloquaer();
			}
		});
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 1070, 580);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		txtDataInicio = new JTextField();
		txtDataInicio.setBounds(34, 47, 127, 28);
		contentPane.add(txtDataInicio);
		txtDataInicio.setColumns(10);

		JLabel lblDataInicio = new JLabel("Data Inicio");
		lblDataInicio.setBounds(34, 32, 127, 14);
		contentPane.add(lblDataInicio);

		txtDataTermino = new JTextField();
		txtDataTermino.setBounds(196, 47, 127, 28);
		txtDataTermino.setColumns(10);
		contentPane.add(txtDataTermino);

		lblDataTermino = new JLabel("Data Termino");
		lblDataTermino.setBounds(196, 32, 127, 14);
		contentPane.add(lblDataTermino);

		JButton btnNewButton = new JButton("Pesquisar", Icons.PesquisarRelatorio(20, 20));
		btnNewButton.setBounds(345, 49, 151, 24);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {

					if (txtDataInicio.getText().equals("")) {
						throw new IllegalArgumentException("Data inicio obrigatório");
					}
					if (txtDataTermino.getText().equals("")) {
						throw new IllegalArgumentException("Data término obrigatório");
					}

					if (!LtpLib.validarData(txtDataInicio.getText())) {
						throw new IllegalArgumentException("Data inicio Invalida");
					}
					if (!LtpLib.validarData(txtDataTermino.getText())) {
						throw new IllegalArgumentException("Data tÃ©rmino Invalida");
					}

					model = new DefaultTableModel() {
						@Override
						public boolean isCellEditable(final int row, final int column) {
							return false;
						}

					};

					model.addColumn("Código");
					model.addColumn("Cliente");
					model.addColumn("Data");
					model.addColumn("Forma de pagamento");
					model.addColumn("Valor da venda");

					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					model.setRowCount(0);
					ArrayList<Venda> listVenda = bizVenda.Consultar(" where p.venDataVenda >= '"
							+ txtDataInicio.getText() + "' and p.venDataVenda <= '" + txtDataTermino.getText() + "'");

					for (Venda objVenda : listVenda) {
						model.addRow(new Object[] { objVenda.getVenCodigo(), objVenda.getVenCliente(),
								LtpLib.formatarData(objVenda.getVenDataVenda(), "dd/MM/yyyy"),
								objVenda.getVenFormaPagamento(),
								LtpLib.formatarValor(objVenda.getVenTotalVenda(), "R$ #,##0.00") });
					}
					table.setModel(model);

				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		contentPane.add(btnNewButton);

		JPanel panel = new JPanel();
		panel.setBounds(34, 103, 1024, 454);
		contentPane.add(panel);

		panel.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 1000, 430);
		panel.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					RelatorioProdutoVendido relProVen = new RelatorioProdutoVendido();
					relProVen.mostrar(frame, table.getValueAt(table.getSelectedRow(), 0).toString());
				}
			}
		});
		scrollPane.setViewportView(table);

		setVisible(true);
	}
}
