package Controller;

import java.util.ArrayList;

import DAO.Conexao;
import Model.Produto;
import Model.Venda;

public class BizVenda implements Icrud {
	private static ArrayList<Venda> list = new ArrayList<Venda>();

	public ArrayList<Venda> getList() {
		return list;
	}

	@Override
	public boolean Cadastrar(Object venda) {

		try {
			Conexao.Save(venda);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean Deletar(String id) {
		Conexao.Remove(new Venda(), Integer.parseInt(id));
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<Venda> Consultar(String where) {

		return (ArrayList<Venda>) Conexao.Show("select p from Venda p "+ where);

	}

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<Venda> Consultar() {

		return (ArrayList<Venda>) Conexao.Show("select p from Venda p");

	}

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<Venda> Consultar(String campo, String valor) {

		return (ArrayList<Venda>) Conexao.Show("select p from Venda p where p." + campo + " = '" + valor + "'");

	}
	public Produto ConsultarProduto(int id) {

		return (Produto) Conexao.Show(new Produto(),id);

	}
}
