package Controller;

import java.util.ArrayList;

import DAO.Conexao;
import Model.ProdutoVendido;

public class BizProdutoVenda implements Icrud {
	private static ArrayList<ProdutoVendido> list = new ArrayList<ProdutoVendido>();

	public ArrayList<ProdutoVendido> getList() {
		return list;
	}
@Override
	public boolean Cadastrar(Object produtoVendido) {

		try {
			Conexao.Save(produtoVendido);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean Deletar(String id) {
		Conexao.Remove(new ProdutoVendido(), Integer.parseInt(id));
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<ProdutoVendido> Consultar(String id) {

		return (ArrayList<ProdutoVendido>) Conexao.Show("select p from ProdutoVendido p where p.idProdutoVendido = '" + id + "'");

	}

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<ProdutoVendido> Consultar() {

		return (ArrayList<ProdutoVendido>) Conexao.Show("select p from ProdutoVendido p");

	}

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<ProdutoVendido> Consultar(String campo, String valor) {

		return (ArrayList<ProdutoVendido>) Conexao.Show("select p from ProdutoVendido p where p." + campo + " = '" + valor + "'");

	}
}
