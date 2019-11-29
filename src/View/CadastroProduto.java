package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.CloseAction;

import org.hibernate.HibernateError;
import org.hibernate.HibernateException;

import Controller.BizArmacao;
import Controller.BizProduto;
import Controller.BizUF;
import DAO.Conexao;
import Model.Armacao;
import Model.Produto;
import Model.UF;
import utilitarios.Icons;
import utilitarios.LtpLib;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Window.Type;
import java.util.ArrayList;
import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class CadastroProduto extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtNome;
	private JTextField txtPrecoCusto;
	private BizProduto bizProduto = new BizProduto();
	private JComboBox<String> cmbTipoProd;
	private BizArmacao bizArmacao = new BizArmacao();
	private JPanel panel_1 = new JPanel();
	private JButton btnSalvar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					CadastroProduto frame = new CadastroProduto();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public void preencherCampo(ArrayList<Produto> listProd) {

		for (Produto prod : listProd) {
			txtCodigo.setText(Integer.toString(prod.getIdProduto()));
			txtNome.setText(prod.getNome());
			txtPrecoCusto.setText(Float.toString(prod.getPreco()));
			txtEstoque.setText(Integer.toString(prod.getEstoque()));
			cmbTipoProd.setSelectedItem(prod.getProdTipo());

			if (prod instanceof Armacao) {

				Armacao prodArm = (Armacao) prod;
				txtAro.setText(prodArm.getAro());
				txtCor.setText(prodArm.getFormato());
				txtMaterial.setText(prodArm.getMaterial());
				txtFormato.setText(prodArm.getFormato());
				txtFormato.setText(prodArm.getFormato());
			}

		}

	}

	public void bloqueioCampo() {
		txtCodigo.setEnabled(false);
		txtNome.setEnabled(false);
		txtPrecoCusto.setEnabled(false);
		txtAro.setEnabled(false);
		txtCor.setEnabled(false);
		txtMaterial.setEnabled(false);
		txtEstoque.setEditable(false);
		txtFormato.setEditable(false);
		btnSalvar.setVisible(false);
		cmbTipoProd.setEditable(false);

	}

	private JFrame tela;
	private JTextField txtAro;
	private JTextField txtCor;
	private JTextField txtFormato;
	private JTextField txtMaterial;
	private JTextField txtEstoque;
	private String comando;

	public void mostrar(JFrame tela, String _comando) {
		this.tela = tela;
		setVisible(true);
		comando = _comando;
	}

	public CadastroProduto() {
		setTitle("Cadastro produto");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				if (tela instanceof TelaProdutos) {
					((TelaProdutos) tela).atualizar();
				} else {
					((telaPrinc) tela).Desbloquaer();
				}
			}
		});
		ImageIcon icone = new ImageIcon(Icons.class.getResource("/img/logo.png"));
		Image img = icone.getImage();
		setIconImage(img);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 470, 403);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(10, 22, 86, 20);
		panel.add(txtCodigo);
		txtCodigo.setColumns(10);
		txtCodigo.setEnabled(false);

		JLabel lblNewLabel = new JLabel("Código");
		lblNewLabel.setBounds(10, 11, 86, 14);
		panel.add(lblNewLabel);

		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(117, 22, 297, 20);
		panel.add(txtNome);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(117, 11, 282, 14);
		panel.add(lblNome);

		txtPrecoCusto = new JTextField();
		txtPrecoCusto.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				String x = LtpLib.RetiraSimbolo(txtPrecoCusto.getText());
				txtPrecoCusto.setText(LtpLib.RetiraSimbolo(txtPrecoCusto.getText()));
			}

			@Override
			public void focusLost(FocusEvent e) {

				Float valor = txtPrecoCusto.getText().equals("") ? 0 : Float.parseFloat(txtPrecoCusto.getText());

				txtPrecoCusto.setText(LtpLib.formatarValor(valor, "#,###.00"));
			}
		});
		txtPrecoCusto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				String caracteres = "0987654321";
				if (!caracteres.contains(evt.getKeyChar() + "")) {
					evt.consume();
				}
			}
		});

		txtPrecoCusto.setColumns(10);
		txtPrecoCusto.setBounds(10, 64, 86, 20);
		panel.add(txtPrecoCusto);

		JLabel lblRg = new JLabel("Preço");
		lblRg.setBounds(10, 53, 86, 14);
		panel.add(lblRg);

		JLabel lblTelefone = new JLabel("TipoProduto");
		lblTelefone.setBounds(118, 53, 86, 14);
		panel.add(lblTelefone);

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvar();

			}
		});
		btnSalvar.setBounds(10, 324, 89, 23);
		panel.add(btnSalvar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				if (tela != null) {
					((TelaProdutos) tela).atualizar();
				}
			}
		});
		btnCancelar.setBounds(325, 324, 89, 23);
		panel.add(btnCancelar);

		cmbTipoProd = new JComboBox();
		cmbTipoProd.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				panel_1.setVisible(
						cmbTipoProd.getSelectedItem() != null && cmbTipoProd.getSelectedItem().equals("Armação"));

			}
		});
		cmbTipoProd.setModel(
				new DefaultComboBoxModel(new String[] { "Selecione", "Armação", "Lente", "Outros" }));
		cmbTipoProd.setBounds(115, 64, 89, 20);
		panel.add(cmbTipoProd);

		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 95, 404, 218);
		panel.add(panel_1);
		panel_1.setLayout(null);
		panel_1.setVisible(false);

		txtAro = new JTextField();
		txtAro.setColumns(10);
		txtAro.setBounds(10, 22, 86, 20);
		panel_1.add(txtAro);

		JLabel lblAro = new JLabel("Aro");
		lblAro.setBounds(10, 11, 86, 14);
		panel_1.add(lblAro);

		JLabel lblCod = new JLabel("Cor");
		lblCod.setBounds(117, 11, 86, 14);
		panel_1.add(lblCod);

		txtCor = new JTextField();
		txtCor.setColumns(10);
		txtCor.setBounds(117, 22, 86, 20);
		panel_1.add(txtCor);

		txtFormato = new JTextField();
		txtFormato.setColumns(10);
		txtFormato.setBounds(236, 22, 140, 20);
		panel_1.add(txtFormato);

		JLabel lblFormato = new JLabel("Formato");
		lblFormato.setBounds(236, 11, 140, 14);
		panel_1.add(lblFormato);

		txtMaterial = new JTextField();
		txtMaterial.setColumns(10);
		txtMaterial.setBounds(10, 74, 140, 20);
		panel_1.add(txtMaterial);

		JLabel lblMaterial = new JLabel("Material");
		lblMaterial.setBounds(10, 63, 140, 14);
		panel_1.add(lblMaterial);

		JLabel lblEstoque = new JLabel("Estoque");
		lblEstoque.setBounds(221, 53, 86, 14);
		panel.add(lblEstoque);

		txtEstoque = new JTextField();
		txtEstoque.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				String caracteres = "0987654321";
				if (!caracteres.contains(evt.getKeyChar() + "")) {
					evt.consume();
				}
			}
		});
		txtEstoque.setColumns(10);
		txtEstoque.setBounds(221, 64, 86, 20);
		panel.add(txtEstoque);

		setVisible(true);
	}

	private void salvar() {
		try {
			Produto objProd = new Produto();

			if (txtNome.getText().length() <= 0) {
				throw new IllegalArgumentException("Nome é obrigatório");
			}
			if (txtPrecoCusto.getText().length() <= 0) {
				throw new IllegalArgumentException("Preço custo é obrigatório");
			}
			if (cmbTipoProd.getSelectedItem() == null || cmbTipoProd.getSelectedItem().toString() == "Selecione") {
				throw new IllegalArgumentException("TIpo do produto c obrigatório");
			}

			Armacao objProdArm = new Armacao();
			if (comando.equals("Editar")) {
				objProd = (Produto) Conexao.Show(objProd, Integer.parseInt(txtCodigo.getText()));
			}
			if (cmbTipoProd.getSelectedItem().equals("Armação") && comando.equals("Editar")) {
				objProdArm = (Armacao) Conexao.Show(objProd, Integer.parseInt(txtCodigo.getText()));
			}

			objProd.setNome(txtNome.getText());
			objProd.setProdTipo(cmbTipoProd.getSelectedItem().toString());
			objProd.setEstoque(Integer.parseInt(txtEstoque.getText()));
			objProd.setPreco(Float.parseFloat(LtpLib.RetiraSimbolo(txtPrecoCusto.getText())));

			if (cmbTipoProd.getSelectedItem().equals("Armação")) {

				objProdArm.setNome(txtNome.getText());
				objProdArm.setProdTipo(cmbTipoProd.getSelectedItem().toString());
				objProdArm.setEstoque(Integer.parseInt(txtEstoque.getText()));
				objProdArm.setPreco(Float.parseFloat(LtpLib.RetiraSimbolo(txtPrecoCusto.getText())));
				objProdArm.setAro(txtAro.getText());
				objProdArm.setCor(txtCodigo.getText());
				objProdArm.setFormato(txtFormato.getText());
				objProdArm.setMaterial(txtMaterial.getText());
				objProdArm.setFormato(txtFormato.getText());

				if (comando.equals("Editar")) {
					Conexao.Update(objProdArm);
				} else if (comando.equals("Inserir")) {
					bizArmacao.Cadastrar(objProdArm);
				}

			} else {
				if (comando.equals("Editar")) {
					Conexao.Update(objProd);
				} else if (comando.equals("Inserir")) {
					bizProduto.Cadastrar(objProd);
				}

			}

			if(tela instanceof TelaProdutos) {
				TelaProdutos frame = (TelaProdutos) tela;
				frame.atualizar();
			}
			else {
				((telaPrinc) tela).Desbloquaer();
			}
			
			this.setVisible(false);

			throw new IllegalArgumentException("Salvo com sucesso");

		} catch (IllegalArgumentException e) {
			if (e.getMessage().length() > 0) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}

	}
}
