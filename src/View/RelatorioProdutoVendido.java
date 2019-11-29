package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.BizCliente;
import Controller.BizProduto;
import Controller.BizVenda;
import Model.ProdutoVendido;
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

public class RelatorioProdutoVendido extends JFrame {

	private JPanel contentPane;
	private BizProduto bizProdutoVendido = new BizProduto();
	private DefaultTableModel model = new DefaultTableModel();
	private JScrollPane scrollPane;
	private JTable table;
	private JFrame tela;
	private String idVenda;

	public void mostrar(JFrame tela, String _idVenda) {
		this.tela = tela;
		setVisible(true);
		idVenda = _idVenda;
		carregarTable();
	}
	
	public RelatorioProdutoVendido() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
			
				
			}
		});
		ImageIcon icone = new ImageIcon(Icons.class.getResource("/img/logo.png"));
		Image img = icone.getImage();
		setIconImage(img);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 1070, 561);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
				scrollPane = new JScrollPane();
				scrollPane.setBounds(34, 12, 1000, 430);
				contentPane.add(scrollPane);
				
						table = new JTable();
						scrollPane.setViewportView(table);

		setVisible(true);
	}
	public void carregarTable() {
		try {

			model = new DefaultTableModel() {
				@Override
				public boolean isCellEditable(final int row, final int column) {
					return false;
				}

			};

			model.addColumn("Código");
			model.addColumn("Produto");
			model.addColumn("Quantidade");

			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			model.setRowCount(0);

			ArrayList<ProdutoVendido> listVenda = bizProdutoVendido.BuscarProdutoVendido(" where prodVenVenda = " + idVenda);

			for (ProdutoVendido objProdVenda : listVenda) {
				model.addRow(new Object[] { objProdVenda.getProdVenCodigo(), objProdVenda.getProdVenProduto(),
						objProdVenda.getProdVenQuantidade() });
			}
			table.setModel(model);

		} catch (IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
}
