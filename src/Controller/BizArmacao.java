package Controller;

import java.util.ArrayList;

import DAO.Conexao;
import Model.Armacao;

public class BizArmacao implements Icrud {
	private static ArrayList<Armacao> list = new ArrayList<Armacao>();

	public ArrayList<Armacao> getList() {
		return list;
	}
@Override
	public boolean Cadastrar(Object armacao) {

		try {
			Conexao.Save(armacao);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean Deletar(String id) {
		Conexao.Remove(new Armacao(), Integer.parseInt(id));
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<Armacao> Consultar(String id) {

		return (ArrayList<Armacao>) Conexao.Show("select p from Armacao p where p.idArmacao = '" + id + "'");

	}

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<Armacao> Consultar() {

		return (ArrayList<Armacao>) Conexao.Show("select p from Armacao p");

	}

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<Armacao> Consultar(String campo, String valor) {

		return (ArrayList<Armacao>) Conexao.Show("select p from Armacao p where p." + campo + " = '" + valor + "'");

	}
}
