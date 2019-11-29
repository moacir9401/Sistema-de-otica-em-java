package Controller;

/**
 * Moacir Afonso Alves
 */

import java.util.ArrayList;

import DAO.Conexao;
import Model.Estoque;

public class BizEstoque implements Icrud {

	private static ArrayList<Estoque> list = new ArrayList<Estoque>();

	public ArrayList<Estoque> getList() {
		return list;
	}

	@Override
	public boolean Cadastrar(Object estoque) {

		try {
			Conexao.Save(estoque);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean Deletar(String id) {
		Conexao.Remove(new Estoque(), Integer.parseInt(id));
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<Estoque> Consultar(String id) {

		return (ArrayList<Estoque>) Conexao.Show("select p from Estoque p where p.idEstoque = '" + id + "'");

	}

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<Estoque> Consultar() {

		return (ArrayList<Estoque>) Conexao.Show("select p from Estoque p");

	}

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<Estoque> Consultar(String campo, String valor) {

		return (ArrayList<Estoque>) Conexao.Show("select p from Estoque p where p." + campo + " = '" + valor + "'");

	}
}
