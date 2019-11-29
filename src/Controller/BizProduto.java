package Controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.Conexao;
import Model.Produto;
import Model.ProdutoVendido;
import View.CadastroProduto;

public class BizProduto implements Icrud {
	private static ArrayList<Produto> list = new ArrayList<Produto>();

	public ArrayList<Produto> getList() {
		return list;
	}

	@Override
	public boolean Cadastrar(Object produto) {

		try {
			Conexao.Save(produto);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean Deletar(String id) {
		Conexao.Remove(new Produto(), Integer.parseInt(id));
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<Produto> Consultar(String id) {

		return (ArrayList<Produto>) Conexao.Show("select p from Produto p where p.idProduto = '" + id + "'");

	}

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<Produto> Consultar() {

		return (ArrayList<Produto>) Conexao.Show("select p from Produto p");

	}

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<Produto> Consultar(String campo, String valor) {

		return (ArrayList<Produto>) Conexao.Show("select p from Produto p where " + campo + " = '" + valor + "'");

	}

	public ArrayList<Produto> BuscarProduto(String where) {

		return (ArrayList<Produto>) Conexao.Show("select p from Produto " + where);

	}
	public ArrayList<ProdutoVendido> BuscarProdutoVendido(String where) {

		return (ArrayList<ProdutoVendido>) Conexao.Show("select p from ProdutoVendido p " + where);

	}
	public DefaultTableModel CarregaTable(DefaultTableModel model, String campo, String nome) {

		model.setRowCount(0);

		list = Consultar(campo, nome);

		for (Produto prod : list) {
			model.addRow(new Object[] { prod.getIdProduto(), prod.getNome(), prod.getPreco() });
		}

		return model;
	}

}
