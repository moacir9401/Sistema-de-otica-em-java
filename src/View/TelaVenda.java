package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

import Controller.BizProduto;
import Controller.BizVenda;
import Model.Produto;
import Model.ProdutoVendido;
import Model.Venda;
import utilitarios.Icons;
import utilitarios.LtpLib;

import java.awt.event.ActionListener;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TelaVenda extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtBuscaProd;
	private JTextField txtValor;
	private JTextField txtEstoque;
	private JTextField txtQuantidade;
	private JTextField txtCliente;
	private JTextField txtData;
	private JTextField txtVendedor;
	private JTextField txtNomeProd;
	private JTextField txtTipoProduto;
	private JTable table;
	private JTextField txtTotal;
	private static TelaVenda frame;
	private JFrame tela;
	private BizProduto bizProduto = new BizProduto();
	private BizVenda bizVenda = new BizVenda();
	private Venda objVenda = new Venda();
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	public String vendedor ;
	private DefaultTableModel model = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(final int row, final int column) {
			return false;
		}

	};

	public void mostrar(JFrame _tela) {
		frame = this;
		this.tela = _tela;
		setVisible(true);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaVenda(String _vendedor) {
		vendedor = _vendedor;
		setTitle("Vendas");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
			}
		});ImageIcon icone = new ImageIcon(Icons.class.getResource("/img/logo.png"));
		Image img = icone.getImage();
		setIconImage(img);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 875, 625);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 24, 264, 239);
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		contentPane.add(panel);
		panel.setLayout(null);

		txtCodigo = new JTextField();
		txtCodigo.setEnabled(false);
		txtCodigo.setBounds(10, 25, 86, 20);
		panel.add(txtCodigo);
		txtCodigo.setColumns(10);

		JLabel lblNewLabel = new JLabel("C\u00F3digo");
		lblNewLabel.setBounds(10, 11, 46, 14);
		panel.add(lblNewLabel);

		txtBuscaProd = new JTextField();
		txtBuscaProd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				try {
					// TODO Faz a busca do produto pelo id digitado
					if (e.VK_ENTER == e.getKeyCode()) {
						if (txtBuscaProd.getText().equals("")) {
							throw new IllegalArgumentException("O campo busca por código Ã© obrigatorio");
						}
						ArrayList<Produto> listProd = bizProduto.Consultar(txtBuscaProd.getText());

						if (listProd.size() == 0) {
							limparCampo();
							throw new IllegalArgumentException("Produto não encontrado");
						}
						for (Produto produto : listProd) {
							preencherProduto(Integer.toString(produto.getIdProduto()), produto.getNome(),
									produto.getProdTipo(), Float.toString(produto.getPreco()),
									Integer.toString(produto.getEstoque()));

						}
					}
				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}

			}
		});
		txtBuscaProd.setColumns(10);
		txtBuscaProd.setBounds(127, 25, 102, 20);
		panel.add(txtBuscaProd);

		JLabel lblBuscarPorCdigo = new JLabel("Buscar por código");
		lblBuscarPorCdigo.setBounds(127, 11, 136, 14);
		panel.add(lblBuscarPorCdigo);

		txtValor = new JTextField();
		txtValor.setEnabled(false);
		txtValor.setColumns(10);
		txtValor.setBounds(10, 77, 86, 25);
		panel.add(txtValor);

		JLabel lblValor = new JLabel("Valor");
		lblValor.setBounds(10, 63, 46, 14);
		panel.add(lblValor);

		txtEstoque = new JTextField();
		txtEstoque.setEnabled(false);
		txtEstoque.setColumns(10);
		txtEstoque.setBounds(10, 126, 86, 25);
		panel.add(txtEstoque);

		JLabel lblEstoque = new JLabel("Estoque");
		lblEstoque.setBounds(10, 112, 86, 14);
		panel.add(lblEstoque);

		txtQuantidade = new JTextField();
		txtQuantidade.setColumns(10);
		txtQuantidade.setBounds(127, 126, 102, 25);
		panel.add(txtQuantidade);

		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setBounds(127, 112, 94, 14);
		panel.add(lblQuantidade);

		JButton btnBuscarProduto = new JButton("Buscar produto");
		btnBuscarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaBuscarProduto buscarProduto = new TelaBuscarProduto();
				buscarProduto.mostrar(frame);
			}
		});
		btnBuscarProduto.setBounds(7, 171, 105, 38);
		panel.add(btnBuscarProduto);

		JButton btnAdicionarNoCarrinho = new JButton("Adicionar");
		btnAdicionarNoCarrinho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO adiciona o produto selecionado no carrinho de compra
				try {
					if (txtQuantidade.getText().length() == 0) {
						throw new IllegalArgumentException("Campo quantidade obrigatorio");
					}
					if (Integer.parseInt(txtEstoque.getText()) < Integer.parseInt(txtQuantidade.getText())) {
						throw new IllegalArgumentException("Nï¿½o possui produto suficiente no estoque");
					}

					if (VerificarCarrinho(txtCodigo.getText())) {
						throw new IllegalArgumentException(
								"Adicionar apenas uma vez, caso queira maior quantidade, remova o produto e adicione novamente");
					}

					Float subTotal = Integer.parseInt(txtQuantidade.getText())
							* Float.parseFloat(LtpLib.RetirarReal(txtValor.getText()));

					model.addRow(new Object[] { txtCodigo.getText(), txtNomeProd.getText(), txtQuantidade.getText(),
							txtValor.getText(), LtpLib.formatarValor(subTotal, "R$ #,##0.00") });
					table.setModel(model);

					txtEstoque.setText(Integer.toString(
							Integer.parseInt(txtEstoque.getText()) - Integer.parseInt(txtQuantidade.getText())));

					CalcularTotal();
					limparCampo();

				} catch (IllegalArgumentException ex) {
					if (ex.getMessage().length() > 0) {
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
				}

			}
		});
		btnAdicionarNoCarrinho.setBounds(124, 171, 105, 38);
		panel.add(btnAdicionarNoCarrinho);

		JLabel lblPrduto = new JLabel("Produto");
		lblPrduto.setBounds(20, 11, 134, 14);
		contentPane.add(lblPrduto);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 288, 264, 239);
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(Color.LIGHT_GRAY));
		contentPane.add(panel_1);

		txtCliente = new JTextField();
		txtCliente.setEnabled(false);
		txtCliente.setColumns(10);
		txtCliente.setBounds(10, 25, 192, 26);
		panel_1.add(txtCliente);

		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(10, 11, 46, 14);
		panel_1.add(lblCliente);

		txtData = new JTextField();
		txtData.setEnabled(false);
		txtData.setColumns(10);
		txtData.setBounds(10, 77, 192, 27);
		Calendar cal = new GregorianCalendar();
		txtData.setText(LtpLib.formatarData(new Date(), "dd/MM/yyyy"));
		panel_1.add(txtData);

		JLabel lblData = new JLabel("Data");
		lblData.setBounds(10, 63, 46, 14);
		panel_1.add(lblData);

		txtVendedor = new JTextField();
		txtVendedor.setEnabled(false);
		txtVendedor.setColumns(10);
		txtVendedor.setBounds(10, 126, 192, 27);
		panel_1.add(txtVendedor);

		txtVendedor.setText(vendedor);
		JLabel lblVendedor = new JLabel("Vendedor");
		lblVendedor.setBounds(10, 112, 46, 14);
		panel_1.add(lblVendedor);

		JButton btnBuscarCliente = new JButton("...");
		btnBuscarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setEnabled(false);
				TelaBuscarCliente buscarCliente = new TelaBuscarCliente();
				frame.setAlwaysOnTop(false);
				buscarCliente.mostrar(frame);
			}
		});
		btnBuscarCliente.setBounds(212, 25, 36, 20);
		panel_1.add(btnBuscarCliente);

		JLabel lblFormaDePagamento = new JLabel("Forma de pagamento");
		lblFormaDePagamento.setBounds(10, 167, 192, 14);
		panel_1.add(lblFormaDePagamento);

		JComboBox cmbFormaPag = new JComboBox();
		cmbFormaPag.setModel(new DefaultComboBoxModel(
				new String[] { "Dinheiro", "Cartão de credito", "Cartão de debito" }));
		cmbFormaPag.setBounds(10, 192, 192, 20);
		panel_1.add(cmbFormaPag);

		JLabel lblDadosVenda = new JLabel("Dados venda");
		lblDadosVenda.setBounds(20, 274, 134, 14);
		contentPane.add(lblDadosVenda);

		txtNomeProd = new JTextField();
		txtNomeProd.setBounds(295, 41, 339, 29);
		txtNomeProd.setEnabled(false);
		txtNomeProd.setColumns(10);
		contentPane.add(txtNomeProd);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(295, 27, 339, 14);
		contentPane.add(lblNome);

		txtTipoProduto = new JTextField();
		txtTipoProduto.setBounds(676, 41, 124, 29);
		txtTipoProduto.setEnabled(false);
		txtTipoProduto.setColumns(10);
		contentPane.add(txtTipoProduto);

		JLabel lblTipoProduto = new JLabel("Tipo produto");
		lblTipoProduto.setBounds(676, 27, 124, 14);
		contentPane.add(lblTipoProduto);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(284, 72, 532, 374);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		model.addColumn("Código");
		model.addColumn("Nome");
		model.addColumn("Quantidade");
		model.addColumn("Valor");
		model.addColumn("Sub Total");
		table.setModel(model);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 5, 512, 358);
		panel_2.add(scrollPane);

		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(702, 460, 46, 14);
		contentPane.add(lblTotal);

		txtTotal = new JTextField();
		txtTotal.setEnabled(false);
		txtTotal.setColumns(10);
		txtTotal.setBounds(702, 473, 107, 39);
		contentPane.add(txtTotal);

		JButton btnRemoverProduto = new JButton("Remover produto");
		btnRemoverProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Remove um produto do carrinho de compras

				if (table.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Selecione o produto");
				} else {
					model.removeRow(table.getSelectedRow());
					table.setModel(model);
					CalcularTotal();
				}
			}
		});
		btnRemoverProduto.setBounds(529, 524, 154, 38);
		contentPane.add(btnRemoverProduto);

		JButton btnVender = new JButton("Vender");
		btnVender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Cadastra a venda
				try {
					if (txtCliente.getText().equals("")) {
						throw new IllegalArgumentException("O campo cliente é obrigatorio");
					}
					if (table.getRowCount() == 0) {
						throw new IllegalArgumentException("Não possui nenhum carrinho para venda");
					}
					
					Object[] options = { "Sim", "Não" };
					int opcao = JOptionPane.showOptionDialog(null, "O total da compra é de " + txtTotal.getText() + " tem certeza que deseja finalizar ?", "",
							JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);

					if (opcao == 1) {
						throw new IllegalArgumentException("Venda não efetuada");
					}
					
					objVenda = new Venda();
					objVenda.setVenCliente(txtCliente.getText());
					objVenda.setVenDataVenda((Date) sdf.parse(txtData.getText()));
					objVenda.setVenFormaPagamento(cmbFormaPag.getSelectedItem().toString());
					objVenda.setVenFuncionario(txtVendedor.getText());
					objVenda.setVenTotalVenda(Float.parseFloat(LtpLib.RetirarReal(txtTotal.getText())));

					bizVenda.Cadastrar(objVenda);

					for (int i = 0; i < table.getRowCount(); i++) {
						
						ProdutoVendido objProdVendido = new ProdutoVendido();
						objProdVendido.setProdVenProduto(table.getValueAt(i, 1).toString());
						objProdVendido.setProdVenQuantidade(Integer.parseInt(table.getValueAt(i, 2).toString()));
						objProdVendido.setProdVenVenda(objVenda.getVenCodigo());
						
						bizVenda.Cadastrar(objProdVendido);
					}


					model.setRowCount(0);
					table.setModel(model);

					JOptionPane.showMessageDialog(null, "Venda efetuada com sucesso!");

				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				} catch (Exception ex) {

				}
			}
		});
		btnVender.setBounds(695, 524, 105, 38);
		contentPane.add(btnVender);

	}

	public void removerEstoque() {
		Produto objProduto = new Produto();

	}

	public void preencherCliente(String cliente) {
		// TODO preenche o campo cliente
		txtCliente.setText(cliente);
		this.setEnabled(true);
	}

	public void CalcularTotal() {

		int totalCarrinho = table.getRowCount();
		float valorTotal = 0;
		for (int i = 0; i < totalCarrinho; i++) {

			valorTotal += Float.parseFloat(LtpLib.RetirarReal(table.getValueAt(i, 4).toString()));
		}

		txtTotal.setText(LtpLib.formatarValor(valorTotal, "R$ #,##0.00"));
	}

	public boolean VerificarCarrinho(String id) {

		int totalCarrinho = table.getRowCount();

		for (int i = 0; i < totalCarrinho; i++) {

			if (id.equals(table.getValueAt(i, 0).toString())) {
				return true;
			}
		}

		return false;
	}

	public void preencherProduto(String id, String nomeProd, String tipoProd, String valor, String estoque) {
// TODO pega os dados do pesquisa e coloca os campos de preenchimento de produto
		txtCodigo.setText(id);
		txtNomeProd.setText(nomeProd);
		txtTipoProduto.setText(tipoProd);
		txtValor.setText(LtpLib.formatarValor(Double.parseDouble(valor), "R$ #,##0.00"));

		int totalCarrinho = table.getRowCount();
		txtEstoque.setText(estoque);

		for (int i = 0; i < totalCarrinho; i++) {

			if (table.getValueAt(i, 0).toString().equals(id)) {

				txtEstoque.setText(Integer
						.toString(Integer.parseInt(estoque) - Integer.parseInt(table.getValueAt(i, 2).toString())));
				break;
			}
		}

		this.setEnabled(true);
	}

	public void limparCampo() {
		// TODO Limpa os campos de preenchimento do produto
		txtQuantidade.setText("");
		txtCodigo.setText("");
		txtNomeProd.setText("");
		txtTipoProduto.setText("");
		txtValor.setText("");
		txtEstoque.setText("");
		txtBuscaProd.setText("");
	}
}
